#include <iostream>
using namespace std;

class Time
{
private:
    int hours, minutes, seconds;

public:

    Time(int h = 0, int m = 0, int s = 0) : hours(h), minutes(m), seconds(s) {}

    void displayTime() const
    {
        cout << hours << " : " << minutes << " : " << seconds << "\n";
    }

    Time operator+(const Time &t) const
    {
        Time result;
        result.seconds = seconds + t.seconds;
        result.minutes = minutes + t.minutes + (result.seconds / 60);
        result.hours = hours + t.hours + (result.minutes / 60);

        result.seconds %= 60;
        result.minutes %= 60;

        return result;
    }
};

int main()
{
    int h1, m1, s1, h2, m2, s2;

    cout << "Enter the hours, minutes and seconds of the first time: ";
    cin >> h1 >> m1 >> s1;
    cout << "Enter the hours, minutes and seconds of the second time: ";
    cin >> h2 >> m2 >> s2;

    Time t1(h1, m1, s1), t2(h2, m2, s2);

    cout << "First time: ";
    t1.displayTime();

    cout << "Second time: ";
    t2.displayTime();

    Time t3 = t1 + t2;

    cout << "Result of addition: ";
    t3.displayTime();

    return 0;
}
