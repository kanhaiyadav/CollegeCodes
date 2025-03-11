import java.util.Scanner;

// Parent class Member
class Member {
    // Data members
    protected String name;
    protected int age;
    protected String phoneNumber;
    protected String address;
    protected double salary;
    
    // Static method to initialize Member data
    public static Member initializeMember(Scanner scanner) {
        Member member = new Member();
        
        System.out.print("Enter name: ");
        member.name = scanner.nextLine();
        
        System.out.print("Enter age: ");
        member.age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter phone number: ");
        member.phoneNumber = scanner.nextLine();
        
        System.out.print("Enter address: ");
        member.address = scanner.nextLine();
        
        System.out.print("Enter salary: ");
        member.salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        return member;
    }
    
    // Method to print salary
    public void printSalary() {
        System.out.println("Salary: " + salary);
    }
    
    // Method to display member details
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        printSalary();
    }
}

// Employee class inheriting from Member
class Employee extends Member {
    private String specialization;
    
    // Method to set specialization
    public void setSpecialization(String spec) {
        specialization = spec;
    }
    
    // Override display details to include specialization
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Specialization: " + specialization);
    }
}

// Manager class inheriting from Member
class Manager extends Member {
    private String department;
    
    // Method to set department
    public void setDepartment(String dept) {
        department = dept;
    }
    
    // Override display details to include department
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Department: " + department);
    }
}

// Main class to test the implementation
public class MemberTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Create and initialize Employee
            System.out.println("Enter Employee details:");
            System.out.println("----------------------");
            Employee employee = new Employee();
            Member tempMember = Member.initializeMember(scanner);
            
            // Copy values from tempMember to employee
            employee.name = tempMember.name;
            employee.age = tempMember.age;
            employee.phoneNumber = tempMember.phoneNumber;
            employee.address = tempMember.address;
            employee.salary = tempMember.salary;
            
            System.out.print("Enter specialization: ");
            String specialization = scanner.nextLine();
            employee.setSpecialization(specialization);
            
            // Create and initialize Manager
            System.out.println("\nEnter Manager details:");
            System.out.println("---------------------");
            Manager manager = new Manager();
            tempMember = Member.initializeMember(scanner);
            
            // Copy values from tempMember to manager
            manager.name = tempMember.name;
            manager.age = tempMember.age;
            manager.phoneNumber = tempMember.phoneNumber;
            manager.address = tempMember.address;
            manager.salary = tempMember.salary;
            
            System.out.print("Enter department: ");
            String department = scanner.nextLine();
            manager.setDepartment(department);
            
            // Display Employee details
            System.out.println("\nEmployee Details:");
            System.out.println("----------------");
            employee.displayDetails();
            
            // Display Manager details
            System.out.println("\nManager Details:");
            System.out.println("---------------");
            manager.displayDetails();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
