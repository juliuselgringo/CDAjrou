package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Prescription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * gestion des requetes sql crud de la classe Prescription
 */
public class PrescriptionDAO extends DAO<Prescription> {

    private static Logger logger = LogManager.getLogger(PrescriptionDAO.class);


    @Override
    public Prescription create(Prescription entity) {

        String insertPrescription = "INSERT INTO prescription (prescription_date, customer_id, doctor_id) VALUES (?, ?, ?)";

        try{
            PreparedStatement pstmt = con.prepareStatement(insertPrescription);

            pstmt.setDate(1, java.sql.Date.valueOf(entity.getPrescriptionDate()));
            pstmt.setInt(2, entity.getCustomerId());
            pstmt.setInt(3, entity.getDoctorId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error insert prescription: " + e);
        }

        return entity;
    }

    @Override
    public Prescription update(Prescription entity) {

        String updatePrescription = "UPDATE prescription SET prescription_date = ?, customer_id = ?, " +
                                    "doctor_id = ? WHERE prescription_id = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(updatePrescription);

            pstmt.setDate(1, java.sql.Date.valueOf(entity.getPrescriptionDate()));
            pstmt.setInt(2, entity.getCustomerId());
            pstmt.setInt(3, entity.getDoctorId());
            pstmt.setInt(4, entity.getPrescriptionId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error update prescription: " + e);
        }

        return entity;
    }

    @Override
    public void delete(Prescription entity) {

        String deletePrescription = "DELETE FROM prescription WHERE prescription_id = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(deletePrescription);

            pstmt.setInt(1, entity.getPrescriptionId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error delete prescription: " + e);
        }
    }

    @Override
    public List<Prescription> getAll() {

        String selectPrescription = "SELECT * FROM prescription ORDER BY prescription_date";
        List<Prescription> prescriptionsList = new ArrayList<>();

        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(selectPrescription);

            while (res.next()) {
                Integer prescriptionId = res.getInt("prescription_id");
                LocalDate prescriptionDate = res.getDate("prescription_date").toLocalDate();
                Integer doctorId = res.getInt("doctor_id");
                Integer customerId = res.getInt("customer_id");

                Prescription prescription = new Prescription(prescriptionId, prescriptionDate.toString(), doctorId,customerId);
                prescriptionsList.add(prescription);
            }
        } catch (SQLException | InputException e) {
            logger.error("Error get all prescriptions: " + e);
        }

        return prescriptionsList;
    }

    @Override
    public Prescription getById(int id) {

        String selectPrescription = "SELECT * FROM prescription WHERE prescription_id = ?";
        Prescription prescription = new Prescription();

        try{
            PreparedStatement pstmt = con.prepareStatement(selectPrescription);

            pstmt.setInt(1, id);
            ResultSet res =  pstmt.executeQuery();

            if (res.next()) {
                prescription.setPrescriptionId(res.getInt("prescription_id"));
                prescription.setPrescriptionDate(res.getDate("prescription_date").toString());
                prescription.setDoctorId(res.getInt("doctor_id"));
                prescription.setCustomerId(res.getInt("customer_id"));


            }
        } catch (SQLException | InputException e) {
            logger.error("Error get prescription: " + e);
        }

        return prescription;
    }

    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
