package cn.cd.caoyeung.service.pattern.bridge;
/**
 * 抽象消息
 * 要点：使用抽象类作为桥接中间类  引用第一阶段实现类
 * @author: CaoYeung
 * @time: 下午8:07:46
 */
public abstract class AbstractMessage {
	/**
	 * 持有实现部分对象
	 */
	MessageImplementor impl;
	
	public AbstractMessage(MessageImplementor msg) {
		super();
		this.impl = msg;
	}
	/**
	 * 发送消息 转调实现发送方法
	 * @param msg
	 * @param toUser
	 */
	public void sendMessage(String msg, String toUser) {
		impl.send(msg, toUser);
	}

}
