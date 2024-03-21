import student.entity.Student;
import student.repo.StudentRepo;
import student.service.StudentMenuService;

import java.util.List;

public class Main {
    private static final StudentRepo repo = new StudentRepo();

    public static void main(String[] args) throws Exception {


        List<Student> list = repo.getList();

        StudentMenuService service = new StudentMenuService();
        service.showMenu();

        System.out.println(list);
    }
}







