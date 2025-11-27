package fr.juliuselgringo.sparadrap.model;

import fr.juliuselgringo.sparadrap.DAO.DrugDAO;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * classe médicament
 */
public class Drug {

    private Integer drugId;
    private String name;
    private Integer categoryId;
    private Double price;
    private LocalDate productionDate;
    private int quantity;
    private Boolean underPrescription;

    private Integer totalOut = 0;

    private final String regexName = "[A-Z][a-z]+([\\s][A-Z][a-z]+)?([\\s][0-9]+)?";

    /**
     * CONSTRUCTOR
     * @param name String
     * @param categoryId Integer
     * @param price Double
     * @param productDate String
     * @param quantity int
     * @param underPrescription Boolean
     * @throws InputException String
     */
    public Drug(String name, Integer categoryId, Double price, String productDate,
                int quantity, Boolean underPrescription) throws InputException {
        this.setName(name);
        this.categoryId = categoryId;
        this.setPrice(price);
        this.setProductionDate(productDate);
        this.setQuantity(quantity);
        this.setUnderPrescription(underPrescription);
    }

    /**
     * CONSTRUCTOR
     * @param drugId Integer
     * @param name String
     * @param categoryId Integer
     * @param price Double
     * @param productDate String
     * @param quantity int
     * @param underPrescription Boolean
     * @throws InputException String
     */
    public Drug(Integer drugId, String name, Integer categoryId, Double price, String productDate,
                int quantity, Boolean underPrescription) throws InputException {
        this.drugId = drugId;
        this.setName(name);
        this.categoryId = categoryId;
        this.setPrice(price);
        this.setProductionDate(productDate);
        this.setQuantity(quantity);
        this.setUnderPrescription(underPrescription);
    }

    /**
     * CONSTRUCTOR
     */
    public Drug(){};

    /**
     * getter drugId
     * @return Integer
     */
    public Integer getDrugId(){
        return this.drugId;
    }

    /**
     * setter drugId
     * @param drugId Integer
     */
    public void setDrugId(Integer drugId){
        this.drugId = drugId;
    }

    /**
     * GETTER name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * SETTER name
     * @param name String
     * @throws InputException String
     */
    public void setName(String name) throws InputException {
        name = name.trim();
        if (name.isEmpty() || name == null) {
            throw new InputException("First name cannot be empty or null");
        }else if (!name.matches(regexName)) {
            throw new InputException("Le nom du médicament doit commencer par une majuscule et ne doit pas avoir d'accent ni trait d'union");
        }else{
            this.name = name;
        }
    }

    /**
     * GETTER price
     * @return Double
     */
    public Double getPrice() {
        return price;
    }

    /**
     * SETTER price
     * @param price Double
     * @throws InputException String
     */
    public void setPrice(Double price) throws InputException {
        if(price == null || price <= 0){
            throw new InputException("Le prix ne peux être inférieur ou égal à 0");
        }else{
            this.price = price;
        }
    }

    /**
     * GETTER productionDate
     * @return LocaleDate
     */
    public LocalDate getProductionDate() {
        return productionDate;
    }

    /**
     * SETTER productionDate
     * @param productDate String
     * @throws InputException String
     */
    public void setProductionDate(String productDate) throws InputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String productionDate = productDate.trim();
        String regexDate = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}";
        LocalDate productionDateP =  null;
            if(productionDate == null ||productionDate.isEmpty() || !productionDate.matches(regexDate)) {
                throw new InputException("La date de production est invalide (jj-mm-aaaa).");
            }
            productionDateP = LocalDate.parse(productDate.trim(), formatter);

            if (productionDateP.isAfter(LocalDate.now())) {
                throw new InputException("La date de production doit être antérieure à la date d'aujourd'hui.");
            } else {
                this.productionDate = productionDateP;
            }

    }

    /**
     * GETTER quantity
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * SETTER quantity
     * @param quantity int
     * @throws InputException String
     */
    public void setQuantity(int quantity) throws InputException {
        if(quantity < 0){
            throw new InputException("La quantité ne peut être inférier à 0");
        }else {
            this.quantity = quantity;
        }
    }

    /**
     * GETTER underPrescription
     * @return Boolean
     */
    public Boolean isUnderPrescription() {
        return this.underPrescription;
    }

    /**
     * SETTER underPrescription
     * @param underPrescription Boolean
     */
    public void setUnderPrescription(Boolean underPrescription) {
        this.underPrescription = underPrescription;
    }

    /**
     * GETTER categoryId
     * @return Integer
     */
    public Integer getCategoryId() {
        return this.categoryId;
    }

    /**
     * SETTER categoryId
     * @param categoryId Integer
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * GETTER totalOut
     * @return Integer
     */
    public Integer getTotalOut(){
        return this.totalOut;
    }

    /**
     * SETTER totalOut
     * @param totalOut Integer
     * @throws InputException String
     */
    public void setTotalOut(Integer totalOut) throws InputException {
        if(totalOut == null || totalOut < 0){
            throw new InputException("La quantité ne peux être négative ou nul");
        }else{
            this.totalOut = totalOut;
        }
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "\n\nNom: " + this.getName() +
                "\n Catégorie: " + this.getCategoryId() +
                "\n Prix: " + this.getPrice() +
                "\n Date de production: " + this.getProductionDate().format(formatter) +
                "\n Quantité en stock: " + this.getQuantity() +
                "\n UnderPrescription: " + this.isUnderPrescription();
    }

    /**
     * MISE A JOUR DU STOCK
     * @param quantity int
     * @throws InputException String
     */
    public void stockUpdate(int quantity) throws InputException {
        DrugDAO drugDAO = new DrugDAO();
        Drug drug = drugDAO.getById(this.getDrugId());
        drug.setQuantity(drug.getQuantity() + quantity);
        drugDAO.update(drug);
    }

    /**
     * CREER UNE MATRICE DES MEDICAMENTS
     * @return String[][]
     */
    public static String[][] createDrugsMatrice(){
        DrugDAO drugDAO = new DrugDAO();
        List<Drug> drugsList = drugDAO.getAll();
        drugDAO.closeConnection();
        String[][] matrices = new String[drugsList.size()][7];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int i = 0;
        for (Drug drug : drugsList) {
            matrices[i][0] = drug.getDrugId().toString();
            matrices[i][1] = drug.getName();
            matrices[i][2] = drug.getCategoryId().toString();
            matrices[i][3] = drug.getPrice().toString();
            matrices[i][4] = drug.getProductionDate().format(formatter);
            matrices[i][5] = ((Integer)drug.getQuantity()).toString();
            matrices[i][6] = drug.isUnderPrescription().toString();
            i++;
        }
        return matrices;
    }

}
