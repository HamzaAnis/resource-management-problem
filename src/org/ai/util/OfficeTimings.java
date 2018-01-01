package org.ai.util;

public class OfficeTimings {

	private Time opening, closing;
	private Duration workingTime;
	private int workingDays; // working day in a week: 5 for sat sun off and 6 for only sun off

	public OfficeTimings(Time opening, Time closing, Duration workingTime, int workingDays) {
		super();
		this.opening = opening;
		this.closing = closing;
		this.workingTime = workingTime;
		this.workingDays = workingDays;
	}

	public Time getOpening() {
		return opening;
	}

	public void setOpening(Time opening) {
		this.opening = opening;
	}

	public Time getClosing() {
		return closing;
	}

	public void setClosing(Time closing) {
		this.closing = closing;
	}

	public Duration getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(Duration workingTime) {
		this.workingTime = workingTime;
	}

	public int getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(int workingDays) {
		this.workingDays = workingDays;
	}
}
