package cn.cd.caoyeung.service.pattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * 深拷贝必须每个类实现 Serializable接口
 * @author: CaoYeung
 * @time: 下午9:24:02
 */
public class DeepCloneGenericObject extends GenericObject{
	private static final long serialVersionUID = 1L;
	public List<User> users = new ArrayList<User>();
	public void findUser(){
		System.out.println("DeepCloneGenericObject:"+this);
		System.out.println(users);
		for(User user : users){
			System.out.println("users:"+user);
		}
	}
	public void addUser(User us){
		this.users.add(us);
	}
	public DeepCloneGenericObject deepClone(){
		DeepCloneGenericObject obj = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ObjectOutputStream objos = new ObjectOutputStream(os);
			objos.writeObject(this);
			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
			ObjectInputStream objis = new ObjectInputStream(is);
			obj = (DeepCloneGenericObject) objis.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	public static void main(String[] args) {
		DeepCloneGenericObject obj = new DeepCloneGenericObject();
		User us = new User("liwei", "122");
		obj.addUser(us);
		obj.findUser();
		DeepCloneGenericObject deepClone = obj.deepClone();
		deepClone.findUser();
	}

}
