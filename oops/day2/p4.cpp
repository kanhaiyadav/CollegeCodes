#include <iostream>
using namespace std;

class Complex
{
private:
    double real;
    double imag;

public:
    Complex() : real(0), imag(0) {}

    Complex(double r, double i) : real(r), imag(i) {}

    Complex operator+(const Complex &c)
    {
        return Complex(real + c.real, imag + c.imag);
    }

    Complex operator-(const Complex &c)
    {
        return Complex(real - c.real, imag - c.imag);
    }

    Complex operator*(const Complex &c)
    {
        return Complex(
            real * c.real - imag * c.imag,
            real * c.imag + imag * c.real  
        );
    }

    Complex operator/(const Complex &c)
    {
        double denominator = c.real * c.real + c.imag * c.imag;
        return Complex(
            (real * c.real + imag * c.imag) / denominator,
            (imag * c.real - real * c.imag) / denominator
        );
    }

    void display()
    {
        cout << real << " + " << imag << "i";
    }
};

int main()
{
    double r1, i1, r2, i2;
    cout << "Enter the real and imaginary parts of the first complex number: ";
    cin >> r1 >> i1;
    cout << "Enter the real and imaginary parts of the second complex number: ";
    cin >> r2 >> i2;

    Complex c1(r1, i1), c2(r2, i2);

    Complex sum = c1 + c2;
    Complex diff = c1 - c2;
    Complex prod = c1 * c2;
    Complex quot = c1 / c2;

    cout << "First complex number: ";
    c1.display();
    cout << "\nSecond complex number: ";
    c2.display();
    cout << "\nSum: ";
    sum.display();
    cout << "\nDifference: ";
    diff.display();
    cout << "\nProduct: ";
    prod.display();
    cout << "\nQuotient: ";
    quot.display();

    return 0;
}
