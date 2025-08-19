package training.afpa.vue;

public class Display {

    public static void print(String Sentence){
        System.out.println(Sentence);
    }

    public static void error(String Sentence){
        System.err.println(Sentence);
    }

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
