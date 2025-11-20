package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Drug;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * gestion des requetes sql crud de la classe Drug
 */
public class DrugDAO extends DAO<Drug>{

    private static final Logger logger = LogManager.getLogger(DrugDAO.class);

    /**
     * ajouter un nouveau médicament à la table drug
     * @param entity Drug
     * @return Drug
     */
    @Override
    public Drug create(Drug entity) {

        String insertDrug = "INSERT INTO drug (drug_name, price, production_date, quantity, under_prescription, " +
                            "category_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement pstmt = con.prepareStatement(insertDrug);

            pstmt.setString(1, entity.getName());
            pstmt.setDouble(2, entity.getPrice());
            pstmt.setDate(3,java.sql.Date.valueOf(entity.getProductionDate()));
            pstmt.setInt(4, entity.getQuantity());
            pstmt.setBoolean(5, entity.isUnderPrescription());
            pstmt.setInt(6, entity.getCategoryId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error insert drug: " + e);
        }

        return entity;
    }

    /**
     * mettre à jour un médicament dans la table Drug
     * @param entity Drug
     * @return Drug
     */
    @Override
    public Drug update(Drug entity) {

        String updateDrug = "UPDATE drug SET drug_name = ?, price = ?, production_date = ?, quantity = ?, "+
                            "under_prescription = ?, category_id = ? WHERE drug_id = ?"  ;

        try{
            PreparedStatement pstmt = con.prepareStatement(updateDrug);

            pstmt.setString(1, entity.getName());
            pstmt.setDouble(2, entity.getPrice());
            pstmt.setDate(3, java.sql.Date.valueOf(entity.getProductionDate()));
            pstmt.setInt(4, entity.getQuantity());
            pstmt.setBoolean(5, entity.isUnderPrescription());
            pstmt.setInt(6, entity.getCategoryId());
            pstmt.setInt(7, entity.getDrugId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error update drug: " + e);
        }

        return entity;
    }

    /**
     * supprimer un médicament de la table Drug
     * @param entity Drug
     */
    @Override
    public void delete(Drug entity) {

        String deleteDrug = "DELETE FROM drug  WHERE drug_id = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(deleteDrug);

            pstmt.setInt(1, entity.getDrugId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error delete drug: " + e);
        }
    }

    /**
     * récupérer une liste de tout les médicaments
     * @return List
     */
    @Override
    public List<Drug> getAll() {

        List<Drug>  drugsList = new ArrayList<>();
        String selectAllDrugs = "SELECT * FROM drug";

        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(selectAllDrugs);

            while (res.next()) {
                Integer drugId = res.getInt("drug_id");
                String drugName = res.getString("drug_name");
                Double price = res.getDouble("price");
                String productionDate = res.getDate("production_date").toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Integer quantity = res.getInt("quantity");
                Boolean underPrescription = res.getBoolean("under_prescription");
                Integer categoryId = res.getInt("category_id");

                Drug drug = new Drug(drugId, drugName, categoryId, price, productionDate, quantity, underPrescription);
                drugsList.add(drug);

            }

        } catch (SQLException | InputException e) {
            logger.error("Error get all drugs: " + e);
        }


        return drugsList;
    }

    /**
     * récupérer un médicament à partir de son id
     * @param id int
     * @return Drug
     */
    @Override
    public Drug getById(int id) {

        String selectDrugId = "SELECT * FROM drug  WHERE drug_id = ?";
        Drug drug = new Drug();

        try{
            PreparedStatement pstmt = con.prepareStatement(selectDrugId);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                drug.setDrugId(rs.getInt("drug_id"));
                drug.setName(rs.getString("drug_name"));
                drug.setCategoryId(rs.getInt("category_id"));
                drug.setPrice(rs.getDouble("price"));
                drug.setProductionDate(rs.getDate("production_date").toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                drug.setQuantity(rs.getInt("quantity"));
                drug.setUnderPrescription(rs.getBoolean("under_prescription"));

            }
        } catch (SQLException | InputException e) {
            logger.error("Error select drug by id: " + e);
        }


        return drug;
    }

    /**
     * fermer la connexion singleton à la DB
     */
    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
