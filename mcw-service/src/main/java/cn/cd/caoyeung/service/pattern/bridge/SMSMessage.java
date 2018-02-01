package cn.cd.caoyeung.service.pattern.bridge;

public class SMSMessage implements MessageImplementor {

	@Override
	public void send(String msg, String toUser) {
		System.out.println("使用短消息发送消息："+msg+",给"+toUser);
	}

}
