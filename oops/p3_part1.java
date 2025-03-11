// Shape implementation using Method Overloading
public class Shape {
    // Method overloading for calculating area
    public double area(double side) {
        // Area of a square
        return side * side;
    }
    
    public double area(double length, double width) {
        // Area of a rectangle
        return length * width;
    }
    
    public double area(double radius, boolean isCircle) {
        // Area of a circle
        if (isCircle) {
            return Math.PI * radius * radius;
        }
        return 0.0; // Default return
    }
    
    // Method overloading for calculating perimeter
    public double perimeter(double side) {
        // Perimeter of a square
        return 4 * side;
    }
    
    public double perimeter(double length, double width) {
        // Perimeter of a rectangle
        return 2 * (length + width);
    }
    
    public double perimeter(double radius, boolean isCircle) {
        // Perimeter of a circle
        if (isCircle) {
            return 2 * Math.PI * radius;
        }
        return 0.0; // Default return
    }
    
    // Main method for testing
    public static void main(String[] args) {
        Shape shape = new Shape();
        
        // Square with side 5
        double squareSide = 5.0;
        System.out.println("Square with side " + squareSide);
        System.out.println("Area: " + shape.area(squareSide));
        System.out.println("Perimeter: " + shape.perimeter(squareSide));
        
        // Rectangle with length 4 and width 6
        double rectLength = 4.0;
        double rectWidth = 6.0;
        System.out.println("\nRectangle with length " + rectLength + " and width " + rectWidth);
        System.out.println("Area: " + shape.area(rectLength, rectWidth));
        System.out.println("Perimeter: " + shape.perimeter(rectLength, rectWidth));
        
        // Circle with radius 3
        double circleRadius = 3.0;
        System.out.println("\nCircle with radius " + circleRadius);
        System.out.println("Area: " + shape.area(circleRadius, true));
        System.out.println("Perimeter: " + shape.perimeter(circleRadius, true));
    }
}
