package cn.cd.caoyeung.webapp.utils.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件
 */
public class EmailEvent extends ApplicationEvent
{
  private static final long serialVersionUID = -863973585019686490L;
  
  private String address;
  private String text;
  
  public EmailEvent(Object source)
  {
    super(source);
  }
  
  public EmailEvent(Object source,String address,String text)
  {
    super(source);
    this.address = address;
    this.text = text;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }
}
