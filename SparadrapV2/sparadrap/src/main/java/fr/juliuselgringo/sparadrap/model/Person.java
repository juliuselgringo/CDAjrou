package fr.juliuselgringo.sparadrap.model;

import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;

/**
 * représente une personne physique
 */
public class Person {

    private String firstName;
    private String lastName;
    private Integer contactId;

    /**
     * caonstructeur
     * @param firstName String
     * @param lastName String
     * @param contactId Integer
     * @throws InputException String
     */
    public Person(String firstName, String lastName, Integer contactId) throws InputException {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setContactId(contactId);
    }

    /**
     * constructeur
     * @param firstName String
     * @param lastName String
     * @throws InputException String
     */
    public Person(String firstName, String lastName) throws InputException{
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    /**
     * CONSTRUCTOR
     */
    public Person(){}

    /**
     * GETTER firstName
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * SETTER firstName
     * @param firstName String
     * @throws InputException String
     */
    public void setFirstName(String firstName) throws InputException {
        final String regexFirstName = "[A-Z][a-z]+([\s][A-Z][a-z]+)?";
        firstName = firstName.trim();
        if(firstName == null || firstName.isEmpty()) {
            throw new InputException("Le prénom ne peut être vide ou null");
        } else if (!firstName.matches(regexFirstName)) {
            throw new InputException("Le prénom doit commencer par une majuscule" +
                    "\nà chaque particule (pour les prénoms composés)" +
                    "\net ne doit pas avoir d'accent ni trait d'union");
        }else {
            this.firstName = firstName;
        }
    }

    /**
     * GETTER lastName
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * SETTER lastName
     * @param lastName String
     * @throws InputException String
     */
    public void setLastName(String lastName) throws InputException {
        final String regexLastName = "[A-Z][a-z]+([\s][A-Z][a-z]+)?";
        lastName = lastName.trim();
        if(lastName == null || lastName.isEmpty()) {
            throw  new InputException("Le nom ne peut être vide ou null");
        } else if (!lastName.matches(regexLastName)) {
            throw new InputException("Le nom doit commencer par une majuscule et ne doit pas avoir d'accent ni trait d'union");
        }else{
            this.lastName = lastName;
        }
    }

    /**
     * GETTER contactId
     * @return Integer
     */
    public Integer getContactId() {
        return this.contactId;
    }

    /**
     * SETTER contactId
     * @param contactId Integer
     * @throws InputException String
     */
    public void setContactId(Integer contactId) throws InputException {
        this.contactId = contactId;
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        return "Personne \n Prénom: " + this.getFirstName() +
                "\n Nom: " + this.getLastName() +
                "\n Coordonnées: " + this.getContactId() + '}';
    }


}
