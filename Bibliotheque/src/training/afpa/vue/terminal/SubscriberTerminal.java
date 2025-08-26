package training.afpa.vue.terminal;

import training.afpa.model.Subscriber;

public class SubscriberTerminal {

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
                createNewSubscriber();
                break;
            case "2":
                removeSubscriber();
                break;
            case "3":
                Display.print(Subscriber.subscribersList.toString());
                break;
            case "4":
                modifySubscriberLastName();
                break;
            case "5":
                modifySubscriberEmail();
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
            if(Subscriber.searchSubscriberByEmail(newEmail) != null){
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
            Subscriber.subscribersList.remove(subscriberToRemove);
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
