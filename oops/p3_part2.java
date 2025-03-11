// Shape implementation using Inheritance
public class Shape {
    public double area() {
        return 0.0; // Default implementation
    }
    
    public double perimeter() {
        return 0.0; // Default implementation
    }
}

// Square class inherits from Shape
class Square extends Shape {
    private double side;
    
    public Square(double side) {
        setSide(side);
    }
    
    public void setSide(double side) {
        if (side > 0) {
            this.side = side;
        }
    }
    
    public double getSide() {
        return side;
    }
    
    @Override
    public double area() {
        return side * side;
    }
    
    @Override
    public double perimeter() {
        return 4 * side;
    }
}

// Rectangle class inherits from Shape
class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(double length, double width) {
        setLength(length);
        setWidth(width);
    }
    
    public void setLength(double length) {
        if (length > 0) {
            this.length = length;
        }
    }
    
    public double getLength() {
        return length;
    }
    
    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        }
    }
    
    public double getWidth() {
        return width;
    }
    
    @Override
    public double area() {
        return length * width;
    }
    
    @Override
    public double perimeter() {
        return 2 * (length + width);
    }
}

// Circle class inherits from Shape
class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        setRadius(radius);
    }
    
    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        }
    }
    
    public double getRadius() {
        return radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

// Main class for testing inheritance implementation
class ShapeTest {
    public static void main(String[] args) {
        // Create a square with side 5
        Square square = new Square(5.0);
        System.out.println("Square with side " + square.getSide());
        System.out.println("Area: " + square.area());
        System.out.println("Perimeter: " + square.perimeter());
        
        // Create a rectangle with length 4 and width 6
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        System.out.println("\nRectangle with length " + rectangle.getLength() + 
                           " and width " + rectangle.getWidth());
        System.out.println("Area: " + rectangle.area());
        System.out.println("Perimeter: " + rectangle.perimeter());
        
        // Create a circle with radius 3
        Circle circle = new Circle(3.0);
        System.out.println("\nCircle with radius " + circle.getRadius());
        System.out.println("Area: " + circle.area());
        System.out.println("Perimeter: " + circle.perimeter());
    }
}
