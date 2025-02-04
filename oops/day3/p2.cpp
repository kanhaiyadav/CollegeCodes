/*Question: Write a program to create a class Vector with data member *arr,
where arr is a pointer to a vector. Use dynamic memory allocation for
this vector. Implement Assignment Operator ( = ) overloading to
assign one vector into another.*/

#include <iostream>
using namespace std;

class Vector
{
private:
    int *arr;
    int size;

public:
    Vector(int s) : size(s)
    {
        arr = new int[size];
    }

    ~Vector()
    {
        delete[] arr;
    }

    Vector &operator=(const Vector &other)
    {
        if (this == &other)
        {
            return *this;
        }

        delete[] arr;

        size = other.size;
        arr = new int[size];
        for (int i = 0; i < size; i++)
        {
            arr[i] = other.arr[i];
        }

        return *this;
    }

    void setValues()
    {
        cout << "Enter " << size << " elements: ";
        for (int i = 0; i < size; i++)
        {
            cin >> arr[i];
        }
    }

    void display() const
    {
        for (int i = 0; i < size; i++)
        {
            cout << arr[i] << " ";
        }
        cout << endl;
    }
};

int main()
{
    int size1, size2;
    cout << "Enter the size of the first vector: ";
    cin >> size1;

    Vector v1(size1);
    v1.setValues();

    cout << "Enter the size of the second vector: ";
    cin >> size2;


    Vector v2(size2);
    v2.setValues();

    cout << "First vector ";
    v1.display();

    cout << "Second vector ";
    v2.display();

    v2 = v1;

    cout << "After coping first vector to second vector: ";
    v2.display();

    return 0;
}
