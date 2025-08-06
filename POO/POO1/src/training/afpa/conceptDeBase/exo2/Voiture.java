package training.afpa.conceptDeBase.exo2;

public class Voiture {

    private String marque;
    private Double prix;

    /**
     *
     */
    public Voiture() {}

    public void setMarque(String marque) {
        this.marque = securityStr(marque);
    }

    /**
     *
     * @return String
     */
    public String getMarque() {
        return marque;
    }

    /**
     *
     * @param prix (Double)
     */
    public void setPrix(Double prix) {
        this.prix = securityInt(prix);
    }

    /**
     *
     * @return Double
     */
    public Double getPrix() {
        return  prix;
    }

    /**
     *
     */
    public void afficher() {
        System.out.println("Marque : " + this.getMarque() + "\nPrix : " + this.getPrix());
    }

    /**
     *
     * @param stringToTest (String)
     * @return String
     */
    private String securityStr(String stringToTest){
        if(!stringToTest.isEmpty()){
            return stringToTest;
        }
        else{
            System.out.println("Erreur lors de la saisie de la valeur.");
            return null;
        }
    }

    /**
     *
     * @param doubleToTest (Double)
     * @return Double
     */
    private Double securityInt(Double doubleToTest){
        if(doubleToTest > 0.00){
            return doubleToTest;
        }
        else{
            System.out.println("Erreur lors de la saisie de la valeur.");
            return null;
        }
    }
}
