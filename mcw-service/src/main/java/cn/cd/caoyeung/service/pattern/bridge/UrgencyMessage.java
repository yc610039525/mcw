package cn.cd.caoyeung.service.pattern.bridge;
/**
 * 加急消息 扩展抽象消息
 * @author: CaoYeung
 * @time: 下午8:08:26
 */
public class UrgencyMessage extends AbstractMessage {

	public UrgencyMessage(MessageImplementor msg) {
		super(msg);
	}

	public void sendMessage(String msg, String toUser) {
		msg ="加急："+msg;
		super.sendMessage(msg, toUser);
	}
	/**
	 * 监控消息发送
	 * @param messageId
	 * @return
	 */
	public Object watch(String messageId){
		return null;
	}
}
