package dao;

import dao.custom.impl.ProgrammeDAOImpl;
import dao.custom.impl.QueryDAOImpl;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public UltraSuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAMME:
                return new ProgrammeDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }

    }

    public enum DAOTypes {
        STUDENT, PROGRAMME, QUERY

    }


}
