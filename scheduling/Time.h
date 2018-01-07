#ifndef TIME_H
#define TIME_H
#include <string>
#include <iostream>

struct Time
{
	int hour, minute;
	std::string arr;

	Time(int hour = 0, int minute = 0, std::string arr = "am") { this->hour = hour, this->minute = minute, this->arr = arr; }

	Duration _24HourClock() { return Duration(arr == "am" ? hour : hour + 12, minute); }


	Time addDuration(Duration& d)
	{
		Time t = *this;

		t.minute += d.minute;

		if (t.minute > 59)
			t.minute -= 60, t.hour += 1;

		bool Bool = false;
		t.hour += d.hour;
		if (t.hour >= 24)
			Bool = true;

		if (t.arr == "am" && t.hour == 12)
		{
			t.arr = "pm";
			return t;
		}

		if (t.arr == "pm" && t.hour == 13)
		{
			t.hour -= 12;
			return t;
		}

		if (t.arr == "am")
		{
			if (t.hour > 12)
				t.hour -= 12, t.arr = "pm";
		}
		else
		{
			if (t.hour > 11)
				t.hour -= 12, t.arr = "am";
		}

		if (Bool)
			t.hour %= 12;

		return t;
	}

	int getMinutes() { return arr == "am" ? hour * 60 + minute : 12 * hour * 60 + minute; }

	void displayTime(std::string massege)
	{
		std::cout << massege << hour << ":" << minute << " " << arr << std::endl;
	}

	bool validTime()
	{
		return !(hour == 0 && minute == 0) && (hour < 13 && hour > -1 && minute > -1 && minute < 60) && (arr.size() == 2 && (arr[0] == 'a' || arr[0] == 'p') && arr[1] == 'm');
	}

	void setTime(std::string massege)
	{
		char temp;
		while (true)
		{
			std::cout << massege;
			if (std::cin >> hour >> temp >> minute >> arr)
			{
				if (this->validTime())
					break;
				else std::cout << "\aPlease Enter a valid Time\n";
			}
			else std::cout << "\aInvalid Format\n";

			std::cin.clear();
			std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
		}
	}

	bool operator==(Time const& rhs)
	{
		return hour == rhs.hour && minute == rhs.minute && arr == rhs.arr;
	}
};
#endif