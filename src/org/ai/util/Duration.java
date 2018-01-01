package org.ai.util;

import java.util.Scanner;

public class Duration {
	private int hour;
	private int minute;

	Duration() {
		hour = minute = 0;
	}

	public Duration(int hour, int minute) {
		super();
		this.hour = hour;
		this.minute = minute;
	}

	private boolean validDuration() {
		return !(hour == 0 && minute == 0) && (hour > -1 && minute > -1);
	}

	// Getting the duration
	public void setDuration(String message) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(message);
			try {
				hour = sc.nextInt();
				minute = sc.nextInt();
				if (this.validDuration()) {
					break;
				} else {
					System.out.println("\t\tPlease enter a valid Duration");
				}
			} catch (Exception e) {
				System.out.println("\t\tPlease Enter a valid Duration");
			}
		}
		sc.close();
	}

	public Duration increment() {
		this.hour++;
		return this;
	}

	public int gethour() {
		return hour;
	}

	public void sethour(int hour) {
		this.hour = hour;
	}

	public int getminute() {
		return minute;
	}

	public void setminute(int minute) {
		this.minute = minute;
	}

}
