package dao.custom;

import dao.SuperDAO;
import entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDAO<Student,String> {
    List<Student> searchStudents(String value);


}
