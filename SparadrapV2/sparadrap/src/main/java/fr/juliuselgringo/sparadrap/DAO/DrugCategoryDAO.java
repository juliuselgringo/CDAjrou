package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.model.DrugCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * gestion des requetes sql crud de la table drug_category
 */
public class DrugCategoryDAO extends DAO<DrugCategory>{

    private static final Logger logger = LogManager.getLogger(CustomerDAO.class);

    /**
     * ajouter une catégorie à la table drug_category
     * @param entity DrugCategory
     * @return DrugCategory
     */
    @Override
    public DrugCategory create(DrugCategory entity) {

        String insertDrugCategory = "INSERT INTO drug_category (category_id, category_name) VALUES (?, ?)";

        try{
            PreparedStatement pstmt = con.prepareStatement(insertDrugCategory);
            pstmt.setInt(1, entity.getCategoryId());
            pstmt.setString(2, entity.getCategoryName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error insert drug category: " + e);
        }

        return entity;
    }

    @Override
    public DrugCategory update(DrugCategory entity) {

        return null;
    }

    @Override
    public void delete(DrugCategory entity) {

    }

    /**
     * récupérer une liste des catégories de médicament depuis la table drug_category
     * @return List
     */
    @Override
    public List<DrugCategory> getAll() {

        String selectAllCategory = "SELECT * FROM drug_category";
        List<DrugCategory> categoriesList = new ArrayList<>();

        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(selectAllCategory);

            while (res.next()) {
                Integer categoryId = res.getInt("category_id");
                String categoryName = res.getString("category_name");

                DrugCategory drugCategory = new DrugCategory(categoryId, categoryName);
                categoriesList.add(drugCategory);
            }

        } catch (SQLException e) {
            logger.error("Error select all dug category " + e);
        }


        return categoriesList;
    }

    /**
     * récupérer une catégoies à partir de son id
     * @param id int
     * @return DrugCategory
     */
    @Override
    public DrugCategory getById(int id) {

        String selectCategoryId = "SELECT * FROM drug_category  WHERE category_id = ?";
        DrugCategory drugCategory = new DrugCategory();

        try{
            PreparedStatement pstmt = con.prepareStatement(selectCategoryId);
            pstmt.setInt(1, id);
            ResultSet res =  pstmt.executeQuery();
            if (res.next()) {
                drugCategory.setCategoryId(res.getInt("category_id"));
                drugCategory.setCategoryName(res.getString("category_name"));

            }
        } catch (SQLException e) {
            logger.error("Error select drug category by id: " + e);
        }

        return drugCategory;
    }

    /**
     * fermer la connexion singleton à la DB
     */
    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
