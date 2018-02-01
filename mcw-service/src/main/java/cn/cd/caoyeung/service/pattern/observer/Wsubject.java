package cn.cd.caoyeung.service.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Wsubject extends Observable {

	@Override
	public synchronized void addObserver(Observer arg0) {
		super.addObserver(arg0);
	}

	@Override
	protected synchronized void clearChanged() {
		super.clearChanged();
	}

	@Override
	public synchronized int countObservers() {
		return super.countObservers();
	}

	@Override
	public synchronized void deleteObserver(Observer arg0) {
		super.deleteObserver(arg0);
	}

	@Override
	public synchronized void deleteObservers() {
		super.deleteObservers();
	}

	@Override
	public synchronized boolean hasChanged() {
		return super.hasChanged();
	}

	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

	@Override
	public void notifyObservers(Object arg0) {
		super.notifyObservers(arg0);
	}

	@Override
	protected synchronized void setChanged() {
		super.setChanged();
	}

}
