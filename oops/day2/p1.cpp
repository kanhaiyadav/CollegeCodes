#include <iostream>
#include <string>
using namespace std;

class StringStatus
{
private:
    string str;
    int upperCaseCount;
    int vowelCount;

    void calculateStatus()
    {
        upperCaseCount = 0;
        vowelCount = 0;
        for (char ch : str)
        {
            if (isupper(ch))
            {
                upperCaseCount++;
            }
            if (tolower(ch) == 'a' || tolower(ch) == 'e' || tolower(ch) == 'i' ||
                tolower(ch) == 'o' || tolower(ch) == 'u')
            {
                vowelCount++;
            }
        }
    }

public:

    StringStatus() : str(""), upperCaseCount(0), vowelCount(0) {}

    StringStatus(const string &input) : str(input)
    {
        calculateStatus();
    }

    StringStatus(const StringStatus &other) : str(other.str), upperCaseCount(other.upperCaseCount), vowelCount(other.vowelCount)
    {
        cout << "*** Copy constructor called ***\n";
    }

    ~StringStatus()
    {
        cout << "\n*** Destructor called for string: " << str;
    }

    void displayStatus() const
    {
        cout<<"------ status ------\n";
        cout << "String: " << str << "\n";
        cout << "Number of uppercase characters: " << upperCaseCount << "\n";
        cout << "Number of vowels: " << vowelCount << "\n";
    }
};

int main()
{

    string str;
    cout << "Enter a string: " << endl;
    cin >> str;

    cout << "\n\n--------------------------------------------" << endl;
    cout << "Using default constructor" << endl;
    StringStatus str1;
    str1.displayStatus();

    cout << "\n\n--------------------------------------------" << endl;
    cout << "Using parameterized constructor" << endl;
    StringStatus str2(str);
    str2.displayStatus();

    cout << "\n\n--------------------------------------------" << endl;
    cout << "Using copy constructor" << endl;
    StringStatus str3 = str2;
    str3.displayStatus();

    return 0;
}
