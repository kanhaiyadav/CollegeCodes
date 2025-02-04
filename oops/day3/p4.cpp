/*Question 4: WAP in C++ to create a class String1 which has a character
array. a) Concatenate two strings by overloading ‘+’ operator.
b) Check equality of two strings by overloading ‘==’ operator.*/

#include <iostream>
#include <cstring>
using namespace std;

class String1
{
private:
    char *str;

public:
    String1(const char *s = "")
    {
        str = new char[strlen(s) + 1];
        strcpy(str, s);
    }

    ~String1()
    {
        delete[] str;
    }

    String1(const String1 &other)
    {
        str = new char[strlen(other.str) + 1];
        strcpy(str, other.str);
    }

    String1 operator+(const String1 &other)
    {
        int newLength = strlen(str) + strlen(other.str) + 1;
        char *newStr = new char[newLength];

        strcpy(newStr, str);
        strcat(newStr, other.str);

        String1 result(newStr);
        delete[] newStr;
        return result;
    }

    bool operator==(const String1 &other)
    {
        return strcmp(str, other.str) == 0;
    }

    void display() const
    {
        cout << str << endl;
    }
};

int main()
{
    string str1, str2;
    cout << "Enter first string: ";
    getline(cin, str1);

    cout << "Enter second string: ";
    getline(cin, str2);

    String1 s1(str1.c_str());
    String1 s2(str2.c_str());

    String1 s3 = s1 + s2;
    cout << "Concatenated String: ";
    s3.display();

    if (s1 == s2)
    {
        cout << "\033[32mStrings are equal\033[32m\n";
    }
    else
    {
        cout << "\033[31mStrings are not equal\033[0m\n";
    }

    return 0;
}
