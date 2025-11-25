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

        Gui.titleLabelMaker(generalPanel,"MENU PINCIPAL", 10,10,300,30);

        JButton purchaseMenu = Gui.buttonMaker(generalPanel, "Achat", 100);
        purchaseMenu.addActionListener(e -> {
            PurchaseSwing.purchase();
            generalFrame.dispose();
        });

        JButton purchaseHistory = Gui.buttonMaker(generalPanel, "Historique des Achats", 130);
        purchaseHistory.addActionListener(e -> {
            HistorySwing.history();
            generalFrame.dispose();
        });

        JButton doctor = Gui.buttonMaker(generalPanel, "Médecins", 160);
        doctor.addActionListener(e -> {
            DoctorSwing.doctorMenu();
            generalFrame.dispose();;
        });

        JButton customer = Gui.buttonMaker(generalPanel, "Clients",190);
        customer.addActionListener(e -> {
            CustomerSwing.customerMenu();
            generalFrame.dispose();
        });

        JButton drug = Gui.buttonMaker(generalPanel, "Médicaments",220);
        drug.addActionListener(e -> {
            DrugSwing.drugMenu();
            generalFrame.dispose();
        });

        JButton prescription = Gui.buttonMaker(generalPanel, "Prescriptions",250);
        prescription.addActionListener(e -> {
            PrescriptionSwing.prescriptionMenu();
            generalFrame.dispose();
        });

        JButton mutual = Gui.buttonMaker(generalPanel, "Mutuelles",280);
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
