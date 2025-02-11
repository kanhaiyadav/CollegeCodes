#include <iostream>
#include <cmath>
using namespace std;

class Shape {
public:
    virtual double area() = 0;
    virtual double volume() = 0;
};

class Rectangle : public Shape {
    double length, width;
public:
    Rectangle(double l, double w) : length(l), width(w) {}
    double area() override { return length * width; }
    double volume() override { return 0; }
};

class Square : public Shape {
    double side;
public:
    Square(double s) : side(s) {}
    double area() override { return side * side; }
    double volume() override { return 0; }
};

class Cylinder : public Shape {
    double radius, height;
public:
    Cylinder(double r, double h) : radius(r), height(h) {}
    double area() override { return 2 * M_PI * radius * (radius + height); }
    double volume() override { return M_PI * radius * radius * height; }
};

int main() {
    Rectangle rect(10, 5);
    cout << "Rectangle Area: " << rect.area() << endl;
    return 0;
}
