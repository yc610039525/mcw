package base;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.cd.caoyeung.webapp.service.UserServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/applicationContext-bean.xml" })
public class SpringJUnit4BaseTest {
	@Autowired
	private UserServiceI userService;

	@SuppressWarnings("rawtypes")
	@Test
	public void testAddUser() {
		System.out.println("<!-- ========================================分隔线========================================= -->");
		List<Map> users = userService.getUserById("");
		LogFactory.getLog(SpringJUnit4BaseTest.class).debug("USERS:"+users);
		System.out.println("<!-- ========================================分隔线========================================= -->");
	}
}
