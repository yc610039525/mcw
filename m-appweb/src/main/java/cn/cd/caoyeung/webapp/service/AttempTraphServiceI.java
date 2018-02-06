package cn.cd.caoyeung.webapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
//@Service("AttempTraphServiceI")
public interface AttempTraphServiceI {
	List<Map> getAttempTraphById(String userId);
}