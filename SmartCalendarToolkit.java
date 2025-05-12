import java.util.Scanner;

public class SmartCalendarToolkit {

    // Check if a year is a leap year
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Get the number of days in a given month and year
    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4: case 6: case 9: case 11:
                return 30;
            default:
                return 31;
        }
    }

    // Get day of the week using Zeller's Congruence
    public static String getDayOfWeek(int day, int month, int year) {
        if (month == 1 || month == 2) {
            month += 12;
            year -= 1;
        }

        int q = day;
        int m = month;
        int k = year % 100;
        int j = year / 100;

        int h = (q + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) + (5 * j)) % 7;

        String[] days = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
        return days[h];
    }

    // Check if the given date is a weekend
    public static boolean isWeekend(int day, int month, int year) {
        String dow = getDayOfWeek(day, month, year);
        return dow.equals("Saturday") || dow.equals("Sunday");
    }

    // Compare two dates in dd-mm-yyyy format
    public static String compareDates(String date1, String date2) {
        String[] parts1 = date1.split("-");
        String[] parts2 = date2.split("-");

        int d1 = Integer.parseInt(parts1[0]);
        int m1 = Integer.parseInt(parts1[1]);
        int y1 = Integer.parseInt(parts1[2]);

        int d2 = Integer.parseInt(parts2[0]);
        int m2 = Integer.parseInt(parts2[1]);
        int y2 = Integer.parseInt(parts2[2]);

        if (y1 == y2 && m1 == m2 && d1 == d2)
            return "Both dates are the same";
        else if (y1 < y2 || (y1 == y2 && m1 < m2) || (y1 == y2 && m1 == m2 && d1 < d2))
            return "Date1 is earlier";
        else
            return "Date2 is earlier";
    }

    // Main method with user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leap year check
        System.out.print("Enter a year to check if it's a leap year: ");
        int year = scanner.nextInt();
        System.out.println("Leap year? " + isLeapYear(year));

        // Days in month
        System.out.print("Enter month and year to get days in month (e.g., 2 2024): ");
        int month = scanner.nextInt();
        int year2 = scanner.nextInt();
        System.out.println("Days in month: " + getDaysInMonth(month, year2));

        // Day of the week
        System.out.print("Enter a date (day month year) to find the day of the week: ");
        int day = scanner.nextInt();
        int mon = scanner.nextInt();
        int yr = scanner.nextInt();
        String dayOfWeek = getDayOfWeek(day, mon, yr);
        System.out.println("Day of the week: " + dayOfWeek);

        // Weekend check
        System.out.println("Is weekend? " + isWeekend(day, mon, yr));

        // Date comparison
        scanner.nextLine(); // Consume newline
        System.out.print("Enter first date (dd-mm-yyyy): ");
        String date1 = scanner.nextLine();
        System.out.print("Enter second date (dd-mm-yyyy): ");
        String date2 = scanner.nextLine();
        System.out.println("Date comparison: " + compareDates(date1, date2));

        scanner.close();
    }
}
