import java.util.ArrayList;
import java.util.Scanner;

import org.ai.util.Meeting;
import org.ai.util.OfficeTimings;
import org.ai.util.Room;

public class Office {
	private OfficeTimings officeTimings;
	private int numberofMeetings;
	private int NORWP; // number of rooms with projectors
	private int NORWOP; // number of rooms with projectors
	ArrayList<Meeting> meetings;
	ArrayList<Room> RWP; // roomsWithProjector
	ArrayList<Room> RWOP; // roomsWithoutProjector
	ArrayList<Meeting> MWP; // meetings with projectors
	ArrayList<Meeting> MWOP; // meetings without projectors

	public Office(OfficeTimings officeTimings, int numberofMeetings, int nORWP, int nORWOP, ArrayList<Meeting> meetings,
			ArrayList<Room> rWP, ArrayList<Room> rWOP, ArrayList<Meeting> mWP, ArrayList<Meeting> mWOP) {
		super();
		this.officeTimings = officeTimings;
		this.numberofMeetings = numberofMeetings;
		NORWP = nORWP;
		NORWOP = nORWOP;
		this.meetings = meetings;
		RWP = rWP;
		RWOP = rWOP;
		MWP = mWP;
		MWOP = mWOP;
	}

	public void seperateMeetings() {
		for (int i = 0; i < numberofMeetings; i++) {
			if (meetings.get(i).isNeedProjector())
				MWP.add(meetings.get(i));
			else
				MWOP.add(meetings.get(i));
		}
	}

	public OfficeTimings getOfficeTimings() {
		return officeTimings;
	}

	public int TakeIntInput(String massege) {
		int integer = 0;
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(massege);
			try {
				integer = sc.nextInt();
				if (integer > 0)
					break;
				else
					System.out.println("\t\tfNagetive number not allowed");
			} catch (Exception e) {
				System.out.println("\t\tPlease use numbers only");
			}
		}
		return integer;
	}

	void setNumberOfRooms() {
		NORWP = TakeIntInput("   Enter the number of rooms with projector: ");
		NORWOP = TakeIntInput("Enter the number of rooms without projector: ");
	}

	public void setRooms() {
		Room temp = new Room();

		for (int i = 0; i < NORWP; i++) {
			temp.setHaveProjector(true);
			temp.setAvailible(true);
			temp.setRoomNo(i + 1);
			temp.setFreeTime(officeTimings.getOpening());

			RWP.add(temp);
		}

		for (int i = 0; i < NORWOP; i++) {
			temp.setHaveProjector(true);
			temp.setAvailible(false);
			temp.setRoomNo(i + 1);
			temp.setFreeTime(officeTimings.getOpening());

			RWOP.add(temp);
		}
	}

	public void setnumberofMeetings() {
		numberofMeetings = TakeIntInput("Enter the number of Meetings: ");
	}

	void FreeAllRooms() {
		for (int i = 0; i < RWP.size(); i++)
			RWP.get(i).setFreeTime(officeTimings.getOpening());

		for (int i = 0; i < RWOP.size(); i++)
			RWOP.get(i).setFreeTime(officeTimings.getOpening());
	}

	public void setOfficeTimings() {
		System.out.println("Please enter the following information for Office Timings:\n\n");
		officeTimings.getOpening().setTime("   Opening Time (HH:MM am/pm): ");
		officeTimings.getClosing().setTime("   Closing Time (HH:MM am/pm): ");
		officeTimings.setWorkingDays(TakeIntInput("       Working days in a week: "));
		officeTimings.getWorkingTime().setDuration("Working time in a day (HH:MM): ");
	}

	public void displayofficeTimings() {
		officeTimings.getOpening().displayTime("Opening Time: ");
		officeTimings.getClosing().displayTime("Closing Time: ");
	}

	public void displaMeetings() {
		for (int i = 0; i < numberofMeetings; i++) {
			System.out.println("Meeting " + i);
			System.out.println(meetings.get(i).getDuration().getminute());
		}
	}

	public void setMeetings() {

		Meeting M_temp = new Meeting();

		String temp = "";
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < numberofMeetings; i++) {
			System.out.println("\t\tPlease Enter following Information for Meeting " + (i + 1) + "!");
			System.out.println("                       Name: ");
			M_temp.setName(sc.nextLine());
			M_temp.getDuration().setDuration("Duration of Meeting (HH:MM): ");

			while (true) {
				System.out.println("   Projector required (Y/N): ");

				temp = sc.nextLine();

				if (temp.equals('Y') || temp.equals('N'))
					break;
				else
					System.out.println("\t\tWrong Input!!!\n");
			}
			if (temp.equals("Y")) {
				M_temp.setNeedProjector(true);
			} else {
				M_temp.setNeedProjector(false);
			}

			meetings.add(M_temp);
		}
		sc.close();
	}

	public void setOfficeTimings(OfficeTimings officeTimings) {
		this.officeTimings = officeTimings;
	}

	public int getNumberofMeetings() {
		return numberofMeetings;
	}

	public void setNumberofMeetings(int numberofMeetings) {
		this.numberofMeetings = numberofMeetings;
	}

	public int getNORWP() {
		return NORWP;
	}

	public void setNORWP(int nORWP) {
		NORWP = nORWP;
	}

	public int getNORWOP() {
		return NORWOP;
	}

	public void setNORWOP(int nORWOP) {
		NORWOP = nORWOP;
	}

	public ArrayList<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
	}

	public ArrayList<Room> getRWP() {
		return RWP;
	}

	public void setRWP(ArrayList<Room> rWP) {
		RWP = rWP;
	}

	public ArrayList<Room> getRWOP() {
		return RWOP;
	}

	public void setRWOP(ArrayList<Room> rWOP) {
		RWOP = rWOP;
	}

	public ArrayList<Meeting> getMWP() {
		return MWP;
	}

	public void setMWP(ArrayList<Meeting> mWP) {
		MWP = mWP;
	}

	public ArrayList<Meeting> getMWOP() {
		return MWOP;
	}

	public void setMWOP(ArrayList<Meeting> mWOP) {
		MWOP = mWOP;
	}

}
