package org.ai.scheduling;

public class Room {
	private int roomNo;
	private boolean haveProjector, isAvailible;
	private Time freeTime; // the time when room will be free to use

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public Room(int roomNo, boolean haveProjector, boolean isAvailible, Time freeTime) {
		super();
		this.roomNo = roomNo;
		this.haveProjector = haveProjector;
		this.isAvailible = isAvailible;
		this.freeTime = freeTime;
	}

	public boolean isHaveProjector() {
		return haveProjector;
	}

	public void setHaveProjector(boolean haveProjector) {
		this.haveProjector = haveProjector;
	}

	public boolean isAvailible() {
		return isAvailible;
	}

	public void setAvailible(boolean isAvailible) {
		this.isAvailible = isAvailible;
	}

	public Time getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(Time freeTime) {
		this.freeTime = freeTime;
	}
}
