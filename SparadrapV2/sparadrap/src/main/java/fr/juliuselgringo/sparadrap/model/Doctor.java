package fr.juliuselgringo.sparadrap.model;

import fr.juliuselgringo.sparadrap.DAO.ContactDAO;
import fr.juliuselgringo.sparadrap.DAO.CustomerDAO;
import fr.juliuselgringo.sparadrap.DAO.DoctorDAO;
import fr.juliuselgringo.sparadrap.DAO.PrescriptionDAO;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * classe docteur ayant comme classe parent Person
 */
public class Doctor extends Person {

    private Integer doctorId;
    /**
     * numéro d'agéement
     */
    private String agreementId;

    /**
     * CONSTRUCTOR
     * @param doctorId Integer
     * @param firstName String
     * @param lastName String
     * @param contactId Integer
     * @param agreementId String
     * @throws InputException String
     */
    public Doctor(Integer doctorId, String firstName, String lastName, Integer contactId, String agreementId) throws InputException {
        super(firstName, lastName, contactId);
        this.doctorId = doctorId;
        setAgreementId(agreementId);
    }

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     * @param agreementId String
     * @throws InputException String
     */
    public Doctor(String firstName, String lastName, String agreementId) throws InputException {
        super(firstName, lastName);
        setAgreementId(agreementId);
    }

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     * @param contactId Integer
     * @param agreementId String
     * @throws InputException String
     */
    public Doctor(String firstName, String lastName, Integer contactId, String agreementId) throws InputException {
        super(firstName, lastName, contactId);
        setAgreementId(agreementId);
    }

    /**
     * CONSTRUCTOR
     */
    public Doctor() {
        super();
    }

    /**
     * getter doctorId
     * @return Integer
     */
    public Integer getDoctorId() {
        return doctorId;
    }

    /**
     * setter doctorId
     * @param doctorId Integer
     */
    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * GETTER agreementId
     * @return String
     */
    public String getAgreementId() {
        return agreementId;
    }

    /**
     * SETTER agreementId
     * @param agreementId String
     * @throws InputException String
     */
    public void setAgreementId(String agreementId) throws InputException {
        agreementId = agreementId.trim();
        if(agreementId == null || agreementId.isEmpty()) {
            throw new InputException("Agreement Id can't be null or empty");
        } else if (agreementId.length() != 11) {
            throw new InputException("Agreement Id invalide");
        }else {
            this.agreementId = agreementId;
        }
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }

    /**
     * To String for details
     * @return String
     */
    public String toStringForDetails(){
        ContactDAO contactDAO = new ContactDAO();
        Contact contact = contactDAO.getById(this.getContactId());
        return "\nDocteur" +
                "\n Prénom: " + this.getFirstName() +
                "\n Nom: " + this.getLastName() +
                "\n Adresse: "  + contact.getAddress() +
                "\n CodePostal: " + contact.getPostalCode() +
                "\n Ville: " + contact.getTown() +
                "\n Telephone: " +  contact.getPhone() +
                "\n Email: " + contact.getEmail() +
                "\n N° d'agréement: " + this.getAgreementId();
    }

    /**
     * CREER UNE MATRICE DES MEDECINS
     * @return String[][]
     */
    public static String[][] createDoctorsMatrice(){
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctorsList = doctorDAO.getAll();
        doctorDAO.closeConnection();
        String[][] matrices = new String[doctorsList.size()][6];
        int i = 0;
        try {
            for (Doctor doctor : doctorsList) {
                ContactDAO contactDAO = new ContactDAO();
                Contact contact = contactDAO.getById(doctor.getContactId());
                matrices[i][0] = doctor.getDoctorId().toString();
                matrices[i][1] = doctor.getFirstName();
                matrices[i][2] = doctor.getLastName();
                matrices[i][3] = doctor.getAgreementId();
                matrices[i][4] = contact.getPhone();
                matrices[i][5] = contact.getEmail();
                i++;
            }
        }catch(NullPointerException npe){};
        return matrices;
    }

    /**
     * CREER UNE MATRICE DES PATIENTS
     * @return String[][]
     */
    public static String[][] createCustomersMatrice(){
       List<Customer> doctorCustomersList = null;

       // ajouter customerDAO -> liste des clients where doctor = this.doctor

        String[][] matrices = new String[doctorCustomersList.size()][5];
        int i = 0;
        for (Customer customer : doctorCustomersList) {
            ContactDAO contactDAO = new ContactDAO();
            Contact contact = contactDAO.getById(customer.getContactId());
            matrices[i][0] = customer.getFirstName();
            matrices[i][1] = customer.getLastName();
            matrices[i][2] = customer.getSocialSecurityId();
            matrices[i][3] = contact.getPhone();
            matrices[i][4] = contact.getEmail();
            i++;
        }
        return matrices;
    }

    /**
     * CREER UNE MATRICE DES PRESCRIPTIONS
     * @return String[][]
     */
    public String[][] createPrescriptionsMatrice(){
        PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
        List<Prescription> prescriptionsList = prescriptionDAO.getPrescriptionListByDoctorId(this.getDoctorId());

        String[][] matrices = new String[prescriptionsList.size()][5];
        int i = 0;
        for (Prescription prescription : prescriptionsList) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getById(prescription.getCustomerId());
            matrices[i][0] = prescription.getPrescriptionId().toString();
            matrices[i][1] = prescription.getPrescriptionDate().format(formatter);
            matrices[i][2] = customer.getLastName();
            matrices[i][3] = this.getLastName();
            try {
                matrices[i][4] = prescription.purchaseNumber.toString();
            }catch(NullPointerException npe){};
            i++;
        }
        return matrices;
    }

    /**
     * CHERCHER UN MEDECIN A PARTIR DE SON NOM
     * @param lastName String
     * @return Doctor
     */
    public static Doctor searchDoctorByName(String lastName){
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctorsList = doctorDAO.getAll();
        doctorDAO.closeConnection();

        Doctor doctorToReturn = null;
        for (Doctor doctor : doctorsList) {
            if (doctor.getLastName().equals(lastName)) {
                doctorToReturn = doctor;
            }
        }
        return doctorToReturn;
    }

}
