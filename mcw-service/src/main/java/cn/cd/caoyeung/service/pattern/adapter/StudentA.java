package cn.cd.caoyeung.service.pattern.adapter;
/**
 * 类适配器
 * @author: CaoYeung
 * @time: 下午7:40:09
 */
public class StudentA extends Person implements Job {

	@Override
	public void speakChinese() {
		System.out.println("speakChinese");
	}

}
