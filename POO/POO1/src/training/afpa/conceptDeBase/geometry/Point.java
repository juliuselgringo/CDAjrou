package training.afpa.conceptDeBase.geometry;

public class Point {

    public Double abscisse;
    public Double ordonnee;

    /**
     *
     * @param pAbscisse
     * @param pOrdonnee
     */
    public Point(Double pAbscisse, Double pOrdonnee) {
        this.abscisse = pAbscisse;
        this.ordonnee = pOrdonnee;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "{" + abscisse + "," + ordonnee+ "}";
    }

}
