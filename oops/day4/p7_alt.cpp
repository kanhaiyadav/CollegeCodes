#include <iostream>
#include <algorithm>

namespace ArrayOperations {
    // Class to handle dynamic array operations
    class DynamicArray {
    private:
        int* arr;
        int size;
        
    public:
        DynamicArray(int n) : size(n) {
            arr = new int[size];
        }
        
        ~DynamicArray() {
            delete[] arr;
        }
        
        void inputArray() {
            std::cout << "Enter " << size << " numbers:\n";
            for(int i = 0; i < size; i++) {
                std::cin >> arr[i];
            }
        }
        
        int* getArray() { return arr; }
        int getSize() { return size; }
        
        namespace ArraySplitter {
            // Function to split and sort array into two subarrays
            void splitAndSort(int* sourceArr, int size, int*& firstHalf, int*& secondHalf, 
                            int& firstSize, int& secondSize) {
                // Calculate sizes for split
                firstSize = size / 2;
                secondSize = size - firstSize;
                
                // Allocate memory for subarrays
                firstHalf = new int[firstSize];
                secondHalf = new int[secondSize];
                
                // Copy elements to first half
                for(int i = 0; i < firstSize; i++) {
                    firstHalf[i] = sourceArr[i];
                }
                
                // Copy elements to second half
                for(int i = 0; i < secondSize; i++) {
                    secondHalf[i] = sourceArr[firstSize + i];
                }
                
                // Sort both halves
                std::sort(firstHalf, firstHalf + firstSize);
                std::sort(secondHalf, secondHalf + secondSize);
            }
            
            // Function to display array
            void displayArray(int* arr, int size, const std::string& arrayName) {
                std::cout << arrayName << ": ";
                for(int i = 0; i < size; i++) {
                    std::cout << arr[i] << " ";
                }
                std::cout << std::endl;
            }
        }
    };
}

int main() {
    int size;
    std::cout << "Enter the size of the array: ";
    std::cin >> size;
    
    // Create dynamic array using outer namespace
    ArrayOperations::DynamicArray dynArr(size);
    dynArr.inputArray();
    
    // Prepare for splitting
    int* firstHalf;
    int* secondHalf;
    int firstSize, secondSize;
    
    // Use inner namespace to split and sort
    using namespace ArrayOperations::DynamicArray::ArraySplitter;
    splitAndSort(dynArr.getArray(), dynArr.getSize(), 
                firstHalf, secondHalf, firstSize, secondSize);
    
    // Display results
    std::cout << "\nOriginal array ";
    displayArray(dynArr.getArray(), dynArr.getSize(), "Original");
    
    std::cout << "\nSplit and sorted arrays:\n";
    displayArray(firstHalf, firstSize, "First Half");
    displayArray(secondHalf, secondSize, "Second Half");
    
    // Clean up dynamically allocated memory
    delete[] firstHalf;
    delete[] secondHalf;
    
    return 0;
}
