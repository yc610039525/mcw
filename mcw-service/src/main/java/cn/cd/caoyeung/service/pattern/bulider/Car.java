package cn.cd.caoyeung.service.pattern.bulider;

public class Car {
	private String  header;
	private String  body;
	private String  tail;
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTail() {
		return tail;
	}
	public void setTail(String tail) {
		this.tail = tail;
	}
	@Override
	public String toString() {
		return "Car [header=" + header + ", body=" + body + ", tail=" + tail + "]";
	}
	
}
