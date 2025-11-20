package fr.juliuselgringo.sparadrap.view;

import fr.juliuselgringo.sparadrap.DAO.ContactDAO;
import fr.juliuselgringo.sparadrap.DAO.DoctorDAO;
import fr.juliuselgringo.sparadrap.DAO.PrescriptionDAO;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Contact;
import fr.juliuselgringo.sparadrap.model.Doctor;
import fr.juliuselgringo.sparadrap.model.Prescription;
import fr.juliuselgringo.sparadrap.utility.Gui;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * classe qui permet d'afficher toute les fonctions liées au médecin
 */
public class DoctorSwing {

    /**
     * constructeur par défaut
     */
    public DoctorSwing() {}

    /**
     * PAGE MEDECIN
     */
    public static void doctorMenu() {
        JFrame frameMenu = Gui.setFrame();
        JPanel panel = Gui.setPanel(frameMenu);

        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctorsList = doctorDAO.getAll();
        doctorDAO.closeConnection();

        Gui.labelMaker(panel, "Sélectionner un médecin dans le tableau:",10,10);

        JButton detailButton = Gui.buttonMaker(panel,"Détails du médecin", 130);
        JButton modifyButton = Gui.buttonMaker(panel, "Modifier un médecin",160);
        JButton deleteButton = Gui.buttonMaker(panel, "Supprimer un médecin",190);
        JButton createButton = Gui.buttonMaker(panel,"Creer un médecin",220);
        JButton customersListButton = Gui.buttonMaker(panel, "Listes des patients", 250);
        JButton prescriptionsListButton = Gui.buttonMaker(panel, "Liste des prescriptions", 280);

        String[] header = new String[]{"id", "Prénom","Nom","N° d'agréement","Téléphone","Email"};
        JTable table = Gui.tableMaker(panel,Doctor.createDoctorsMatrice(),header,800, 40,800,800);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        detailButton.addActionListener(e1 -> {
            int row = table.getSelectedRow();
            if(row >= 0){
                Doctor doctor = doctorsList.get(row);
                displayDoctor(doctor);
            }

        });

        modifyButton.addActionListener(e2 -> {
            int row = table.getSelectedRow();
            if(row >= 0){
                Doctor doctor = doctorsList.get(row);
                formDoctor(doctor, "modify", frameMenu);
            }

        });

        deleteButton.addActionListener(e3 ->{
            int row = table.getSelectedRow();
            if(row >= 0){
                Doctor doctor = doctorsList.get(row);
                try {
                    deleteDoctor(doctor, frameMenu);
                } catch (InputException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        createButton.addActionListener(e4 -> {
            try {
                createDoctor(frameMenu);
            } catch (InputException e) {
                throw new RuntimeException(e);
            }
        });

        customersListButton.addActionListener(e5 -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                Doctor doctor = doctorsList.get(row);
                displayDoctorCustomersList(doctor);
            }
        });

        prescriptionsListButton.addActionListener(e6 -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                Doctor doctor = doctorsList.get(row);
                displayDoctorPrescriptionsList(doctor);
            }
        });

        Gui.tableMaker(panel,Doctor.createDoctorsMatrice(),header,800, 40,800,800);

        JButton backButton = Gui.buttonMaker(panel,"Retour",490);
        backButton.addActionListener(ev -> {
            frameMenu.dispose();
            ProgramSwing.generalMenu();
        });

        JButton exitButton = Gui.buttonMaker(panel, "Quitter", 520);
        exitButton.addActionListener(e -> System.exit(0));

    }

    /**
     * CREER UNE COMBOBOX MEDECIN
     * @param panel JPanel
     * @param y int
     * @return JComboBox
     */
    public static JComboBox getDoctorBox(JPanel panel,int y) {
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctorsList = doctorDAO.getAll();
        doctorDAO.closeConnection();

        JComboBox doctorBox = Gui.comboBoxMaker(panel,10,y,400);
        for(Doctor doctor : doctorsList){
            doctorBox.addItem(doctor);
        }
        return doctorBox;
    }

