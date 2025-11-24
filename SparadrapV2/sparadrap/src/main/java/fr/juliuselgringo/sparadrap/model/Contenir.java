package fr.juliuselgringo.sparadrap.model;

public class Contenir {

    private Integer contenirId;
    private Integer purchaseId;
    private Integer drugId;
    private Integer quantity;

    public Contenir(Integer purchaseId, Integer drugId, Integer quantity) {
        this.purchaseId = purchaseId;
        this.drugId = drugId;
        this.quantity = quantity;
    }

    public Integer getContenirId() {
        return this.contenirId;
    }

    public void setContenirId(Integer contenirId) {
        this.contenirId = contenirId;
    }

    public Integer getPurchaseId() {
        return this.purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getDrugId(){
        return this.drugId;
    }

    public void setDrugId(Integer drugId){
        this.drugId = drugId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
