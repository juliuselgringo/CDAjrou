package training.afpa.exo4.geometry;

public class Cercle {

    private Point centre;
    private Double rayon;

    public Cercle(Point pCentre, Double rayon) {
        this.centre = pCentre;
        this.rayon = rayon;
    }

    public Double perimetre() {
        Double p = 2 * Math.PI * this.rayon;
        return p;
    }

    public Double surface(){
        Double s = Math.PI * Math.pow(this.rayon, 2);
        return s;
    }

    public void testAppartenance(Point point){
        Double distanceAbscisse = this.centre.abscisse - point.abscisse;
        Double distanceOrdonne = this.centre.ordonnee - point.ordonnee;
        double longueurSegment = Math.sqrt(Math.pow(distanceAbscisse, 2) + Math.pow(distanceOrdonne, 2));
        if(longueurSegment == this.rayon) {
            System.out.println("Le point appartient au cerle.");
        }
        else {
            System.out.println("ATTENTION!!! Le point N'appartient PAS au cerle.");
        }
    }
}
