package org.ai.scheduling;

import java.util.Scanner;

public class Date {
	private int day;
	private int month;
	private int year;

	boolean validDate() {
		return (day > -1 && month > -1 && year > -1);
	}

	Date() {
		day = 0;
		month = 0;
		year = 0;
	}

	public Date(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public void setDate(String message) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(message);
			try {
				day = sc.nextInt();
				month = sc.nextInt();
				year = sc.nextInt();
				if (this.validDate()) {
					break;
				} else {
					System.out.println("\t\tPlease enter a valid Date");
				}
			} catch (Exception e) {
				System.out.println("\t\tInvalid Format");
			}
		}
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
