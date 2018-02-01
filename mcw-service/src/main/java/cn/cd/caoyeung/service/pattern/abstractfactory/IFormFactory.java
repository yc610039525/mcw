package cn.cd.caoyeung.service.pattern.abstractfactory;

public interface IFormFactory {
	IButton createButton();
	IText createText();
}
