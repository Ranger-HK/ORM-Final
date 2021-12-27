package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean add(StudentDTO studentDTO);
    List<StudentDTO> find();
    boolean update(StudentDTO studentDTO);
    boolean delete(String id);
}
