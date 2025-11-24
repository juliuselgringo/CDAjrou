package fr.juliuselgringo.sparadrap.DAO;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Contact;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestion des requetes CRUD sql de la classe contact
 */
public class ContactDAO extends DAO<Contact> {

    private static final Logger logger = LogManager.getLogger(ContactDAO.class);

    /**
     * constructeur par défaut
     */
    public ContactDAO() {}


    /**
     * ajouter un nouveau contact
     * @param entity Contact
     * @return Contact
     */
    @Override
    public Contact create(Contact entity) {
        // id créé par mysql (auto_increment)
        Integer newContactId;

        // requête
        String insertIntoContact = "INSERT INTO contact (address, postal_code, town, phone, email) VALUES (?, ?, ?, ?, ?)";

        try{
            // instance PreparedStatement relié à la requête avec retour de l'auto_increment
            PreparedStatement pstmt = con.prepareStatement(insertIntoContact, PreparedStatement.RETURN_GENERATED_KEYS);

            // implémentation des paramètres
            pstmt.setString(1, entity.getAddress());
            pstmt.setString(2, entity.getPostalCode());
            pstmt.setString(3, entity.getTown());
            pstmt.setString(4, entity.getPhone());
            pstmt.setString(5, entity.getEmail());

            //exécution de la requête
            pstmt.executeUpdate();

            ResultSet res = pstmt.getGeneratedKeys();
            if(res.next()){
                // on récupère la valeur de la colonne d'index 1 qui est contact_id
                newContactId = res.getInt(1);
                entity.setContactId(newContactId);
            }
        }catch(SQLException e){
            logger.error("Error inserting contact: " + e);
        }

        return entity;
    }

    /**
     * mettre à jour un contact
     * @param entity Contact
     * @return Contact
     */
    @Override
    public Contact update(Contact entity) {

        String updateContactReq = "UPDATE contact SET address=?, postal_code=?, town=?, phone=?, email=? WHERE contact_id=?";

        Integer contactId = entity.getContactId();

        try{
            PreparedStatement pstmt = con.prepareStatement(updateContactReq);

            pstmt.setString(1, entity.getAddress());
            pstmt.setString(2, entity.getPostalCode());
            pstmt.setString(3, entity.getTown());
            pstmt.setString(4, entity.getPhone());
            pstmt.setString(5, entity.getEmail());

            pstmt.setInt(6, contactId);

            pstmt.executeUpdate();

        }catch(SQLException e){
            logger.error("Error updating contact: " + e);
        }

        return entity;
    }

    /**
     * supprimer un contact
     * @param entity Contact
     */
    @Override
    public void delete(Contact entity) {

        String deleteContactReq = "DELETE FROM contact WHERE contact_id=?";

        Integer contactId = entity.getContactId();

        try{
            PreparedStatement pstmt = con.prepareStatement(deleteContactReq);

            pstmt.setInt(1, contactId);

            pstmt.executeUpdate();

        }catch(SQLException e){
            logger.error("Error Delete contact: " + e);
        }
    }

    /**
     * récupérer tout les contacts
     * @return List
     */
    @Override
    public List<Contact> getAll() {
        String allContact = "SELECT * FROM contact";
        List<Contact> contactsList = new ArrayList<>();

        try{
            // création de l'instance statement
            Statement stmt = con.createStatement();
            // exécution de la requête
            ResultSet res = stmt.executeQuery(allContact);

            // récupération des résultats de la requête
            while(res.next()){
                Integer contactId = res.getInt("contact_id");
                String address = res.getString("address");
                String postalCode = res.getString("postal_code");
                String town = res.getString("town");
                String phone = res.getString("phone");
                String email = res.getString("email");

                Contact contact = new Contact(contactId, address,postalCode,town,phone,email);

                contactsList.add(contact);
            }

        } catch(SQLException | InputException e) {
            logger.error("Erreur lors de la sélection des contacts: " + e);
            return new ArrayList<>();  // Liste vide au lieu de crash
        }

        return contactsList;
    }

    /**
     * récupérer un contact à partir de son id
     * @param id Integer
     * @return Contact
     */
    @Override
    public Contact getById(int id) {
        Contact contact = new Contact();
        String contactById = "SELECT * FROM contact WHERE contact_id = ?";

        try{
            // preparation requête dans l'instance pstmt
            PreparedStatement pstmt = con.prepareStatement(contactById);
            // injection des paramètres
            pstmt.setInt(1, id);
            // execution de la requête
            ResultSet res = pstmt.executeQuery();

            while(res.next()){

                contact.setContactId(id);
                contact.setAddress(res.getString("address"));
                contact.setPostalCode(res.getString("postal_code"));
                contact.setTown(res.getString("town"));
                contact.setPhone(res.getString("phone"));
                contact.setEmail(res.getString("email"));

            }


        }catch(SQLException | InputException e){
            logger.error("Error getting contact by Id: " + id + " / " + e);
        }

        return contact;
    }

    /**
     * fermer un connection
     */
    @Override
    public void closeConnection() {
        Singleton.closeInstanceDB();
    }
}
