#include <iostream>
#include <cstring>
using namespace std;

class MyClass
{
private:
    char *message;    
    static int count;

public:
    // Default constructor
    MyClass()
    {
        message = nullptr;
        count++;
        cout << "Default constructor called. Total objects: " << count << "\n";
    }

    // Parameterized constructor
    MyClass(const char *msg)
    {
        message = new char[strlen(msg) + 1];
        strcpy(message, msg);
        count++;
        cout << "\n*** Parameterized constructor called *** \nMessage: " << message << ". \nTotal objects: " << count << "\n";
    }

    // Destructor
    ~MyClass()
    {
        cout << "\n*** Destructor called ***";
        count--;
        if (message)
        {
            cout << "\nDeleting message: " << message << ". \nTotal objects remaining: " << count << "\n";
            delete[] message; // Free dynamically allocated memory
        }
        else
        {
            cout << "\nNo message to delete. \nTotal objects remaining: " << count << "\n";
        }
    }

    static int getObjectCount()
    {
        return count;
    }
};

int MyClass::count = 0;

MyClass globalObj("Global Object");

void objInFunctionScope()
{
    // Local automatic object
    MyClass localObj("Local Object");

}

int main()
{
    cout << "\n\n----- main function starts -----\n";

    static MyClass staticObj("Static Object");

    // Call function to create objects
    objInFunctionScope();

    // Nameless object
    MyClass("Nameless Object");

    // Display total count
    cout << "\n----- Main function ends -----\n\n\nObjects currently in existence: " << MyClass::getObjectCount() << "\n";

    return 0;
}
