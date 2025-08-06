package training.afpa.conceptDeBase.exo3;

public class AppMain {
    public static void main(String[] args) {

        Rectangle monRectangle = new Rectangle(10.0,5.0);
        monRectangle.afficher();
        TestRectangle monTestRectangle = new TestRectangle();
        monTestRectangle.afficherSurfacePerimetre();
    }
}
