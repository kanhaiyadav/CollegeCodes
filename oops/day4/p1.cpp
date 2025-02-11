#include <iostream>
using namespace std;

class Student
{
protected:
    int roll_no;
    string branch;
    string subject;

public:
    void setStudentDetails(int r, string b, string s)
    {
        roll_no = r;
        branch = b;
        subject = s;
    }

    void displayStudentDetails()
    {
        cout << "Roll No: " << roll_no << "\nBranch: " << branch << "\nSubject: " << subject << endl;
    }
};

class InternalMarks : virtual public Student
{
protected:
    int attendance;
    int performance;
    int labCopy;

public:
    void setInternalMarks(int a, int p, int l)
    {
        attendance = a;
        performance = p;
        labCopy = l;
    }

    int calculateInternalMarks()
    {
        return attendance + performance + labCopy;
    }
};

class ExternalMarks : virtual public Student
{
protected:
    int labExam;
    int viva;

public:
    void setExternalMarks(int le, int v)
    {
        labExam = le;
        viva = v;
    }

    int calculateExternalMarks()
    {
        return labExam + viva;
    }
};

class Result : public InternalMarks, public ExternalMarks
{
public:
    void displayResult()
    {
        int internalMarks = calculateInternalMarks();
        int externalMarks = calculateExternalMarks();
        int totalMarks = internalMarks + externalMarks;

        displayStudentDetails();
        cout << "Internal Marks: " << internalMarks << endl;
        cout << "External Marks: " << externalMarks << endl;
        cout << "Total Marks: " << totalMarks << endl;
    }
};

int main()
{
    int roll, att, per, lab, le, v;
    string br, sub;
    cout<<"Enter student details(roll no, branch name and subject):  ";
    cin>>roll>>br>>sub;
    cout<<"Enter internal marks(attendance, performance and lab copy):  ";
    cin>>att>>per>>lab;
    cout<<"Enter external marks(lab exam and viva):  ";
    cin>>le>>v;
    cout << endl;

    Result student1;
    student1.setStudentDetails(roll, br, sub);
    student1.setInternalMarks(att, per, lab);
    student1.setExternalMarks(le, v);

    student1.displayResult();

    return 0;
}
