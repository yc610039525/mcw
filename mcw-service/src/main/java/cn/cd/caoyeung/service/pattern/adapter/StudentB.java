package cn.cd.caoyeung.service.pattern.adapter;
/**
 * 对象适配器
 * @author: CaoYeung
 * @time: 下午7:39:53
 */
public class StudentB implements Job {
	Person defaultPerson;
	
	public StudentB(Person defaultPerson) {
		super();
		this.defaultPerson = defaultPerson;
	}

	@Override
	public void speakJapanese() {
		defaultPerson.speakJapanese();
	}

	@Override
	public void speakEgnlish() {
		defaultPerson.speakEgnlish();
	}

	@Override
	public void speakChinese() {
		System.out.println("speakChinese");
	}

}
