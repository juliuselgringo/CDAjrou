package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Contact;
import fr.juliuselgringo.sparadrap.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.juliuselgringo.sparadrap.model.Doctor;
import fr.juliuselgringo.sparadrap.model.Mutual;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * gestion de requetes sql crud de la classe customer
 */
public class CustomerDAO extends DAO<Customer>{

    private static final Logger logger = LogManager.getLogger(CustomerDAO.class);

    /**
     * ajouter un nouveau client dans la table customer
     * @param entity
     * @return
     */
    @Override
    public Customer create(Customer entity) {

        String insertCustomer = "INSERT INTO customer (customer_firstName, customer_lastName, social_security_id," +
                                "customer_birthDate, mutual_id, doctor_id, contact_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Integer customerId = entity.getCustomerId();

        try{
            PreparedStatement pstmt = con.prepareStatement(insertCustomer,PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, entity.getFirstName());
            pstmt.setString(2, entity.getLastName());
            pstmt.setString(3, entity.getSocialSecurityId());
            pstmt.setDate(4, java.sql.Date.valueOf(entity.getDateOfBirth()));
            pstmt.setInt(5, entity.getMutualId());
            pstmt.setInt(6, entity.getDoctorId());
            pstmt.setInt(7, entity.getContactId());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                customerId =  rs.getInt(1);
                entity.setCustomerId(customerId);
            }

        } catch (SQLException e) {
            logger.error("Error SQL insert into customer: " + e);
        } catch (InputException e) {
            logger.error("Error insert into customer: " + e);
        }

        return entity;
    }

    /**
     * mettre à jour un client dans la table Customer
     * @param entity Customer
     * @return Customer
     */
    @Override
    public Customer update(Customer entity) {

        String updateCustomer = "UPDATE customer SET customer_firstName = ?, customer_lastName = ?, "+
                                "social_security_id = ?, customer_birthDate= ?, mutual_id= ?, doctor_id= ?, " +
                                "contact_id= ? WHERE customer_id = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(updateCustomer);

            pstmt.setString(1, entity.getFirstName());
            pstmt.setString(2, entity.getLastName());
            pstmt.setString(3, entity.getSocialSecurityId());
            pstmt.setDate(4, java.sql.Date.valueOf(entity.getDateOfBirth()));
            pstmt.setInt(5, entity.getMutualId());
            pstmt.setInt(6, entity.getDoctorId());
            pstmt.setInt(7, entity.getContactId());
            pstmt.setInt(8, entity.getCustomerId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error update customer: " + e);
        }

        return entity;
    }

    /**
     * Supprimer un client de la table Customer
     * @param entity Customer
     */
    @Override
    public void delete(Customer entity) {

        String deleteCustomer = "DELETE FROM customer WHERE customer_id = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(deleteCustomer);

            pstmt.setInt(1,entity.getCustomerId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error delete customer: " + e);
        }

    }

    /**
     * récupérer une liste de tout les clients
     * @return List
     */
    @Override
    public List<Customer> getAll() {

        String selectCustomer = "SELECT * FROM customer";
        List<Customer> customersList = new ArrayList<>();

        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectCustomer);
            while (rs.next()) {
                Integer customerId = rs.getInt("customer_id");
                String firstName = rs.getString("customer_firstName");
                String lastName = rs.getString("customer_lastName");
                String socialSecurityId = rs.getString("social_security_id");
                LocalDate birthDate = rs.getDate("customer_birthDate").toLocalDate();
                Integer mutualId = rs.getInt("mutual_id");
                Integer doctorId = rs.getInt("doctor_id");
                Integer contactId = rs.getInt("contact_id");

                String formattedBirthDate = birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                Customer customer = new Customer(customerId, firstName, lastName, contactId, socialSecurityId, formattedBirthDate, mutualId, doctorId);
                customersList.add(customer);

            }
        } catch (SQLException | InputException e) {
            logger.error("Error getting all customers: " + e);
        }

        return customersList;
    }

    /**
     * récupérer un client à partir de son id
     * @param id int
     * @return Customer
     */
    @Override
    public Customer getById(int id) {

        String selectCustomerById = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = new  Customer();

        try{
            PreparedStatement pstmt = con.prepareStatement(selectCustomerById);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Integer customerId = rs.getInt("customer_id");
                String firstName = rs.getString("customer_firstName");
                String lastName = rs.getString("customer_lastName");
                String socialSecurityId = rs.getString("social_security_id");
                String birthDate = rs.getDate("customer_birthDate").toLocalDate()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                Integer mutualId = rs.getInt("mutual_id");
                Integer doctorId = rs.getInt("doctor_id");
                Integer contactId = rs.getInt("contact_id");

                customer = new Customer(customerId, firstName, lastName, contactId, socialSecurityId, birthDate, mutualId, doctorId);

            }

        } catch (SQLException | InputException e) {
            logger.error("Error getting customer: " + e);
        }

        return customer;
    }

    /**
     * fermer la connexion singleton à la DB
     */
    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
