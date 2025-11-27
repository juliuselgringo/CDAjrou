package fr.juliuselgringo.sparadrap.model;

import fr.juliuselgringo.sparadrap.DAO.*;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.utility.Display;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * classe client qui a pour classe parent Person
 */
public class Customer extends Person {

    private Integer customerId;
    private String socialSecurityId;
    private LocalDate dateOfBirth;
    private Integer mutualId;
    private Integer doctorId;

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     * @param contactId Integer
     * @param socialSecurityId String
     * @param dateOfBirth String
     * @param mutualId Integer
     * @param doctorId Integer
     * @throws InputException String
     */
    public Customer(String firstName, String lastName, Integer contactId,String socialSecurityId,
                    String dateOfBirth, Integer mutualId, Integer doctorId) throws InputException {
        super(firstName, lastName, contactId);
        setSocialSecurityId(socialSecurityId);
        setDateOfBirth(dateOfBirth);
        this.mutualId =  mutualId;
        this.doctorId = doctorId;
    }

    /**
     * CONSTRUCTOR
     * @param customerId Integer
     * @param firstName String
     * @param lastName String
     * @param contactId Interger
     * @param socialSecurityId String
     * @param dateOfBirth String
     * @param mutualId Integer
     * @param doctorId Integer
     * @throws InputException String
     */
    public Customer(Integer customerId, String firstName, String lastName, Integer contactId,String socialSecurityId,
                    String dateOfBirth, Integer mutualId, Integer doctorId) throws InputException {
        super(firstName, lastName, contactId);
        this.customerId = customerId;
        setSocialSecurityId(socialSecurityId);
        setDateOfBirth(dateOfBirth);
        this.mutualId =  mutualId;
        this.doctorId = doctorId;
    }

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     * @param contactId Integer
     * @throws InputException String
     */
    public Customer(String firstName, String lastName, Integer contactId) throws InputException {
        super(firstName, lastName, contactId);
    }

    /**
     * constructor
     * @param firstName string
     * @param lastName string
     * @param socialSecurityId string
     * @throws InputException String
     */
    public Customer (String firstName, String lastName, String socialSecurityId) throws InputException{
        super(firstName, lastName);
        this.socialSecurityId = socialSecurityId;
        
    }

    /**
     * CONSTRUCTOR
     */
    public Customer(){
        super();
    }

    /**
     * getter customerId
     * @return Integer
     */
    public Integer getCustomerId(){
        return this.customerId;
    }

    /**
     * setter customerId Integer
     * @param customerId Interger
     * @throws InputException String
     */
    public void setCustomerId(Integer customerId) throws InputException {
        this.customerId = customerId;
    }

    /**
     * GETTER socialSecurityId
     * @return String
     */
    public String getSocialSecurityId() {
        return socialSecurityId;
    }

    /**
     * SETTER socialSecurityId
     * @param socialSecurityId String
     * @throws InputException String
     */
    public void setSocialSecurityId(String socialSecurityId) throws InputException {
        socialSecurityId = socialSecurityId.trim();
        final String regexSocialSecurityId = "\\d{15}";
        if (socialSecurityId.isEmpty()) {
            throw new InputException("Le n° de sécurité sociale ne peut être vide ou nul");
        } else if (!socialSecurityId.matches(regexSocialSecurityId)) {
            throw new InputException("Le n° de sécurité sociale est invalide");
        } else {
            this.socialSecurityId = socialSecurityId;
        }
    }

