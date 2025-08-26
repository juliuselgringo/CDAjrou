package training.afpa.vue.swingGui;

import training.afpa.model.Subscriber;
import training.afpa.vue.terminal.Display;

import javax.swing.*;

public class SubscriberSwing {
    /**
     * CREER UNE MATRICE SUBSCRIBER
     * @return String[][]
     */
    private static String[][] createSubscribersMatrice(){
        String[][] subscribersMatrice = new String[Subscriber.subscribersList.size()][4];
        int i = 0;
        for(Subscriber subscriber : Subscriber.subscribersList){
            subscribersMatrice[i][0] = subscriber.getFirstName();
            subscribersMatrice[i][1] = subscriber.getLastName();
            subscribersMatrice[i][2] = subscriber.getEmail();
            subscribersMatrice[i][3] = subscriber.getSubDate();
            i++;
        }


        return subscribersMatrice;
    }

    public static String[] createSubscribersEmailList(){
        String[] subscribersEmailList = new String[Subscriber.subscribersList.size()];
        int i = 0;
        for (Subscriber subscriber : Subscriber.subscribersList){
            subscribersEmailList[i] = subscriber.getEmail();
            i++;
        }
        return subscribersEmailList;
    }

    /**
     * MENU SUBSCRIBER SWING
     */
    public static void swingMenu(){
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        Gui.labelMaker(panel,"Menu Pret",10,10);
        JButton createSubscriberButton = Gui.buttonMaker(panel, "Nouvel Abonné", 40);
        JButton deleteSubscriberButton = Gui.buttonMaker(panel, "Supprimer un Abonné", 70);
        JButton modifySubscriberLastNameButton = Gui.buttonMaker(panel, "Modifer le nom d'un Abonné", 100);
        JButton modifySubscriberEmailButton = Gui.buttonMaker(panel, "Modifer l'email d'un Abonné", 130);

        String[] tableHeaders = {"prénom","nom","email","date d'inscription"};

        Gui.tableMaker(panel,createSubscribersMatrice(),tableHeaders,500,10,700,900);
        JButton refreshButton = Gui.buttonMaker(panel, "Raffraichir",930);

        refreshButton.addActionListener(e -> {
            frame.dispose();
            SubscriberSwing.swingMenu();
        });

        createSubscriberButton.addActionListener(e -> createSubscriber());

        deleteSubscriberButton.addActionListener(e -> deleteSubscriber());

        modifySubscriberLastNameButton.addActionListener(e -> modifySubscriberLastName());

        modifySubscriberEmailButton.addActionListener(e -> modifySubscriberEmail());

        JButton backButton = Gui.buttonMaker(panel,"Retour",190);

        backButton.addActionListener(e -> {
            frame.dispose();
        });

    }

    /**
     * CREER ABONNE SWING
     */
    public static void createSubscriber(){
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);


        Gui.labelMaker(panel,"Saisissez le prénom du nouvel abonné: ",10,10);
        JTextField newFirstNameField = Gui.textFieldMaker(panel,10,40);
        Gui.labelMaker(panel,"Saisissez le nom du nouvel abonné: ",10,70);
        JTextField newLastNameField = Gui.textFieldMaker(panel,10,100);
        Gui.labelMaker(panel,"Saisissez l'email du nouvel abonné: ",10,130);
        JTextField newEmailField = Gui.textFieldMaker(panel,10,150);


        JButton saveBtn = Gui.buttonMaker(panel,"Enregistrer",180);

        saveBtn.addActionListener(e -> {
            String newFirstName = newFirstNameField.getText().trim();
            String newLastName = newLastNameField.getText().trim();
            String newEmail = newEmailField.getText().trim();
            if(Subscriber.searchSubscriberByEmail(newEmail) != null){
                JOptionPane.showMessageDialog(null,
                        "Cet abonné existe déjà.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    Subscriber newSubscriber = new Subscriber(newFirstName, newLastName, newEmail);
                    JOptionPane.showMessageDialog(null,
                            "Nouvel abonné enregitré: " + newSubscriber,
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                catch(Exception err){
                    Display.error(err.getMessage());
                    JOptionPane.showMessageDialog(null,
                            err.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backButton = Gui.buttonMaker(panel,"Retour",240);

        backButton.addActionListener(e -> {
            frame.dispose();
        });

    }

    public static void deleteSubscriber(){
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        try{
            Gui.labelMaker(panel,"Saisissez l'email de l'abonné à supprimer: ",10,10);
            JTextField emailField = Gui.textFieldMaker(panel,10,40);

            JButton deleteBtn = Gui.buttonMaker(panel,"Supprimer",70);

            deleteBtn.addActionListener(e -> {
                String subscriberEmailToRemove = emailField.getText().trim();
                Subscriber subscriberToRemove = Subscriber.searchSubscriberByEmail(subscriberEmailToRemove);
                if (subscriberToRemove != null) {
                    Subscriber.subscribersList.remove(subscriberToRemove);
                    JOptionPane.showMessageDialog(null,
                            "L'abonne a été supprimer avec succes!",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "L'abonne n'existe pas!",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        catch(Exception e){
            Display.error(e.getMessage());
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }

        JButton backButton = Gui.buttonMaker(panel,"Retour",130);

        backButton.addActionListener(e -> {
            frame.dispose();
        });

    }

    public static void modifySubscriberLastName(){
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        try{
            Gui.labelMaker(panel,"Saisissez l'email de l'abonné à supprimer: ",10,10);
            JTextField emailField = Gui.textFieldMaker(panel,10,40);

            JButton modifyBtn = Gui.buttonMaker(panel,"Modifier",70);

            modifyBtn.addActionListener(e -> {
                String subscriberEmailToModify = emailField.getText().trim();
                Subscriber subscriberToModify = Subscriber.searchSubscriberByEmail(subscriberEmailToModify);
                if (subscriberToModify != null) {
                    String newLastName = JOptionPane.showInputDialog(null,
                            "Saissez le nouveau nom de famille de l'abonné: ");
                    subscriberToModify.setLastName(newLastName);
                    JOptionPane.showMessageDialog(null,
                            "Le nom de famille a été modifié avec succes!",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "L'abonne n'existe pas!",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        catch(Exception e){
            Display.error(e.getMessage());
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }

        JButton backButton = Gui.buttonMaker(panel,"Retour",130);

        backButton.addActionListener(e -> {
            frame.dispose();
        });
    }

    public static void modifySubscriberEmail(){
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);


            Gui.labelMaker(panel,"Saisissez l'email de l'abonné à supprimer: ",10,10);
            JTextField emailField = Gui.textFieldMaker(panel,10,40);

            JButton modifyBtn = Gui.buttonMaker(panel,"Modifier",70);

            modifyBtn.addActionListener(e -> {
                String subscriberEmailToModify = emailField.getText().trim();
                Subscriber subscriberToModify = Subscriber.searchSubscriberByEmail(subscriberEmailToModify);
                if (subscriberToModify != null) {
                    String newEmail = JOptionPane.showInputDialog(null,
                            "Saissez le nouvel email de l'abonné: ");
                    try{
                        subscriberToModify.setEmail(newEmail);
                        JOptionPane.showMessageDialog(null,
                                "L'email a été modifié avec succes!",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch(Exception err){
                        Display.error(err.getMessage());
                        JOptionPane.showMessageDialog(null,
                                err.getMessage(),
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "L'abonne n'existe pas!",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            });

        JButton backButton = Gui.buttonMaker(panel,"Retour",130);

        backButton.addActionListener(e -> {
            frame.dispose();
        });
    }

}
