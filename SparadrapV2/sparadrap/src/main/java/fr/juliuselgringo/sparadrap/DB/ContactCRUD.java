package fr.juliuselgringo.sparadrap.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.*;

import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Contact;

/**
 * requête CRUD de la table contact
 */
public class ContactCRUD {
    
    private static final Logger logger = LogManager.getLogger(ContactCRUD.class);

    /**
     * select * from contact
     * @param con Connection
     * @return List<Contact>
     * @throws InputException String
     */
    public static List<Contact> selectAllContact(Connection con) throws InputException {
        String allContact = "SELECT * FROM contact";
        List<Contact> contactsList = new ArrayList<>();

        try{
            // création de l'instance statement
            Statement stmt = con.createStatement();
            // exécution de la requête
            ResultSet res = stmt.executeQuery(allContact);

            // récupération des résultats de la requête
            while(res.next()){
                String address = res.getString("address");
                String postalCode = res.getString("postal_code");
                String town = res.getString("town");
                String phone = res.getString("phone");
                String email = res.getString("email");

                Contact contact = new Contact(address,postalCode,town,phone,email);

                contactsList.add(contact);
            }
            
        } catch(SQLException e) {
            System.out.println("Erreur lors de la sélection des contacts: " + e.getMessage());
            logger.error("Erreur lors de la sélection des contacts: " + e.getMessage());
            return new ArrayList<>();  // Liste vide au lieu de crash
        }

        return contactsList;
    }

    /**
     * select * from contact where contact_id = ?
     * @param con Connection
     * @param contactId Integer
     * @return Contact
     * @throws InputException String
     */
    public static Contact selectContactById(Connection con, Integer contactId) throws InputException{

        Contact contact = new Contact();
        String contactById = "SELECT * FROM contact WHERE contact_id = ?";

        try{
            // preparation requête dans l'instance pstmt
            PreparedStatement pstmt = con.prepareStatement(contactById);
            // injection des paramètres
            pstmt.setInt(1, contactId);
            // execution de la requête
            ResultSet res = pstmt.executeQuery();
            
            while(res.next()){
                
                contact.setContactId(contactId);
                contact.setAddress(res.getString("address"));
                contact.setPostalCode(res.getString("postal_code"));
                contact.setTown(res.getString("town"));
                contact.setPhone(res.getString("phone"));
                contact.setEmail(res.getString("email"));
                
            }


        }catch(SQLException e){
            System.out.println("Error getting contact by Id: " + contactId + " / " + e.getMessage());
            logger.error("Error getting contact by Id: " + contactId + " / " + e.getMessage());
        }

        return contact;
    }

    /**
     * insert into contact
     * @param con Connection
     * @param contact Contact
     * @return Integer
     */
    public static Integer insertNewContact(Connection con, Contact contact){

        // id créé par mysql (auto_increment)
        Integer newContactId = 0;

        // requête
        String insertIntoContact = "INSERT INTO contact (address, postal_code, town, phone, email) VALUES (?, ?, ?, ?, ?)";

        try{
            // instance PreparedStatement relié à la requête avec retour de l'auto_increment
            PreparedStatement pstmt = con.prepareStatement(insertIntoContact, PreparedStatement.RETURN_GENERATED_KEYS);

            // implémentation des paramètres
            pstmt.setString(1, contact.getAddress());
            pstmt.setString(2, contact.getPostalCode());
            pstmt.setString(3, contact.getTown());
            pstmt.setString(4, contact.getPhone());
            pstmt.setString(5, contact.getEmail());

            //exécution de la requête
            pstmt.executeUpdate();

            ResultSet res = pstmt.getGeneratedKeys();
            if(res.next()){
                // on récupère la valeur de la colonne d'index 1 qui est contact_id
                newContactId = res.getInt(1);
                System.out.println("Insert successfull contact_id: " + newContactId);
            }
        }catch(SQLException e){
            System.err.println("Error inserting contact: " + e.getMessage());
            logger.error("Error inserting contact: " + e.getMessage());
        }

        return newContactId;
    }


    /**
     * update contact
     * @param con Connection
     * @param contact Contact
     */
    public static void updateContact(Connection con, Contact contact){

        String updateContactReq = "UPDATE contact SET address=?, postal_code=?, town=?, phone=?, email=? WHERE contact_id=?";

        Integer contactId = contact.getContactId();

        try{
            PreparedStatement pstmt = con.prepareStatement(updateContactReq);

            pstmt.setString(1, contact.getAddress());
            pstmt.setString(2, contact.getPostalCode());
            pstmt.setString(3, contact.getTown());
            pstmt.setString(4, contact.getPhone());
            pstmt.setString(5, contact.getEmail());

            pstmt.setInt(6, contactId);

            pstmt.executeUpdate();
            System.out.println("Update success for contactId: " + contactId);
        }catch(SQLException e){
            System.err.println("Error udating contact: " + e);
            logger.error("Error udating contact: " + e);
        }
    }
    

    /**
     * delete contact
     * @param con Connection
     * @param contact Contact
     */
    public static void deleteContact(Connection con, Contact contact){

        String deleteContactReq = "DELETE FROM contact WHERE contact_id=?";

        Integer contactId = contact.getContactId();

        try{
            PreparedStatement pstmt = con.prepareStatement(deleteContactReq);

            pstmt.setInt(1, contactId);

            pstmt.executeUpdate();
            System.out.println("Delete success for contactId: " + contactId);
        
        }catch(SQLException e){
            System.err.println("Error Delete contact: " + e);
            logger.error("Error Delete contact: " + e);
        }

    }

}
