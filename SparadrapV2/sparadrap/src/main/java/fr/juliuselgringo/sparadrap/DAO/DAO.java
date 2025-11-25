package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;

import java.sql.Connection;
import java.util.List;

/**
 * classe abstraite pour les classe DAO
 * @param <T> Object
 */
public abstract class DAO<T> {

    protected Connection con = Singleton.getInstanceDB();

    /**
     *  constructeur par défaut
     */
    protected DAO() {};

    /**
     * créer une entité
     * @param entity T
     * @return T
     */
    public abstract T create(T entity);

    /**
     * mettre à jour une entité
     * @param entity T
     * @return T
     */
    public abstract T update(T entity);

    /**
     * supprimer une entité
     * @param entity T
     */
    public abstract void delete(T entity);

    /**
     * retourne une liste des entités
     * @return List
     */
    public abstract List<T> getAll();

    /**
     * retourne une entité à partir de son id
     * @param id T
     * @return T
     */
    public abstract T getById(int id);

    /**
     * ferme la connexion à la base de données
     */
    public abstract void closeConnection();

}
