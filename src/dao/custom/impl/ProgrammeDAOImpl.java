package dao.custom.impl;

import dao.custom.ProgrammeDAO;
import entity.Programme;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class ProgrammeDAOImpl implements ProgrammeDAO {
    @Override
    public Boolean add(Programme entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Boolean update(Programme entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Boolean delete(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Programme programme = session.get(Programme.class,s);
        session.delete(programme);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Programme> find() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Programme");
        List<Programme> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<Programme> searchPrograms(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Programme p WHERE p.programmeID LIKE ?1");
        query.setParameter(1, '%' + value + '%');
        List list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public List<String> getAllProgramIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT p.programmeID FROM Programme p");
        List<String> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }
}
