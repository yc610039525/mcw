package cn.cd.caoyeung.service.pattern.observer;

public class AdCardDisplay implements Observer,IAdCardDisPlayExt{
	/**
	 * 公告牌基本信息   目的与天气主题同步
	 */
	private String address;
    private float temperature;
    private float humidity;
    private ISubject weatherData;
    /**
     * 初始化订阅者时  必须知道订阅主题是什么 并将主题添加到订阅列表
     * @param weatherData
     */
    public AdCardDisplay(ISubject weatherData,String address){
        this.weatherData = weatherData;
        this.address = address;
        this.weatherData.registerObserver(this);      //注册观察者
    }
    /**
     * 订阅者信息变更处理
     */
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
    	System.out.println("Subscriber update Data......");
        System.out.println("Weather Data：address:"+address+",Current conditions:"+temperature+"F degrees , "+humidity+"% humidity");
    }

}