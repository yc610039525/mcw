package cn.cd.caoyeung.service.common.designpattern.abstractfactory;

public interface IFormFactory {
	IButton createButton();
	IText createText();
}
