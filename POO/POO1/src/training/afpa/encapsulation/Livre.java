package training.afpa.encapsulation;

public class Livre {

    private String titre;
    private String auteur;
    private int nbPages;
    private Double price;
    private Boolean fixedPrice = false;

    /**
     * CONSTRUCTOR
     */
    public Livre(){}

    /**
     * CONSTRUCTOR
     * @param unAuteur (String)
     * @param unTitre (String)
     */
    public Livre(String unAuteur, String unTitre){
        this.auteur = unAuteur;
        this.titre = unTitre;
    }

    /**
     * CONSTRUCTOR
     * @param unAuteur String
     * @param unTitre String
     * @param nbPages int
     */
    public Livre(String unAuteur, String unTitre, int nbPages){
        this.auteur = unAuteur;
        this.titre = unTitre;
        securityNbPages(nbPages);
    }

    /**
     * CONSTRUCTOR
     * @param pTitre String
     * @param pPrice int
     */
    public Livre(String pTitre, Double pPrice){
        this.titre = pTitre;
        if(pPrice > 0){
            this.price = pPrice;
            this.fixedPrice = true;
        }
    }

    /**
     * GETTER auteur
     * @return String
     */
    public String getAuteur(){
        return this.auteur;
    }

    /**
     * GETTER titre
     * @return String
     */
    public String getTitre(){
        return this.titre;
    }

    /**
     * GETTER nbPages
     * @return int
     */
    public int getNbPages(){
        return this.nbPages;
    }

    /**
     * GETTER price
     * @return Double
     */
    public Double getPrice(){
        return this.price;
    }

    /**
     * SETTER auteur
     * @param unAuteur (String)
     */
    public void setAuteur(String unAuteur){
        this.auteur = unAuteur;
    }

    /**
     * SETTER titre
     * @param unTitre (String)
     */
    public void setTitre(String unTitre){
        this.titre = unTitre;
    }

    /**
     * SETTER nbPages
     * @param pNbPages (int)
     */
    public void setNbPages(int pNbPages){
        securityNbPages(pNbPages);
    }

    /**
     * SETTER price
     * @param pPrice Double
     */
    public void setPrice(Double pPrice){
        if(pPrice != null &&  pPrice > 0 &&  !this.fixedPrice){
            this.price = pPrice;
            this.fixedPrice = true;
        }
        else{
            System.err.println("Le prix pour '" + this.titre + "' saisi est soit invalide, soit déjà fixé et ne peux être modifié.");
        }
    }

    /**
     * @param nbPages (int)
     */
    private void securityNbPages(int nbPages){
        if(nbPages <= 0){
            System.err.println("Le nombre de pages est negatif");
        }
        else{
            this.nbPages = nbPages ;
        }
    }

}
