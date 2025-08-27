package training.afpa.vue.swingGui;

import training.afpa.model.Book;
import training.afpa.vue.terminal.Display;

import javax.swing.*;

public class BookSwing {

    private static final String[] tableHeaders = {"Titre","Auteur","ISBN","Disponibilite","Quantite"};

    /**
     * CREE UNE MATRICE LIVRE POUR AFFICHAGE TABLEAU
     * @return String[][]
     */
    public static String[][] createBooksMatrice(){
        String[][] booksMatrice = new String[Book.booksList.size()][5];
        int i = 0;
        for (Book book : Book.booksList) {
            booksMatrice[i][0] = book.getTitle();
            booksMatrice[i][1] = book.getAuthor();
            booksMatrice[i][2] = book.getIsbn();
            booksMatrice[i][3] = book.getAvailable().toString();
            booksMatrice[i][4] = book.getQuantity() + "";
            i++;
        }
        return booksMatrice;
    }

    /**
     * MENU LIVRE
     */
    public static void swingMenu(){
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        Gui.labelMaker(panel,"Menu Livre",10,10);
        JButton createSubscriberButton = Gui.buttonMaker(panel, "Nouveau Livre", 40);
        JButton deleteSubscriberButton = Gui.buttonMaker(panel, "Supprimer un Livre", 70);
        JButton modifySubscriberLastNameButton = Gui.buttonMaker(panel, "Modifer la quantité d'un livre", 100);
        JButton modifySubscriberEmailButton = Gui.buttonMaker(panel, "Consulter un livre", 130);

        Gui.tableMaker(panel,createBooksMatrice(),tableHeaders,500,10,700,900);
        JButton refreshButton = Gui.buttonMaker(panel, "Raffraichir",930);

        refreshButton.addActionListener(e -> {
            frame.dispose();
            BookSwing.swingMenu();
        });

        createSubscriberButton.addActionListener(e -> createBook());

        deleteSubscriberButton.addActionListener(e -> deleteBook());

        modifySubscriberLastNameButton.addActionListener(e -> modifyBookQuantity());

        modifySubscriberEmailButton.addActionListener(e -> searchABookByTitle());

        JButton backButton = Gui.buttonMaker(panel,"Retour",190);

        backButton.addActionListener(e -> frame.dispose());
    }

    /**
     * CREER UN NOUVEAU LIVRE
     */
    public static void createBook() {
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);


        Gui.labelMaker(panel,"Saisissez le titre du livre: ",10,10);
        JTextField newTitleField = Gui.textFieldMaker(panel,10,40);
        Gui.labelMaker(panel,"Saisissez l'auteur du livre: ",10,70);
        JTextField newAuthorField = Gui.textFieldMaker(panel,10,100);
        Gui.labelMaker(panel,"Saisissez l'ISBN du livre: ",10,130);
        JTextField newIsbnField = Gui.textFieldMaker(panel,10,160);
        Gui.labelMaker(panel,"Saisissez la quatité du livre: ",10,190);
        JTextField newQuantityField = Gui.textFieldMaker(panel,10,220);


        JButton saveBtn = Gui.buttonMaker(panel,"Enregistrer",250);

