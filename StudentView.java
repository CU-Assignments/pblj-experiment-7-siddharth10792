import java.util.List;
import java.util.Scanner;

public class StudentView {
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Create Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch(choice) {
                case 1:
                    // Create Student
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    Student newStudent = new Student(0, name, department, marks);
                    controller.createStudent(newStudent);
                    break;
                case 2:
                    // View All Students
                    List<Student> students = controller.getAllStudents();
                    System.out.println("\n--- Student List ---");
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;
                case 3:
                    // Update Student
                    System.out.print("Enter Student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Department: ");
                    String newDept = scanner.nextLine();
                    System.out.print("Enter New Marks: ");
                    double newMarks = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    Student updatedStudent = new Student(updateId, newName, newDept, newMarks);
                    controller.updateStudent(updatedStudent);
                    break;
                case 4:
                    // Delete Student
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    controller.deleteStudent(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    controller.close();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
            
        } while(choice != 5);
        
        scanner.close();
    }
}
