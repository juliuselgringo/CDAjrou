package fr.juliuselgringo.sparadrap.view;

import fr.juliuselgringo.sparadrap.DAO.ContactDAO;
import fr.juliuselgringo.sparadrap.DAO.CustomerDAO;
import fr.juliuselgringo.sparadrap.DAO.MutualDAO;
import fr.juliuselgringo.sparadrap.DAO.DoctorDAO;
import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.*;
import fr.juliuselgringo.sparadrap.utility.Display;
import fr.juliuselgringo.sparadrap.utility.Gui;

import javax.swing.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * classe qui gère l'affichage des fonctions client
 */
public class CustomerSwing {

    /**
     * Constructeur par défaut
     */
    public CustomerSwing() {}

    /**
     * PAGE CLIENT
     */
    public static void customerMenu() {
        JFrame frameMenu = Gui.setFrame();
        JPanel panel = Gui.setPanel(frameMenu);

        CustomerDAO  customerDAO = new CustomerDAO();
        List<Customer> customersList = customerDAO.getAll();

        Gui.labelMaker(panel, "Sélectionner un client dans le tableau:",10,10);

        JButton detailButton = Gui.buttonMaker(panel,"Détails du client", 130);
        JButton modifyButton = Gui.buttonMaker(panel, "Modifier un client",160);
        JButton deleteButton = Gui.buttonMaker(panel, "Supprimer un client",190);
        JButton createButton = Gui.buttonMaker(panel,"Creer un client",220);

        String[] header = new String[]{"Id","Prénom","Nom","Date Naissance","Téléphone","Mutuelle","Medecin"};
        JTable table = Gui.tableMaker(panel,Customer.createCustomersMatrice(),header,800, 40,800,800);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        detailButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0){
                Customer customer = customersList.get(row);
                displayCustomer(customer);
            }

        });

        modifyButton.addActionListener(ev -> {
            int row =table.getSelectedRow();
            if(row >= 0){
                Customer customer = customersList.get(row);
                formCustomer(customer, "modify", frameMenu);
            }

        });

        deleteButton.addActionListener(eve ->{
            int row = table.getSelectedRow();
            if(row >= 0){
                Customer customer = customersList.get(row);
                try {
                    deleteCustomer(customer, frameMenu);
                } catch (InputException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        createButton.addActionListener(ev -> {
            try {
                createCustomer(frameMenu);
            } catch (InputException e) {
                throw new RuntimeException(e);
            }
        });


        JButton backButton = Gui.buttonMaker(panel,"Retour",490);
        backButton.addActionListener(ev -> {
            frameMenu.dispose();
            ProgramSwing.generalMenu();
        });

        JButton exitButton = Gui.buttonMaker(panel, "Quitter", 520);
        exitButton.addActionListener(e -> {
            Singleton.closeInstanceDB();
            System.exit(0);
        });
    }

    /**
     * AFFICHER LES DETAILS D UN CLIENT
     * @param customer Customer
     */
    public static void displayCustomer(Customer customer) {
        JFrame customerFrame = Gui.setPopUpFrame(800,500);
        JPanel customerPanel = Gui.setPanel(customerFrame);
        Gui.textAreaMaker(customerPanel, customer.toStringForDetails(),10,10,700,300 );

        JButton mutualButton = Gui.buttonMaker(customerPanel, "Mutuelles",340);
        mutualButton.addActionListener(e -> {
            customerFrame.dispose();
            JFrame frame = Gui.setPopUpFrame(800,500);
            JPanel panel = Gui.setPanel(frame);

            MutualDAO mutualDAO = new MutualDAO();
            Mutual mutual = mutualDAO.getById(customer.getMutualId());
            Gui.textAreaMaker(panel, mutual.toString(),10,10,700,300 );

            JButton back2Button = Gui.buttonMaker(panel,"Retour",400);
            back2Button.addActionListener(ev -> frame.dispose());

            JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 430);
            exitButton2.addActionListener(eve -> {
                Singleton.closeInstanceDB();
                System.exit(0);
            });
        });

        JButton back2Button = Gui.buttonMaker(customerPanel,"Retour",400);
        back2Button.addActionListener(ev -> customerFrame.dispose());

        JButton exitButton2 = Gui.buttonMaker(customerPanel, "Quitter", 430);
        exitButton2.addActionListener(eve -> {
            Singleton.closeInstanceDB();
            System.exit(0);
        });
    }

    /**
     * CREER UN COMBO BOX CLIENT
     * @param panel JPanel
     * @param y int
     * @return JComboBox
     */
    public static JComboBox getCustomerBox(JPanel panel,int y) {
        CustomerDAO  customerDAO = new CustomerDAO();
        List<Customer> customersList = customerDAO.getAll();

        JComboBox customerBox = Gui.comboBoxMaker(panel,10, y,300);
        for(Customer customer : customersList){
            customerBox.addItem(customer);
        }
        return customerBox;
    }

    /**
     * FORMULAIRE POUR MODIFIER OU CREER UN CLIENTS
     * String type "modify" ou "create"
     * @param customer Customer
     * @param frameMenu JFrame
     */
    public static void formCustomer(Customer customer,String type, JFrame frameMenu) {
        JFrame frameForm = Gui.setPopUpFrame(800,1000);
        JPanel panel = Gui.setPanel(frameForm);

        MutualDAO mutualDAO = new MutualDAO();
        DoctorDAO doctorDAO= new DoctorDAO();
        List<Mutual> mutualsList = mutualDAO.getAll();
        List<Doctor> doctorsList = doctorDAO.getAll();

        CustomerDAO customerDAO = new CustomerDAO();
        ContactDAO contactDAO = new ContactDAO();
        Contact contact = contactDAO.getById(customer.getContactId());

        Gui.labelMaker(panel,"Prénom: ",10,10);
        JTextField firstNameField = Gui.textFieldMaker(panel,10,40);
        firstNameField.setText(customer.getFirstName());

        Gui.labelMaker(panel,"Nom: ",400,10);
        JTextField lastNameField = Gui.textFieldMaker(panel,400,40);
        lastNameField.setText(customer.getLastName());

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

        Gui.labelMaker(panel,"date de naissance(JJ-MM-AAAA): ",10,250);
        JTextField birthField = Gui.textFieldMaker(panel,10,280);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            birthField.setText(customer.getDateOfBirth().format(formatter));
        }catch(NullPointerException npe) {
            Display.error(npe.getMessage());
        }

        Gui.labelMaker(panel,"N° de sécurité sociale (15 chiffres): ",400,250);
        JTextField secuField = Gui.textFieldMaker(panel,400,280);
        secuField.setText(customer.getSocialSecurityId());

        Gui.labelMaker(panel,"Mutuelle: ",10,310);
        JComboBox mutualBox = Gui.comboBoxMaker(panel,10,340,700);
        for(Mutual mutual : mutualsList){
            mutualBox.addItem(mutual);
        }

        Gui.labelMaker(panel,"Médecin: ",10,380);
        JComboBox docBox = Gui.comboBoxMaker(panel,10,410,700);
        for(Doctor doc : doctorsList){
            docBox.addItem(doc);
        }

        JButton save =  Gui.buttonMaker(panel,"Enregistrer",450);

        JButton back2Button = Gui.buttonMaker(panel,"Annuler",480);
        back2Button.addActionListener(ev -> {
            if(type.equals("create")){
                contactDAO.delete(contact);
                customerDAO.delete(customer);
            }
            frameForm.dispose();
            frameMenu.dispose();
            customerMenu();
        });

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 510);
        exitButton2.addActionListener(eve -> {
            if(type.equals("create")){
                contactDAO.delete(contact);
                customerDAO.delete(customer);
            }
            Singleton.closeInstanceDB();
            System.exit(0);
        });

        save.addActionListener(ev -> {
            try {
                customer.setFirstName(firstNameField.getText());
                customer.setLastName(lastNameField.getText());
                customer.setDateOfBirth(birthField.getText());
                customer.setSocialSecurityId(secuField.getText());
                customer.setMutualId(((Mutual) Objects.requireNonNull(mutualBox.getSelectedItem())).getMutualId());
                customer.setDoctorId(((Doctor) Objects.requireNonNull(docBox.getSelectedItem())).getDoctorId());
                contact.setTown(townField.getText());
                contact.setPhone(phoneField.getText());
                contact.setEmail(emailField.getText());
                contact.setAddress(addressField.getText());
                contact.setPostalCode(postalField.getText());


                contactDAO.update(contact);
                customerDAO.update(customer);


                JOptionPane.showMessageDialog(null,"Vos modification ont bien été enregitré",
                        "Success",JOptionPane.INFORMATION_MESSAGE);

                frameForm.dispose();
                frameMenu.dispose();
                customerMenu();
            }catch(InputException ie) {
                JOptionPane.showMessageDialog(null, ie.getMessage(),"Erreur",JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e) {
                e.getStackTrace();
            }

        });
    }

    /**
     * CREER UN CLIENT
     * @param frame JFrame
     * @throws InputException String
     */
    public static void createCustomer(JFrame frame) throws InputException {
        Customer customer = new Customer("Na", "Na", 0,"123456789012345","01-01-2001",1,1);
        CustomerDAO customerDAO = new CustomerDAO();

        Contact contact = new Contact("0  rue na", "00000", "Na", "00 00 00 00 00", "na@na.na");
        ContactDAO contactDAO = new ContactDAO();
        contact = contactDAO.create(contact);

        customer.setContactId(contact.getContactId());
        customer = customerDAO.create(customer);
        formCustomer(customer, "create",frame);
    }

    /**
     * SUPPRIMER UN CLIENT
     * @param customer Customer
     * @param frame1 JFrame
     * @throws InputException String
     * @throws IOException String
     */
    public static void deleteCustomer(Customer customer, JFrame frame1) throws InputException, IOException {
        int resp = JOptionPane.showConfirmDialog(null,
                "Etes vous sur de vouloir supprimer ce client" + customer.getLastName(),
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION) {

            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.delete(customer);

            JOptionPane.showMessageDialog(null, "Le client a été supprimé avec succès.",
                    "Succès",JOptionPane.INFORMATION_MESSAGE);
        }
        frame1.dispose();
        customerMenu();
    }

}
