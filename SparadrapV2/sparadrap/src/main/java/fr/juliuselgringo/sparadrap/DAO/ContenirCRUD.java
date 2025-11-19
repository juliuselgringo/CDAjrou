package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.model.Drug;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import fr.juliuselgringo.sparadrap.model.Purchase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContenirCRUD {

    protected Connection con = Singleton.getInstanceDB();
    private static final Logger logger = LogManager.getLogger(CustomerDAO.class);

    public ContenirCRUD() {}

    public void create(Purchase purchase, Map<Drug, Integer> purchaseDrugsQuantity){

        for(Drug drug :  purchaseDrugsQuantity.keySet()){
            for(Integer quantity : purchaseDrugsQuantity.values()){
                String insertContenir = "INSERT INTO contenir (drug_id, purchase_id, buying_quantity) VALUES (?,?,?)";

                try{
                    PreparedStatement pstmt = con.prepareStatement(insertContenir);

                    pstmt.setInt(1, drug.getDrugId());
                    pstmt.setInt(2, purchase.getPurchaseId());
                    pstmt.setInt(3, quantity);
                    pstmt.executeUpdate();

                } catch (SQLException e) {
                    logger.error("Error insert contenir" + e);
                }
            }
        }
    }

    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
