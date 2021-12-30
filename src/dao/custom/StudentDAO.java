package dao.custom;

import dao.SuperDAO;
import entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDAO<Student, String> {
    List<Student> searchStudents(String value);

    boolean register(Student student, String cmb1, String cmb2, String cmb3);

    boolean updateNatively(String studentRegNo, String cmb1);


}
