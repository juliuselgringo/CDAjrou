package fr.juliuselgringo.sparadrap.model;

/**
 * Classe permettant le suivi du contenu d'une commande
 */
public class Contenir {

    private Integer contenirId;
    private Integer purchaseId;
    private Integer drugId;
    private Integer quantity;

    /**
     * constructeur
     * @param purchaseId Integer
     * @param drugId Integer
     * @param quantity Integer
     */
    public Contenir(Integer purchaseId, Integer drugId, Integer quantity) {
        this.purchaseId = purchaseId;
        this.drugId = drugId;
        this.quantity = quantity;
    }

    /**
     * getter contenirId
     * @return Integer
     */
    public Integer getContenirId() {
        return this.contenirId;
    }

    /**
     * setter contenirId
     * @param contenirId Integer
     */
    public void setContenirId(Integer contenirId) {
        this.contenirId = contenirId;
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
     * getter quantity
     * @return Integer
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * setter quantity
     * @param quantity Integer
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
