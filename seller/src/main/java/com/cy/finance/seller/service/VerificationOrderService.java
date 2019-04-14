package com.cy.finance.seller.service;

import com.cy.finance.entity.VerificationOrder;
import com.cy.finance.entity.enums.OrderType;
import com.cy.finance.seller.enums.ChannelEnum;
import com.cy.finance.seller.repositorybackup.VerificationOrderRepository;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: chuan yu
 * @Date: 4/13/19 4:45 PM
 */
@Service
public class VerificationOrderService {

  @Autowired
  private VerificationOrderRepository verificationOrderRepository;

  @Value("${verification.rootdir:/Users/chuanyu/IdeaProjects/verification}")
  private String fileRootDir;

  private static String END_LINE = System.getProperty("line.separator","\n");

  private static DateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private static DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public File generateVerificationOrderFile(String channelId, Date date) {
    File file = getFilePath(fileRootDir, channelId, date);
    if (file.exists()) {
      return file;
    }
    try {
      file.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Date start = getStartOfDay(date);
    Date end = add24Hours(start);
    List<String> orders = verificationOrderRepository.queryVerificationOrders(channelId,start,end);
    String content = String.join(END_LINE,orders);
    FileUtil.writeAsString(file,content);
    return file;
  }

  private Date add24Hours(Date start) {
    return new Date(start.getTime() + 1000 * 60 *60 *24);
  }

  private Date getStartOfDay(Date day) {
    String start_str = DAY_FORMAT.format(day);
    Date start = null;
    try {
      start = DAY_FORMAT.parse(start_str);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return start;
  }

  public File getFilePath(String rootDir, String channelId, Date date) {
    String name = DAY_FORMAT.format(date) + "-" + channelId + ".rtf";
    return Paths.get(rootDir,name).toFile();
  }


  public static VerificationOrder parseLineAndGenerateVerificationOrder(String line){
    VerificationOrder order = new VerificationOrder();
    String[] props =  line.split("\\|");
    order.setOrderId(props[0]);
    order.setOuterOrderId(props[1]);
    order.setChannelId(props[2]);
    order.setChannelUserId(props[3]);
    order.setProductId(props[4]);
    order.setOrderType(OrderType.valueOf(props[5]));
    order.setAmount(new BigDecimal(props[6]));
    try {
      order.setCreateAt(DATETIME_FORMAT.parse(props[7]));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return order;
  }

  public void saveChannelOrders(String channelId,Date day){
    ChannelEnum conf = ChannelEnum.getByChanId(channelId);
    File file = getFilePath(conf.getRootDir(),channelId,day);
    if(!file.exists()){
      return;
    }
    String content = null;
    try {
      content = FileUtil.readAsString(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.split(END_LINE);
    List<VerificationOrder> orders = new ArrayList<>();
    for (String line : lines) {
      orders.add(parseLineAndGenerateVerificationOrder(line));
    }
    verificationOrderRepository.save(orders);
  }

  public List<String> verifyOrder(String channelId,Date day){
    List<String> errors = new ArrayList<>();
    Date start = getStartOfDay(day);
    Date end = add24Hours(start);
    List<String> excessOrders = verificationOrderRepository.queryExcessOrders(channelId,start,end);
    List<String> missOrders = verificationOrderRepository.queryMissOrders(channelId,start,end);
    List<String> differentOrders = verificationOrderRepository.queryDifferentOrders(channelId,start,end);

    errors.add("excess orders:"+String.join(",",excessOrders));
    errors.add("missing orders:"+String.join(",",missOrders));
    errors.add("different orders:"+String.join(",",differentOrders));

    return errors;
  }
}
