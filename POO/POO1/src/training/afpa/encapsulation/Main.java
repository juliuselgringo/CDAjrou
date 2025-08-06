package training.afpa.encapsulation;

public class Main {

    public static void main(String[] args) {

        Livre harryPottier = new Livre("J.R. Rousseling", "Harry Pottier");
        Livre leSoigneurDesAgneaux = new Livre("J.R. Tolesselot", "Le Soigneur Des Agneaux");

        System.out.println(harryPottier.getAuteur());
        System.out.println(leSoigneurDesAgneaux.getAuteur());


        Livre myTestBook =  new Livre("J.R. Rousselot", "My TestBook");
        myTestBook.setNbPages(1);
        System.out.println("Nombre de page: " + myTestBook.getNbPages());

        harryPottier.setNbPages(600);
        leSoigneurDesAgneaux.setNbPages(1000);
        Displayer.displayPageNb(harryPottier);
        Displayer.displayPageNb(leSoigneurDesAgneaux);
        Displayer.displayPageNbOfTwoBooks(harryPottier, leSoigneurDesAgneaux);

        Displayer.displayBookDescription(harryPottier);

        Livre laHordeAContreCourant = new Livre();
        Livre vernonSubutex = new Livre("Virginie Despentes", "Vernon Subutex",300);

        Livre testPrice = new Livre("Test Price",15.99);
        testPrice.setPrice(12.55);
        System.out.println("test : " + testPrice.getPrice());
        Livre testPrice2 = new Livre("Test Price","Test2");
        testPrice2.setPrice(12.55);
        System.out.println("test2 : " + testPrice2.getPrice());
    }
}
