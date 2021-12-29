package bo.custom.impl;

import bo.custom.ProgrammeBO;
import dao.DAOFactory;
import dao.custom.impl.ProgrammeDAOImpl;
import dto.ProgrammeDTO;
import entity.Programme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.ProgrammeTM;

import java.util.List;

public class ProgrammeBOImpl implements ProgrammeBO {
    ProgrammeDAOImpl programmeDAO = (ProgrammeDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAMME);

    @Override
    public boolean add(ProgrammeDTO programmeDTO) {
        return programmeDAO.add(new Programme(
                programmeDTO.getProgrammeID(),
                programmeDTO.getProgrammeName(),
                programmeDTO.getDuration(),
                programmeDTO.getFee()
        ));
    }

    @Override
    public ObservableList<ProgrammeTM> find() {
        List<Programme> list = programmeDAO.find();
        ObservableList<ProgrammeTM> dtoArrayList = FXCollections.observableArrayList();

        ProgrammeDTO programmeDTO = null;

        for (Programme programme : list
        ) {
            dtoArrayList.add(new ProgrammeTM(
                    programme.getProgrammeID(),
                    programme.getProgrammeName(),
                    programme.getDuration(),
                    programme.getFee()
            ));
        }
        return dtoArrayList;
    }

    @Override
    public boolean update(ProgrammeDTO programmeDTO) {
        return programmeDAO.update(new Programme(
                programmeDTO.getProgrammeID(),
                programmeDTO.getProgrammeName(),
                programmeDTO.getDuration(),
                programmeDTO.getFee()
        ));
    }

    @Override
    public boolean delete(String id) {
        return programmeDAO.delete(id);
    }

    @Override
    public ObservableList<ProgrammeTM> search(String value) {
        List<Programme> list = programmeDAO.searchPrograms(value);
        ObservableList<ProgrammeTM> dtoArrayList = FXCollections.observableArrayList();

        for (Programme program : list) {
            dtoArrayList.add(new ProgrammeTM(
                    program.getProgrammeID(),
                    program.getProgrammeName(),
                    program.getDuration(),
                    program.getFee()
            ));
        }
        return dtoArrayList;
    }

    @Override
    public List<String> getAllProgramIds() {
        return programmeDAO.getAllProgramIds();
    }

    @Override
    public ProgrammeDTO getProgramDetails(String id) {
        return programmeDAO.getProgramList(id);
    }
}
