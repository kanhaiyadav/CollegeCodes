#include <iostream>
using namespace std;

class Father {
protected:
    string name;
    int age;
public:
    Father(string n, int a) : name(n), age(a) {}
    virtual void show() { cout << "Father: " << name << ", Age: " << age << endl; }
};

class Son : public Father {
private:
    string son_name;
    int son_age;
public:
    Son(string fn, int fa, string sn, int sa) : Father(fn, fa), son_name(sn), son_age(sa) {}
    void show() override { cout << "Son: " << son_name << ", Age: " << son_age << endl; }
};

int main() {
    Father* fptr;
    Son s("John", 45, "Mike", 20);
    fptr = &s;
    fptr->show();  // Displays Son's information due to virtual function
    return 0;
}
