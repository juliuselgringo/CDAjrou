package training.afpa.encapsulation;

public class Displayer {

    /**
     *
     * @param book Livre
     */
    public static void displayPageNb(Livre book){
        System.out.println("Le livre " + book.getTitre() + " contient " + book.getNbPages() +  " pages.");
    }

    /**
     *
     * @param book1 Livre
     * @param book2 Livre
     */
    public static void displayPageNbOfTwoBooks(Livre book1, Livre book2){
        int nbPages = Calculator.sumPagesOfTwoBooks(book1, book2);
        System.out.println("\nLes livres " + book1.getTitre() + " et " + book2.getTitre() +
                " contiennent à eux deux " + nbPages + " pages");
    }

    /**
     *
     * @param book (Book)
     */
    public static void displayBookDescription(Livre book){
        System.out.println("\nTitre: " + book.getTitre() + "\n" +
                "Auteur: " + book.getAuteur() + "\n" +
                "Nombre de pages: " + book.getNbPages()
        );
    }
}
