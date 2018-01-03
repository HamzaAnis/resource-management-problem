import org.ai.util.Office;
import org.ai.util.Time;

public class BackTracking {
	Office office;

	public void run() {
		office.seperateMeetings();
		int sum = 0;
		int roomIndex = 0;
		int day = 1;
		Time time = office.getOfficeTimings().getOpening();

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
}
