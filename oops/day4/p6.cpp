#include <iostream>
#include <fstream>
using namespace std;

// Namespace for tracking test duration
namespace Exam {
    class TIME {
        int start, end;
    public:
        TIME(int s = 0, int e = 0) : start(s), end(e) {}

        void inputTime() {
            cout << "Enter start time (in minutes from midnight): ";
            cin >> start;
            cout << "Enter end time (in minutes from midnight): ";
            cin >> end;
        }

        void displayDuration() {
            cout << "Duration: " << (end - start) << " minutes" << endl;
        }

        // Save to file
        void saveToFile(const string& filename) {
            ofstream file(filename, ios::app);
            if (file.is_open()) {
                file << start << " " << end << " " << (end - start) << "\n";
                file.close();
            } else {
                cout << "Error opening file!" << endl;
            }
        }

        friend class ExamTracker;
    };

    class ExamTracker {
    public:
        static void displayAllRecords(const string& filename) {
            ifstream file(filename);
            if (!file.is_open()) {
                cout << "No records found!" << endl;
                return;
            }
            int start, end, duration;
            cout << "\nExam Records:\n";
            while (file >> start >> end >> duration) {
                cout << "Start: " << start << ", End: " << end << ", Duration: " << duration << " minutes\n";
            }
            file.close();
        }
    };
}

int main() {
    Exam::TIME t;
    t.inputTime();
    t.displayDuration();
    
    string filename = "exam_records.txt";
    t.saveToFile(filename);

    cout << "\nDisplaying all exam durations:\n";
    Exam::ExamTracker::displayAllRecords(filename);

    return 0;
}
