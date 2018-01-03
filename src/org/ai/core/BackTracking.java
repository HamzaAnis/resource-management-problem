package org.ai.core;
import java.util.ArrayList;

import org.ai.util.Office;
import org.ai.util.Time;

public class BackTracking {
	Office office;
	int[] used;

	int cs = 0; // cs=Current Sum
	ArrayList<ArrayList<Integer>> combinations;
	ArrayList<ArrayList<Integer>> combinations1;

	public BackTracking() {
		combinations = new ArrayList<ArrayList<Integer>>();
		combinations1 = new ArrayList<ArrayList<Integer>>();
		used = new int[100];
		cs = 0;
	}

	public void run() {
		office.seperateMeetings();
		int sum = 0;
		int roomIndex = 0;
		int day = 1;
		Time time = office.getOfficeTimings().getOpening();

		// ****************************
		// * Meetings with projectors *
		// ****************************

		if (office.getRWP().size() > 0) {
			for (int i = 0; i < office.getMWP().size(); i++)
				sum += office.getMWP().get(i).getDuration().getminute();

			if (sum >= office.getOfficeTimings().getWorkingTime().getminute()) {
				B_MCWP(office, 0);

				combinations = removeRepetitions(combinations);

				for (int k = 0; k < combinations.size(); k++, roomIndex++) {
					if (roomIndex >= office.getRWP().size())
						roomIndex = 0;
					day++;
					office.FreeAllRooms();

					for (int l = 0; l < combinations.get(k).size(); l++) {
						office.getMWP().get(combinations.get(k).get(l)).setRoomAllocated(true);
						office.getMWP().get(combinations.get(k).get(l)).setRoom(office.getRWP().get(roomIndex));

						office.getMWP().get(combinations.get(k).get(l))
								.setTime(office.getRWP().get(roomIndex).getFreeTime());
						office.getRWP().get(roomIndex).setFreeTime(office.getRWP().get(roomIndex).getFreeTime()
								.addDuration(office.getMWP().get(combinations.get(k).get(l)).getDuration()));
						office.getMWP().get(combinations.get(k).get(l)).setDay(day);
					}
				}
			}
		}
		if (office.getRWP().size() > 0) {
			for (int i = 0; i < office.getMWP().size(); i++)
				sum += office.getMWP().get(i).getDuration().getminute();

			for (int k = 0; k < office.getMWP().size(); k++) {
				if (!office.getMWP().get(k).isRoomAllocated()) {
					if (roomIndex >= office.getRWP().size())
						roomIndex = 0;
					day++;
					this.office.FreeAllRooms();

					time = office.getRWP().get(roomIndex).getFreeTime()
							.addDuration(office.getMWP().get(k).getDuration());

					if (time.getMinutes() > office.getOfficeTimings().getClosing().getMinutes()) {
						roomIndex++;
						if (roomIndex >= office.getRWP().size())
							roomIndex = 0;
						day++;
						office.FreeAllRooms();
					}

					office.getMWP().get(k).setRoomAllocated(true);
					office.getMWP().get(k).setRoom(office.getRWP().get(roomIndex));

					office.getMWP().get(k).setTime(office.getRWP().get(roomIndex).getFreeTime());

					office.getRWP().get(roomIndex).setFreeTime(office.getRWP().get(roomIndex).getFreeTime()
							.addDuration(office.getMWP().get(k).getDuration()));
					office.getMWP().get(k).setDay(day);

				}
			}
		} else {
			System.out.println("Your office don't have any room with projector!\\n\\n");
		}

		// *******************************
		// * Meetings without projectors *
		// *******************************

		sum = 0;
		roomIndex = 0;
		day = 1;
		time = office.getOfficeTimings().getOpening();

		// ****************************

		if (office.getRWOP().size() > 0) {
			for (int i = 0; i < office.getMWOP().size(); i++)
				sum += office.getMWOP().get(i).getDuration().getminute();

			if (sum >= office.getOfficeTimings().getWorkingTime().getminute()) {
				B_MCWOP(office, 0);

				combinations1 = removeRepetitions(combinations1);

				for (int k = 0; k < combinations1.size(); k++, roomIndex++) {
					if (roomIndex >= office.getRWOP().size())
						roomIndex = 0;
					day++;
					office.FreeAllRooms();

					for (int l = 0; l < combinations1.get(k).size(); l++) {
						office.getMWOP().get(combinations1.get(k).get(l)).setRoomAllocated(true);
						office.getMWOP().get(combinations1.get(k).get(l)).setRoom(office.getRWOP().get(roomIndex));

						office.getMWOP().get(combinations1.get(k).get(l))
								.setTime(office.getRWOP().get(roomIndex).getFreeTime());
						office.getRWOP().get(roomIndex).setFreeTime(office.getRWOP().get(roomIndex).getFreeTime()
								.addDuration(office.getMWOP().get(combinations.get(k).get(l)).getDuration()));
						office.getMWOP().get(combinations1.get(k).get(l)).setDay(day);
					}
				}
			}
		}
		if (office.getRWOP().size() > 0) {
			for (int i = 0; i < office.getMWOP().size(); i++)
				sum += office.getMWOP().get(i).getDuration().getminute();

			for (int k = 0; k < office.getMWOP().size(); k++) {
				if (!office.getMWOP().get(k).isRoomAllocated()) {
					if (roomIndex >= office.getRWOP().size())
						roomIndex = 0;
					day++;
					office.FreeAllRooms();

					time = office.getRWOP().get(roomIndex).getFreeTime()
							.addDuration(office.getMWOP().get(k).getDuration());

					if (time.getMinutes() > office.getOfficeTimings().getClosing().getMinutes()) {
						roomIndex++;
						if (roomIndex >= office.getRWOP().size())
							roomIndex = 0;
						day++;
						office.FreeAllRooms();
					}

					office.getMWOP().get(k).setRoomAllocated(true);
					office.getMWOP().get(k).setRoom(office.getRWOP().get(roomIndex));

					office.getMWOP().get(k).setTime(office.getRWOP().get(roomIndex).getFreeTime());
					office.getRWOP().get(roomIndex).setFreeTime(office.getRWOP().get(roomIndex).getFreeTime());
					office.getRWOP().get(roomIndex).setFreeTime(office.getRWOP().get(roomIndex).getFreeTime()
							.addDuration(office.getMWOP().get(k).getDuration()));
					office.getMWOP().get(k).setDay(day);
				}
			}
		} else {
			System.out.println("Your office don't have any simple!\\n\\n");
		}
	}

