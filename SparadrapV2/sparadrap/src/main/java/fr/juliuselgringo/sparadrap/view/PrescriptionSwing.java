package fr.juliuselgringo.sparadrap.view;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.model.Customer;
import fr.juliuselgringo.sparadrap.model.Doctor;
import fr.juliuselgringo.sparadrap.model.Prescription;
import fr.juliuselgringo.sparadrap.utility.Gui;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * classe qui permet d'afficher les fonctions liées aux ordonnances
 */
public class PrescriptionSwing {

    /**
     * constructeur par défaut
     */
    public PrescriptionSwing() {}

    /**
     * menu ordonnance
     */
    public static void prescriptionMenu(){

        JFrame frameMenu = Gui.setFrame();
        JPanel panel = Gui.setPanel(frameMenu);

        Gui.titleLabelMaker(panel,"MENU ACHAT AVEC PRESCRIPTION", 10,10,500,30);

        Gui.labelMaker(panel, "Sélectionner un médecin:",10,100);
        JComboBox doctorBox = DoctorSwing.getDoctorBox(panel,130);
        JButton detailButton = Gui.buttonMaker(panel,"Afficher les prescriptions/médecin", 160);

        Gui.labelMaker(panel, "Sélectionner un client",10,190);
        JComboBox customerBox = CustomerSwing.getCustomerBox(panel,220);
        JButton historyButton = Gui.buttonMaker(panel,"Afficher les prescriptions/client",250);

        detailButton.addActionListener(e -> {
            Doctor doctor = (Doctor) doctorBox.getSelectedItem();
            frameMenu.setVisible(false);
            DoctorSwing.displayDoctorPrescriptionsList(doctor, frameMenu);
        });

        historyButton.addActionListener(e2 -> {
            Customer customer = (Customer) customerBox.getSelectedItem();
            frameMenu.setVisible(false);
            CustomerSwing.displayCustomerPrescriptionsList(customer, frameMenu);
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
     * AFFICHE LES DETAILS D UNE PRESCRIPTION
     * @param prescription Prescription
     * @param frameMenu JFrame
     */
    public static void displayPrescription(Prescription prescription, JFrame frameMenu) {
        JFrame frame = Gui.setPopUpFrame(800,500);
        frame.setTitle("Détails de la prescription");
        JPanel panel = Gui.setPanel(frame);

        Gui.textAreaMakerScroll(panel, prescription.toString(),10,10,500,300);

        JButton openPdf = Gui.buttonMaker(panel,"Ouvrir le PDF", 320);
        openPdf.addActionListener(e -> prescription.openPdfPrescription());

        JButton backButton = Gui.buttonMaker(panel,"Retour",350);
        backButton.addActionListener(ev -> {
            frame.dispose();
            frameMenu.setVisible(true);
        });

        JButton exitButton = Gui.buttonMaker(panel, "Quitter", 380);
        exitButton.addActionListener(e -> {
            Singleton.closeInstanceDB();
            System.exit(0);
        });
    }

}
