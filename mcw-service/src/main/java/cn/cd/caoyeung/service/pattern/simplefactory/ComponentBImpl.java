package cn.cd.caoyeung.service.pattern.simplefactory;

public class ComponentBImpl implements IComponent {

	@Override
	public String getName() {
		return "创维";
	}

	@Override
	public int getPrice() {
		return 900;
	}

}
