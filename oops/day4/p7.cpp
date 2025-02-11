#include <iostream>
#include <algorithm>
using namespace std;

namespace ArrayOps {
    int* createArray(int size) { return new int[size]; }
    namespace Sort {
        void splitSort(int* arr, int size) {
            sort(arr, arr + size / 2);
            sort(arr + size / 2, arr + size);
        }
    }
}

int main() {
    int arr[] = {7, 3, 8, 1, 6, 2, 9, 5};
    int size = sizeof(arr) / sizeof(arr[0]);

    ArrayOps::Sort::splitSort(arr, size);
    for (int i = 0; i < size; i++) cout << arr[i] << " ";
    return 0;
}
