package jdbc;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        StudentDAO studentDao = new StudentDAO();

        List<Student> students = studentDao.list();
        students.stream().forEach(System.out::println);

        Student student = studentDao.getById(1);
        System.out.print(student);

        System.out.println();
        studentDao.insert(new Student("Julius", 56, "PA"));

        System.out.println();
        studentDao.delete(4);

        System.out.println();
        Student studentToUpdate = studentDao.getById(2);
        studentToUpdate.setName("Ferreira Priscila");
        studentToUpdate.setAge(29);
        studentDao.update(studentToUpdate);

    }
}
