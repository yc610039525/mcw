package cn.cd.caoyeung.service.pattern.prototype;

public class CloneGenericObject implements Cloneable {
	private String name = "lw";
	private String sex = "f";
	private String tel = "";
	public String getName() {
		return name;
	}
	
	public CloneGenericObject() {
		super();
		System.out.println("执行构造函数.....");
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	@Override
	protected CloneGenericObject clone(){
		CloneGenericObject  object= null;
		try {
			object = (CloneGenericObject)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return object;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public static void main(String[] args){
		CloneGenericObject object = new CloneGenericObject();
		System.out.println(object);
		CloneGenericObject object2 = object.clone();
		System.out.println(object2);
	}
	
}
