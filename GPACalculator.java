import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String courseName;
    String grade;
    int creditHours;

    public Course(String courseName, String grade, int creditHours) {
        this.courseName = courseName;
        this.grade = grade;
        this.creditHours = creditHours;
    }
}

public class GPACalculator {

    static ArrayList<Course> courses = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    public static double gradeToPoints(String grade) {
        return switch (grade) {
            case "A+" -> 4.0;
            case "A" -> 3.7 ;
            case "A-" -> 3.4;
            case "B+" -> 3.2;
            case "B" -> 3.0;
            case "B-" -> 2.8;
            case "C+" -> 2.6;
            case "C" -> 2.4;
            case "C-" -> 2.2;
            case "D+" -> 2.0;
            case "D" -> 1.5;
            case "D-" -> 1.0;
            case "F" -> 0.0;
            default -> -1;

        };
    }

    public static boolean isValidGrade(String grade) {
        String[] validGrades = {"A+", "A", "A-", "B+", "B", "B-",
                "C+", "C", "C-", "D+", "D", "D-", "F"};
        for (String g : validGrades) {
            if (g.equals(grade)) {
                return true;
            }
        }
        return false;
    }

    public static boolean courseExists(String courseName) {
        for (Course c : courses) {
            if (c.courseName.equalsIgnoreCase(courseName)) {
                return true;
            }
        }
        return false;
    }

    public static void calculateGPA() {
        if (courses.isEmpty()) {
            System.out.println("--------------------------------------------");
            System.out.println("  No courses added yet. Cannot calculate GPA.");
            System.out.println("--------------------------------------------");
            return;
        }

        double totalPoints = 0;
        int totalCredits = 0;

        for (Course c : courses) {
            double points = gradeToPoints(c.grade);
            totalPoints += points * c.creditHours;
            totalCredits += c.creditHours;
        }

        double gpa = totalPoints / totalCredits;

        System.out.println("============================================");
        System.out.println("              GPA RESULT                    ");
        System.out.println("============================================");
        System.out.printf("  Total Credit Hours : %d%n", totalCredits);
        System.out.printf("  Your GPA           : %.2f / 4.00%n", gpa);

        if (gpa >= 3.5) {
            System.out.println("  Status             : Excellent (A)");
        } else if (gpa >= 3.0) {
            System.out.println("  Status             : Very Good (B+)");
        } else if (gpa >= 2.5) {
            System.out.println("  Status             : Good (B)");
        } else if (gpa >= 2.0) {
            System.out.println("  Status             : Pass");
        } else {
            System.out.println("  Status             : Below Average");
        }

        System.out.println("============================================");
    }

    public static void displayCourses() {
        if (courses.isEmpty()) {
            System.out.println("--------------------------------------------");
            System.out.println("  No courses added yet.");
            System.out.println("--------------------------------------------");
            return;
        }

        System.out.println("============================================");
        System.out.println("           YOUR COURSES LIST                ");
        System.out.println("============================================");
        System.out.printf("  %-20s %-6s %-8s%n", "Course Name", "Grade", "Credits");
        System.out.println("  ------------------------------------------");

        for (Course c : courses) {
            System.out.printf("  %-20s %-6s %-8d%n", c.courseName, c.grade, c.creditHours);
        }

        System.out.println("============================================");
    }

    public static void addCourse() {
        System.out.println("--------------------------------------------");
        System.out.println("            ADD NEW COURSE                  ");
        System.out.println("--------------------------------------------");

        String courseName ;
        while (true) {
            System.out.print("  Enter Course Name: ");
            courseName = scanner.nextLine().trim();

            if (courseName.isEmpty()) {
                System.out.println("  [!] Invalid Input: Course name cannot be empty. Try again.");
            } else if (courseExists(courseName)) {
                System.out.println("  [!] Duplicate: This course is already added. Try a different course.");
            } else {
                break;
            }
        }

        String grade;
        while (true) {
            System.out.print("  Enter Grade (e.g. A+, B, C-): ");
            grade = scanner.nextLine().trim().toUpperCase();

            if (isValidGrade(grade)) {
                break;
            } else {
                System.out.println("  [!] Invalid Input: Grade must be one of: A+, A, A-, B+, B, B-, C+, C, C-, D+, D, D-, F");
            }
        }

        int creditHours;
        while (true) {
            System.out.print("  Enter Credit Hours (e.g. 1, 2, 3): ");
            String input = scanner.nextLine().trim();

            try {
                creditHours = Integer.parseInt(input);
                if (creditHours < 0) {
                    System.out.println("  [!] Invalid Input: Credit hours must be a positive number.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid Input: Credit hours must be a whole number (integer). Try again.");
            }
        }

        courses.add(new Course(courseName, grade, creditHours));
        System.out.println("  [✓] Course \"" + courseName + "\" added successfully!");
        System.out.println("--------------------------------------------");
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("============================================");
        System.out.println("         GPA CALCULATOR - MAIN MENU        ");
        System.out.println("============================================");
        System.out.println("  1. Add a Course");
        System.out.println("  2. View All Courses");
        System.out.println("  3. Calculate My GPA");
        System.out.println("  4. Exit");
        System.out.println("============================================");
        System.out.print("  Choose an option (1-4): ");
    }

     static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("       WELCOME TO GPA CALCULATOR            ");
        System.out.println("============================================");

        String studentName;
        while (true) {
            System.out.print("  Enter Your Name: ");
            studentName = scanner.nextLine().trim();
            if (studentName.isEmpty()) {
                System.out.println("  [!] Invalid Input: Name cannot be empty.");
            } else {
                break;
            }
        }

        String studentID;
        while (true) {
            System.out.print("  Enter Your Student ID: ");
            studentID = scanner.nextLine().trim();
            if (studentID.isEmpty()) {
                System.out.println("  [!] Invalid Input: Student ID cannot be empty.");
            } else {
                break;
            }
        }

        System.out.println("--------------------------------------------");
        System.out.println("  Welcome, " + studentName + "!");
        System.out.println("  Student ID: " + studentID);
        System.out.println("  Let's calculate your GPA.");
        System.out.println("--------------------------------------------");

        boolean running = true;
        while (running) {
            printMenu();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addCourse();
                case "2" -> displayCourses();
                case "3" -> calculateGPA();
                case "4" -> {
                    System.out.println("--------------------------------------------");
                    System.out.println("  Goodbye, " + studentName + "! Good luck!");
                    System.out.println("--------------------------------------------");
                    running = false;
                }
                default -> System.out.println("  [!] Invalid Input: Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }
}