package cn.cd.caoyeung.service.common.designpattern.abstractfactory;

public class LinuxFormFactory implements IFormFactory {

	@Override
	public IButton createButton() {
		return new LinuxButton();
	}

	@Override
	public IText createText() {
		return new LinuxText();
	}

}
