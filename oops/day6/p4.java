class Time {
    private int hour, minute, second;

    // Constructor to initialize time values
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // Method to add two Time objects and print the result
    public void add(Time t) {
        int totalSec = this.second + t.second;
        int totalMin = this.minute + t.minute + (totalSec / 60);
        int totalHour = this.hour + t.hour + (totalMin / 60);

        // Normalize time values
        totalSec %= 60;
        totalMin %= 60;
        totalHour %= 24; // Keeping time within 24-hour format

        System.out.println("Added Time: " + totalHour + "h " + totalMin + "m " + totalSec + "s");
    }
}

// Main class to test the Time class
public class p4 {
    public static void main(String[] args) {
        // Create two Time objects with different values
        Time time1 = new Time(2, 45, 50);
        Time time2 = new Time(1, 30, 20);

        // Add time1 and time2
        time1.add(time2);
    }
}
