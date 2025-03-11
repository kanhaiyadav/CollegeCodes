import java.util.Scanner;

// Parent class with protected instance variables
class Student {
    protected int Roll_number;
    protected String Student_Name;
    protected static String College_Name = "HITK";
    
    // Constructor to initialize data members
    public Student(int rollNumber, String studentName) {
        Roll_number = rollNumber;
        Student_Name = studentName;
    }
    
    // Method to display student details
    public void showdata() {
        System.out.println("Roll Number: " + Roll_number);
        System.out.println("Student Name: " + Student_Name);
        System.out.println("College Name: " + College_Name);
    }
}

// Derived class inheriting from Student
class Exam extends Student {
    private double subject1Marks;
    private double subject2Marks;
    private double subject3Marks;
    
    // Constructor to initialize data members
    public Exam(int rollNumber, String studentName, double marks1, double marks2, double marks3) {
        // Call parent class constructor
        super(rollNumber, studentName);
        
        // Initialize Exam class data members without using "this"
        subject1Marks = marks1;
        subject2Marks = marks2;
        subject3Marks = marks3;
    }
    
    // Method to calculate average marks
    private double calculateAverage() {
        return (subject1Marks + subject2Marks + subject3Marks) / 3.0;
    }
    
    // Method to display student information along with average marks
    public void showresult() {
        // Display student details by calling parent class method
        showdata();
        
        // Display subject marks
        System.out.println("Subject 1 Marks: " + subject1Marks);
        System.out.println("Subject 2 Marks: " + subject2Marks);
        System.out.println("Subject 3 Marks: " + subject3Marks);
        
        // Calculate and display average marks
        double average = calculateAverage();
        System.out.println("Average Marks: " + average);
    }
}

// Main class to test the implementation
public class StudentExamTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Get student details from user
            System.out.println("Enter student details:");
            
            System.out.print("Roll Number: ");
            int rollNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Student Name: ");
            String studentName = scanner.nextLine();
            
            // Get marks for three subjects
            System.out.println("Enter marks for three subjects:");
            
            System.out.print("Subject 1 Marks: ");
            double subject1Marks = scanner.nextDouble();
            
            System.out.print("Subject 2 Marks: ");
            double subject2Marks = scanner.nextDouble();
            
            System.out.print("Subject 3 Marks: ");
            double subject3Marks = scanner.nextDouble();
            
            // Create Exam object with user input
            Exam student = new Exam(rollNumber, studentName, subject1Marks, subject2Marks, subject3Marks);
            
            // Display all the details of the student
            System.out.println("\nStudent Details:");
            System.out.println("----------------");
            student.showresult();
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input. " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
