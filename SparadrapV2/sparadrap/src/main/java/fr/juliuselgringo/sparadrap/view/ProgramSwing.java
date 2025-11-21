package fr.juliuselgringo.sparadrap.view;

import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.utility.Gui;
import javax.swing.*;

/**
 * classe qui permet d'afficher les différentes fonctions disponible dans l'application
 */
public class ProgramSwing {

    /**
     * constructeur par défaut
     */
    public ProgramSwing() {}

    /**
     * Menu general
     */
    public static void generalMenu(){
        JFrame generalFrame = Gui.setFrame();
        JPanel generalPanel = Gui.setPanel(generalFrame);

        JButton purchaseMenu = Gui.buttonMaker(generalPanel, "Achat", 10);
        purchaseMenu.addActionListener(e -> {
            PurchaseSwing.purchase();
            generalFrame.dispose();
        });

        JButton purchaseHistory = Gui.buttonMaker(generalPanel, "Historique des Achats", 40);
        purchaseHistory.addActionListener(e -> {
            HistorySwing.history();
            generalFrame.dispose();
        });

        JButton doctor = Gui.buttonMaker(generalPanel, "Médecins", 70);
        doctor.addActionListener(e -> {
            DoctorSwing.doctorMenu();
            generalFrame.dispose();;
        });

        JButton customer = Gui.buttonMaker(generalPanel, "Clients",100);
        customer.addActionListener(e -> {
            CustomerSwing.customerMenu();
            generalFrame.dispose();
        });

        JButton drug = Gui.buttonMaker(generalPanel, "Médicaments",130);
        drug.addActionListener(e -> {
            DrugSwing.drugMenu();
            generalFrame.dispose();
        });

        JButton prescription = Gui.buttonMaker(generalPanel, "Prescriptions",160);
        prescription.addActionListener(e -> {
            PrescriptionSwing.prescriptionMenu();
            generalFrame.dispose();
        });

        JButton mutual = Gui.buttonMaker(generalPanel, "Mutuelles",190);
        mutual.addActionListener(e -> {
            MutualSwing.mutualMenu();
            generalFrame.dispose();
        });


        JButton exitButton = Gui.buttonMaker(generalPanel, "Quitter", 400);
        exitButton.addActionListener(e -> {
            Singleton.closeInstanceDB();
            System.exit(0);
        });
    }
}
