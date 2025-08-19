package training.afpa.model;

import java.time.LocalDate;

public class Person {

    private String firstName;
    private String lastName;

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     */
    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    /**
     * GETTER firstName
     * @return String
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * SETTER firstName
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        if(firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("firstName cannot be null or empty");
        }
        this.firstName = firstName;
    }

    /**
     * GETTER String
     * @return String
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * SETTER lastName
     * @param lastName String
     */
    public void setLastName(String lastName) {
        if(lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }else {
            this.lastName = lastName;
        }
    }

    /**
     * TO STRING PERSON
     * @return String
     */
    @Override
    public String toString() {
        return "Personne{" + "firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + '}';
    }
}
