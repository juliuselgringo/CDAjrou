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

        Gui.titleLabelMaker(prescriptionPanel,"MENU ACHAT AVEC PRESCRIPTION", 10,10,500,30);

        Gui.labelMaker(prescriptionPanel,"Saisissez la date de la prescription sous le format JJ-MM-AAAA:",
                10,70);
        JTextField dateField = Gui.textFieldMaker(prescriptionPanel,10,100);

        Gui.labelMaker(prescriptionPanel,"Sélectionner le client:",10,130);
        JComboBox customerBox = CustomerSwing.getCustomerBox(prescriptionPanel,160);

        Gui.labelMaker(prescriptionPanel,"Sélectionner le médecin prescripteur:",10,190);
        JComboBox doctorBox = DoctorSwing.getDoctorBox(prescriptionPanel,220);

        JButton validateButton = Gui.buttonMaker(prescriptionPanel, "Valider", 250);
        validateButton.addActionListener(e -> {
            Customer customer = (Customer) customerBox.getSelectedItem();

            Purchase newPrescriptionPurchase = new Purchase(true);

            Doctor doctor = (Doctor) doctorBox.getSelectedItem();
            String prescriptionDate = dateField.getText();
            try {
                //creation de la prescription en DB
                Prescription newPrescription = new Prescription(prescriptionDate, doctor.getDoctorId(), customer.getCustomerId());

                PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                newPrescription = prescriptionDAO.create(newPrescription);
                //maj commande avec id prescription
                newPrescriptionPurchase.setPrescritionId(newPrescription.getPrescriptionId());
                // creation de la commande en DB
                PurchaseDAO purchaseDAO = new PurchaseDAO();
                newPrescriptionPurchase = purchaseDAO.create(newPrescriptionPurchase);
                //vers la saisie

                createPurchase(newPrescriptionPurchase);
                //n° cde dans prescription
                newPrescription.purchaseNumber = newPrescriptionPurchase.getPurchaseNumber();
                prescriptionFrame.dispose();
            }catch (InputException ie){
                JOptionPane.showMessageDialog(null, "Erreur de saisie: Impossible d'ajouter la prescription");
            }
        });

        JButton backButton = Gui.buttonMaker(prescriptionPanel,"Retour",490);
        backButton.addActionListener(e -> {
            prescriptionFrame.dispose();
            ProgramSwing.generalMenu();
        });

        JButton exitButton = Gui.buttonMaker(prescriptionPanel, "Quitter", 520);
        exitButton.addActionListener(e -> {
            Singleton.closeInstanceDB();
            System.exit(0);
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

        Gui.titleLabelMaker(purchasePanel,"SAISIE DE LA COMMANDE", 10,10,500,30);

        Gui.labelMaker(purchasePanel, "Sélectionner le médicament à ajouter: ",10,100);
        JComboBox drugBox =  Gui.comboBoxMaker(purchasePanel,10,130,1000);
        for (Drug drug : drugsList){
            drugBox.addItem(drug);
        }
        Gui.labelMaker(purchasePanel, "Saisissez la quantité à ajouter à l'achat:",10,160);
        JTextField quantityField = Gui.textFieldMaker(purchasePanel,10,190);

        JButton addButton = Gui.buttonMaker(purchasePanel,"Ajouter",220);

        JButton saveButton = Gui.buttonMaker(purchasePanel,"Valider la commande",250);

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
                        drugToAdd.stockUpdate(-quantity);
                        createDisplayPurchaseDrugs(purchasePanel, newPurchase, 500, 200);
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
                Prescription prescription = prescriptionDAO.getById(newPurchase.getPrescriptionId());

                // enregistrement de la prescription sur le client
                prescriptionDAO.update(prescription);

                try {
                    // création et enregistrement du pdf de le prescription
                    prescription.savePrescriptionAsPdf(newPurchase.getPurchaseId());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                PurchaseDAO purchaseDAO = new PurchaseDAO();
                purchaseDAO.update(newPurchase);

            }
            JOptionPane.showMessageDialog(null, "La commande a été enregistré avec succès");
            purchaseFrame.dispose();
            ProgramSwing.generalMenu();
        });

        JButton cancelButton = Gui.buttonMaker(purchasePanel,"Annuler",490);
        cancelButton.addActionListener(e ->  {

            cancelPurchase(newPurchase);

            purchaseFrame.dispose();
            ProgramSwing.generalMenu();
        });

        JButton exitButton = Gui.buttonMaker(purchasePanel, "Quitter", 520);
        exitButton.addActionListener(e -> {

            cancelPurchase(newPurchase);

            Singleton.closeInstanceDB();
            System.exit(0);
        });
    }

    /**
     * supprime la commande en cours, son contenu, sa prescription et met à jour le stock
     * @param newPurchase Purchase
     */
    public static void cancelPurchase(Purchase newPurchase){
        ContenirCRUD contenirCRUD = new ContenirCRUD();
        List<Contenir> purchaseContain = contenirCRUD.selectAll(newPurchase.getPurchaseId());
        // mise à jour des stock
        for(Contenir contenir : purchaseContain){
            DrugDAO drugDAO = new DrugDAO();
            Drug drugToUpdate = drugDAO.getById(contenir.getDrugId());
            try {
                drugToUpdate.stockUpdate(contenir.getQuantity());
            } catch (InputException ex) {
                throw new RuntimeException(ex);
            }
        }
        // suppression du contenu de la commande
        contenirCRUD.delete(newPurchase.getPurchaseId());

        PurchaseDAO purchaseDAO = new PurchaseDAO();

        // suppression de la commande et de la prescription
        if(newPurchase.getWithPrescription()){
            PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
            Prescription prescriptionToDelete = prescriptionDAO.getById(newPurchase.getPrescriptionId());
            purchaseDAO.delete(newPurchase);
            prescriptionDAO.delete(prescriptionToDelete);
        }else{
            purchaseDAO.delete(newPurchase);
        }

    }

    /**
     * CREER UNE FENETRE QUI AFFICHE LE CONTENU D UN ACHAT + MAJ PRIX TOTAL
     * @param panel JPanel
     * @param newPurchase Purchase
     */
    public static void createDisplayPurchaseDrugs(JPanel panel, Purchase newPurchase, int x, int y) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String[][] display = newPurchase.createMatrice();
        Gui.tableMaker(panel,display,purchaseHistoryTableHeaders,x,y,800,300);

        // MAJ et affichage du prix de la commande en cours
        newPurchase.setTotalPrice();
        JTextField priceField = Gui.textFieldMaker(panel,500,550);
        priceField.setText("Prix Total : " + decimalFormat.format(newPurchase.getTotalPrice()) + " €");
        priceField.setEditable(false);

        // Rest à payer en cas de commande avec prescription
        if(newPurchase.getWithPrescription()) {
            PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
            Prescription prescription = prescriptionDAO.getById(newPurchase.getPrescriptionId());
            Double pricePostMutualRate = newPurchase.getTotalPrice() * (1 - getMutualRateByPrescription(prescription));
            JTextField priceFieldPostMutualRate = Gui.textFieldMaker(panel, 500, 580);
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
