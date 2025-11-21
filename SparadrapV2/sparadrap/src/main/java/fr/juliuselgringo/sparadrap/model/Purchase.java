package fr.juliuselgringo.sparadrap.model;

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
    private Double totalPrice;
    private String[][] purchaseDetails;
    private Integer prescriptionId;

    /**
     * liste medicament, quantite d'une commande
     */
    private static Map<Drug, Integer> purchaseDrugsQuantity = new HashMap<>();

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
     * GETTER purchaseDrugsQuantity
     * @return Map
     */
    public Map<Drug, Integer>  getPurchaseDrugsQuantity() {
        return this.purchaseDrugsQuantity;
    }

    /**
     * GETTER totalPrice
     * @return Double
     */
    public Double getTotalPrice(){
        return this.totalPrice;
    }

    /**
     * SETTER totalPrice
     */
    public void setTotalPrice() {
        this.totalPrice = 0.00;
        Map<Drug, Integer> purchaseMap = this.getPurchaseDrugsQuantity();
        for(Map.Entry<Drug, Integer> entity : purchaseMap.entrySet()) {
            Drug drug = entity.getKey();
            int quantity = entity.getValue();
            this.totalPrice += drug.getPrice() * quantity;
        }
    }

    /**
     * SETTER purchaseDrugsQuantity
     * @param drug Drug
     * @param quantity int
     */
    public void setPurchaseDrugsQuantity(Drug drug, int quantity) {
        if(this.withPrescription){
            PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
            Prescription prescription = prescriptionDAO.getById(this.prescriptionId);
            prescription.setDrugsQuantityPrescriptionList(drug,quantity);
            this.purchaseDrugsQuantity = prescription.getDrugsQuantityPrescriptionList();
        }else {
            this.purchaseDrugsQuantity.put(drug, quantity);
        }
    }

    /**
     * GETTER purchaseDetails
     * @return String[][]
     */
    public String[][] getPurchaseDetails() {
        return this.purchaseDetails;
    }

    /**
     * SETTER purchaseDetails
     */
    public void setPurchaseDetails(){
        String[][] purchaseDetails = new String[this.purchaseDrugsQuantity.size()][5];
        int i = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Drug drug : this.purchaseDrugsQuantity.keySet()) {
            purchaseDetails[i][0] = this.getPurchaseDate().format(formatter);
            purchaseDetails[i][1] = this.getPurchaseNumber().toString();
            if(this.withPrescription) {
                PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
                Prescription prescription = prescriptionDAO.getById(this.prescriptionId);
                purchaseDetails[i][2] = prescription.getCustomer().getLastName();
            }else {
                purchaseDetails[i][2] = "Anonyme (achat sans prescription)";
            }
            purchaseDetails[i][3] = drug.getName();
            i++;
        }
        i = 0;
        for (Integer quantity : this.purchaseDrugsQuantity.values()) {
            purchaseDetails[i][4] = quantity.toString();
            i++;
        }

        this.purchaseDetails = purchaseDetails;
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
     * @return ArrayList
     */
    public static ArrayList searchPurchaseByPeriod(LocalDate startDate, LocalDate endDate) {
        // créer la requete purchaseDAO
        return null;
    }

    /**
     * SUPPRIMER UNE COMMANDE L HISTORIQUE
     */
    public void deletePurchaseFromHistory() {
        // créer le delete purchaseDAO
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
                    purchaseMatrice[i][2] = purchase.getPrescriptionId().toString();
                }
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return purchaseMatrice;
    }

}
