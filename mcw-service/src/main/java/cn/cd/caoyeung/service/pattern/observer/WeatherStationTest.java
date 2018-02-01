package cn.cd.caoyeung.service.pattern.observer;

public class WeatherStationTest {
	/**
	 * 注意如果维护中心单独抽取出来就是发布订阅模式
	 * 订阅维护中心  MaintainCenter或registerCenter
	 */
    public static void main(String[] args) {
        WeatherData weatherDataSubject = new WeatherData();
        AdCardDisplay wannianSqureDisplay = new AdCardDisplay(weatherDataSubject,"CunXi Road");
        AdCardDisplay cunxiRoadDisplay = new AdCardDisplay(weatherDataSubject,"Wan Nian Squre");
        weatherDataSubject.setMeasurements(80, 65, 30.4f);
    }
}