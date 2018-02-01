package cn.cd.caoyeung.service.pattern.observer;
/**
 * 基于发布订阅方式   如：A气象数据  -B气象展示   新闻 -各大新闻网站信息展示
 * A变动-触发B变动   A动态扩展B
 *
 */
public interface ISubject {
    /**
     * ①注册观察者  订阅者知道我的订阅主题并在初始化的时候添加到订阅列表
     * @param observer
     */
    public void registerObserver(Observer observer);
    
    /**
     * ②删除观察者 同理订阅者销毁时移除订阅列表
     * @param observer
     */
    public void removeOberver(Observer observer);
    
    /** 
     * ③当主题状态发生改变时，订阅主题信息变动时   主动触发订阅者的信息展示  如果维护抽取出为调度注册中心可升级为发布订阅者
     */
    public void notifyObserver();
}