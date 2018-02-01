package cn.cd.caoyeung.service.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements ISubject{
	/**
	 * 订阅者列表
	 */
    private List<Observer> observers;
    /**
     * 天气主题基本信息
     */
    private float tempterature;
    private float pressure;
    private float humidity;
    
    public WeatherData(){
        observers = new ArrayList<Observer>();
    }
    
    @Override
    public void notifyObserver() {
        for(int i = 0; i < observers.size();i++){
            Observer observer = observers.get(i);
            //触发订阅者信息变更处理
            observer.update(tempterature, humidity, pressure);
        }
    }
   
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeOberver(Observer observer) {
        int i = observers.indexOf(observer);
        if(i >= 0){
            observers.remove(i);
        }
    }

    /**
     * 气象站得到更新的观测数据时，通知观察者
     */
    public void updateAllADCardDisplay(){
        notifyObserver();
    }
    /**
     * 订阅主题信息变更  同时通知订阅者信息变化
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void setMeasurements(float temperature,float humidity,float pressure){
    	System.out.println("Publisher update Data......");
        this.tempterature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        updateAllADCardDisplay();
    }
}