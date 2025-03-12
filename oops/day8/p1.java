// Abstract ThreeDObject class
abstract class ThreeDObject {
    // Abstract methods to be implemented by subclasses
    public abstract double surfaceArea();
    public abstract double volume();
}

// Box class - a rectangular prism
class Box extends ThreeDObject {
    private double length;
    private double width;
    private double height;
    
    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double surfaceArea() {
        return 2 * (length * width + length * height + width * height);
    }
    
    @Override
    public double volume() {
        return length * width * height;
    }
}

// Cube class - a special case of Box where all sides are equal
class Cube extends ThreeDObject {
    private double side;
    
    public Cube(double side) {
        this.side = side;
    }
    
    @Override
    public double surfaceArea() {
        return 6 * side * side;
    }
    
    @Override
    public double volume() {
        return side * side * side;
    }
}

// Cylinder class
class Cylinder extends ThreeDObject {
    private double radius;
    private double height;
    
    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }
    
    @Override
    public double surfaceArea() {
        return 2 * Math.PI * radius * (radius + height);
    }
    
    @Override
    public double volume() {
        return Math.PI * radius * radius * height;
    }
}

// Cone class
class Cone extends ThreeDObject {
    private double radius;
    private double height;
    
    public Cone(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }
    
    @Override
    public double surfaceArea() {
        // Surface area = π × radius × (radius + slant height)
        // Slant height = √(radius² + height²)
        double slantHeight = Math.sqrt(radius * radius + height * height);
        return Math.PI * radius * (radius + slantHeight);
    }
    
    @Override
    public double volume() {
        return (1.0/3.0) * Math.PI * radius * radius * height;
    }
}

// Main class to test the implementations
public class Main {
    public static void main(String[] args) {
        // Create objects of different shapes
        Cube cube = new Cube(5.0);
        Cylinder cylinder = new Cylinder(3.0, 7.0);
        Cone cone = new Cone(4.0, 6.0);
        
        // Print surface area and volume of each shape
        System.out.println("Cube (side = 5.0):");
        System.out.println("Surface Area: " + cube.surfaceArea());
        System.out.println("Volume: " + cube.volume());
        
        System.out.println("\nCylinder (radius = 3.0, height = 7.0):");
        System.out.println("Surface Area: " + cylinder.surfaceArea());
        System.out.println("Volume: " + cylinder.volume());
        
        System.out.println("\nCone (radius = 4.0, height = 6.0):");
        System.out.println("Surface Area: " + cone.surfaceArea());
        System.out.println("Volume: " + cone.volume());
    }
}
