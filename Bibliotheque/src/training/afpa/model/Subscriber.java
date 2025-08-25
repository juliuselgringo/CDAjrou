package training.afpa.model;

import training.afpa.vue.terminal.Display;
import training.afpa.vue.terminal.UserInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class Subscriber extends Person {

    private String firstName;
    private String lastName;
    private String email;
    private String subDate;
    private final String REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
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
        subscribersList.sort(Comparator.comparing(Subscriber::getFirstName));
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

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        return "\nSubscriber{First name: " + this.getFirstName() +
                ", Last name: " + this.getLastName() +
                ", email: " + this.getEmail() +
                ", subDate: " + this.getSubDate() + "}\n";
    }

    /**
     * CHERCHER UN ABONNE PAR EMAIL
     * @param subscriberEmail String
     * @return Subscriber
     */
    public static Subscriber searchSubscriberByEmail(String subscriberEmail) {
        Subscriber subscriberFound = null;
        for(Subscriber subscriber : Subscriber.subscribersList){
            if(subscriberEmail.equals(subscriber.getEmail())){
                subscriberFound =  subscriber;
            }
        }

        return subscriberFound;
    }

    /**
     * MENU ABONNE
     */
    public static void subscriberMenu(){
        Display.subscriberMenu();
        String subscriberSelection = UserInput.menuSelection();
        switch (subscriberSelection) {
            case "0":
                break;
            case "1":
                Subscriber.createNewSubscriber();
                break;
            case "2":
                Subscriber.removeSubscriber();
                break;
            case "3":
                Display.print(subscribersList.toString());
                break;
            case "4":
                Subscriber.modifySubscriberLastName();
                break;
            case "5":
                Subscriber.modifySubscriberEmail();
                break;
            default:
                break;
        }
    }

    /**
     * CREER UN NOUVEL ABONNE
     */
    public static void createNewSubscriber(){
        try{
            Display.print("Saisissez le prénom du nouvel abonné: ");
            String newFirstName = UserInput.userInputText();
            Display.print("Saisissez le nom du nouvel abonné: ");
            String newLastName = UserInput.userInputText();
            Display.print("Saisissez l'email du nouvel abonné: ");
            String newEmail = UserInput.userInputText();
            if(searchSubscriberByEmail(newEmail) != null){
                Display.error("Cet utilisateur existe déjà dans la base de données");
            }
            else{
                Subscriber newSubscriber = new Subscriber(newFirstName, newLastName, newEmail);
                Display.print("Nouvel abonné enregitré: " + newSubscriber);
            }
        }
        catch(Exception e){
            Display.error(e.getMessage());
        }

    }

    /**
     * SUPRIMER UN ABONNE
     */
    public static void removeSubscriber(){
        Display.print("Saisissez l'email de l'utilisateur: ");
        String subscriberEmailToRemove = UserInput.userInputEmail();
        Subscriber subscriberToRemove = Subscriber.searchSubscriberByEmail(subscriberEmailToRemove);
        if(subscriberToRemove != null){
            subscribersList.remove(subscriberToRemove);
            Display.print("L'abonne a été supprimer avec succes!");
        }
    }

    /**
     * MODIFIER NOM DE FAMILLE ABONNE
     */
    public static void modifySubscriberLastName(){
        try {
            Display.print("Saisissez l'email de l'utilisateur: ");
            String subscriberEmailToModify = UserInput.userInputEmail();
            Subscriber subscriberToModify = Subscriber.searchSubscriberByEmail(subscriberEmailToModify);
            if (subscriberToModify != null) {
                Display.print("Saissez le nouveau nom de famille de l'abonné: ");
                String newLastName = UserInput.userInputText();
                subscriberToModify.setLastName(newLastName);
                Display.print("Le nom de famille a été modifié avec succes!");
            }

        }catch(NullPointerException e){
            Display.error("L'utilisateur n'as pas été touvé dans le système. " + e.getMessage());
        }
    }

    /**
     * MODIFIER EMAIL SUBSCRIBER
     */
    public static void modifySubscriberEmail(){
        try {
            Display.print("Saisissez l'email de l'utilisateur: ");
            String subscriberEmailToModify = UserInput.userInputEmail();
            Subscriber subscriberToModify = Subscriber.searchSubscriberByEmail(subscriberEmailToModify);
            if (subscriberToModify != null) {
                Display.print("Saissez le nouvel email de l'abonné: ");
                String newEmail = UserInput.userInputEmail();
                subscriberToModify.setLastName(newEmail);
                Display.print("L'email a été modifié avec succes!");
            }

        }catch(NullPointerException e){
            Display.error("L'utilisateur n'as pas été touvé dans le système. " + e.getMessage());
        }
    }



}
