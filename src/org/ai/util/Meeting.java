package org.ai.util;

public class Meeting {
	private Duration duration;
	private Time time;
	private String name;
	private int day;
	private Room room;
	private boolean needProjector, roomAllocated;

	public Meeting() {
		duration = new Duration();
		time = new Time();
		name = "";
		day = 0;
		room = new Room();
		needProjector = roomAllocated = false;
	}

	public Meeting(Duration duration, Time time, String name, int day, Room room, boolean needProjector,
			boolean roomAllocated) {
		super();
		this.duration = duration;
		this.time = time;
		this.name = name;
		this.day = day;
		this.room = room;
		this.needProjector = needProjector;
		this.roomAllocated = roomAllocated;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public boolean isNeedProjector() {
		return needProjector;
	}

	public void setNeedProjector(boolean needProjector) {
		this.needProjector = needProjector;
	}

	public boolean isRoomAllocated() {
		return roomAllocated;
	}

	public void setRoomAllocated(boolean roomAllocated) {
		this.roomAllocated = roomAllocated;

	}
}
