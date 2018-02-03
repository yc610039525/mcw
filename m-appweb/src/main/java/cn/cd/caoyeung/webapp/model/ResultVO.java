package cn.cd.caoyeung.webapp.model;

import java.io.Serializable;

public class ResultVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String msg;
	private Object data;
	
	public ResultVO() {
		
	}

	public ResultVO(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public ResultVO(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}