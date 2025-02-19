// time.cpp
#include <iostream>
#include <string>
#include <vector>

namespace ExamSystem {
    class ExamAnalyzer;  // Forward declaration
    
    class TIME {
        private:
            int hours;
            int minutes;
            int seconds;
            
        public:
            TIME(int h = 0, int m = 0, int s = 0);
            void setTime(int h, int m, int s);
            void displayTime() const;
            TIME operator-(const TIME& other) const;
            
            friend class ExamAnalyzer;
    };

    // Implement TIME methods
    TIME::TIME(int h, int m, int s) : hours(h), minutes(m), seconds(s) {}
    
    void TIME::setTime(int h, int m, int s) {
        hours = h;
        minutes = m;
        seconds = s;
    }
    
    void TIME::displayTime() const {
        std::cout << hours << " hours, " 
                  << minutes << " minutes, " 
                  << seconds << " seconds" << std::endl;
    }
    
    TIME TIME::operator-(const TIME& other) const {
        int totalSeconds1 = hours * 3600 + minutes * 60 + seconds;
        int totalSeconds2 = other.hours * 3600 + other.minutes * 60 + other.seconds;
        int diffSeconds = totalSeconds1 - totalSeconds2;
        if(diffSeconds < 0) diffSeconds = -diffSeconds;
        
        TIME result;
        result.hours = diffSeconds / 3600;
        diffSeconds %= 3600;
        result.minutes = diffSeconds / 60;
        result.seconds = diffSeconds % 60;
        return result;
    }
}

// main.cpp
using namespace ExamSystem;

class ExamAnalyzer {
public:
    static void analyzeExamDuration(const std::string& studentId, const TIME& duration) {
        std::cout << "Analysis for student " << studentId << ":\n";
        std::cout << "Total seconds taken: " 
                  << (duration.hours * 3600 + duration.minutes * 60 + duration.seconds)
                  << std::endl;
        
        if (duration.hours >= 3) {
            std::cout << "Warning: Student took more than 3 hours!\n";
        } else if (duration.hours < 1) {
            std::cout << "Note: Student completed quickly (under 1 hour)\n";
        }
    }
};

int main() {
    // Create and use TIME objects
    TIME start1(10, 0, 0);
    TIME end1(12, 30, 15);
    
    std::cout << "Start time: ";
    start1.displayTime();
    std::cout << "End time: ";
    end1.displayTime();
    
    TIME duration = end1 - start1;
    std::cout << "Duration: ";
    duration.displayTime();
    
    // Use friend class
    ExamAnalyzer::analyzeExamDuration("CAND001", duration);
    
    return 0;
}
