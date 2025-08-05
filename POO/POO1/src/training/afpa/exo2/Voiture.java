package training.afpa.exo2;

public class Voiture {

    private String marque;
    private Double prix;

    public Voiture() {}

    public void setMarque(String marque) {
        this.marque = securityStr(marque);
    }

    public String getMarque() {
        return marque;
    }

    public void setPrix(Double prix) {
        this.prix = securityInt(prix);
    }

    public Double getPrix() {
        return  prix;
    }

    public void afficher() {
        System.out.println("Marque : " + marque + "\nPrix : " + prix);
    }

    private String securityStr(String stringToTest){
        if(stringToTest.length()>0 && stringToTest != null){
            return stringToTest;
        }
        else{
            System.out.println("Erreur lors de la saisie de la valeur.");
            return null;
        }
    }

    private Double securityInt(Double doubleToTest){
        if(doubleToTest > 0.00 && doubleToTest != null){
            return doubleToTest;
        }
        else{
            System.out.println("Erreur lors de la saisie de la valeur.");
            return null;
        }
    }
}
