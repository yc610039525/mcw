package cn.cd.caoyeung.webapp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cd.caoyeung.webapp.mapper.AttempTraphMapper;
import cn.cd.caoyeung.webapp.service.AttempTraphServiceI;
@Service("attempTraphServiceI")
public class AttempTraphServiceImpl implements AttempTraphServiceI {
	@Resource
    private AttempTraphMapper attempTraphMapper;
	@Override
	public List<Map> getAttempTraphById(String userId) {
		List<Map> traphs = attempTraphMapper.getAttempTraphById();
		return traphs;
	}

}
