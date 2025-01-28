#include <iostream>
#include <string>
using namespace std;

class Student;

class Teacher
{
public:


    void assignID(Student &student, const string &newID);

    void generateGrade(Student &student, int marks);
};

class Student
{
private:
    string studentID;
    char grade;

public:

    void ID_details()
    {
        if (studentID.empty())
            cout << "Student ID: Not assigned yet." << endl;
        else
            cout << "Student ID: " << studentID << endl;
    }

    void displayGrade()
    {
        if (grade == '\0')
            cout << "Grade: Not assigned yet." << endl;
        else
            cout << "Grade: " << grade << endl;
    }

    friend void Teacher::assignID(Student &student, const string &newID);
    friend void Teacher::generateGrade(Student &student, int marks);
};

void Teacher::assignID(Student &student, const string &newID)
{
    student.studentID = newID;
}

void Teacher::generateGrade(Student &student, int marks)
{
    if (marks >= 90)
        student.grade = 'A';
    else if (marks >= 75)
        student.grade = 'B';
    else if (marks >= 60)
        student.grade = 'C';
    else if (marks >= 50)
        student.grade = 'D';
    else
        student.grade = 'F';
}

int main()
{
    Student student1;
    Teacher teacher;

    student1.ID_details();
    student1.displayGrade();

    string id;
    float marks;

    cout << "Enter the student ID and marks: ";
    cin >> id >> marks;
    
    teacher.assignID(student1, id);
    teacher.generateGrade(student1, marks);

    student1.ID_details();
    student1.displayGrade();

    return 0;
}
