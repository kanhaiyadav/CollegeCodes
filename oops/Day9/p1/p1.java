import java.util.Scanner;

class MyThread implements Runnable {
    private String threadName;
    
    public MyThread(String name) {
        this.threadName = name;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + " with priority " + 
                                  Thread.currentThread().getPriority() + 
                                  " displays: " + i);
                Thread.sleep(2000); // Sleep for 2 seconds
            }
        } catch (InterruptedException e) {
            System.out.println(threadName + " was interrupted.");
        }
    }
}

public class p1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("This program demonstrates threads with different priorities.");
        System.out.print("Do you want to run the demonstration? (y/n): ");
        String userInput = scanner.nextLine().trim().toLowerCase();
        
        if (userInput.equals("y") || userInput.equals("yes")) {
            // Create three MyThread objects
            MyThread minPriorityTask = new MyThread("Min-Priority Thread");
            MyThread avgPriorityTask = new MyThread("Avg-Priority Thread");
            MyThread maxPriorityTask = new MyThread("Max-Priority Thread");
            
            // Create threads with the MyThread objects
            Thread minThread = new Thread(minPriorityTask, "Min-Thread");
            Thread avgThread = new Thread(avgPriorityTask, "Avg-Thread");
            Thread maxThread = new Thread(maxPriorityTask, "Max-Thread");
            
            // Set priorities
            minThread.setPriority(Thread.MIN_PRIORITY);     // Priority 1
            avgThread.setPriority(Thread.NORM_PRIORITY);    // Priority 5
            maxThread.setPriority(Thread.MAX_PRIORITY);     // Priority 10
            
            // Display thread priorities
            System.out.println("\nThread priorities:");
            System.out.println("Min-Priority Thread: " + minThread.getPriority());
            System.out.println("Avg-Priority Thread: " + avgThread.getPriority());
            System.out.println("Max-Priority Thread: " + maxThread.getPriority());
            
            System.out.println("\nStarting threads...\n");
            
            // Start threads
            minThread.start();
            avgThread.start();
            maxThread.start();
            
            // Wait for threads to finish
            try {
                minThread.join();
                avgThread.join();
                maxThread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }
            
            System.out.println("\nAll threads have completed.");
        } else {
            System.out.println("Demonstration cancelled.");
        }
        
        scanner.close();
    }
}