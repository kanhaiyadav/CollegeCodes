// College interface
interface College {
    String CollegeName = "ABC College";
    int CollegeCode = 101;
    
    void display();
}

// Department class
class Department {
    protected String deptName;
    protected String deptHead;
    
    // Method to accept values
    public void acceptDepartmentDetails(String deptName, String deptHead) {
        this.deptName = deptName;
        this.deptHead = deptHead;
    }
    
    // Method to display department details
    public void displayDepartmentDetails() {
        System.out.println("Department Name: " + deptName);
        System.out.println("Department Head: " + deptHead);
    }
}

// Student class extending Department and implementing College
class Student extends Department implements College {
    private String studentName;
    private int regNo;
    private double avgMarks;
    
    // Constructor
    public Student(String studentName, int regNo, double avgMarks) {
        this.studentName = studentName;
        this.regNo = regNo;
        this.avgMarks = avgMarks;
    }
    
    // Implementation of the abstract method from College interface
    @Override
    public void display() {
        System.out.println("College Name: " + CollegeName);
        System.out.println("College Code: " + CollegeCode);
        displayDepartmentDetails();
        System.out.println("Student Name: " + studentName);
        System.out.println("Registration Number: " + regNo);
        System.out.println("Average Marks: " + avgMarks);
    }
}

// Driver class to test the Student class
public class CollegeManagementSystem {
    public static void main(String[] args) {
        // Create Student object
        Student student = new Student("John Doe", 12345, 85.5);
        
        // Set department details
        student.acceptDepartmentDetails("Computer Science", "Dr. Smith");
        
        // Display all details
        System.out.println("Student Details:");
        System.out.println("---------------");
        student.display();
    }
}