        saveBtn.addActionListener(e -> {
            String newTitle = newTitleField.getText().trim();
            String newAuthor = newAuthorField.getText().trim();
            String newIsbn = newIsbnField.getText().trim();
            int newQuantity = 0;
            try {
                newQuantity = Integer.parseInt(newQuantityField.getText().trim());
            }catch (NumberFormatException err){
                JOptionPane.showMessageDialog(null,"saisi invalide",
                        "Erreur",JOptionPane.ERROR_MESSAGE);
            }

            if(Book.searchBookByTitle(newTitle) != null){
                JOptionPane.showMessageDialog(null,
                        "Ce Livre existe déjà.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    Book newBook = new Book(newTitle, newAuthor, newIsbn, newQuantity);
                    JOptionPane.showMessageDialog(null,
                            "Nouveau livre enregitré: " + newBook,
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
                catch(Exception err){
                    Display.error(err.getMessage());
                    JOptionPane.showMessageDialog(null,
                            err.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backButton = Gui.buttonMaker(panel,"Retour",310);

        backButton.addActionListener(e -> frame.dispose());
    }

    /**
     * SUPPRIMER UN LIVRE
     */
    public static void deleteBook() {
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);
        String[] bookTitleList = new String[Book.booksList.size()];
        int i = 0;
        for(Book book : Book.booksList){
            bookTitleList[i] = book.getTitle();
            i++;
        }
        Gui.labelMaker(panel,"Sélectionner le titre du livre à supprimer: ",10,10);
        JComboBox<String> bookBox = Gui.comboBoxMaker(panel,bookTitleList,10,40);

        bookBox.addActionListener(e -> {
            String titleToDelete = bookBox.getSelectedItem().toString();

            int response = JOptionPane.showConfirmDialog(null,"Etes-vous sur de vouloir supprimer: " +
                    titleToDelete,"Confirmation",JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION){
                Book.booksList.remove(Book.searchBookByTitle(titleToDelete));
                JOptionPane.showMessageDialog(panel,"Le livre a été supprimer avec succès.",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
            else {
                frame.dispose();
                BookSwing.deleteBook();
            }
        });

        JButton backButton = Gui.buttonMaker(panel,"Retour",100);

        backButton.addActionListener(e -> frame.dispose());

    }

    /**
     * MODIFIER LA QUANTITE D UN LIVRE
     */
    public static void modifyBookQuantity() {
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);
        String[] bookTitleList = new String[Book.booksList.size()];
        int i = 0;
        for(Book book : Book.booksList){
            bookTitleList[i] = book.getTitle();
            i++;
        }
        Gui.labelMaker(panel,"Sélectionner le titre du livre à modifier: ",10,10);
        JComboBox<String> bookBox = Gui.comboBoxMaker(panel,bookTitleList,10,40);

        bookBox.addActionListener(e -> {
            String titleToModify = bookBox.getSelectedItem().toString();
            try {
                int newQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Saisissez la quantité: "));
                if (newQuantity >= 0) {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Etes-vous sur de vouloir modifier la quantité à " + newQuantity + ".",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        Book.searchBookByTitle(titleToModify).setQuantity(newQuantity);
                        JOptionPane.showMessageDialog(panel, "La quantité a été modifié avec succès.",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    } else {
                        frame.dispose();
                        BookSwing.modifyBookQuantity();
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "La quantité saisie est invalide.");
                }
            } catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Saisie invalide.","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backButton = Gui.buttonMaker(panel,"Retour",100);

        backButton.addActionListener(e -> frame.dispose());
    }

    /**
     * CREER UNE LISTE DES TITRE DE LIVRE POUR COMBO BOX
     * @return String[]
     */
    public static String[] createBookTitleList(){
        String[] bookTitleList = new String[Book.booksList.size()];
        int i = 0;
        for(Book book : Book.booksList){
            bookTitleList[i] = book.getTitle();
            i++;
        }
        return bookTitleList;
    }

    /**
     * CHERCHER UN LIVRE PAR TITRE
     */
    public static void searchABookByTitle() {
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        String[] bookTitleList = createBookTitleList();
        Gui.labelMaker(panel,"Sélectionner le titre du livre à modifier: ",10,10);
        JComboBox<String> bookBox = Gui.comboBoxMaker(panel,bookTitleList,10,40);

        bookBox.addActionListener(e -> {
            JFrame frame2 = Gui.setFrame();
            JPanel panel2 = Gui.setPanel(frame2);
            String titleToModify = bookBox.getSelectedItem().toString();
            Book searchedBook = Book.searchBookByTitle(titleToModify);
            String[][] searchedBookMatrice = {{titleToModify,searchedBook.getAuthor(), searchedBook.getIsbn(),
                    searchedBook.getIsbn(), searchedBook.getQuantity()+""}};
            Gui.tableMaker(panel2, searchedBookMatrice,tableHeaders,500,10,700,900);

            JButton backButton = Gui.buttonMaker(panel2,"Retour",10);

            backButton.addActionListener(ev -> frame2.dispose());
        });

        JButton backButton = Gui.buttonMaker(panel,"Retour",100);
        backButton.addActionListener(e -> frame.dispose());
    }

}
