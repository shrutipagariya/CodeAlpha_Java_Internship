import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Ask for number of students
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        // Arrays to store student names and grades
        String[] names = new String[n];
        double[] grades = new double[n];

        // Input student details
        for (int i = 0; i < n; i++) {

            System.out.print("Enter name of student " + (i + 1) + ": ");
            names[i] = sc.next();

            System.out.print("Enter grade of " + names[i] + ": ");
            grades[i] = sc.nextDouble();
        }

        // Initialize values
        double total = 0;
        double highest = grades[0];
        double lowest = grades[0];
        String highestStudent = names[0];
        String lowestStudent = names[0];

        // Calculate total, highest and lowest
        for (int i = 0; i < n; i++) {

            total = total + grades[i];

            if (grades[i] > highest) {
                highest = grades[i];
                highestStudent = names[i];
            }

            if (grades[i] < lowest) {
                lowest = grades[i];
                lowestStudent = names[i];
            }
        }

        // Calculate average
        double average = total / n;

        // Display summary report
        System.out.println();
        System.out.println("========== STUDENT GRADE REPORT ==========");

        for (int i = 0; i < n; i++) {
            System.out.println(names[i] + " : " + grades[i]);
        }

        System.out.println("------------------------------------------");
        System.out.println("Average Grade : " + average);
        System.out.println("Highest Grade : " + highest + " (" + highestStudent + ")");
        System.out.println("Lowest Grade  : " + lowest + " (" + lowestStudent + ")");
        System.out.println("==========================================");

        sc.close();
    }
}