package cn.cd.caoyeung.webapp.service;

import java.util.List;
import java.util.Map;

public interface UserServiceI {
//    void addUser(User user);
//    User getUserById(String userId);
	List<Map> getUserById(String userId);
}