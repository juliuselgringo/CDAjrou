package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.model.Customer;
import fr.juliuselgringo.sparadrap.model.Doctor;
import fr.juliuselgringo.sparadrap.model.Drug;
import fr.juliuselgringo.sparadrap.model.Purchase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PurchaseDAO extends DAO<Purchase> {

    private static final Logger logger = LogManager.getLogger(PurchaseDAO.class);

    @Override
    public Purchase create(Purchase entity) {

        if(!entity.getWithPrescription()) {
            String insertPurchase = "INSERT INTO purchase (purchase_id, purchase_date, with_prescription, total_price) VALUES (?, ?, ?, ?)";

            try{
                PreparedStatement pstmt = con.prepareStatement(insertPurchase);

                pstmt.setInt(1, entity.getPurchaseId());
                pstmt.setDate(2, java.sql.Date.valueOf(entity.getPurchaseDate()));
                pstmt.setBoolean(3, entity.getWithPrescription());
                pstmt.setDouble(4, entity.getTotalPrice());

                pstmt.executeUpdate();

            } catch (SQLException e) {
                logger.error("Error insert purchase" + e);
            }
        }else{
            String insertPurchase = "INSERT INTO purchase (purchase_id, purchase_date, with_prescription, total_price, " +
                                    "customer_id, doctor_id) VALUES (?, ?, ?, ?, ?, ?)";

            try{
                PreparedStatement pstmt = con.prepareStatement(insertPurchase);

                pstmt.setInt(1, entity.getPurchaseId());
                pstmt.setDate(2, java.sql.Date.valueOf(entity.getPurchaseDate()));
                pstmt.setBoolean(3, entity.getWithPrescription());
                pstmt.setDouble(4, entity.getTotalPrice());
                pstmt.setInt(5, entity.getPrescription().getCustomerId());
                pstmt.setInt(6, entity.getPrescription().getDoctorId());

                pstmt.executeUpdate();

            } catch (SQLException e) {
                logger.error("Error insert purchase" + e);
            }
        }

        return entity;
    }

    @Override
    public Purchase update(Purchase entity) {
        return null;
    }

    @Override
    public void delete(Purchase entity) {

    }

    @Override
    public List<Purchase> getAll() {
        return List.of();
    }

    @Override
    public Purchase getById(int id) {
        return null;
    }

    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
