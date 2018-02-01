package cn.cd.caoyeung.service.common.designpattern.bridge;

public interface MessageImplementor {
	void send(String msg,String toUser);
}
