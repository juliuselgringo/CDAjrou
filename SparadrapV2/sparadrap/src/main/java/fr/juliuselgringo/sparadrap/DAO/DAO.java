package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {

    protected Connection con = Singleton.getInstanceDB();

    protected DAO() {};

    public abstract T create(T entity);

    public abstract T update(T entity);

    public abstract void delete(T entity);

    public abstract List<T> getAll();

    public abstract T getById(int id);

    public abstract void closeConnection();

}
