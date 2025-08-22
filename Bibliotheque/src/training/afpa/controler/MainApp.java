package training.afpa.controler;

import training.afpa.model.*;
import training.afpa.vue.Display;
import training.afpa.vue.Gui;
import training.afpa.vue.UserInput;

import javax.swing.*;

public class MainApp {

    public static void main(String[] args) throws Exception {
        //MainApp.terminalProgram();
        MainApp.swingProgram();

    }

    /**
     * LANCE LE TERMINAL CONCU POUR SWING
     */
    public static void swingProgram() {
        JFrame frame = Gui.setFrame(300,300,700,500);
        JPanel panel = Gui.setPanel(frame);

        Gui.labelMaker(panel,"Menu de getion de la Bibliothèque",10,10);
        JButton loanButton = Gui.buttonMaker(panel, "Gestion de prets", 40);
        JButton subscriberButton = Gui.buttonMaker(panel, "Gestion de abonnés", 70);
        JButton bookButton = Gui.buttonMaker(panel, "Gestion de Livre", 100);

        loanButton.addActionListener(e -> {
            Loan.swingMenu();
        });

    }

    /**
     * LANCE LE PROGRAMME CONCU POUR LE TERMINAL
     * @throws Exception
     */
    public static void terminalProgram() throws Exception {
        Boolean start = true;

        try{
            libraryCreation();
        }catch(Exception e){
            System.err.println("Erreur dans le programme d'initialisation: " + e.getMessage());
            System.exit(1);
        };

        while(start) {
            Display.menu();
            String UserSelection = UserInput.menuSelection();

            switch (UserSelection) {
                case "0":
                    start = false;
                    UserInput.in.close();
                    break;
                case "1":
                    Loan.loanMenu();
                    break;
                case "2":
                    Subscriber.subscriberMenu();
                    break;
                case "3":
                    Book.bookMenu();
                    break;
                default:
                    Display.error("Menu : Saisie invalide");
                    break;
            }
        }
    }

    /**
     * CREE DES DONNEES POUR LA BIBLIOTHEQUE
     */
    public static void libraryCreation() {
        new Librerian("Jean",
                "Duboncoin",
                "JeDub");

        new Book("1984", "George Orwell", "978-2070368226", 0);
        new Book("Le Petit Prince", "Antoine de Saint-Exupéry", "978-2070612758", 10);
        new Book("Sapiens : Une brève histoire de l'humanité", "Yuval Noah Harari", "978-2226391022", 3);
        new Book("Orgueil et Préjugés", "Jane Austen", "978-2070400237", 7);
        new Book("Le Seigneur des Anneaux : La Communauté de l'Anneau", "J.R.R. Tolkien", "978-2266282207", 4);
        new Book("Les Misérables", "Victor Hugo", "978-2070401609", 2);
        new Book("Le Horla", "Guy de Maupassant", "978-2070368103", 8);
        new Book("La Horde du Contrevent", "Alain Damasio", "978-2843624563", 6);
        new Book("Thinking, Fast and Slow", "Daniel Kahneman", "978-0374533557", 3);
        new Book("Bel-Ami", "Guy de Maupassant", "978-2070368097", 5);
        new Book("Dune", "Frank Herbert", "978-2266279962", 4);
        new Book("Le Comte de Monte-Cristo", "Alexandre Dumas", "978-2070402064", 9);
        new Book("Les Fleurs du Mal", "Charles Baudelaire", "978-2070403375", 6);
        new Book("L'Étranger", "Albert Camus", "978-2070360060", 7);
        new Book("Harry Potter à l'école des sorciers", "J.K. Rowling", "978-2070584628", 12);
        new Book("Le Rouge et le Noir", "Stendhal", "978-2070368073", 3);
        new Book("Vernon Subutex 1", "Virginie Despentes", "978-2246854430", 5);
        new Book("La Peste", "Albert Camus", "978-2070360138", 4);
        new Book("Les Trois Mousquetaires", "Alexandre Dumas", "978-2070401975", 8);
        new Book("L'Alchimiste", "Paulo Coelho", "978-2253133026", 10);


        new Subscriber("test", "test", "test@test.fr");
        new Subscriber("Lucas", "Martin", "lucas.martin@mistral.com");
        new Subscriber("Camille", "Dubois", "camille.dubois@mistral.com");
        new Subscriber("Thomas", "Bernard", "thomas.bernard@mistral.com");
        new Subscriber("Léa", "Robert", "lea.robert@mistral.com");
        new Subscriber("Antoine", "Richard", "antoine.richard@mistral.com");
        new Subscriber("Manon", "Durand", "manon.durand@mistral.com");
        new Subscriber("Hugo", "Lefèvre", "hugo.lefevre@mistral.com");
        new Subscriber("Chloé", "Morel", "chloe.morel@mistral.com");
        new Subscriber("Nathan", "Fournier", "nathan.fournier@mistral.com");
        new Subscriber("Élodie", "Girard", "elodie.girard@mistral.com");
        new Subscriber("Julien", "Roussel", "julien.roussel@mistral.com");
        new Subscriber("Marie", "Lambert", "marie.lambert@mistral.com");
        new Subscriber("Paul", "Roux", "paul.roux@mistral.com");
        new Subscriber("Sophie", "Vasseur", "sophie.vasseur@mistral.com");
        new Subscriber("Alexandre", "Bonnet", "alexandre.bonnet@mistral.com");
        new Subscriber("Emma", "Muller", "emma.muller@mistral.com");
        new Subscriber("Romain", "Fontaine", "romain.fontaine@mistral.com");
        new Subscriber("Alice", "Gauthier", "alice.gauthier@mistral.com");
        new Subscriber("Maxime", "Chevalier", "maxime.chevalier@mistral.com");
        new Subscriber("Jeanne", "Perrin", "jeanne.perrin@mistral.com");

    }

}
