public class p2 {
    public static void main(String[] args) {
        // Check if exactly 3 arguments are passed
        if (args.length != 3) {
            System.out.println("Error: Please provide exactly 3 arguments - <size> <initial value> <increment>");
            return;
        }

        try {
            // Parse command-line arguments
            int size = Integer.parseInt(args[0]); // Array size
            int initialValue = Integer.parseInt(args[1]); // Starting value
            int increment = Integer.parseInt(args[2]); // Increment value

            // Validate size
            if (size <= 0) {
                System.out.println("Error: Array size must be greater than zero.");
                return;
            }

            // Initialize array
            int[] array = new int[size];

            // Populate array
            for (int i = 0; i < size; i++) {
                array[i] = initialValue + (i * increment);
            }

            // Print the array
            System.out.println("Generated Array:");
            for (int num : array) {
                System.out.print(num + " ");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid integer values.");
        }
    }
}
