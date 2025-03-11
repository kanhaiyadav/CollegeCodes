// Rectangle class with length and breadth data members
class Rectangle {
    protected double length;
    protected double breadth;
    
    // Constructor to initialize length and breadth
    public Rectangle(double l, double b) {
        length = l;
        breadth = b;
    }
    
    // Method to calculate and print area
    public void printArea() {
        double area = length * breadth;
        System.out.println("Area of Rectangle: " + area);
    }
    
    // Method to calculate and print perimeter
    public void printPerimeter() {
        double perimeter = 2 * (length + breadth);
        System.out.println("Perimeter of Rectangle: " + perimeter);
    }
}

// Square class inheriting from Rectangle
class Square extends Rectangle {
    // Constructor with a single parameter for side
    public Square(double side) {
        // Call parent class constructor with same value for length and breadth
        super(side, side);
    }
    
    // Override printArea method for Square
    @Override
    public void printArea() {
        double area = length * length;
        System.out.println("Area of Square: " + area);
    }
    
    // Override printPerimeter method for Square
    @Override
    public void printPerimeter() {
        double perimeter = 4 * length;
        System.out.println("Perimeter of Square: " + perimeter);
    }
}

// Main class to test the Rectangle and Square classes
public class RectangleSquareTest {
    public static void main(String[] args) {
        // Create a Rectangle object
        Rectangle rectangle = new Rectangle(5, 4);
        System.out.println("Rectangle with length = 5 and breadth = 4:");
        rectangle.printArea();
        rectangle.printPerimeter();
        
        System.out.println();
        
        // Create a Square object
        Square square = new Square(5);
        System.out.println("Square with side = 5:");
        square.printArea();
        square.printPerimeter();
    }
}
