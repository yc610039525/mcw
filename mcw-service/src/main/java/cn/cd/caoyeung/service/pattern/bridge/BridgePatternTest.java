package cn.cd.caoyeung.service.pattern.bridge;

public class BridgePatternTest {
	public static void main(String[] args) {
		MessageImplementor emailMsg = new EmailMessage();
		AbstractMessage msg = new UrgencyMessage(emailMsg);
		msg.sendMessage("国庆滚回家去", "李伟");
		MessageImplementor smsmsg = new SMSMessage();
		AbstractMessage msg2 = new UrgencyMessage(smsmsg);
		msg2.sendMessage("国庆滚回家去", "李明");
	}

}
