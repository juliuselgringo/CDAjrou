package training.afpa.model;

import java.time.LocalDate;
import java.util.Date;

public class Subscriber extends Person {

    private String email;
    private LocalDate subDate;
    private String REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     * @param email String
     */
    public Subscriber(String firstName, String lastName, String email) {
        super(firstName, lastName);
        setEmail(email);
        this.subDate = LocalDate.now();

    }

    /**
     * GETTER email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * SETTER email
     * @param email String
     */
    public void setEmail(String email) {
        try {
            if (email == null || email.isEmpty() || !email.matches(REGEX)) {
                throw new IllegalArgumentException("email invalide");
            }
            this.email = email;
        }catch(IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Subscriber{" + "email=" + this.email + ", subDate=" + this.subDate + '}';
    }
}
