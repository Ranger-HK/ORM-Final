package bo.custom;

import bo.SuperBO;
import dto.ProgrammeDTO;
import javafx.collections.ObservableList;
import view.tm.ProgrammeTM;

import java.util.List;

public interface ProgrammeBO extends SuperBO {
    boolean add(ProgrammeDTO programmeDTO);

    ObservableList<ProgrammeTM> find();

    boolean update(ProgrammeDTO programmeDTO);

    boolean delete(String id);

    ObservableList<ProgrammeTM> search(String value);

    List<String> getAllProgramIds();

    ProgrammeDTO getProgramDetails(String id);

    ObservableList<ProgrammeTM> findStudentProgram(String value);
}
