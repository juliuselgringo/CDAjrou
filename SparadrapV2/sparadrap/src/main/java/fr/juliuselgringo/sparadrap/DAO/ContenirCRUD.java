package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.model.Contenir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContenirCRUD {

    protected Connection con = Singleton.getInstanceDB();
    private static final Logger logger = LogManager.getLogger(ContenirCRUD.class);

    public Contenir create(Contenir entity) {

        String insertContenir = "INSERT INTO contenir (drug_id, purchase_id, buying_quantity) VALUES (?,?,?)";

        try {
            PreparedStatement pstmt = con.prepareStatement(insertContenir);

            pstmt.setInt(1,entity.getDrugId());
            pstmt.setInt(2,entity.getPurchaseId());
            pstmt.setInt(3, entity.getQuantity());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            logger.error("Error sql insert contenir: " + e);
        }
        return entity;
    }

    public void delete(Integer purchaseId) {

        String deleteContenir = "DELETE FROM contenir WHERE purchase_id = ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(deleteContenir);

            pstmt.setInt(1, purchaseId);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            logger.error("Error sql delete contenir: " + e);
        }

    }

    public List<Contenir> selectAll(Integer purchaseId) {

        String deleteContenir = "SELECT * FROM contenir WHERE purchase_id = ?";
        List<Contenir> contenirList = new ArrayList<>();

        try {
            PreparedStatement pstmt = con.prepareStatement(deleteContenir);

            pstmt.setInt(1, purchaseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer drugId = rs.getInt("drug_id");
                Integer quantity = rs.getInt("buying_quantity");

                Contenir contenir = new Contenir(purchaseId, drugId, quantity);
                contenirList.add(contenir);
            }
        } catch (SQLException e) {
            logger.error("Error sql select all contenir: " + e);
        }

        return contenirList;

    }

}

