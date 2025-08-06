package training.afpa.conceptDeBase.exo3;

import java.text.DecimalFormat;

public class Rectangle {

    private Double longueur;
    private Double largeur;

    /**
     *
     * @param longueur (Double)
     * @param largeur (Double)
     */
    public Rectangle(Double longueur, Double largeur) {
        this.longueur = security(longueur);
        this.largeur = security(largeur);
    }

    /**
     *
     * @return Double
     */
    public Double surface(){
        return this.longueur * this.largeur;
    }

    /**
     *
     * @return Double
     */
    public Double perimetre(){
        return (this.longueur + this.largeur) * 2;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Rectangle " + this.longueur + " x " + this.largeur;
    }

    /**
     *
     */
    public void afficher(){
        DecimalFormat df = new DecimalFormat("#.##");
        String s = df.format(this.surface());
        String p = df.format(this.perimetre());
        System.out.println("Le rectangle " + this + " a une surface de " + s +
                "cm^2 et a un perimetre de " + p + "cm");
    }

    /**
     *
     * @param doubleToTest (Double)
     * @return Double
     */
    private Double security(Double doubleToTest){
        if(doubleToTest <= 0.0){
            System.out.println("Erreur lors de la saisie de la valeur.");
            return null;
        }
        else{
            return doubleToTest;
        }
    }

}
