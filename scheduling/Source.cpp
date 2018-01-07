#include<iostream>
#include<string>
#include<vector>
#include<stack>

using namespace std;

string numberToDay(int);
int TakeIntInput(string);

struct Time;
struct Date;
struct Duration;
struct Room;
struct Meeting;
struct OfficeTimings;
struct Office;

char algorithSelection();
void UserInterface(Office &);
void programExecution(char);

void Backtracking(Office &);
void Backjumping(Office &);
void ForwardChecking(Office &);
void main(void)
{
	//	system("color 3F");
	char option = algorithSelection();

	programExecution(option);

	cout << endl;
}

string numberToDay(int day)
{
	string days[8] = { "", "Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Satureday", "Sunday" };
	return days[day];
}
int TakeIntInput(string massege)
{
	int integer = 0;

	while (true)
	{
		cout << massege;
		if (cin >> integer)
		{
			if (integer > 0)
				break;
			else cout << "\aNagetive number not allowed\n";
		}
		else cout << "\aPlease use numbers only\n";

		cin.clear();
		cin.ignore(numeric_limits<streamsize>::max(), '\n');
	}

	return integer;
}
int absolute(int a, int b)
{
	int c = a - b;
	return c > 0 ? c : -c;
}
bool checkin = false;

struct Duration
{
	int hour, minute;

	Duration(int hour = 0, int minute = 0) { this->hour = hour, this->minute = minute; }

	int getMinutes() { return hour * 60 + minute; }
	bool validDuration()
	{
		return !(hour == 0 && minute == 0) && (hour > -1 && minute > -1);
	}

	Duration operator++(int)
	{
		this->hour++;

		return *this;
	}

	void setDuration(string massege)
	{
		char temp;
		while (true)
		{
			cout << massege;
			if (cin >> hour >> temp >> minute)
			{
				if (this->validDuration())
					break;
				else cout << "\aPlease Enter a valid Duration\n";
			}
			else cout << "\aInvalid Format\n";

			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
		}
	}
};
struct Date
{
	int day, month, year;

	bool validDate()
	{
		return (day > -1 && month > -1 && year > -1);
	}

	void setDate(string massege)
	{
		char temp;
		while (true)
		{
			cout << massege;
			if (cin >> day >> temp >> month >> temp >> year)
			{
				if (this->validDate())
					break;
				else cout << "\aPlease Enter a valid Time\n";
			}
			else cout << "\aInvalid Format\n";

			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
		}
	}
};

struct Room
{
	int roomNo;
	bool haveProjector, isAvailible;
	Time freeTime; // the time when room will be free to use
};
struct Meeting
{
	Duration duration;
	Time time;
	string name; int day;
	Room room;
	bool needProjector, roomAllocated;
	Meeting()
	{
		day = 0;
		roomAllocated = false;
	}
};
struct OfficeTimings
{
	Time opening, closing;
	Duration workingTime;
	int workingDays; // working day in a week: 5 for sat sun off and 6 for only sun off
};
struct Office
{
	OfficeTimings officeTimings;
	int numberofMeetings;
	int NORWP; // number of rooms with projectors
	int NORWOP; // number of rooms with projectors
	vector<Meeting> meetings;
	vector<Room> RWP; // roomsWithProjector
	vector<Room> RWOP; // roomsWithoutProjector
	vector<Meeting> MWP; // meetings with projectors
	vector<Meeting> MWOP; // meetings without projectors

	void seperateMeetings()
	{
		for (int i = 0; i < numberofMeetings; i++)
		{
			if (meetings[i].needProjector)
				MWP.push_back(meetings[i]);
			else
				MWOP.push_back(meetings[i]);
		}
	}
	void setNumberOfRooms()
	{
		NORWP = TakeIntInput("   Enter the number of rooms with projector: ");
		NORWOP = TakeIntInput("Enter the number of rooms without projector: ");
	}

	void setRooms()
	{
		Room temp;

		for (int i = 0; i < NORWP; i++)
		{
			temp.haveProjector = true;
			temp.isAvailible = true;
			temp.roomNo = i + 1;
			temp.freeTime = officeTimings.opening;

			RWP.push_back(temp);
		}

		for (int i = 0; i < NORWOP; i++)
		{
			temp.haveProjector = true;
			temp.isAvailible = false;
			temp.roomNo = i + 1;
			temp.freeTime = officeTimings.opening;

			RWOP.push_back(temp);
		}
	}

	void setnumberofMeetings()
	{
		numberofMeetings = TakeIntInput("Enter the number of Meetings: ");
	}

