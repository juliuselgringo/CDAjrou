package fr.juliuselgringo.sparadrap.model;

import fr.juliuselgringo.sparadrap.DAO.MutualDAO;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;

import java.util.ArrayList;
import java.util.List;

/**
 * classe mutuelle
 */
public class Mutual {

    private Integer mutualId;
    private String name;
    private Contact contact;
    private Double rate;

    /**
     * liste des clients d'une mutuelle
     */
    public ArrayList<Customer> mutualCustomersList = new ArrayList<>();


    /**
     * CONSTRUCTOR
     * @param mutualId Integer
     * @param name String
     * @param contact Contact
     * @param rate Double
     * @throws InputException String
     */
    public Mutual(Integer mutualId, String name, Contact contact, Double rate) throws InputException {
        this.mutualId = mutualId;
        setName(name);
        this.contact = contact;
        setRate(rate);
    }

    /**
     * CONSTRUCTOR
     * @param name String
     * @param contact Contact
     * @param rate Double
     * @throws InputException String
     */
    public Mutual(String name, Contact contact, Double rate) throws InputException {
        setName(name);
        this.contact = contact;
        setRate(rate);
    }

    /**
     * CONSTRUCTOR
     */
    public Mutual(){}

    /**
     * getter mutualId
     * @return Integer
     */
    public Integer getMutualId() {
        return mutualId;
    }

    /**
     * setter mutualId
     * @param mutualId Integer
     */
    public void setMutualId(Integer mutualId) {
        this.mutualId = mutualId;
    }

    /**
     * GETTER name
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * SETTER name
     * @param name String
     * @throws InputException String
     */
    public void setName(String name) throws InputException {
        final String regexName = "[A-Z][a-z]+([\s][A-Z][a-z]+)?([\\s][0-9]+)?";
        name = name.trim();
        if(name.isEmpty() || name == null) {
            throw new InputException("Le nom de la mutuelle ne peut être vide");
        } else if (!name.matches(regexName)) {
            throw new InputException("Le nom doit commencer par une majuscule et ne doit pas avoir d'accent ni trait d'union");
        }else {
            this.name = name;
        }
    }

    /**
     * GETTER contact
     * @return Contact
     */
    public Contact getContact() {
        return this.contact;
    }

    /**
     * SETTER contact
     * @param contact Contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * GETTER rate
     * @return Double
     */
    public Double getRate() {
        return rate;
    }

    /**
     * SETTER rate
     * @param rate Double
     * @throws InputException String
     */
    public void setRate(Double rate) throws InputException {
        if(rate <= 0 || rate >= 1) {
            throw new InputException("Le taux ne peut être négatif ou supérieur à 1");
        }else {
            this.rate = rate;
        }
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        return "\nNom: " + this.getName() +
                "\n" +
                this.getContact() +
                "\nTaux: " + this.getRate() + "\n";
    }

    /**
     * CREER UNE MATRICE DES MUTUELLES
     * @return String[][]
     */
    public static String[][] createMutualMatrice(){
        MutualDAO mutualDAO = new MutualDAO();
        List<Mutual> mutualsList = mutualDAO.getAll();
        mutualDAO.closeConnection();
        String[][] matrice = new String[mutualsList.size()][8];
        int i = 0;
        try {
            for (Mutual mutual : mutualsList) {
                matrice[i][0] = mutual.getMutualId().toString();
                matrice[i][1] = mutual.getName();
                matrice[i][2] = mutual.getContact().getAddress();
                matrice[i][3] = mutual.getContact().getPostalCode();
                matrice[i][4] = mutual.getContact().getTown();
                matrice[i][5] = mutual.getContact().getPhone();
                matrice[i][6] = mutual.getContact().getEmail();
                matrice[i][7] = mutual.getRate().toString();

                i++;
            }
        }catch(Exception e) {}

        return matrice;
    }

    /**
     * CREER UNE MATRICE DE LA LISTE DE CLIENT D UNE MUTUELLE
     * @return String[][]
     */
    public String[][] getMutualCustomersListMatrice(){
        String[][] matrice = new String[this.mutualCustomersList.size()][5];
        int i = 0;
        for(Customer customer : this.mutualCustomersList){
            matrice[i][0] = customer.getLastName();
            matrice[i][1] = customer.getFirstName();
            matrice[i][2] = customer.getSocialSecurityId();
            matrice[i][3] = customer.getContact().getEmail();
            matrice[i][4] = customer.getContact().getPhone();
            i++;
        }
        return matrice;
    }

}
