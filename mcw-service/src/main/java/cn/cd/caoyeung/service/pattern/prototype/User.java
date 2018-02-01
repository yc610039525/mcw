package cn.cd.caoyeung.service.pattern.prototype;


public class User extends GenericObject{
	private static final long serialVersionUID = 1L;
	public String name;
	public String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	
}
