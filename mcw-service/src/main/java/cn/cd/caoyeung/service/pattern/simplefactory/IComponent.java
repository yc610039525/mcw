package cn.cd.caoyeung.service.pattern.simplefactory;
/**
 * 利用反射 XML技术将简单工厂配置化
 * 创建范围用于创建一个组件或一个模块范围
 * @author Admin
 *
 */
public interface IComponent {
    String getName();
    int getPrice();
}
