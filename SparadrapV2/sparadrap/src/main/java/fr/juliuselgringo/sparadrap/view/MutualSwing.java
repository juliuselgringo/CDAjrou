package fr.juliuselgringo.sparadrap.view;

import fr.juliuselgringo.sparadrap.DAO.ContactDAO;
import fr.juliuselgringo.sparadrap.DAO.MutualDAO;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.*;
import fr.juliuselgringo.sparadrap.utility.Gui;

import javax.swing.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * classe qui permet l'affichage des fonctions liées aux mutelles
 */
public class MutualSwing {

    /**
     * constructeur par défaut
     */
    public MutualSwing() {}

    /**
     * PAGE MUTUELLE
     */
    public static void mutualMenu(){
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        MutualDAO mutualDAO = new MutualDAO();
        List<Mutual> mutualsList = mutualDAO.getAll();
        mutualDAO.closeConnection();

        Gui.labelMaker(panel,"Sélectionner une mutuelle dans le tableau: ",10,10);

        JTable table = setTable(panel);

        JButton detailButton = Gui.buttonMaker(panel,"Détails de la mutuelle", 130);
        JButton modifyButton = Gui.buttonMaker(panel, "Modifier la mutuelle",160);
        JButton deleteButton = Gui.buttonMaker(panel, "Supprimer une mutuelle",190);
        JButton createButton = Gui.buttonMaker(panel,"Créer une mutuelle",220);
        JButton displayMutualCustomersListButton = Gui.buttonMaker(panel, "Afficher la liste des clients",250);

        detailButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                Mutual mutual = mutualsList.get(row);
                displayMutual(mutual);
            }
        });

        modifyButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                Mutual mutual = mutualsList.get(row);
                mutualForm(mutual, "modify", frame);
            }
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                Mutual mutual = mutualsList.get(row);
                try {
                    deleteMutual(mutual,frame);
                } catch (InputException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        createButton.addActionListener(e -> {
            try {
                createMutual(frame);
            } catch (InputException ex) {
                throw new RuntimeException(ex);
            }
        });

        displayMutualCustomersListButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                Mutual mutual = mutualsList.get(row);
                try {
                    displayMutualCustomersList(mutual);
                } catch (InputException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        JButton back2Button = Gui.buttonMaker(panel,"Retour",340);
        back2Button.addActionListener(ev -> {
            frame.dispose();
            ProgramSwing.generalMenu();
        });

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 370);
        exitButton2.addActionListener(eve -> System.exit(0));
    }

    /**
     * AFFICHE LE DETAIL D UNE MUTUELLE
     * @param mutual Mutual
     */
    public static void displayMutual(Mutual mutual){
        JFrame frame = Gui.setPopUpFrame(1200,500);
        JPanel panel = Gui.setPanel(frame);
        Gui.textAreaMaker(panel, mutual.toString(),10,10,1200,300 );

        JButton back2Button = Gui.buttonMaker(panel,"Retour",340);
        back2Button.addActionListener(ev -> frame.dispose());

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 370);
        exitButton2.addActionListener(eve -> System.exit(0));
    }

    /**
     * CREER UN TABLEAU DES MUTUELLES
     * @param panel JPanel
     * @return JTable
     */
    public static JTable setTable(JPanel panel){
        String[] header = new String[]{"id", "Nom","Adresse","Code Postal","Ville","Téléphone", "Email", "Taux de remboursement"};
        JTable table = Gui.tableMaker(panel, Mutual.createMutualMatrice(),header,800, 40,800,800);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return table;
    }

    /**
     * FORMULAIRE POUR LA CREATION OU LA MODIFICATION D UNE MUTUELLE
     * @param mutual Mutual
     * @param type String
     * @param frame1 JFrame
     */
    public static void mutualForm(Mutual mutual, String type, JFrame frame1){
        JFrame frame = Gui.setPopUpFrame(800,1000);
        JPanel panel = Gui.setPanel(frame);

        MutualDAO mutualDAO = new MutualDAO();
        List<Mutual> mutualsList = mutualDAO.getAll();
        mutualDAO.closeConnection();

        Contact contact = mutual.getContact();

        Gui.labelMaker(panel,"Nom: ",10,10);
        JTextField nameField = Gui.textFieldMaker(panel,10,40);
        nameField.setText(mutual.getName());

        Gui.labelMaker(panel,"Adresse: ",10,70);
        JTextField addressField = Gui.textFieldMaker(panel,10,100);
        addressField.setText(contact.getAddress());

        Gui.labelMaker(panel,"code postal: ",10,130);
        JTextField postalField = Gui.textFieldMaker(panel,10,160);
        postalField.setText(contact.getPostalCode());

        Gui.labelMaker(panel,"Ville: ",400,130);
        JTextField townField = Gui.textFieldMaker(panel,400,160);
        townField.setText(contact.getTown());

        Gui.labelMaker(panel,"téléphone (XX XX XX XX XX): ",10,190);
        JTextField phoneField = Gui.textFieldMaker(panel,10,220);
        phoneField.setText(contact.getPhone());

        Gui.labelMaker(panel,"Email: ",400,190);
        JTextField emailField = Gui.textFieldMaker(panel,400,220);
        emailField.setText(contact.getEmail());

        Gui.labelMaker(panel,"Taux de remboursement: ",10,250);
        JTextField rateField = Gui.textFieldMaker(panel,10,280);
        try {
            rateField.setText(mutual.getRate().toString());
        }catch(NullPointerException npe) {}

        JButton save =  Gui.buttonMaker(panel,"Enregistrer",450);

        JButton back2Button = Gui.buttonMaker(panel,"Annuler",480);
        back2Button.addActionListener(ev -> {
            frame1.dispose();
            frame.dispose();
            mutualMenu();
        });

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 510);
        exitButton2.addActionListener(eve -> {
            System.exit(0);
        });

        save.addActionListener(ev -> {
            try {
                mutual.setName(nameField.getText());
                contact.setAddress(addressField.getText());
                contact.setPostalCode(postalField.getText());
                contact.setTown(townField.getText());
                contact.setPhone(phoneField.getText());
                contact.setEmail(emailField.getText());
                mutual.setRate(Double.parseDouble(rateField.getText()));
                // enregistrement dans la DB
                ContactDAO contactDAO = new ContactDAO();
                MutualDAO mutualDao = new MutualDAO();
                if(type.equals("create")){
                    contactDAO.create(contact);
                    mutualDao.create(mutual);
                    mutualDao.closeConnection();
                }else{
                    contactDAO.update(contact);
                    mutualDao.update(mutual);
                    mutualDao.closeConnection();
                }
                JOptionPane.showMessageDialog(null,"Vos modification ont bien été enregitré",
                        "Success",JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                frame1.dispose();
                mutualMenu();
            } catch (InputException ie) {
                JOptionPane.showMessageDialog(null, ie.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    /**
     * SUPPRIMER UNE MUTUELLE
     * @param mutual Mutual
     * @param frame JFrame
     * @throws InputException String
     * @throws IOException String
     */
    public static void deleteMutual(Mutual mutual,JFrame frame) throws InputException, IOException {
        int resp = JOptionPane.showConfirmDialog(null,"Etes vous sur de vouloir supprimer cette mutuelle?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            MutualDAO mutualDAO = new MutualDAO();
            mutualDAO.delete(mutual);
            mutualDAO.closeConnection();
            JOptionPane.showMessageDialog(null, "La mutuelle a été supprimé avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            mutualMenu();
        }
        frame.dispose();
        mutualMenu();
    }

    /**
     * CREER UNE MUTUELLE
     * @param frame JFrame
     * @throws InputException String
     */
    public static void createMutual(JFrame frame) throws InputException {
        Contact contact = new Contact();
        Mutual newMutual = new Mutual();
        newMutual.setContact(contact);
        mutualForm(newMutual,"create",frame);
    }

    /**
     * AFFICHE UN TABLEAU DES CLIENTS D UNE MUTUELLE
     * @param mutual Mutual
     * @throws InputException String
     */
    public static void displayMutualCustomersList(Mutual mutual) throws InputException {
        JFrame frame = Gui.setPopUpFrame(800,500);
        JPanel panel = Gui.setPanel(frame);

        String[] header = {"Nom", "Prénom", "N° Secu", "Email", "Téléphone"};
        JTable table = Gui.tableMaker(panel, mutual.getMutualCustomersListMatrice(), header,10,10,700,300);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton deleteButton = Gui.buttonMaker(panel,"Supprimer un client de la liste",340);
        deleteButton.addActionListener(ev -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Customer customer = mutual.mutualCustomersList.get(selectedRow);
                int resp = JOptionPane.showConfirmDialog(null,"Etes vous sur de vouloir supprimer le client de cette mutuelle?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION) {
                    mutual.mutualCustomersList.remove(customer);
                    JOptionPane.showMessageDialog(null,"Le client a été retiré de la liste","Information", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    try {
                        displayMutualCustomersList(mutual);
                    } catch (InputException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        JButton back2Button = Gui.buttonMaker(panel,"Retour",400);
        back2Button.addActionListener(ev -> frame.dispose());

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 430);
        exitButton2.addActionListener(eve -> System.exit(0));
    }
}
