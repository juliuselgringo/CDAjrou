package fr.juliuselgringo.sparadrap.model;

import fr.juliuselgringo.sparadrap.DAO.CustomerDAO;
import fr.juliuselgringo.sparadrap.DAO.DoctorDAO;
import fr.juliuselgringo.sparadrap.DAO.PrescriptionDAO;
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
    private Mutual mutual;
    private Doctor doctor;

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     * @param contact Contact
     * @param socialSecurityId String
     * @param dateOfBirth String
     * @param mutual Mutual
     * @param doctor Doctor
     * @throws InputException String
     */
    public Customer(String firstName, String lastName, Contact contact,String socialSecurityId,
                    String dateOfBirth, Mutual mutual, Doctor doctor) throws InputException {
        super(firstName, lastName, contact);
        setSocialSecurityId(socialSecurityId);
        setDateOfBirth(dateOfBirth);
        this.mutual =  mutual;
        this.mutual.mutualCustomersList.add(this);
        this.doctor = doctor;
    }

    /**
     * CONSTRUCTOR
     * @param customerId Integer
     * @param firstName String
     * @param lastName String
     * @param contact Contact
     * @param socialSecurityId String
     * @param dateOfBirth String
     * @param mutual Mutual
     * @param doctor Doctor
     * @throws InputException String
     */
    public Customer(Integer customerId, String firstName, String lastName, Contact contact,String socialSecurityId,
                    String dateOfBirth, Mutual mutual, Doctor doctor) throws InputException {
        super(firstName, lastName, contact);
        this.customerId = customerId;
        setSocialSecurityId(socialSecurityId);
        setDateOfBirth(dateOfBirth);
        this.mutual =  mutual;
        this.mutual.mutualCustomersList.add(this);
        this.doctor = doctor;
    }

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     * @param contact Contact
     * @throws InputException String
     */
    public Customer(String firstName, String lastName, Contact contact) throws InputException {
        super(firstName, lastName, contact);
    }

    /**
     * constructor
     * @param firstName
     * @param lastName
     * @param socialSecurityId
     * @throws InputException String
     */
    public Customer (String firstName, String lastName, String socialSecurityId) throws InputException{
        super(firstName, lastName);
        
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
     * @param customerId
     * @throws InputException
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
     * GETTER Mutual
     * @return Mutual
     */
    public Mutual getMutual() {
        return this.mutual;
    }

    /**
     * SETTER mutual
     * @param mutual Mutual
     * @throws InputException String
     */
    public void setMutual(Mutual mutual) throws InputException {
        this.mutual = mutual;
        this.mutual.mutualCustomersList.add(this);
    }

    /**
     * GETTER Doctor
     * @return Doctor
     */
    public Doctor getDoctor() {
        return this.doctor;
    }

    /**
     * SETTER doctor
     * @param doctor Doctor
     * @throws InputException String
     */
    public void setDoctor(Doctor doctor) throws InputException {
        this.doctor = doctor;
    }

    /**
     *  GETTER customerPrescriptionsList
     * @return List
     */
    public List<Prescription> getCustomerPrescriptionsList() {
        List<Prescription> prescriptionsList = new ArrayList<>();

        PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
       // ajouter méthode de recherche des prescriptions à partir de customerLastName

        return prescriptionsList;
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Prénom: " + this.getFirstName() + ", Nom: " + this.getLastName();
    }


    /**
     * TO STRING DE TOUTES LES INFOS CLIENTS
     * @return String
     */
    public String toStringForDetails(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Client \nPrénom: " + this.getFirstName() +
                "\nNom: " + this.getLastName() +
                "\nDate de naissance: " + this.getDateOfBirth().format(formatter) +
                "\nTel:" + this.getContact().getPhone() +
                "\nMutuelle: " + this.getMutual().getName() + " " + this.getMutual().getContact().getPostalCode() +
                "\nDocteur: " + this.getDoctor().getLastName() + " " + this.getDoctor().getContact().getPostalCode() + "\n";
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
                matrices[i][0] = customer.getCustomerId().toString();
                matrices[i][1] = customer.getFirstName();
                matrices[i][2] = customer.getLastName();
                matrices[i][3] = customer.getDateOfBirth().format(formatter);
                matrices[i][4] = customer.getContact().getPhone();
                matrices[i][5] = customer.getMutual().getName() + " " + customer.getMutual().getContact().getPostalCode();
                matrices[i][6] = customer.getDoctor().getLastName() + " " + customer.getDoctor().getContact().getPostalCode();
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
        List<Prescription> prescriptionsList = prescriptionDAO.getAll();

        String[][] matrice = new String[prescriptionsList.size()][3];
        int i = 0;
        for(Prescription prescription : prescriptionsList){
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getById(prescription.getCustomerId());
            DoctorDAO doctorDAO = new DoctorDAO();
            Doctor doctor = doctorDAO.getById(prescription.getDoctorId());

            matrice[i][0] = prescription.getPrescriptionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            matrice[i][1] = customer.getLastName();
            matrice[i][2] = doctor.getLastName();
            i++;
        }
        return matrice;
    }


}
