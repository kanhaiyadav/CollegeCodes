// File: p1/Student.java
package p1;

import java.util.Scanner;

public class Student {
    protected String name;
    protected int roll_no;
    
    // Default constructor
    public Student() {
        this.name = "";
        this.roll_no = 0;
    }
    
    // Parameterized constructor
    public Student(String name, int roll_no) {
        this.name = name;
        this.roll_no = roll_no;
    }
    
    // Method to read input
    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter student name: ");
        this.name = scanner.nextLine();
        
        System.out.print("Enter roll number: ");
        this.roll_no = scanner.nextInt();
    }
    
    // Method to display output
    public void displayOutput() {
        System.out.println("\nStudent Details:");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + roll_no);
    }
}

// File: Marks.java
// This class is not in p1 package (default package)
import p1.Student;
import java.util.Scanner;

public class Marks extends Student {
    private int subject1;
    private int subject2;
    private int subject3;
    
    // Default constructor
    public Marks() {
        super();
        this.subject1 = 0;
        this.subject2 = 0;
        this.subject3 = 0;
    }
    
    // Method to read marks
    public void readMarks() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter marks for Subject 1: ");
        this.subject1 = scanner.nextInt();
        
        System.out.print("Enter marks for Subject 2: ");
        this.subject2 = scanner.nextInt();
        
        System.out.print("Enter marks for Subject 3: ");
        this.subject3 = scanner.nextInt();
    }
    
    // Override displayOutput to include marks
    @Override
    public void displayOutput() {
        super.displayOutput();
        System.out.println("Marks Details:");
        System.out.println("Subject 1: " + subject1);
        System.out.println("Subject 2: " + subject2);
        System.out.println("Subject 3: " + subject3);
    }
}

// File: Main.java
// Main class to run the program
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Marks[] students = new Marks[numStudents];
        
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for Student " + (i+1) + ":");
            students[i] = new Marks();
            students[i].readInput();
            students[i].readMarks();
        }
        
        System.out.println("\n-------- STUDENT RECORDS --------");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nStudent " + (i+1) + ":");
            students[i].displayOutput();
        }
        
        scanner.close();
    }
}
