import org.ai.util.Office;
import org.ai.util.Time;

public class BackJumping {
	private Office office;

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public BackJumping() {
		office = new Office();
	}

	public BackJumping(Office office) {
		super();
		this.office = office;
	}

	// this is not implemented from runable
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

					office.getMWP().get(k).setTime(office.getRWP().get(roomIndex).getFreeTime());;
					office.getRWP().get(roomIndex).setFreeTime( office.getRWP().get(roomIndex).getFreeTime().addDuration(office.getMWP().get(k).getDuration()));
					office.getMWP().get(k).setDay(day);;
				}
			}
		}

	}
}
