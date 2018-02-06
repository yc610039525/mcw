package base;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.cd.caoyeung.webapp.service.AttempTraphServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/applicationContext-bean.xml" })
public class SpringJUnit4BaseTest {
	@Autowired
	private AttempTraphServiceI attempTraphServiceI;

	@SuppressWarnings("rawtypes")
	@Test
	public void testAddUser() {
		System.out.println("<!-- ========================================分隔线========================================= -->");
		List<Map> attempTraphs = attempTraphServiceI.getAttempTraphById("");
		LogFactory.getLog(SpringJUnit4BaseTest.class).debug("AttempTrap:"+attempTraphs);
		System.out.println("<!-- ========================================分隔线========================================= -->");
	}
}
