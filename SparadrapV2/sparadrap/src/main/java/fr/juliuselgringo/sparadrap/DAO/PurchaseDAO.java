package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.model.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PurchaseDAO extends DAO<Purchase> {

    private static final Logger logger = LogManager.getLogger(PurchaseDAO.class);


    @Override
    public Purchase create(Purchase entity) {

        if(!entity.getWithPrescription()) {
            String insertPurchase = "INSERT INTO purchase (purchase_date, with_prescription, total_price) VALUES (?, ?, ?)";

            try{
                PreparedStatement pstmt = con.prepareStatement(insertPurchase, PreparedStatement.RETURN_GENERATED_KEYS);

                pstmt.setDate(1, java.sql.Date.valueOf(entity.getPurchaseDate()));
                pstmt.setBoolean(2, entity.getWithPrescription());
                pstmt.setDouble(3, entity.getTotalPrice());

                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    entity.setPurchaseId(rs.getInt(1));
                }
            } catch (SQLException e) {
                logger.error("Error insert purchase: " + e);
            }
        }else{
            String insertPurchase = "INSERT INTO purchase (purchase_date, with_prescription, total_price, " +
                                    "customer_id, prescription_id) VALUES (?, ?, ?, ?, ?)";

            try{
                PreparedStatement pstmt = con.prepareStatement(insertPurchase, PreparedStatement.RETURN_GENERATED_KEYS);

                PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                Prescription prescription = prescriptionDAO.getById(entity.getPrescriptionId());

                pstmt.setDate(1, java.sql.Date.valueOf(entity.getPurchaseDate()));
                pstmt.setBoolean(2, entity.getWithPrescription());
                pstmt.setDouble(3, entity.getTotalPrice());
                pstmt.setInt(4, prescription.getCustomerId());
                pstmt.setInt(5, entity.getPrescriptionId());

                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    entity.setPurchaseId(rs.getInt(1));
                }

            } catch (SQLException e) {
                logger.error("Error insert purchase under prescription: " + e);
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
        String deletePurchase = "DELETE FROM purchase WHERE purchase_id = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(deletePurchase);

            pstmt.setInt(1, entity.getPurchaseId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error delete purchase: " + e);
        }
    }


    @Override
    public List<Purchase> getAll() {

        List<Purchase> purchasesList = new ArrayList<>();
        String selectAllPurchase = "SELECT * FROM purchase";

        try {
            PreparedStatement pstmt = con.prepareStatement(selectAllPurchase);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer purchaseId = rs.getInt("purchase_id");
                LocalDate purchaseDate = rs.getDate("purchase_date").toLocalDate();
                Boolean withPrescription = rs.getBoolean("with_prescription");
                Integer prescriptionId = rs.getInt("prescription_id");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                Purchase purchase = new Purchase(purchaseId, purchaseDate.format(formatter).toString(), withPrescription, prescriptionId);
                purchasesList.add(purchase);
            }
        }catch(SQLException e){
            logger.error("Error getting all purchases: " + e);
        }

        return purchasesList;
    }

    @Override
    public Purchase getById(int id) {
        return null;
    }

    public List<Purchase> getByDate(LocalDate start, LocalDate end) {

        List<Purchase> purchasesList = new ArrayList<>();
        String selectAllPurchase = "SELECT * FROM purchase WHERE purchase_date BETWEEN ? AND ?";

        try {
            PreparedStatement pstmt = con.prepareStatement(selectAllPurchase);

            pstmt.setDate(1, java.sql.Date.valueOf(start));
            pstmt.setDate(2, java.sql.Date.valueOf(end));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer purchaseId = rs.getInt("purchase_id");
                LocalDate purchaseDate = rs.getDate("purchase_date").toLocalDate();
                Boolean withPrescription = rs.getBoolean("with_prescription");
                Integer prescriptionId = rs.getInt("prescription_id");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                Purchase purchase = new Purchase(purchaseId, purchaseDate.format(formatter).toString(), withPrescription, prescriptionId);
                purchasesList.add(purchase);
            }
        }catch(SQLException e){
            logger.error("Error getting all purchases: " + e);
        }

        return purchasesList;
    }

    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
