package student.service;

import common.ComonException;
import common.RunnableAsMenu;
import student.entity.Student;
import student.repo.StudentRepo;

import java.util.List;
import java.util.Scanner;

public class StudentMenuService implements RunnableAsMenu {

    private final StudentRepo repo = new StudentRepo();

    private Student requireAndCreate() {

        Student student = new Student();

        System.out.print("Student name: ");
        student.setName(new Scanner(System.in).nextLine());

        System.out.print("Student surname: ");
        student.setSurname(new Scanner(System.in).nextLine());

        System.out.print("Student email: ");
        student.setEmail(new Scanner(System.in).nextLine());


        System.out.print("Student age: ");
        student.setAge(new Scanner(System.in).nextInt());

        System.out.print("Student univercity: ");
        student.setUniversity(new Scanner(System.in).nextLine());

        System.out.println("-------------------------------");

        return student;

    }

    @Override
    public void initialize() {
        System.out.println("How many students will you register ? ");
        int countOfStudents = new Scanner(System.in).nextInt();
        for (int i = 0; i < countOfStudents; i++) {
            System.out.println("Registiration number " + (i + 1));
            repo.insert(requireAndCreate());
            System.out.println("Student added Successfully:");
            System.out.println("----------------------");

        }
        printAll();

    }

    @Override
    public void initializeNew() {
        System.out.println("How many students would you like to add ?");
        int additionalStudents = new Scanner(System.in).nextInt();
        for (int i = 0; i < additionalStudents; i++) {
            repo.insert(requireAndCreate());
            System.out.println("Successfully created new student....\n");
        }
    }

    @Override
    public void update() {
        try {
            System.out.println("Which-ID student do you want to change?? ");
            int studentId = new Scanner(System.in).nextInt();
            Student student = repo.findById(studentId);
            System.out.println("Which field do you want to change? " +
                    "\n" + "Name: " + student.getName() +
                    "\n" + "Surname: " + student.getSurname() +
                    "\n" + "Email: " + student.getEmail() +
                    "\n" + "Age: " + student.getAge() +
                    "\n" + "University: " + student.getUniversity());
            System.out.println("Name/Suranme/Email/Age/University");
            String updateStudentFiled = new Scanner(System.in).nextLine();
            if (updateStudentFiled.equalsIgnoreCase("Name")) {
                System.out.print("Enter new student name: ");
                student.setName(new Scanner(System.in).nextLine());
            } else if (updateStudentFiled.equalsIgnoreCase("Surname")) {
                System.out.print("Enter new student surname: ");
                student.setSurname(new Scanner(System.in).nextLine());
            } else if (updateStudentFiled.equalsIgnoreCase("Email")) {
                System.out.print("Enter new student Email: ");
                student.setEmail(new Scanner(System.in).nextLine());
            } else if (updateStudentFiled.equalsIgnoreCase("Age")) {
                System.out.println("Enter new student Age: ");
                student.setAge(new Scanner(System.in).nextInt());
            } else if (updateStudentFiled.equalsIgnoreCase("University")) {
                System.out.println("Enter new student Univerity:  ");
                student.setUniversity(new Scanner(System.in).nextLine());
            }
            System.out.println("Successfully updaded new student information: " + student);
            System.out.println("---------------------------------------------------------");

            repo.update(student);
        } catch (Exception e) {
            throw new ComonException(e);
        }
    }


    @Override
    public void delete() {
        System.out.println("Which-ID student do you want to delete?");
        int deleteStudent = new Scanner(System.in).nextInt();
        Student student = new Student();
        student.setId(deleteStudent);
        repo.delete(student);
    }

    @Override
    public void find() {
        System.out.println("Enter name or surname of student ");
        String textStudent = new Scanner(System.in).nextLine();
        List<Student> students = repo.getList(textStudent, textStudent);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Override
    public void printAll() {
        List<Student> students = repo.getList();
        System.out.println("Registered Students: ");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + "." + "Student: " + students.get(i));
            System.out.println("----------------------------");
        }
    }
}



