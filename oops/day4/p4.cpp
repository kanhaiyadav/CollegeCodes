#include <iostream>
#include <cmath>
using namespace std;

template <typename T>
class SquareRoot {
public:
    static T calculate(T num) {
        if (num < 0) throw "Negative number error";
        return sqrt(num);
    }
};

int main() {
    try {
        cout << "Square Root: " << SquareRoot<int>::calculate(25) << endl;
    } catch (...) {
        cout << "Invalid input!" << endl;
    }
    return 0;
}
