package dao.custom;

import dao.SuperDAO;
import dto.ProgrammeDTO;
import entity.Programme;

import java.util.List;

public interface ProgrammeDAO extends SuperDAO<Programme,String> {
    List<Programme> searchPrograms(String value);
    List<String> getAllProgramIds();
    ProgrammeDTO getProgramList(String id);

    }