    /**
     * GETTER dateOfBirth
     *
     * @return LocalDate
     */
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * SETTER dateOfBirth
     * @param dateOfBirth LocalDate
     * @throws InputException String
     */
    public void setDateOfBirth(String dateOfBirth) throws InputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDateOfBirth = null;
        try {
            localDateOfBirth = LocalDate.parse(dateOfBirth.trim(), formatter);
        }catch(DateTimeParseException dtpe){
            Display.error(dtpe.getMessage());
            throw new InputException("Saisie date de naissance invalide");
        }
        if(localDateOfBirth.isAfter(LocalDate.now())) {
            throw new InputException("La date de naissance ne peut être postérieure à la date d'aujourd'hui");
        }else{
            this.dateOfBirth = localDateOfBirth;
        }
    }

    /**
     * GETTER mutualId
     * @return Integer
     */
    public Integer getMutualId() {
        return this.mutualId;
    }

    /**
     * SETTER mutualId
     * @param mutualId Integer
     * @throws InputException String
     */
    public void setMutualId(Integer mutualId) throws InputException {
        this.mutualId = mutualId;
    }

    /**
     * GETTER doctorId
     * @return Integer
     */
    public Integer getDoctorId() {
        return this.doctorId;
    }

    /**
     * SETTER doctorId
     * @param doctorId Integer
     * @throws InputException String
     */
    public void setDoctorId(Integer doctorId) throws InputException {
        this.doctorId = doctorId;
    }

    /**
     *  GETTER customerPrescriptionsList
     * @return List
     */
    public List<Prescription> getCustomerPrescriptionsList() {


        PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
        List<Prescription> prescriptionsList = prescriptionDAO.getPrescriptionListByCustomerId(this.getCustomerId());

        return prescriptionsList;
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return  this.getFirstName() + " " + this.getLastName();
    }


    /**
     * TO STRING DE TOUTES LES INFOS CLIENTS
     * @return String
     */
    public String toStringForDetails(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        CustomerDAO customerDAO = new CustomerDAO();
        ContactDAO contactDAO = new ContactDAO();
        Contact contact = contactDAO.getById(this.getContactId());
        return "Client \n Prénom: " + this.getFirstName() +
                "\n Nom: " + this.getLastName() +
                "\n Date de naissance: " + this.getDateOfBirth().format(formatter) +
                "\n Adresse: " + contact.getAddress() +
                "\n Code Postal: " + contact.getPostalCode() +
                "\n Ville: " + contact.getTown() +
                "\n Telephone: " + contact.getPhone() +
                "\n Email: " + contact.getEmail();

    }

    /**
     * CREER UNE MATRICE DES CLIENTS
     * @return String[][]
     */
    public static String[][] createCustomersMatrice(){
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customersList = customerDAO.getAll();
        customerDAO.closeConnection();
        String[][] matrices = new String[customersList.size()][7];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int i = 0;
        try {
            for (Customer customer : customersList) {
                ContactDAO contactDAO = new ContactDAO();
                Contact contact = contactDAO.getById(customer.getContactId());
                MutualDAO mutualDAO = new MutualDAO();
                Mutual mutual = mutualDAO.getById(customer.getMutualId());
                DoctorDAO doctorDAO = new DoctorDAO();
                Doctor doctor = doctorDAO.getById(customer.getDoctorId());
                matrices[i][0] = customer.getCustomerId().toString();
                matrices[i][1] = customer.getFirstName();
                matrices[i][2] = customer.getLastName();
                matrices[i][3] = customer.getDateOfBirth().format(formatter);
                matrices[i][4] = contact.getPhone();
                matrices[i][5] = mutual.getName();
                matrices[i][6] = doctor.getLastName();
                i++;
            }
        }catch(NullPointerException npe){};
        return matrices;
    }

    /**
     * CREER UNE MATRICE DES PRESCRIPTION D UN CLIENT
     * @return String[][]
     */
    public String[][] createCustomerPrescriptionsMatrice(){

        PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
        List<Prescription> prescriptionsList = this.getCustomerPrescriptionsList();
        String[][] matrice = new String[prescriptionsList.size()][4];
        int i = 0;
        for(Prescription prescription : prescriptionsList){
            DoctorDAO doctorDAO = new DoctorDAO();
            Doctor doctor = doctorDAO.getById(prescription.getDoctorId());

            matrice[i][0] = prescription.getPrescriptionId().toString();
            matrice[i][1] = prescription.getPrescriptionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            matrice[i][2] = this.getLastName();
            matrice[i][3] = doctor.getLastName();
            i++;
        }
        return matrice;
    }


}