	void FreeAllRooms()
	{
		for (int i = 0; i < RWP.size(); i++)
			RWP[i].freeTime = officeTimings.opening;

		for (int i = 0; i < RWOP.size(); i++)
			RWOP[i].freeTime = officeTimings.opening;
	}

	void setMeetings()
	{

		Meeting M_temp;

		string temp;

		cin.ignore();

		for (int i = 0; i < numberofMeetings; i++)
		{
			cout << "\n\aPlease Enter following Information for Meeting " << (i + 1) << "!\n";
			cout << "                       Name: ";
			getline(cin, M_temp.name);
			M_temp.duration.setDuration("Duration of Meeting (HH:MM): ");
			cin.ignore();
			while (true)
			{
				cout << "   Projector required (Y/N): ";
				getline(cin, temp);

				temp[0] > 90 ? temp[0] = temp[0] - 32 : temp[0];

				if (temp[0] == 'Y' || temp[0] == 'N')
					break;
				else cout << "\aWrong Input!!!\n";
			}

			temp[0] == 'Y' ? M_temp.needProjector = true : M_temp.needProjector = false;

			meetings.push_back(M_temp);
		}
	}

	void setOfficeTimings()
	{
		cout << "Please enter the following information for Office Timings:\n\n";
		officeTimings.opening.setTime("   Opening Time (HH:MM am/pm): ");
		officeTimings.closing.setTime("   Closing Time (HH:MM am/pm): ");
		officeTimings.workingDays = TakeIntInput("       Working days in a week: ");
		officeTimings.workingTime.setDuration("Working time in a day (HH:MM): ");
	}

	void displayofficeTimings()
	{
		officeTimings.opening.displayTime("Opening Time: ");
		officeTimings.closing.displayTime("Closing Time: ");
	}

	void displaMeetings()
	{
		for (int i = 0; i < numberofMeetings; i++)
		{
			cout << "Meeting " << i << endl;
			cout << meetings[i].duration.getMinutes() << endl;
		}
	}

	void displaySchedualing()
	{
		if (NORWP > 0 && MWP.size() > 0)
		{
			cout << "***************************\n";
			cout << "* Meetings with projector *\n";
			cout << "***************************\n\n";

			for (int i = 0; i < MWP.size(); i++)
			{
				cout << "Meeting" << i + 1 << ":\n";
				cout << "              Name: " << MWP[i].name << endl;
				cout << "               Day: " << MWP[i].day << endl;
				cout << "              Time: " << MWP[i].time.hour << ":" << MWP[i].time.minute << " " << MWP[i].time.arr << endl;
				cout << "          Duration: " << MWP[i].duration.hour << ":" << MWP[i].duration.minute << endl;
				cout << "       Room Number: " << MWP[i].room.roomNo << endl << endl;
			}
		}

		if (NORWOP && MWOP.size() > 0)
		{
			cout << "******************************\n";
			cout << "* Meetings without projector *\n";
			cout << "******************************\n\n";

			for (int i = 0; i < MWOP.size(); i++)
			{
				cout << "Meeting" << i + 1 << ":\n";
				cout << "              Name: " << MWOP[i].name << endl;
				cout << "               Day: " << MWOP[i].day << endl;
				MWOP[i].time.displayTime("              Time: ");
				cout << "          Duration: " << MWOP[i].duration.hour << ":" << MWOP[i].duration.minute << endl;
				cout << "       Room Number: " << MWOP[i].room.roomNo << endl << endl;
			}
		}
	}

	void sortMeetings()
	{
		quickSort(meetings, 0, numberofMeetings - 1);
	}

private:

