package cn.cd.caoyeung.webapp.model;

public class LoginParam {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 登录结果标记
	 */
	private Boolean flag;
	/**
	 * 登录结果描述
	 */
	private String msg;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
