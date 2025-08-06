package training.afpa.encapsulation;

public class Calculator {

    /**
     *
     * @param book1 Livre
     * @param book2 Livre
     * @return int
     */
    public static int sumPagesOfTwoBooks(Livre book1, Livre book2){
        return book1.getNbPages() + book2.getNbPages();
    }
}