    /**
     * affiche un médecin dans un pop up
     * @param doctor Doctor
     */
    public static void displayDoctor(Doctor doctor) {
        JFrame frame = Gui.setPopUpFrame(1200,500);
        JPanel panel = Gui.setPanel(frame);
        Gui.textAreaMaker(panel, doctor.toStringForDetails(),10,10,1200,300 );

        JButton back2Button = Gui.buttonMaker(panel,"Retour",340);
        back2Button.addActionListener(ev -> frame.dispose());

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 370);
        exitButton2.addActionListener(eve -> System.exit(0));
    }

    /**
     * FORMULAIRE POUR MODIFIER OU CREER UN MEDECIN
     * String type "create" ou "momdify"
     * @param doctor Doctor
     * @param type String
     * @param frameMenu JFrame
     */
    public static void formDoctor(Doctor doctor, String type, JFrame frameMenu) {
        JFrame frameForm = Gui.setPopUpFrame(800, 1000);
        JPanel panel = Gui.setPanel(frameForm);

        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctorsList = doctorDAO.getAll();
        doctorDAO.closeConnection();

        Contact contact = doctor.getContact();

        Gui.labelMaker(panel, "Prénom: ", 10, 10);
        JTextField firstNameField = Gui.textFieldMaker(panel, 10, 40);
        firstNameField.setText(doctor.getFirstName());

        Gui.labelMaker(panel, "Nom: ", 400, 10);
        JTextField lastNameField = Gui.textFieldMaker(panel, 400, 40);
        lastNameField.setText(doctor.getLastName());

        Gui.labelMaker(panel, "Adresse: ", 10, 70);
        JTextField addressField = Gui.textFieldMaker(panel, 10, 100);
        addressField.setText(contact.getAddress());


        Gui.labelMaker(panel, "code postal: ", 10, 130);
        JTextField postalField = Gui.textFieldMaker(panel, 10, 160);
        postalField.setText(contact.getPostalCode());

        Gui.labelMaker(panel, "Ville: ", 400, 130);
        JTextField townField = Gui.textFieldMaker(panel, 400, 160);
        townField.setText(contact.getTown());

        Gui.labelMaker(panel, "téléphone: ", 10, 190);
        JTextField phoneField = Gui.textFieldMaker(panel, 10, 220);
        phoneField.setText(contact.getPhone());

        Gui.labelMaker(panel, "Email: ", 400, 190);
        JTextField emailField = Gui.textFieldMaker(panel, 400, 220);
        emailField.setText(contact.getEmail());

        Gui.labelMaker(panel, "N° agreement: ", 10, 250);
        JTextField agreementField = Gui.textFieldMaker(panel, 10, 280);
        agreementField.setText(doctor.getAgreementId());

        JButton save = Gui.buttonMaker(panel, "Enregistrer", 450);

        JButton back2Button = Gui.buttonMaker(panel, "Annuler", 480);
        back2Button.addActionListener(ev -> {
            frameMenu.dispose();
            frameForm.dispose();
            doctorMenu();
        });

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 510);
        exitButton2.addActionListener(eve -> {
            System.exit(0);
        });

        save.addActionListener(ev -> {
            try {
                doctor.setFirstName(firstNameField.getText());
                doctor.setLastName(lastNameField.getText());
                doctor.setAgreementId(agreementField.getText());
                contact.setTown(townField.getText());
                contact.setPhone(phoneField.getText());
                contact.setEmail(emailField.getText());
                contact.setAddress(addressField.getText());
                contact.setPostalCode(postalField.getText());

                DoctorDAO doctorDao = new DoctorDAO();
                ContactDAO contactDao = new ContactDAO();
                if(type.equals("create")){
                    contactDao.create(contact);
                    doctorDao.create(doctor);
                    doctorDao.closeConnection();
                }else{
                    contactDao.update(contact);
                    doctorDao.update(doctor);
                    doctorDao.closeConnection();
                }
                JOptionPane.showMessageDialog(null, "Vos modification ont bien été enregitré",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                frameForm.dispose();
                frameMenu.dispose();
                doctorMenu();
            } catch (InputException ie) {
                JOptionPane.showMessageDialog(null, ie.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }

        });
    }

    /**
     * CRER UN MEDECIN
     * @param frame JFrame
     * @throws InputException String
     */
    public static void createDoctor(JFrame frame) throws InputException {
        Contact contact = new Contact();
        Doctor doctor= new Doctor();
        doctor.setContact(contact);
        formDoctor(doctor, "create",frame);
    }

    /**
     * SUPPRIMER UN MEDECIN
     * @param doctor Doctor
     * @param frame1 JFrame
     * @throws InputException String
     * @throws IOException String
     */
    public static void deleteDoctor(Doctor doctor, JFrame frame1) throws InputException, IOException {
        int resp = JOptionPane.showConfirmDialog(null,
                "Etes vous sur de vouloir supprimer ce médecin" + doctor.getLastName(),
                "Confirmation", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION) {
            DoctorDAO doctorDao = new DoctorDAO();
            doctorDao.delete(doctor);
            ContactDAO contactDao = new ContactDAO();
            contactDao.delete(doctor.getContact());
            doctorDao.closeConnection();
            JOptionPane.showMessageDialog(null, "Le médecin a été supprimé avec succès.",
                    "Succès",JOptionPane.INFORMATION_MESSAGE);
        }
        frame1.dispose();
        doctorMenu();
    }

    /**
     * AFFICHER LA LISTE DES PATIENTS D UN MEDECIN
     * @param doctor Doctor
     */
    public static void displayDoctorCustomersList(Doctor doctor){
        JFrame frame2 = Gui.setPopUpFrame(800,500);
        frame2.setTitle("Liste des patients");
        JPanel panel2 = Gui.setPanel(frame2);
        String[] header = new String[]{"Prénom", "Nom", "N° Secu", "Téléphone", "Email"};

        Gui.tableMaker(panel2, doctor.createCustomersMatrice(), header,10,10,700,300);
        JButton backButton = Gui.buttonMaker(panel2,"Retour",400);
        backButton.addActionListener(ev -> frame2.dispose());

        JButton exitButton = Gui.buttonMaker(panel2, "Quitter", 430);
        exitButton.addActionListener(e -> System.exit(0));
    }

    /**
     * AFFICHER LE LISTE DES PRESCRIPTION D UN MEDECIN
     * @param doctor Doctor
     */
    public static void displayDoctorPrescriptionsList(Doctor doctor){
        JFrame frame = Gui.setPopUpFrame(800,500);
        JPanel panel = Gui.setPanel(frame);
        frame.setTitle("Liste des prescriptions");
        String[] header = new String[]{"Id", "Date","Nom du patient", "Nom du médecin","Numéro de commande"};

        JTable table = Gui.tableMaker(panel, doctor.createPrescriptionsMatrice(), header,10,10,700,300);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if(e.getValueIsAdjusting()) {

                // ajouter prescriptionDAO -> liste des prescriptions where doctorLastName = this.doctor.getLastName()
                int selectedRow = table.getSelectedRow();
                PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                List<Prescription> prescriptionsList = prescriptionDAO.getPrescriptionListByDoctorId(doctor.getDoctorId());

                if(selectedRow >= 0) {
                    Prescription prescription = prescriptionsList.get(selectedRow);
                    PrescriptionSwing.displayPrescription(prescription);
                    frame.dispose();
                }
            }
        });
        Gui.labelMaker(panel,"Cliquez dans le tableau pour avoir les détails de la prescription",
                10,330);

        JButton backButton = Gui.buttonMaker(panel,"Retour",400);
        backButton.addActionListener(ev -> frame.dispose());

        JButton exitButton = Gui.buttonMaker(panel, "Quitter", 430);
        exitButton.addActionListener(e -> System.exit(0));
    }

}
