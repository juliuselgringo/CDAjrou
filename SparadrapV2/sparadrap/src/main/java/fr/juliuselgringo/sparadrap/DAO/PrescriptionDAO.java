package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Prescription;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * gestion des requetes sql crud de la classe Prescription
 */
public class PrescriptionDAO extends DAO<Prescription> {

    private static Logger logger = LogManager.getLogger(PrescriptionDAO.class);

    /**
     * constructeur par défaut
     */
    public PrescriptionDAO() {}


    /**
     * ajouter une nouvelle prescription
     * @param entity Prescription
     * @return Prescription
     */
    @Override
    public Prescription create(Prescription entity) {

        String insertPrescription = "INSERT INTO prescription (prescription_date, customer_id, doctor_id) VALUES (?, ?, ?)";

        try{
            PreparedStatement pstmt = con.prepareStatement(insertPrescription, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setDate(1, java.sql.Date.valueOf(entity.getPrescriptionDate()));
            pstmt.setInt(2, entity.getCustomerId());
            pstmt.setInt(3, entity.getDoctorId());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setPrescriptionId(rs.getInt(1));
            }

        } catch (SQLException e) {
            logger.error("Error insert prescription: " + e);
        }

        return entity;
    }

    /**
     * met à jour une prescription
     * @param entity Prescription
     * @return Prescription
     */
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

    /**
     * supprime une prescription
     * @param entity Prescription
     */
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

    /**
     * retourne une liste de toutes les prescription
     * @return List
     */
    @Override
    public List<Prescription> getAll() {

        String selectPrescription = "SELECT * FROM prescription";
        List<Prescription> prescriptionsList = new ArrayList<>();

        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(selectPrescription);

            while (res.next()) {
                Integer prescriptionId = res.getInt("prescription_id");
                String prescriptionDate = res.getDate("prescription_date").toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Integer doctorId = res.getInt("doctor_id");
                Integer customerId = res.getInt("customer_id");

                Prescription prescription = new Prescription(prescriptionId, prescriptionDate, doctorId,customerId);
                prescriptionsList.add(prescription);
            }
        } catch (SQLException | InputException e) {
            logger.error("Error get all prescriptions: " + e);
        }

        return prescriptionsList;
    }

    /**
     * retourne une prescription à partir de son id
     * @param id int
     * @return Prescription
     */
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
                prescription.setPrescriptionDate(res.getDate("prescription_date").toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                prescription.setDoctorId(res.getInt("doctor_id"));
                prescription.setCustomerId(res.getInt("customer_id"));


            }
        } catch (SQLException | InputException e) {
            logger.error("Error get prescription: " + e);
        }

        return prescription;
    }

    /**
     * fermer la connexion singleton à la DB
     */
    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }

    /**
     * liste des prescriptions d'un médecin
     * @param id int
     * @return List
     */
    public List<Prescription> getPrescriptionListByDoctorId(int id){
        List<Prescription> prescriptionsList = new ArrayList<>();
        String selectPrescription = "SELECT * FROM prescription WHERE doctor_id = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(selectPrescription);
            pstmt.setInt(1, id);

            ResultSet res =  pstmt.executeQuery();
            while(res.next()) {
                Integer prescriptionId = res.getInt("prescription_id");
                String prescriptionDate = res.getDate("prescription_date").toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Integer doctorId = res.getInt("doctor_id");
                Integer customerId = res.getInt("customer_id");

                Prescription prescription = new Prescription(prescriptionId, prescriptionDate, doctorId, customerId);
                prescriptionsList.add(prescription);
            }

        } catch (SQLException e) {
            logger.error("Error SQL get prescriptions by doctor Id: " + e);
        }catch(InputException e){
            logger.error("Error Input get prescriptions by doctor Id: " + e);
        }

        return prescriptionsList;
    }

}
