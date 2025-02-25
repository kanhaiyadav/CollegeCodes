class Box {
    private double width, height, length;

    // Constructor 1: Default values
    public Box() {
        this.width = 1;
        this.height = 1;
        this.length = 1;
    }

    // Constructor 2: Set all dimensions
    public Box(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    // Constructor 3: Cube (Same width, height, length)
    public Box(double side) {
        this.width = this.height = this.length = side;
    }

    // Method to calculate volume
    public double volume() {
        return width * height * length;
    }

    // Method to compare two Box objects
    public boolean equals(Box b) {
        return this.width == b.width && this.height == b.height && this.length == b.length;
    }

    // Display Box details (for debugging)
    public void display() {
        System.out.println("Box [Width=" + width + ", Height=" + height + ", Length=" + length + "]");
    }
}

// Main class to test the Box class
public class p3 {
    public static void main(String[] args) {
        // Creating different Box objects using overloaded constructors
        Box box1 = new Box(3, 4, 5); // Custom dimensions
        Box box2 = new Box(3, 4, 5); // Identical to box1
        Box box3 = new Box(2);       // Cube with side 2
        Box box4 = new Box();        // Default box (1x1x1)

        // Calculating and printing volumes
        System.out.println("Volume of Box 1: " + box1.volume());
        System.out.println("Volume of Box 2: " + box2.volume());
        System.out.println("Volume of Box 3: " + box3.volume());
        System.out.println("Volume of Box 4: " + box4.volume());

        // Checking equality of boxes
        System.out.println("Is Box 1 equal to Box 2? " + box1.equals(box2)); // true
        System.out.println("Is Box 1 equal to Box 3? " + box1.equals(box3)); // false
        System.out.println("Is Box 3 equal to Box 4? " + box3.equals(box4)); // false
    }
}
