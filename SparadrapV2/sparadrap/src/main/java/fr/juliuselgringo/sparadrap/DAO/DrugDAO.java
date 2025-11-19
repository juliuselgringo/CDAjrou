package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.model.Drug;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * gestion des requetes sql crud de la classe Drug
 */
public class DrugDAO extends DAO<Drug>{

    private static final Logger logger = LogManager.getLogger(DrugDAO.class);

    @Override
    public Drug create(Drug entity) {
        return null;
    }

    @Override
    public Drug update(Drug entity) {
        return null;
    }

    @Override
    public void delete(Drug entity) {

    }

    @Override
    public List<Drug> getAll() {
        return List.of();
    }

    @Override
    public Drug getById(int id) {
        return null;
    }

    @Override
    public void closeConnection() {

    }
}
