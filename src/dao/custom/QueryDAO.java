package dao.custom;

import dao.UltraSuperDAO;
import entity.Programme;

import java.util.List;

public interface QueryDAO extends UltraSuperDAO {
    List<Programme> findProgramDetails(String value);

}
