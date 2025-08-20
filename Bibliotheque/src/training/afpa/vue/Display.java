package training.afpa.vue;

public class Display {

    /**
     * PRINT LINE OUT
     * @param sentence String
     */
    public static void print(String sentence){
        System.out.println(sentence);
    }

    /**
     * PRINT LINE ERR
     * @param sentence String
     */
    public static void error(String sentence){
        System.err.println(sentence);
    }

    /**
     * PRINT MENU
     */
    public static void menu(){
        System.out.println("Menu de gestion de la bibliotheque\n" +
                "1) Nouvel emprunt\n" +
                "2) Nouvel abonné\n" +
                "3) Entrée nouveau livre en stock\n" +
                "4) Liste des abonnés\n" +
                "5) Liste des livres\n" +
                "6) Liste des prêts\n" +
                "7) Consulter le stock d'un titre\n" +
                "0) Quitter \n");
    }
}
