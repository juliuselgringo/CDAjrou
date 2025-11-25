package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Contact;
import fr.juliuselgringo.sparadrap.model.Doctor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * gestion des requetes crud sql de la classe doctor
 */
public class DoctorDAO extends DAO<Doctor> {

    private static final Logger logger = LogManager.getLogger(DoctorDAO.class);

    /**
     * constructeur par défaut
     */
    public DoctorDAO(){}

    /**
     * ajouter un médecin à la table doctor
     * @param entity Doctor
     * @return Doctor
     */
    @Override
    public Doctor create(Doctor entity) {

        String insertIntoDoctor = "INSERT INTO doctor (doctor_firstName, doctor_lastName, agreement_id, contact_id) VALUES (?, ?, ?, ?)";
        Integer doctorId;

        try{
            PreparedStatement pstmt = con.prepareStatement(insertIntoDoctor, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, entity.getFirstName());
            pstmt.setString(2, entity.getLastName());
            pstmt.setString(3, entity.getAgreementId());
            pstmt.setInt(4, entity.getContactId());

            pstmt.executeUpdate();
            ResultSet res =  pstmt.getGeneratedKeys();
            if(res.next()){
                doctorId = res.getInt(1);
                entity.setDoctorId(doctorId);
            }

        }catch(SQLException e){
            logger.error("Error while inserting into database: " + e);
        }

        return entity;
    }

    /**
     * modifier un medecin
     * @param entity Doctor
     * @return Doctor
     */
    @Override
    public Doctor update(Doctor entity) {

        String updateDoctor = "UPDATE doctor SET doctor_firstName=?, doctor_lastName=?, agreement_id=?, contact_id=? WHERE doctor_id=?";

        try{
            PreparedStatement pstmt = con.prepareStatement(updateDoctor);

            pstmt.setString(1, entity.getFirstName());
            pstmt.setString(2, entity.getLastName());
            pstmt.setString(3, entity.getAgreementId());
            pstmt.setInt(4, entity.getContactId());

            pstmt.setInt(5, entity.getDoctorId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error while updating doctor: " + e);
        }

        return entity;

    }

    /**
     * supprimer un medecin
     * @param entity Doctor
     */
    @Override
    public void delete(Doctor entity) {

        String deleteDoctor = "DELETE FROM doctor WHERE doctor_id=?";

        try{
            PreparedStatement pstmt = con.prepareStatement(deleteDoctor);

            pstmt.setInt(1, entity.getDoctorId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while deleting doctor: " + e);
        }
    }

    /**
     * récupérer une liste des medecins
     * @return List
     */
    @Override
    public List<Doctor> getAll() {

        String selectDoctor = "SELECT * FROM doctor";

        List<Doctor> doctorsList = new ArrayList<>();

        try{
            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery(selectDoctor);

            while(res.next()){
                Integer doctorId = res.getInt("doctor_id");
                String firstName = res.getString("doctor_firstName");
                String lastName = res.getString("doctor_lastName");
                String agreementId = res.getString("agreement_id");
                Integer contactId = res.getInt("contact_id");

                Doctor doctor = new Doctor(doctorId, firstName, lastName, contactId, agreementId);
                doctorsList.add(doctor);
            }

        } catch (SQLException | InputException e) {
            logger.error("Error while getting doctorsList: " + e);
        }

        return doctorsList;
    }

    /**
     * rechercher un medecin par son id
     * @param id int
     * @return Doctor
     */
    @Override
    public Doctor getById(int id) {

        String selectDoctorById = "SELECT * FROM doctor WHERE doctor_id=?";

        Doctor doctor = new Doctor();

        try{
            PreparedStatement pstmt = con.prepareStatement(selectDoctorById);

            pstmt.setInt(1, id);

            ResultSet res =  pstmt.executeQuery();
            if(res.next()){
                doctor.setDoctorId(res.getInt("doctor_id"));
                doctor.setFirstName(res.getString("doctor_firstName"));
                doctor.setLastName(res.getString("doctor_lastName"));
                doctor.setAgreementId(res.getString("agreement_id"));
                doctor.setContactId(res.getInt("contact_id"));
            }

        } catch (SQLException | InputException e) {
            logger.error("Error while getting doctorById: " + e);
        }

        return  doctor;
    }

    /**
     * fermer la connexion singleton à la DB
     */
    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