	int partition(vector<Meeting>& arr, int left, int right)
	{
		int i = left, j = right;
		Meeting tmp;
		Meeting pivot = arr[(left + right) / 2];

		while (i <= j) {
			while (arr[i].duration.getMinutes() < pivot.duration.getMinutes())
				i++;
			while (arr[j].duration.getMinutes() > pivot.duration.getMinutes())
				j--;
			if (i <= j) {
				/*swap(arr[i], arr[j]);*/
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		};

		return i;
	}

	void quickSort(vector<Meeting>& arr, int left, int right) {
		int index = partition(arr, left, right);
		if (left < index - 1)
			quickSort(arr, left, index - 1);
		if (index < right)
			quickSort(arr, index, right);
	}
};

char algorithSelection()
{
	string option;

	cout << "*****************************\n";
	cout << "* Resource Managemnt System *\n";
	cout << "*****************************\n\n";
	cout << "Select a method for managing Resources.\n";
	cout << "Press:\n";
	cout << "      a. Backtraking.\n";
	cout << "      b. Backjumping.\n";
	cout << "      c. Forward Checking.\n";

	while (true)
	{
		cout << "\nEnter your choice here: ";
		getline(cin, option);

		if (option[0] == 'a' || option[0] == 'b' || option[0] == 'c')
			break;
		else cout << "\aInvalid Input!!!\n";
	}

	return option[0];
}
void programExecution(char option)
{
	Office office;
	UserInterface(office);
	office.sortMeetings();

	if (option == 'a')
	{
		Backtracking(office);
	}
	else if (option == 'b')
	{
		Backjumping(office);
	}
	else
	{
		ForwardChecking(office);
	}

	system("cls");
	cout << "Meetings Schedualing Plans: \n\n";
	office.displaySchedualing();
}
void UserInterface(Office& office)
{
	system("cls");

	cout << "*****************************\n";
	cout << "* Resource Managemnt System *\n";
	cout << "*****************************\n\n";

	office.setOfficeTimings();

	cout << "\nRoom(s) Information:\n\n";
	office.setNumberOfRooms();
	office.setRooms();

	cout << "\nMeeting(s) Information:\n\n";
	office.setnumberofMeetings();
	office.setMeetings();
}

int used[100];
int cs = 0; // cs=Current Sum
vector<vector<int>> combinations;

void B_MCWP(Office & office, int k) // Meetings combination with projectors
{
	if (k >= office.MWP.size()) return;  // boundry check
	int i;
	used[k] = 1; // use element k
	cs += office.MWP[k].duration.getMinutes();

	if (cs == office.officeTimings.workingTime.getMinutes()) {
		vector<int> temp;
		for (i = 0; i <= k; i++)
			if (used[i] == 1)
				temp.push_back(i);
		combinations.push_back(temp);
	}
	if (cs < office.officeTimings.workingTime.getMinutes())  // only when current sum is not enough
		B_MCWP(office, k + 1);

	used[k] = 0; // not use element k
	cs -= office.MWP[k].duration.getMinutes();
	B_MCWP(office, k + 1);
}

vector<vector<int>> combinations1;
void B_MCWOP(Office & office, int k) // Meetings combination without projectors
{
	if (k >= office.MWOP.size()) return;  // boundry check
	int i;
	used[k] = 1; // use element k
	cs += office.MWOP[k].duration.getMinutes();

	if (cs == office.officeTimings.workingTime.getMinutes()) {
		vector<int> temp;
		for (i = 0; i <= k; i++)
			if (used[i] == 1)
				temp.push_back(i);
		combinations1.push_back(temp);
	}
	if (cs < office.officeTimings.workingTime.getMinutes())  // only when current sum is not enough
		B_MCWOP(office, k + 1);

	used[k] = 0; // not use element k
	cs -= office.MWOP[k].duration.getMinutes();
	B_MCWOP(office, k + 1);
}

void removeRepetitions(vector<vector<int>>& combinations)
{
	vector<int> Vector;

	for (int k = 0; k < combinations.size(); k++)
	{
		for (int l = 0; l < combinations[k].size(); l++)
		{
			bool Bool = false;

			for (int m = 0; m < Vector.size(); m++)
			{
				if (Vector[m] == combinations[k][l])
					Bool = true;
			}

			if (Bool)
			{
				combinations.erase(combinations.begin() + k);
				k--;
				break;
			}
			else
				Vector.push_back(combinations[k][l]);
		}
	}
}

void Backtracking(Office & office)
{
	office.seperateMeetings();

	int sum = 0, roomIndex = 0, day = 1;
	Time time = office.officeTimings.opening;

	//****************************
	//* Meetings with projectors *
	//****************************

	if (office.RWP.size() > 0)
	{
		for (int i = 0; i < office.MWP.size(); i++)
			sum += office.MWP[i].duration.getMinutes();

		if (sum >= office.officeTimings.workingTime.getMinutes())
		{
			B_MCWP(office, 0);

			removeRepetitions(combinations);

			for (int k = 0; k < combinations.size(); k++, roomIndex++)
			{
				if (roomIndex >= office.RWP.size())
					roomIndex = 0, day++, office.FreeAllRooms();

				for (int l = 0; l < combinations[k].size(); l++)
				{
					office.MWP[combinations[k][l]].roomAllocated = true;
					office.MWP[combinations[k][l]].room = office.RWP[roomIndex];

					office.MWP[combinations[k][l]].time = office.RWP[roomIndex].freeTime;
					office.RWP[roomIndex].freeTime = office.RWP[roomIndex].freeTime.addDuration(office.MWP[combinations[k][l]].duration);
					office.MWP[combinations[k][l]].day = day;
				}
			}
		}

		//office.FreeAllRooms();

		for (int k = 0; k < office.MWP.size(); k++)
		{
			if (!office.MWP[k].roomAllocated)
			{
				if (roomIndex >= office.RWP.size())
					roomIndex = 0, day++, office.FreeAllRooms();

				time = office.RWP[roomIndex].freeTime.addDuration(office.MWP[k].duration);

				if (time.getMinutes() > office.officeTimings.closing.getMinutes())
				{
					roomIndex++;
					if (roomIndex >= office.RWP.size())
						roomIndex = 0, day++, office.FreeAllRooms();
				}



				office.MWP[k].roomAllocated = true;
				office.MWP[k].room = office.RWP[roomIndex];

				office.MWP[k].time = office.RWP[roomIndex].freeTime;
				office.RWP[roomIndex].freeTime = office.RWP[roomIndex].freeTime.addDuration(office.MWP[k].duration);
				office.MWP[k].day = day;
			}
		}

	}
	else cout << "\aYour office don't have any room with projector!\n\n";

	//*******************************
	//* Meetings without projectors *
	//*******************************


	sum = 0, roomIndex = 0, day = 1;
	time = office.officeTimings.opening;

	if (office.RWOP.size() > 0)
	{
		for (int i = 0; i < office.MWOP.size(); i++)
			sum += office.MWOP[i].duration.getMinutes();

		if (sum >= office.officeTimings.workingTime.getMinutes())
		{
			B_MCWOP(office, 0);

			removeRepetitions(combinations1);

			for (int k = 0; k < combinations1.size(); k++, roomIndex++)
			{
				if (roomIndex >= office.RWOP.size())
					roomIndex = 0, day++, office.FreeAllRooms();

				for (int l = 0; l < combinations1[k].size(); l++)
				{
					office.MWOP[combinations1[k][l]].roomAllocated = true;
					office.MWOP[combinations1[k][l]].room = office.RWOP[roomIndex];

					office.MWOP[combinations1[k][l]].time = office.RWOP[roomIndex].freeTime;
					office.RWOP[roomIndex].freeTime = office.RWOP[roomIndex].freeTime.addDuration(office.MWOP[combinations1[k][l]].duration);
					office.MWOP[combinations1[k][l]].day = day;
				}
			}
		}

		for (int k = 0; k < office.MWOP.size(); k++)
		{
			if (!office.MWOP[k].roomAllocated)
			{
				if (roomIndex >= office.RWOP.size())
					roomIndex = 0, day++, office.FreeAllRooms();

				time = office.RWOP[roomIndex].freeTime.addDuration(office.MWOP[k].duration);

				if (time.getMinutes() > office.officeTimings.closing.getMinutes())
				{
					roomIndex++;
					if (roomIndex >= office.RWOP.size())
						roomIndex = 0, day++, office.FreeAllRooms();
				}

				office.MWOP[k].roomAllocated = true;
				office.MWOP[k].room = office.RWOP[roomIndex];

				office.MWOP[k].time = office.RWOP[roomIndex].freeTime;
				office.RWOP[roomIndex].freeTime = office.RWOP[roomIndex].freeTime.addDuration(office.MWOP[k].duration);
				office.MWOP[k].day = day;
			}
		}

	}
	else cout << "\aYour office don't have any simple!\n\n";
}


void B_algo(Office& office)
{

}
void Backjumping(Office & office)
{

	office.seperateMeetings();

	int sum = 0, roomIndex = 0, day = 1;
	Time time = office.officeTimings.opening;

	//****************************
	//* Meetings with projectors *
	//****************************

	if (office.RWP.size() > 0)
	{
		for (int i = 0; i < office.MWP.size(); i++)
			sum += office.MWP[i].duration.getMinutes();

		for (int k = 0; k < office.MWP.size(); k++)
		{
			if (!office.MWP[k].roomAllocated)
			{
				if (roomIndex >= office.RWP.size())
					roomIndex = 0, day++, office.FreeAllRooms();

				time = office.RWP[roomIndex].freeTime.addDuration(office.MWP[k].duration);

				if (time.getMinutes() > office.officeTimings.closing.getMinutes())
				{
					roomIndex++;
					if (roomIndex >= office.RWP.size())
						roomIndex = 0, day++, office.FreeAllRooms();
				}



				office.MWP[k].roomAllocated = true;
				office.MWP[k].room = office.RWP[roomIndex];

				office.MWP[k].time = office.RWP[roomIndex].freeTime;
				office.RWP[roomIndex].freeTime = office.RWP[roomIndex].freeTime.addDuration(office.MWP[k].duration);
				office.MWP[k].day = day;
			}
		}
	}
	else cout << "\aYour office don't have any room with projector!\n\n";

	//*******************************
	//* Meetings without projectors *
	//*******************************

	sum = 0, roomIndex = 0, day = 1;
	time = office.officeTimings.opening;

	if (office.RWOP.size() > 0)
	{
		for (int i = 0; i < office.MWOP.size(); i++)
			sum += office.MWOP[i].duration.getMinutes();

		for (int k = 0; k < office.MWOP.size(); k++)
		{
			if (!office.MWOP[k].roomAllocated)
			{
				if (roomIndex >= office.RWOP.size())
					roomIndex = 0, day++, office.FreeAllRooms();

				time = office.RWOP[roomIndex].freeTime.addDuration(office.MWOP[k].duration);

				if (time.getMinutes() > office.officeTimings.closing.getMinutes())
				{
					roomIndex++;
					if (roomIndex >= office.RWOP.size())
						roomIndex = 0, day++, office.FreeAllRooms();
				}

				office.MWOP[k].roomAllocated = true;
				office.MWOP[k].room = office.RWOP[roomIndex];

				office.MWOP[k].time = office.RWOP[roomIndex].freeTime;
				office.RWOP[roomIndex].freeTime = office.RWOP[roomIndex].freeTime.addDuration(office.MWOP[k].duration);
				office.MWOP[k].day = day;
			}
		}
	}
	else cout << "\aYour office don't have any simple!\n\n";
}
void ForwardChecking(Office & office)
{
	office.seperateMeetings();

	int sum = 0, roomIndex = 0, day = 1;
	Time time = office.officeTimings.opening;

	if (office.RWP.size() > 0)
	{
		for (int i = 0; i < office.MWP.size(); i++)
			sum += office.MWP[i].duration.getMinutes();

		for (int k = 0; k < office.MWP.size(); k++)
		{
			if (!office.MWP[k].roomAllocated)
			{
				if (roomIndex >= office.RWP.size())
					roomIndex = 0, day++, office.FreeAllRooms();

				time = office.RWP[roomIndex].freeTime.addDuration(office.MWP[k].duration);

				if (time.getMinutes() > office.officeTimings.closing.getMinutes())
				{
					roomIndex++;
					if (roomIndex >= office.RWP.size())
						roomIndex = 0, day++, office.FreeAllRooms();
				}



				office.MWP[k].roomAllocated = true;
				office.MWP[k].room = office.RWP[roomIndex];

				office.MWP[k].time = office.RWP[roomIndex].freeTime;
				office.RWP[roomIndex].freeTime = office.RWP[roomIndex].freeTime.addDuration(office.MWP[k].duration);
				office.MWP[k].day = day;
			}
		}
	}
	else cout << "\aYour office don't have any room with projector!\n\n";
	sum = 0, roomIndex = 0, day = 1;
	time = office.officeTimings.opening;

	if (office.RWOP.size() > 0)
	{
		for (int i = 0; i < office.MWOP.size(); i++)
			sum += office.MWOP[i].duration.getMinutes();  

		for (int k = 0; k < office.MWOP.size(); k++)
		{
			if (!office.MWOP[k].roomAllocated)
			{
				if (roomIndex >= office.RWOP.size())
					roomIndex = 0, day++, office.FreeAllRooms();

				time = office.RWOP[roomIndex].freeTime.addDuration(office.MWOP[k].duration);

				if (time.getMinutes() > office.officeTimings.closing.getMinutes())
				{
					roomIndex++;
					if (roomIndex >= office.RWOP.size())
						roomIndex = 0, day++, office.FreeAllRooms();
				}

				office.MWOP[k].roomAllocated = true;
				office.MWOP[k].room = office.RWOP[roomIndex];

				office.MWOP[k].time = office.RWOP[roomIndex].freeTime;
				office.RWOP[roomIndex].freeTime = office.RWOP[roomIndex].freeTime.addDuration(office.MWOP[k].duration);
				office.MWOP[k].day = day;
			}
		}


	}
	else cout << "\aYour office don't have any simple!\n\n";
}