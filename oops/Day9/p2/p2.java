import java.util.Random;

class MyClass extends Thread {
    private int counter = 0;
    private int priorityIncrement;
    private long lastPriorityUpdateTime;
    
    public MyClass(String name, int priorityIncrement) {
        super(name);
        this.priorityIncrement = priorityIncrement;
        // Set random priority between 1 and 5
        setPriority(new Random().nextInt(5) + 1);
        this.lastPriorityUpdateTime = System.currentTimeMillis();
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {  // Run for 10 cycles
                // Update counter
                counter++;
                
                // Print thread information
                System.out.println(getName() + " - Priority: " + getPriority() + 
                                  ", Counter: " + counter);
                
                // Check if it's time to increase priority (every 30ms)
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastPriorityUpdateTime >= 30) {
                    // Increase priority but don't exceed max
                    setPriority(Math.min(getPriority() + priorityIncrement, MAX_PRIORITY));
                    lastPriorityUpdateTime = currentTime;
                    System.out.println(getName() + " → Priority increased to: " + getPriority());
                }
                
                // Sleep for 10ms
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted.");
        }
    }
}

public class p2 {
    public static void main(String[] args) throws InterruptedException {
        // Create 4 threads with priority increments of 1, 2, 2, 1
        MyClass[] threads = {
            new MyClass("Thread-1", 1),
            new MyClass("Thread-2", 2),
            new MyClass("Thread-3", 2),
            new MyClass("Thread-4", 1)
        };
        
        // Print initial priorities
        System.out.println("Starting threads with random priorities:");
        for (MyClass t : threads) {
            System.out.println(t.getName() + " initial priority: " + t.getPriority() + 
                              " (increment: " + (t.getName().equals("Thread-1") || 
                                              t.getName().equals("Thread-4") ? "1" : "2") + ")");
            t.start();
        }
        
        // Wait for all threads to complete
        for (MyClass t : threads) {
            t.join();
        }
        
        System.out.println("All threads have completed.");
    }
}