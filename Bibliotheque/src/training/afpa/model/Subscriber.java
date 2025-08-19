package training.afpa.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Subscriber extends Person {

    private String email;
    private String subDate;
    private String REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    public static ArrayList<Subscriber> subscribersList = new ArrayList<>();

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     * @param email String
     */
    public Subscriber(String firstName, String lastName, String email) {
        super(firstName, lastName);
        setEmail(email);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.subDate = LocalDate.now().format(formatter);
        subscribersList.add(this);
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
        email = email.trim();
        if (email == null || email.isEmpty() || !email.matches(REGEX)) {
            throw new IllegalArgumentException("email invalide");
        }
        this.email = email;
    }

    /**
     * GETTER subDate
     * @return String
     */
    public String getSubDate() {
        return subDate;
    }


    @Override
    public String toString() {
        return "\nSubscriber{First name: " + this.getFirstName() +
                ", Last name: " + this.getLastName() +
                ", email: " + this.getEmail() +
                ", subDate: " + this.getSubDate() + "}\n";
    }

    public static Subscriber searchSubscriberByEmail(String subscriberEmail) {
        Subscriber subscriberFound = null;
        for(Subscriber subscriber : Subscriber.subscribersList){
            if(subscriberEmail.equals(subscriber.getEmail())){
                subscriberFound =  subscriber;
            }
        }
        return  subscriberFound;
    }
}
