package training.afpa.exo3;

public class Rectangle {

    private Double longueur;
    private Double largeur;

    public Rectangle(Double longueur, Double largeur) {
        this.longueur = security(longueur);
        this.largeur = security(largeur);
    }

    public Double surface(){
        Double s = this.longueur * this.largeur;
        return s;
    }

    public Double perimetre(){
        Double p = (this.longueur + this.largeur) * 2;
        return p;
    }

    @Override
    public String toString() {
        return "Rectangle " + this.longueur + " x " + this.largeur;
    }

    public void afficher(){
        Double s = this.surface();
        Double p = this.perimetre();
        System.out.println("Le rectangle " + this.toString() + " a une surface de " + s +
                "cm^2 et a un perimetre de " + p + "cm");
    }

    private Double security(Double doubleToTest){
        if(doubleToTest <= 0.0 || doubleToTest == null){
            System.out.println("Erreur lors de la saisie de la valeur.");
            return null;
        }
        else{
            return doubleToTest;
        }
    }

}
