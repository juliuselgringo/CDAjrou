package training.afpa.conceptDeBase.geometry;

import java.text.DecimalFormat;

public class Cercle {

    private Point centre;
    private Double rayon;

    /**
     *
     * @param pCentre (Point)
     * @param rayon (Point)
     */
    public Cercle(Point pCentre, Double rayon) {
        this.centre = pCentre;
        securityRayon(rayon);
    }

    /**
     *
     * @return Double
     */
    public Double perimetre() {
        return 2 * Math.PI * this.rayon;
    }

    /**
     *
     * @return  Double
     */
    public Double surface(){
        return Math.PI * Math.pow(this.rayon, 2);
    }

    /**
     *
     * @param point (Point)
     */
    public void testAppartenance(Point point){
        double distanceAbscisse = this.centre.abscisse - point.abscisse;
        double distanceOrdonne = this.centre.ordonnee - point.ordonnee;
        Double longueurSegment = Math.sqrt(Math.pow(distanceAbscisse, 2) + Math.pow(distanceOrdonne, 2));
        if(longueurSegment.equals(this.rayon)) {
            System.out.println("Le point appartient au cerle.");
        }
        else {
            System.out.println("ATTENTION!!! Le point N'appartient PAS au cerle.");
        }
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "{" + "centre=" + centre.toString() + ", rayon=" + rayon + '}';
    }

    /**
     *
     */
    public void afficher(){
        DecimalFormat df = new DecimalFormat("#.##");
        Double surface = this.surface();
        String s = df.format(surface);
        Double perimetre = this.perimetre();
        String p =  df.format(perimetre);
        System.out.println("Le cercle " + this + " a un perimetre de " + p + " et une surface de " + s);
    }

    public void securityRayon(Double rayon){
        if(rayon >= 0.0) {
            this.rayon = rayon;
        }
    }
}
