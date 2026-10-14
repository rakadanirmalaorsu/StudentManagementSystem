import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private String course;
    private double marks;

    public Student(int id, String name, int age, String gender,
                   String phone, String email, String address,
                   String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.course = course;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void display() {
        System.out.println("----------------------------------------");
        System.out.println("Student ID : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Age        : " + age);
        System.out.println("Gender     : " + gender);
        System.out.println("Phone      : " + phone);
        System.out.println("Email      : " + email);
        System.out.println("Address    : " + address);
        System.out.println("Course     : " + course);
        System.out.println("Marks      : " + marks);
    }
}

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========== STUDENT MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Edit Student Details");
            System.out.println("5. Delete Student");
            System.out.println("6. Search Student by Name");
            System.out.println("7. Sort Students by Name");
            System.out.println("8. Count Students");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchById();
                    break;
                case 4:
                    editStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    searchByName();
                    break;
                case 7:
                    sortStudents();
                    break;
                case 8:
                    System.out.println("Total students: " + students.size());
                    break;
                case 9:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Enter 1 to 9.");
            }
        }
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid whole number: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (Double.isFinite(value)) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Ask again below.
            }
            System.out.print("Enter a valid number: ");
        }
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student ---");

        System.out.print("Enter student ID: ");
        int id = readInt();

        if (id <= 0 || findById(id) != null) {
            System.out.println("Invalid or duplicate student ID.");
            return;
        }

        String name = readText("Enter name: ");

        System.out.print("Enter age: ");
        int age = readInt();
        if (age < 1 || age > 120) {
            System.out.println("Invalid age.");
            return;
        }

        String gender = readText("Enter gender: ");

        String phone = readText("Enter phone number: ");
        if (!phone.matches("[0-9+() -]{7,20}")) {
            System.out.println("Invalid phone number format.");
            return;
        }

        for (Student s : students) {
            if (s.getPhone().equals(phone)) {
                System.out.println("This phone number is already registered.");
                return;
            }
        }

        String email = readText("Enter email: ");
        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            System.out.println("Invalid email format.");
            return;
        }

        String address = readText("Enter address: ");
        String course = readText("Enter course: ");

        System.out.print("Enter marks (0-100): ");
        double marks = readDouble();
        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        Student student = new Student(
            id, name, age, gender, phone, email,
            address, course, marks
        );

        students.add(student);
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        System.out.println("\n--- All Students ---");

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students) {
            s.display();
        }
        System.out.println("----------------------------------------");
        System.out.println("Total records: " + students.size());
    }

    private static void searchById() {
        System.out.print("Enter student ID to search: ");
        int id = readInt();

        Student s = findById(id);
        if (s != null) {
            s.display();
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void editStudent() {
        System.out.print("Enter student ID to edit: ");
        int id = readInt();

        Student s = findById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter new details below.");
        System.out.println("ID cannot be changed.");

        String name = readText("New name: ");

        System.out.print("New age: ");
        int age = readInt();
        if (age < 1 || age > 120) {
            System.out.println("Invalid age. Update cancelled.");
            return;
        }

        String gender = readText("New gender: ");
        String phone = readText("New phone number: ");

        if (!phone.matches("[0-9+() -]{7,20}")) {
            System.out.println("Invalid phone number. Update cancelled.");
            return;
        }

        for (Student other : students) {
            if (other.getId() != id && other.getPhone().equals(phone)) {
                System.out.println("Phone number belongs to another student.");
                return;
            }
        }

        String email = readText("New email: ");
        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            System.out.println("Invalid email. Update cancelled.");
            return;
        }

        String address = readText("New address: ");
        String course = readText("New course: ");

        System.out.print("New marks (0-100): ");
        double marks = readDouble();
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks. Update cancelled.");
            return;
        }

        s.setName(name);
        s.setAge(age);
        s.setGender(gender);
        s.setPhone(phone);
        s.setEmail(email);
        s.setAddress(address);
        s.setCourse(course);
        s.setMarks(marks);

        System.out.println("Student details updated successfully!");
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = readInt();

        Student s = findById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        s.display();
        System.out.print("Confirm deletion? (yes/no): ");
        String confirmation = sc.nextLine().trim();

        if (confirmation.equalsIgnoreCase("yes")) {
            students.remove(s);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void searchByName() {
        String name = readText("Enter name to search: ");
        boolean found = false;

        for (Student s : students) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                s.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching student found.");
        }
    }

    private static void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records to sort.");
            return;
        }

        students.sort(
            Comparator.comparing(Student::getName,
                String.CASE_INSENSITIVE_ORDER)
        );

        System.out.println("Students sorted by name:");
        viewStudents();
    }
}