	void B_MCWP(Office _office, int k) // Meetings combination with projectors
	{
		if (k >= _office.getMWP().size())
			return; // boundry check
		int i;
		used[k] = 1; // use element k
		cs += _office.getMWP().get(k).getDuration().getminute();

		if (cs == _office.getOfficeTimings().getWorkingTime().getminute()) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (i = 0; i <= k; i++)
				if (used[i] == 1)
					temp.add(i);
			combinations.add(temp);
		}
		if (cs < _office.getOfficeTimings().getWorkingTime().getminute()) // only when current sum is not enough
			B_MCWP(_office, k + 1);

		used[k] = 0; // not use element k
		cs -= _office.getMWP().get(k).getDuration().getminute();
		B_MCWP(_office, k + 1);
	}

	void B_MCWOP(Office _office, int k) // Meetings combination with projectors
	{
		if (k >= _office.getMWOP().size())
			return; // boundry check
		int i;
		used[k] = 1; // use element k
		cs += _office.getMWOP().get(k).getDuration().getminute();

		if (cs == _office.getOfficeTimings().getWorkingTime().getminute()) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (i = 0; i <= k; i++)
				if (used[i] == 1)
					temp.add(i);
			combinations1.add(temp);
		}
		if (cs < _office.getOfficeTimings().getWorkingTime().getminute()) // only when current sum is not enough
			B_MCWOP(_office, k + 1);

		used[k] = 0; // not use element k
		cs -= _office.getMWOP().get(k).getDuration().getminute();
		B_MCWOP(_office, k + 1);
	}

	public BackTracking(Office office, int[] used, int cs, ArrayList<ArrayList<Integer>> combinations,
			ArrayList<ArrayList<Integer>> combinations1) {
		super();
		this.office = office;
		this.used = used;
		this.cs = cs;
		this.combinations = combinations;
		this.combinations1 = combinations1;

	}

	public ArrayList<ArrayList<Integer>> removeRepetitions(ArrayList<ArrayList<Integer>> combinations_) {
		ArrayList<Integer> Vector = new ArrayList<Integer>();
		for (int k = 0; k < combinations_.size(); k++) {
			for (int l = 0; l < combinations_.get(k).size(); l++) {
				boolean Bool = false;

				for (int m = 0; m < Vector.size(); m++) {

					// listA.containsAll(listB) && listB.containsAll(listA)

					if (Vector.get(m) == combinations_.get(k).get(l))
						Bool = true;
				}

				if (Bool) {
					combinations_.remove(k);
					k--;
					break;
				} else
					Vector.add(combinations_.get(k).get(l));
			}
		}
		return combinations_;
	}

	public BackTracking(Office office) {
		super();
		this.office = office;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public int[] getUsed() {
		return used;
	}

	public void setUsed(int[] used) {
		this.used = used;
	}

	public int getCs() {
		return cs;
	}

	public void setCs(int cs) {
		this.cs = cs;
	}

	public ArrayList<ArrayList<Integer>> getCombinations() {
		return combinations;
	}

	public void setCombinations(ArrayList<ArrayList<Integer>> combinations) {
		this.combinations = combinations;
	}

}