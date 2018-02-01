package cn.cd.caoyeung.webapp.model;

import java.util.Map;

public class User {
	public String name;
	public int age;
	public String gender;
	public String address;
	public Map<String,Object> attr;
	
	public Map<String, Object> getAttr() {
		return attr;
	}
	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + "]";
	}
	
	

}
