package com.sist.retail.vo;

public class WeatherVO {
	private String weather;
	private double  curTemp;
	private double minTemp;
	private double maxTemp;
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public double getCurTemp() {
		return curTemp;
	}
	public void setCurTemp(double curTemp) {
		this.curTemp = curTemp;
	}
	public double getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}
	public double getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}	
}