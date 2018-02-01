package cn.cd.caoyeung.webapp.utils.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 *触发事件监听
 */
public class EmainNotifier implements ApplicationListener
{
  Logger logger=Logger.getLogger(EmainNotifier.class);
  @Override
  public void onApplicationEvent(ApplicationEvent evt) {
    if (evt instanceof EmailEvent) {
      EmailEvent emailEvent = (EmailEvent) evt;
      logger.info("邮件地址: " + emailEvent.getAddress());
      logger.info("邮件正文: " + emailEvent.getText());
    } else {
    	logger.info(evt);
    }
  }
}
