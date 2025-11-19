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

    @Override
    public Customer create(Customer entity) {

        String insertCustomer = "INSERT INTO customer (customer_firstName, customer_lastName, social_security_id," +
                                "customer_birthDate, mutual_id, doctor_id, contact_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement pstmt = con.prepareStatement(insertCustomer);

            pstmt.setString(1, entity.getFirstName());
            pstmt.setString(2, entity.getLastName());
            pstmt.setString(3, entity.getSocialSecurityId());
            pstmt.setDate(4, java.sql.Date.valueOf(entity.getDateOfBirth()));
            pstmt.setInt(5, entity.getMutual().getMutualId());
            pstmt.setInt(6, entity.getDoctor().getDoctorId());
            pstmt.setInt(7, entity.getContact().getContactId());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            logger.error("Error insert into customer: " + e);
        }

        return entity;
    }

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
            pstmt.setInt(5, entity.getMutual().getMutualId());
            pstmt.setInt(6, entity.getDoctor().getDoctorId());
            pstmt.setInt(7, entity.getContact().getContactId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error update customer: " + e);
        }

        return entity;
    }

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

    @Override
    public List<Customer> getAll() {

        String selectCustomer = "SELECT * FROM customer ORDER BY customer_lastName";
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

                ContactDAO contactDAO = new ContactDAO();
                Contact contact = contactDAO.getById(contactId);
                MutualDAO mutualDAO = new MutualDAO();
                Mutual mutual = mutualDAO.getById(mutualId);
                DoctorDAO doctorDAO = new DoctorDAO();
                Doctor doctor = doctorDAO.getById(doctorId);

                Customer customer = new Customer(customerId, firstName, lastName, contact, socialSecurityId, birthDate.toString(), mutual, doctor);
                customersList.add(customer);

            }
        } catch (SQLException | InputException e) {
            logger.error("Error getting all customers: " + e);
        }

        return customersList;
    }

    @Override
    public Customer getById(int id) {

        String selectCustomerById = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = null;

        try{
            PreparedStatement pstmt = con.prepareStatement(selectCustomerById);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Integer customerId = rs.getInt("customer_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String socialSecurityId = rs.getString("social_security_id");
                LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
                Integer mutualId = rs.getInt("mutual_id");
                Integer doctorId = rs.getInt("doctor_id");
                Integer contactId = rs.getInt("contact_id");

                ContactDAO contactDAO = new ContactDAO();
                Contact contact = contactDAO.getById(contactId);
                MutualDAO mutualDAO = new MutualDAO();
                Mutual mutual = mutualDAO.getById(mutualId);
                DoctorDAO doctorDAO = new DoctorDAO();
                Doctor doctor = doctorDAO.getById(doctorId);

                customer = new Customer(customerId, firstName, lastName, contact, socialSecurityId, birthDate.toString(), mutual, doctor);

            }

        } catch (SQLException | InputException e) {
            logger.error("Error getting customer: " + e);
        }

        return customer;
    }

    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
