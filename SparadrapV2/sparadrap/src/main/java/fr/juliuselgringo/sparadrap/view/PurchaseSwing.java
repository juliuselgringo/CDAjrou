package fr.juliuselgringo.sparadrap.view;

import fr.juliuselgringo.sparadrap.DAO.*;
import fr.juliuselgringo.sparadrap.DAO.connection.Singleton;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.*;
import fr.juliuselgringo.sparadrap.view.ProgramSwing;
import fr.juliuselgringo.sparadrap.utility.Gui;

import javax.swing.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * classe permettant d'afficher les fonctions liées aux achats
 */
public class PurchaseSwing {

    /**
     * titre des colonnes de table historique des commandes
     */
    public static final String[] purchaseHistoryTableHeaders = new String []{"Date","Numéro de commande",
            "Client", "Médicament", "Quantite"};

    /**
     * constucteur par défaut
     */
    public PurchaseSwing() {}

    /**
     * FENETRE DE CREATION D ACHAT AVEC OU SANS PRESCRIPTION
     */
    public static void purchase() {
        JFrame purchaseFrame = Gui.setFrame();
        Gui.setPanel(purchaseFrame);

        int response = JOptionPane.showConfirmDialog(null, "Achat avec prescription?",
                "Confirmation",JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {

            createPrescriptionPurchase();
            purchaseFrame.dispose();
        }else {
            Purchase newPurchase = new Purchase(false);
            PurchaseDAO purchaseDAO = new PurchaseDAO();
            newPurchase = purchaseDAO.create(newPurchase);
            createPurchase(newPurchase);
            purchaseFrame.dispose();
        }

    }

    /**
     * CREER UN FORMULAIRE POUR UNE PRESCRIPTION
     */
    public static void createPrescriptionPurchase() {
        JFrame prescriptionFrame = Gui.setFrame();
        JPanel prescriptionPanel = Gui.setPanel(prescriptionFrame);

        Gui.labelMaker(prescriptionPanel,"Saisissez la date de la prescription sous le format JJ-MM-AAAA:",
                10,10);
        JTextField dateField = Gui.textFieldMaker(prescriptionPanel,10,40);

        Gui.labelMaker(prescriptionPanel,"Sélectionner le client:",10,70);
        JComboBox customerBox = CustomerSwing.getCustomerBox(prescriptionPanel,100);

        Gui.labelMaker(prescriptionPanel,"Sélectionner le médecin prescripteur:",10,130);
        JComboBox doctorBox = DoctorSwing.getDoctorBox(prescriptionPanel,160);

        JButton backButton = Gui.buttonMaker(prescriptionPanel,"Retour",190);
        backButton.addActionListener(e -> {
            prescriptionFrame.dispose();
            ProgramSwing.generalMenu();
        });

        JButton exitButton = Gui.buttonMaker(prescriptionPanel, "Quitter", 220);
        exitButton.addActionListener(e -> {
            Singleton.closeInstanceDB();
            System.exit(0);
        });

        customerBox.addActionListener(e -> {
            Customer customer = (Customer) customerBox.getSelectedItem();

            doctorBox.addActionListener(ev ->{
                Purchase newPrescriptionPurchase = new Purchase(true);

                Doctor doctor = (Doctor) doctorBox.getSelectedItem();
                String prescriptionDate = dateField.getText();
                try {
                    Prescription newPrescription = new Prescription(prescriptionDate, doctor.getDoctorId(), customer.getCustomerId());
                    PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                    newPrescription = prescriptionDAO.create(newPrescription);
                    newPrescriptionPurchase.setPrescritionId(newPrescription.getPrescriptionId());
                    PurchaseDAO purchaseDAO = new PurchaseDAO();
                    newPrescriptionPurchase = purchaseDAO.create(newPrescriptionPurchase);
                    createPurchase(newPrescriptionPurchase);
                    newPrescription.purchaseNumber = newPrescriptionPurchase.getPurchaseNumber();
                    prescriptionFrame.dispose();
                }catch (InputException ie){
                    JOptionPane.showMessageDialog(null, "Erreur de saisie: Impossible d'ajouter la prescription");
                }
            });
        });

    }

    /**
     * CREER UNE FENETRE D ACHAT
     * @param newPurchase Purchase
     */
    public static void createPurchase(Purchase newPurchase) {
        JFrame purchaseFrame = Gui.setFrame();
        JPanel purchasePanel = Gui.setPanel(purchaseFrame);

        DrugDAO drugDAO = new DrugDAO();
        List<Drug> drugsList = drugDAO.getAll();

        Gui.labelMaker(purchasePanel, "Sélectionner le médicament à ajouter: ",10,10);
        JComboBox drugBox =  Gui.comboBoxMaker(purchasePanel,10,40,1000);
        for (Drug drug : drugsList){
            drugBox.addItem(drug);
        }
        Gui.labelMaker(purchasePanel, "Saisissez la quantité à ajouter à l'achat:",10,70);
        JTextField quantityField = Gui.textFieldMaker(purchasePanel,10,100);

        JButton addButton = Gui.buttonMaker(purchasePanel,"Ajouter",130);

        JButton saveButton = Gui.buttonMaker(purchasePanel,"Valider la commande",190);

        addButton.addActionListener(e -> {

            Drug drugToAdd;
            drugToAdd = (Drug)drugBox.getSelectedItem();
            int quantity = 0;
            try {
                if (!drugToAdd.isUnderPrescription() || ((drugToAdd.isUnderPrescription() && newPurchase.getWithPrescription()))) {
                    quantity = Integer.parseInt(quantityField.getText());
                    if (quantity <= 0) {
                        throw new InputException("La quantité est invalide.");
                    }
                    // suivi des médicaments et quantités de la commande
                    ContenirCRUD contenirCRUD = new ContenirCRUD();
                    Contenir contenir = new Contenir(newPurchase.getPurchaseId(), drugToAdd.getDrugId(), quantity);
                    contenirCRUD.create(contenir);

                    try {
                        // MAJ stock et affichage de la commande en cours, MAJ prix total
                        Drug.stockUpdate(drugToAdd, -quantity);
                        createDisplayPurchaseDrugs(purchasePanel, newPurchase);
                    } catch (InputException ie) {
                        JOptionPane.showMessageDialog(null, "Erreur de saisie ou quantité indisponible: " + ie.getMessage(),
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Ce médicament est sous ordonnance!",
                            "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }catch (InputException ie){
                JOptionPane.showMessageDialog(null,ie.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
            }

        });

        saveButton.addActionListener(e -> {
            if(newPurchase.getWithPrescription()){

                CustomerDAO customerDAO = new CustomerDAO();
                PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                Prescription prescription = prescriptionDAO.getById(newPurchase.getPurchaseId());
                Customer customer = customerDAO.getById(prescription.getCustomerId());

                // enregistrement de la commande+prescription sur le client
                prescriptionDAO.create(prescription);

                try {
                    // création et enregistrement du pdf de le prescription
                    prescription.savePrescriptionAsPdf();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                PurchaseDAO purchaseDAO = new PurchaseDAO();
                purchaseDAO.create(newPurchase);

            }
            JOptionPane.showMessageDialog(null, "La commande a été enregistré avec succès");
            purchaseFrame.dispose();
            ProgramSwing.generalMenu();
        });

        JButton cancelButton = Gui.buttonMaker(purchasePanel,"Annuler",220);
        cancelButton.addActionListener(e ->  {
            purchaseFrame.dispose();
            ProgramSwing.generalMenu();
        });

        JButton exitButton = Gui.buttonMaker(purchasePanel, "Quitter", 250);
        exitButton.addActionListener(e -> {
            Singleton.closeInstanceDB();
            System.exit(0);
        });
    }

    /**
     * MET A JOUR LE STOCK ET L HISTORIQUE APRES ANNULATION
     * @param newPurchase Purchase
     */
    public static void cancelPurchase(Purchase newPurchase){
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        ContenirCRUD contenirCRUD = new ContenirCRUD();
        purchaseDAO.delete(newPurchase);
        contenirCRUD.delete(newPurchase.getPurchaseId());
    }

    /**
     * CREER UNE FENETRE QUI AFFICHE LE CONTENU D UN ACHAT + MAJ PRIX TOTAL
     * @param panel JPanel
     * @param newPurchase Purchase
     */
    public static void createDisplayPurchaseDrugs(JPanel panel, Purchase newPurchase) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String[][] display = newPurchase.createMatrice();
        Gui.tableMaker(panel,display,purchaseHistoryTableHeaders,500,100,800,300);

        // MAJ et affichage du prix de la commande en cours
        newPurchase.setTotalPrice();
        JTextField priceField = Gui.textFieldMaker(panel,500,450);
        priceField.setText("Prix Total : " + decimalFormat.format(newPurchase.getTotalPrice()) + " €");
        priceField.setEditable(false);

        // Rest à payer en cas de commande avec prescription
        if(newPurchase.getWithPrescription()) {
            PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
            Prescription prescription = prescriptionDAO.getById(newPurchase.getPurchaseId());
            Double pricePostMutualRate = newPurchase.getTotalPrice() * (1 - getMutualRateByPrescription(prescription));
            JTextField priceFieldPostMutualRate = Gui.textFieldMaker(panel, 500, 480);
            priceFieldPostMutualRate.setText("Prix Total après déduction mutuelle : " +
                    decimalFormat.format(pricePostMutualRate) + " €");
            priceFieldPostMutualRate.setEditable(false);
        }
    }

    /**
     * CONSULTER UNE COMMANDE
     * @param purchase Purchase
     */
    public static void displayAPurchase(Purchase purchase) {
        JFrame frame2 = Gui.setPopUpFrame(1400,800);
        JPanel panel2 = Gui.setPanel(frame2);

        String[][] purchaseHistoryMatrice = purchase.createMatrice();

        Gui.tableMaker(panel2, purchaseHistoryMatrice,
                purchaseHistoryTableHeaders,10,40,1200,200);

        JButton backButton2 = Gui.buttonMaker(panel2,"Retour",300);
        backButton2.addActionListener(ev -> frame2.dispose());

        JButton exitButton = Gui.buttonMaker(panel2, "Quitter", 330);
        exitButton.addActionListener(e -> {
            Singleton.closeInstanceDB();
            System.exit(0);
        });
    }

    /**
     * RECUPERER TAUX MUTUELLE A PARTIR DE PRESCRIPTION
     * @param prescription Prescription
     * @return Double
     */
    public static Double getMutualRateByPrescription(Prescription prescription) {
        Integer customerId = prescription.getCustomerId();
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customersList = customerDAO.getAll();

        Mutual mutual = null;
        for(Customer customer : customersList){
            if(customer.getCustomerId().equals(customerId)){
                MutualDAO mutualDAO = new MutualDAO();
                mutual = mutualDAO.getById(customer.getMutualId());
            }
        }
        return mutual.getRate();
    }

}
