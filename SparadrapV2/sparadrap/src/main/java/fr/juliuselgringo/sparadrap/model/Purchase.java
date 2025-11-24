package fr.juliuselgringo.sparadrap.model;

import fr.juliuselgringo.sparadrap.DAO.ContenirCRUD;
import fr.juliuselgringo.sparadrap.DAO.DrugDAO;
import fr.juliuselgringo.sparadrap.DAO.PrescriptionDAO;
import fr.juliuselgringo.sparadrap.DAO.PurchaseDAO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * classe achat
 */
public class Purchase implements Serializable {

    private Integer purchaseId;


    private LocalDate purchaseDate;
    private Integer purchaseNumber;
    private Boolean withPrescription;
    private Double totalPrice = 0.0;
    private Integer prescriptionId;


    /**
     * increment pour les numéros d'achat
     */
    public static int incrementPurchaseNumber = 1;

    /**
     * CONSTRUCTOR
     * @param withPrescription Boolean
     */
    public Purchase(Boolean withPrescription) {
        this.purchaseDate = LocalDate.now();
        this.withPrescription = withPrescription;
        this.purchaseNumber = incrementPurchaseNumber;
        incrementPurchaseNumber++;
    }

    /**
     * CONSTRUCTOR
     * @param purchaseDate String
     * @param withPrescription Boolean
     */
    public Purchase(String purchaseDate, Boolean withPrescription) {
        setPurchaseDate(purchaseDate);
        this.withPrescription = withPrescription;
        this.purchaseNumber = incrementPurchaseNumber;
        incrementPurchaseNumber++;
    }

    /**
     * CONSTRUCTOR
     * @param purchaseId Integer
     * @param purchaseDate String
     * @param withPrescription Boolean
     */
    public Purchase(Integer purchaseId, String purchaseDate, Boolean withPrescription, Integer prescriptionId) {
        setPurchaseDate(purchaseDate);
        this.purchaseId = purchaseId;
        this.withPrescription = withPrescription;
        this.purchaseNumber = incrementPurchaseNumber;
        this.prescriptionId =  prescriptionId;
        incrementPurchaseNumber++;
    }

    /**
     * getter purchaseId
     * @return Integer
     */
    public Integer getPurchaseId() {
        return this.purchaseId;
    }

    /**
     * setter purchaseId
     * @param purchaseId Integer
     */
    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * GETTER purchaseDate
     * @return LocalDate
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * SETTER purchaseDate
     * @param purchaseDate String
     */
    public  void setPurchaseDate(String purchaseDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.purchaseDate = LocalDate.parse(purchaseDate, formatter);
    }

    /**
     * GETTER purchaseNumber
     * @return Integer
     */
    public Integer getPurchaseNumber() {
        return this.purchaseNumber;
    }

    /**
     * GETTER withPrescription
     * @return Boolean
     */
    public Boolean getWithPrescription() {
        return withPrescription;
    }

    /**
     * GETTER prescriptionId
     * @return Integer
     */
    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    /**
     * setter de prescriptionId
     * @param prescriptionId Integer
     */
    public void setPrescritionId(Integer prescriptionId){
        this.prescriptionId = prescriptionId;
    }



    /**
     * GETTER totalPrice
     * @return Double
     */
    public Double getTotalPrice(){
        return this.totalPrice;
    }

    /**
     * retourne le prix totale d'une commande
     * @return Double
     */
    public void setTotalPrice(){
        List<Contenir> contenirs = this.getContent();
        for (Contenir contenir : contenirs) {
            DrugDAO drugDAO = new DrugDAO();
            this.totalPrice += contenir.getQuantity() * drugDAO.getById(contenir.getDrugId()).getPrice();
        }

    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String stringToReturn = "\nDate: " + this.getPurchaseDate().format(formatter) +
                ", Numéro de commande: " + this.getPurchaseNumber();
        return stringToReturn;
    }

    /**
     * CREER UNE LISTE DE COMMANDE PAR PERIODE
     * @param startDate LocalDate
     * @param endDate LocalDate
     * @return List
     */
    public static List<Purchase> searchPurchaseByPeriod(LocalDate startDate, LocalDate endDate) {
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        return purchaseDAO.getByDate(startDate, endDate);

    }

    /**
     * CREER UNE MATRICE DES ACHATS
     * @return String[][]
     */
    public static String[][] createPurchasesMatrice(){

        PurchaseDAO purchaseDAO = new PurchaseDAO();
        List<Purchase> purchasesHistory = purchaseDAO.getAll();

        String[][] purchaseMatrice = new String[purchasesHistory.size()][5];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int i = 0;
        try {
            for (Purchase purchase : purchasesHistory) {
                purchaseMatrice[i][0] = purchase.getPurchaseDate().format(formatter);
                purchaseMatrice[i][1] = purchase.getPurchaseNumber().toString();
                if(!purchase.getWithPrescription()){
                    purchaseMatrice[i][2] = "sans ordonnance";
                }else {
                    PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                    purchaseMatrice[i][2] = prescriptionDAO.getById(purchase.getPrescriptionId()).getCustomer().getLastName();
                }
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return purchaseMatrice;
    }

    /**
     * retourne une liste des médicaments/quantités contenu dans une commande
     * @return List
     */
    public List<Contenir> getContent(){
        ContenirCRUD contenirCRUD = new ContenirCRUD();

        return contenirCRUD.selectAll(this.getPurchaseId());
    }

    public String[][] createMatrice(){
        List<Contenir> contenirs = this.getContent();
        String[][] purchaseMatrice = new String[contenirs.size()][5];
        int i = 0;
        for (Contenir contenir : contenirs) {
            purchaseMatrice[i][0] = this.getPurchaseDate().toString();
            purchaseMatrice[i][1] = this.getPurchaseNumber().toString();
            if(!this.getWithPrescription()){
                purchaseMatrice[i][2] = "NA";
            }else {
                PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                purchaseMatrice[i][2] = prescriptionDAO.getById(this.getPrescriptionId()).getCustomer().getLastName();
            }
            DrugDAO drugDAO = new DrugDAO();
            purchaseMatrice[i][3] = drugDAO.getById(contenir.getDrugId()).getName();
            purchaseMatrice[i][4] = contenir.getQuantity().toString();
            i++;
        }

        return purchaseMatrice;
    }

}
