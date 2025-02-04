/*Question 1: Write a program to implement prefix and postfix increment and
decrement operator overloading on a data member of a class.*/

#include <iostream>
using namespace std;

class Counter
{
private:
    int value;

public:
    Counter(int v = 0) : value(v) {}

    void display() const
    {
        cout << "Value: " << value << endl;
    }

    Counter &operator++()
    {
        ++value;
        return *this;
    }

    Counter operator++(int)
    {
        Counter temp = *this;
        value++;
        return temp;
    }

    Counter &operator--()
    {
        --value;
        return *this;
    }

    Counter operator--(int)
    {
        Counter temp = *this;
        value--;
        return temp;
    }
};

int main()
{
    int n;
    cout << "Enter the initial value of the counter: ";
    cin >> n;
    Counter obj(n);

    cout << "Initial ";
    obj.display();

    cout << "\nAfter prefix increment (++obj): \n";
    (++obj).display();

    cout << "After postfix increment (obj++):\ncurrent ";
    (obj++).display();
    cout << "new ";
    obj.display();

    cout << "After prefix decrement (--obj): \n";
    (--obj).display();

    cout << "After postfix decrement (obj--):\ncurrent ";
    (obj--).display();
    cout << "new ";
    obj.display();

    return 0;
}
