package training.afpa.conceptDeBase.geometry;

public class TestCercle {

    public static void main(String[] args) {

        Point leCentre = new Point(10.0,10.0);
        Cercle monCercle = new Cercle(leCentre,5.0);
        Point unPoint = new Point(10.0,5.0);
        monCercle.testAppartenance(unPoint);
        monCercle.afficher();

    }
}
