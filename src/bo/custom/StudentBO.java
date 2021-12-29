package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;
import javafx.collections.ObservableList;
import view.tm.StudentTM;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean add(StudentDTO studentDTO);
    ObservableList<StudentTM> find();
    boolean update(StudentDTO studentDTO);
    boolean delete(String id);
}
