package fr.juliuselgringo.sparadrap.view;

import fr.juliuselgringo.sparadrap.DAO.DrugCategoryDAO;
import fr.juliuselgringo.sparadrap.DAO.DrugDAO;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Drug;
import fr.juliuselgringo.sparadrap.model.DrugCategory;
import fr.juliuselgringo.sparadrap.utility.Gui;

import javax.swing.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * classe qui permet d'afficher les fonctionnalités liées aux médicaments
 */
public class DrugSwing {

    /**
     * constructeur par défaut
     */
    public DrugSwing(){}

    /**
     * Menu médicament
     */
    public static void drugMenu(){
        JFrame frameMenu = Gui.setFrame();
        JPanel panel = Gui.setPanel(frameMenu);

        DrugDAO drugDAO = new DrugDAO();
        List<Drug> drugsList = drugDAO.getAll();
        drugDAO.closeConnection();

        Gui.labelMaker(panel,"Sélectionner un médicament dans le tableau: ",10,10);
        JTable table = setTable(panel);

        JButton detailButton = Gui.buttonMaker(panel,"Détails du médicament", 130);
        JButton modifyButton = Gui.buttonMaker(panel, "Modifier un médicament",160);
        JButton deleteButton = Gui.buttonMaker(panel, "Supprimer un médicament",190);
        JButton createButton = Gui.buttonMaker(panel,"Creer un médicament",220);

        detailButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                Drug drug = drugsList.get(row);
                displayDrug(drug);
            }
        });

        modifyButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                Drug drug = drugsList.get(row);
                formDrug(drug, "modify", frameMenu);
            }
        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                Drug drug = drugsList.get(row);
                deleteDrug(drug,frameMenu);
            }
        });

        createButton.addActionListener(e -> {
           createDrug(frameMenu);
        });

        JButton back2Button = Gui.buttonMaker(panel,"Retour",340);
        back2Button.addActionListener(ev -> {
            frameMenu.dispose();
            ProgramSwing.generalMenu();
        });

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 370);
        exitButton2.addActionListener(eve -> System.exit(0));

    }

    /**
     * AFFICHER LES DETAILS D UN MEDICAMENT
     * @param drug Drug
     */
    public static void displayDrug(Drug drug){
        JFrame frame = Gui.setPopUpFrame(1200,500);
        JPanel panel = Gui.setPanel(frame);
        Gui.textAreaMaker(panel, drug.toString(),10,10,1200,300 );

        JButton back2Button = Gui.buttonMaker(panel,"Retour",340);
        back2Button.addActionListener(ev -> frame.dispose());

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 370);
        exitButton2.addActionListener(eve -> System.exit(0));
    }

    /**
     * FORMULAIRE POUR MODIFIER OU CREER UN MEDICAMENT
     * type String "modify" ou "create"
     * @param drug Drug
     * @param type String
     * @param frameMenu JFrame
     */
    public static void formDrug(Drug drug, String type,JFrame frameMenu){
        JFrame frameForm = Gui.setPopUpFrame(800,1000);
        JPanel panel = Gui.setPanel(frameForm);

        DrugDAO drugDAO = new DrugDAO();
        List<Drug> drugsList = drugDAO.getAll();
        drugDAO.closeConnection();

        Gui.labelMaker(panel,"Nom: ",10,10);
        JTextField nameField = Gui.textFieldMaker(panel,10,40);
        nameField.setText(drug.getName());

        Gui.labelMaker(panel,"Catégorie: ",400,10);
        DrugCategoryDAO drugCategoryDAO = new DrugCategoryDAO();
        List<DrugCategory> drugCategoryList = drugCategoryDAO.getAll();
        drugCategoryDAO.closeConnection();
        JComboBox categoryNameField = Gui.comboBoxMaker(panel,400,40,200);
        for(DrugCategory drugCategory : drugCategoryList){
            categoryNameField.addItem(drugCategory);
        }


        Gui.labelMaker(panel,"Prix: ",10,70);
        JTextField priceField = Gui.textFieldMaker(panel,10,100);
        try {
            priceField.setText(drug.getPrice().toString());
        }catch(NullPointerException npe){}

        Gui.labelMaker(panel,"Date de production : ",10,130);
        JTextField productionDateField = Gui.textFieldMaker(panel,10,160);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            productionDateField.setText(drug.getProductionDate().format(formatter));
        }catch(NullPointerException npe){}

        Gui.labelMaker(panel,"Quantité: ",400,130);
        JTextField quantityField = Gui.textFieldMaker(panel,400,160);
        quantityField.setText(((Integer)drug.getQuantity()).toString());

        Gui.labelMaker(panel,"Sous prescription (true ou false)",10,190);
        JTextField isUnderPrescriptionField = Gui.textFieldMaker(panel,10,220);
        try {
            isUnderPrescriptionField.setText(drug.isUnderPrescription().toString());
        }catch(NullPointerException npe){}

        JButton save =  Gui.buttonMaker(panel,"Enregistrer",450);

        JButton back2Button = Gui.buttonMaker(panel,"Annuler",480);
        back2Button.addActionListener(ev -> {
            frameMenu.dispose();
            frameForm.dispose();
            drugMenu();
        });

        JButton exitButton2 = Gui.buttonMaker(panel, "Quitter", 510);
        exitButton2.addActionListener(eve -> {
            System.exit(0);
        });

        save.addActionListener(ev -> {
            try {
                drug.setName(nameField.getText());
                DrugCategory drugCategory = (DrugCategory) categoryNameField.getSelectedItem();
                drug.setCategoryId(drugCategory.getCategoryId());
                drug.setPrice(Double.parseDouble(priceField.getText()));
                drug.setProductionDate(productionDateField.getText());
                drug.setQuantity(Integer.parseInt(quantityField.getText()));
                drug.setUnderPrescription(Boolean.parseBoolean(isUnderPrescriptionField.getText()));

                DrugDAO drugDAO1 = new DrugDAO();
                if(type.equals("create")){
                    drugDAO1.create(drug);
                    drugDAO1.closeConnection();
                }else{
                    drugDAO1.update(drug);
                    drugDAO1.closeConnection();
                }

                JOptionPane.showMessageDialog(null,"Vos modification ont bien été enregitré",
                        "Success",JOptionPane.INFORMATION_MESSAGE);
                frameForm.dispose();
                frameMenu.dispose();
                drugMenu();
            } catch (InputException ie) {
                JOptionPane.showMessageDialog(null, ie.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    /**
     * SUPPRIMER UN MEDICAMENT
     * @param drug Drug
     * @param frame JFrame
     */
    public static void deleteDrug(Drug drug, JFrame frame){
        DrugDAO drugDAO = new DrugDAO();
        List<Drug> drugsList = drugDAO.getAll();
        drugDAO.closeConnection();
        int resp = JOptionPane.showConfirmDialog(null,"Etes vous sur de vouloir supprimer ce médicament?",
                "Confirmation",JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            drugsList.remove(drug);
            JOptionPane.showMessageDialog(null, "Le médicament a été supprimé avec succès.",
                    "Succès",JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            drugMenu();
        }

        frame.dispose();
        drugMenu();
    }

    /**
     * CREER UN TABLEAU DES MEDICAMENTS
     * @param panel JPanel
     * @return Jtable
     */
    public static JTable setTable(JPanel panel){
        String[] header = new String[]{"Id", "Nom","Catégorie","Prix","Date de Production","Quantité", "Sous prescription"};
        JTable table = Gui.tableMaker(panel, Drug.createDrugsMatrice(),header,800, 40,800,800);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return table;
    }

    /**
     * Créer un médicament
     * @param frame JFrame
     */
    public static void createDrug(JFrame frame){
        Drug drug = new Drug();
        formDrug(drug, "create", frame);
    }

}
