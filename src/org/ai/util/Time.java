package org.ai.util;

import java.util.Scanner;

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

	public Time addDuration(Duration d) {
		this.minute += d.getminute();

		if (this.minute > 59) {
			this.minute -= 60;
			this.hour += 1;
		}
		boolean Bool = false;
		this.hour += d.gethour();
		if (this.hour >= 24)
			Bool = true;

		if (this.arr == "am" && this.hour == 12) {
			this.arr = "pm";
			return this;
		}

		if (this.arr == "pm" && this.hour == 13) {
			this.hour -= 12;
			return this;
		}

		if (this.arr == "am") {
			if (this.hour > 12) {
				this.hour -= 12;
				this.arr = "pm";
			}
		} else {
			if (this.hour > 11) {
				this.hour -= 12;
				this.arr = "am";
			}
		}
		if (Bool)
			this.hour %= 12;

		return this;
	}

	public int getMinutes() {
		return arr == "am" ? hour * 60 + minute : 12 * hour * 60 + minute;
	}

	public void displayTime(String message) {
		System.out.println(message + hour + ":" + minute + " " + arr);
	}

	public boolean validTime() {
		return !(hour == 0 && minute == 0) && (hour < 13 && hour > -1 && minute > -1 && minute < 60)
				&& (arr.length() == 2 && (arr.equals("am") || arr.equals("pm")));
	}

	public void setTime(String message) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(message);
			try {
				hour = sc.nextInt();
				minute = sc.nextInt();
				arr = sc.nextLine();
				if (this.validTime()) {
					break;
				} else {
					System.out.println("\t\tPlease enter a valid Time");
				}
			} catch (Exception e) {
				System.out.println("\t\tPlease Enter a valid Time");
			}
		}
		sc.close();
	}

	public boolean checkEqual(Time rhs) {
		return hour == rhs.hour && minute == rhs.minute && arr == rhs.arr;
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
