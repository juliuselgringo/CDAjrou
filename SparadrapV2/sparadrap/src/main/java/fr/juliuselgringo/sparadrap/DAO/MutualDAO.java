package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Contact;
import fr.juliuselgringo.sparadrap.model.Mutual;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestion des requetes crud sql de la class mutual
 */
public class MutualDAO extends DAO<Mutual> {

    private static final Logger logger = LogManager.getLogger(MutualDAO.class);

    /**
     * constructeur par défaut
     */
    public MutualDAO() {}

    /**
     * ajouter une nouvelle mutuelle
     * @param entity Mutual
     * @return Mutual
     */
    @Override
    public Mutual create(Mutual entity) {
        Integer newMutuelId = null;

        String insertIntoMutual = "INSERT INTO mutual(mutual_name, rate, contact_id) VALUES (?, ?, ?)";

        try{
            PreparedStatement pstmt = con.prepareStatement(insertIntoMutual, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, entity.getName());
            pstmt.setDouble(2, entity.getRate());
            pstmt.setInt(3, entity.getContactId());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                newMutuelId = rs.getInt(1);
                entity.setMutualId(newMutuelId);
            }

        }catch(SQLException e){
            logger.error("Error creating mutual: " + e.getMessage());
        }

        return entity;
    }

    /**
     * mettre à jour une mutuelle
     * @param entity Mutual
     * @return Mutual
     */
    @Override
    public Mutual update(Mutual entity) {

        String updateMutual = "UPDATE mutual SET  mutual_name=?, rate=?, contact_id=? WHERE mutual_id=?";

        try{
            PreparedStatement pstmt = con.prepareStatement(updateMutual);

            pstmt.setString(1, entity.getName());
            pstmt.setDouble(2, entity.getRate());
            pstmt.setInt(3, entity.getContactId());
            pstmt.setInt(4, entity.getMutualId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error updating mutual: " + e.getMessage());
        }

        return entity;
    }

    /**
     * supprimer une mutuelle
     * @param entity Mutual
     */
    @Override
    public void delete(Mutual entity) {

        String deleteMutual = "DELETE FROM mutual WHERE mutual_id=?";

        try{
            PreparedStatement pstmt = con.prepareStatement(deleteMutual);

            pstmt.setInt(1, entity.getMutualId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error deleting mutual: " + e.getMessage());
        }

    }

    /**
     * récupérer une liste de toute les mutuelles
     * @return List
     */
    @Override
    public List<Mutual> getAll() {
        List<Mutual> mutualsList = new ArrayList<>();

        String allMutual = "SELECT * FROM mutual";

        try{
            //declaration statement
            Statement stmt = con.createStatement();
            // exécution de la requête
            ResultSet res = stmt.executeQuery(allMutual);

            // récupération des résultats
            while(res.next()){
                Integer mutualId = res.getInt("mutual_id");
                String mutualName = res.getString("mutual_name");
                Double rate = res.getDouble("rate");
                Integer contactId = res.getInt("contact_id");

                Mutual mutual = new Mutual(mutualId, mutualName, contactId, rate);

                mutualsList.add(mutual);
            }



        }catch(SQLException | InputException e){
            logger.error("Error selecting all mutual: " + e);
        }


        return mutualsList;
    }

    /**
     * récupérer une mutuelle à partir de son id
     * @param id Integer
     * @return Mutual
     */
    @Override
    public Mutual getById(int id) {

        String selectMutualById = "SELECT * FROM mutual WHERE mutual_id=?";
        Mutual mutual = new Mutual();

        try{
            PreparedStatement pstmt = con.prepareStatement(selectMutualById);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                mutual.setMutualId(rs.getInt("mutual_id"));
                mutual.setName(rs.getString("mutual_name"));
                mutual.setRate(rs.getDouble("rate"));
                mutual.setContactId(rs.getInt("contact_id"));

            }

        } catch (SQLException | InputException e) {
            logger.error("Error getting mutual: " + e.getMessage());
        }

        return mutual;
    }

    /**
     * fermer la connection singleton à la DB
     */
    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
