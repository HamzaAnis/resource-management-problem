package org.ai.scheduling;

public class Time {
	private int hour;
	private int minute;
	private String arr;

	Time() {
		hour = 0;
		minute = 0;
		arr = "am";
	}

	public Time(int hour, int minute, String arr) {
		super();
		this.hour = hour;
		this.minute = minute;
		this.arr = arr;
	}

	public Duration __24HourClock() {
		Duration temp = new Duration(arr == "am" ? hour : hour + 12, minute);
		return temp;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

}
