package teacher.service;

import common.ComonException;
import common.RunnableAsMenu;
import teacher.entity.Teacher;
import teacher.repo.TeacherRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherMenuService implements RunnableAsMenu {


    private final TeacherRepo repo = new TeacherRepo();

    private Teacher requireAndCreate() {

        Teacher teacher = new Teacher();

        System.out.print("Teacher name: ");
        teacher.setName(new Scanner(System.in).nextLine());

        System.out.print("Teacher surname: ");
        teacher.setSurname(new Scanner(System.in).nextLine());

        System.out.print("Teacher email: ");
        teacher.setEmail(new Scanner(System.in).nextLine());

        System.out.print("Teacher age: ");
        teacher.setAge(new Scanner(System.in).nextInt());

        System.out.print("Teacher salary: ");
        teacher.setSalary(new Scanner(System.in).nextDouble());
        System.out.println("Teacher added Successfully:");
        System.out.println("----------------------");

        return teacher;

    }

    @Override
    public void initialize() {
        System.out.println("How many teachers will you register ? ");
        int countOfTeachers = new Scanner(System.in).nextInt();
        for (int i = 0; i < countOfTeachers; i++) {
            System.out.println("Registiration number " + (i + 1));
            repo.insert(requireAndCreate());
            System.out.println("Teacher added Successfully:");
            System.out.println("----------------------");
        }

        printAll();

    }

    @Override
    public void initializeNew() {
        System.out.println("How many teachers would you like to add?? ");
        int additionalTeacher = new Scanner(System.in).nextInt();
        for (int i = 0; i < additionalTeacher; i++) {
            repo.insert(requireAndCreate());
            System.out.println("Successfully created new teacher....\n");
        }
    }

    @Override
    public void update() {
        try {
            System.out.println("Which-ID teacher do you want to change? ");
            int teacherId = new Scanner(System.in).nextInt();
            Teacher teacher = repo.findById(teacherId);
            System.out.println("Which field do you want to change ? " +
                    "\n" + "Name: " + teacher.getName() +
                    "\n" + "Surname: " + teacher.getSurname() +
                    "\n" + "Email: " + teacher.getEmail() +
                    "\n" + "Age: " + teacher.getAge() +
                    "\n" + "Salary: " + teacher.getSalary());
            System.out.println("Name/Surname/Email/Age/Salary");
            String updateFieldTeacher = new Scanner(System.in).nextLine();
            if (updateFieldTeacher.equalsIgnoreCase("Name")) {
                System.out.print("Enter new teacher name: ");
                teacher.setName(new Scanner(System.in).nextLine());
            } else if (updateFieldTeacher.equalsIgnoreCase("Surname")) {
                System.out.print("Enter new teacher surname: ");
                teacher.setSurname(new Scanner(System.in).nextLine());
            } else if (updateFieldTeacher.equalsIgnoreCase("Email")) {
                System.out.print("Enter new teacher email: ");
                teacher.setEmail(new Scanner(System.in).nextLine());
            } else if (updateFieldTeacher.equalsIgnoreCase("Age")) {
                System.out.print("Enter new teacher age: ");
                teacher.setAge(new Scanner(System.in).nextInt());
            } else if (updateFieldTeacher.equalsIgnoreCase("Salary")) {
                System.out.print("Enter new teacher salary: ");
                teacher.setSalary(new Scanner(System.in).nextDouble());
            }
            System.out.println("Successfully updaded new teacher information: " + teacher);
            System.out.println("---------------------------------------------------------");

            repo.update(teacher);
        } catch (
                Exception e) {
            throw new ComonException(e);
        }
    }


    @Override
    public void delete() {
        System.out.println("Which-ID teacher do you want to delete?");
        int deleteTeacher = new Scanner(System.in).nextInt();
        Teacher teacher = new Teacher();
        teacher.setId(deleteTeacher);
        repo.delete(teacher);


    }

    @Override
    public void find() {
        System.out.println("Enter name or surname of teacher ");
        String textTeacher = new Scanner(System.in).nextLine();
        List<Teacher> teachers = repo.getList(textTeacher, textTeacher);
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }

    @Override
    public void printAll() {
        List<Teacher> teachers = repo.getList();
        System.out.println("Registered Teachers: ");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + "." + "Teacher: " + teachers.get(i));
            System.out.println("----------------------------");
        }
    }

}
