package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.StudentTM;

import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean add(StudentDTO studentDTO) {
        return studentDAO.add(new Student(
                studentDTO.getRegNumber(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getContactNumber(),
                studentDTO.getAddress(),
                studentDTO.getDob(),
                studentDTO.getEmail(),
                studentDTO.getNic(),
                studentDTO.getGender()
        ));
    }

    @Override
    public ObservableList<StudentTM> find() {
        List<Student> list = studentDAO.find();
        ObservableList<StudentTM> dtoArrayList = FXCollections.observableArrayList();

        StudentDTO studentDTO = null;
        for (Student student : list
        ) {
            dtoArrayList.add(new StudentTM(
                    student.getRegNumber(),
                    student.getName(),
                    student.getAge(),
                    student.getContactNumber(),
                    student.getAddress(),
                    student.getDob(),
                    student.getEmail(),
                    student.getNic(),
                    student.getGender()
            ));

        }
        return dtoArrayList;
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        return studentDAO.update(new Student(
                studentDTO.getRegNumber(),
                studentDTO.getName(),
                studentDTO.getAge(),
                studentDTO.getContactNumber(),
                studentDTO.getAddress(),
                studentDTO.getDob(),
                studentDTO.getEmail(),
                studentDTO.getNic(),
                studentDTO.getGender()
        ));
    }

    @Override
    public boolean delete(String id) {
        return studentDAO.delete(id);
    }

    @Override
    public ObservableList<StudentTM> search(String value) {
        List<Student> list = studentDAO.searchStudents(value);
        ObservableList<StudentTM> dtoArrayList = FXCollections.observableArrayList();
        for (Student student : list
        ) {
            dtoArrayList.add(new StudentTM(
                    student.getRegNumber(),
                    student.getName(),
                    student.getAge(),
                    student.getContactNumber(),
                    student.getAddress(),
                    student.getDob(),
                    student.getEmail(),
                    student.getNic(),
                    student.getGender()));
        }
        return dtoArrayList;

    }
}

