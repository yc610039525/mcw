package cn.cd.caoyeung.service.pattern.bridge;

public interface MessageImplementor {
	void send(String msg,String toUser);
}
