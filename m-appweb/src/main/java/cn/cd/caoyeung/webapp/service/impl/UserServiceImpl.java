package cn.cd.caoyeung.webapp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cd.caoyeung.webapp.mapper.UserMapper;
import cn.cd.caoyeung.webapp.service.UserServiceI;
@Service("userService")
public class UserServiceImpl implements UserServiceI {
	@Resource
    private UserMapper userMapper;
//	@Override
//	public void addUser(User user) {
//
//	}

	@Override
	public List<Map> getUserById(String userId) {
		List<Map> allUser = userMapper.getAllUser();
		return allUser;
	}

}
