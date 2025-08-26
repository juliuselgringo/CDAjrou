package training.afpa.vue.terminal;

import training.afpa.model.Book;

public class BookTerminal {

    /**
     * MENU LIVRE
     */
    public static void bookMenu(){
        Display.bookMenu();
        String bookSelection = UserInput.menuSelection();
        switch (bookSelection) {
            case "0":
                break;
            case "1":
                createNewBook();
                break;
            case "2":
                removeBook();
                break;
            case "3":
                Display.print(Book.booksList.toString());
                break;
            case "4":
                modifyBookQuantity();
                break;
            case  "5":
                consultBooksStock();
            default:
                break;
        }
    }

    /**
     * CREER UN NOUVEAU LIVRE
     */
    public static void createNewBook() {
        try{
            Display.print("Saisissez le titre du nouveau livre: ");
            String newTitle = UserInput.userInputText();
            Display.print("Saisissez l'auteur du nouveau livre: ");
            String newAuthor = UserInput.userInputText();
            Display.print("Saisissez l'ISBN du nouveau livre: ");
            String newIsbn = UserInput.userInputText();
            Display.print("Saisissez la quantité de livre: ");
            int newQuantity = UserInput.userInputInt();
            if(Book.searchBookByTitle(newTitle) != null){
                Display.error("Ce livre existe déjà dans la base de donnée.");
            }
            else{
                Book newBook = new Book(newTitle,newAuthor,newIsbn, newQuantity);
                Display.print("Nouveau livre enregitré: " + newBook.toString());
            }
        }
        catch(Exception e){
            Display.error(e.getMessage());
        }
    }

    /**
     * SUPPRIMER UN LIVRE
     */
    public static void removeBook() {
        Display.print("Saisissez le titre du livre à supprimer:");
        String newTitleToRemove = UserInput.userInputText();
        Book bookToremove = Book.searchBookByTitle(newTitleToRemove);
        if(bookToremove != null){
            Book.booksList.remove(bookToremove);
            Display.print("Le livre a été supprimé avec succès.");
        }
        else{
            Display.error("Ce livre n'est pas en stock.");
        }
    }

    /**
     * MODIFIER LA QUANTITE D UN LIVRE
     */
    public static void modifyBookQuantity() {
        Display.print("Saisissez le titre du livre: ");
        String titleToSearch = UserInput.userInputText();
        Book bookToModify = Book.searchBookByTitle(titleToSearch);
        if(bookToModify != null){
            Display.print("Saisissez la nouvelle quantité de livre: ");
            int newQuantity = UserInput.userInputInt();
            bookToModify.setQuantity(newQuantity);
            Display.print("La quantité a été modifié avec succès.");
        }
        else{
            Display.error("Ce livre n'est pas en stock.");
        }
    }

    /**
     * CONSULTER LE STOCK PAR TITRE
     */
    public static void consultBooksStock() {
        Display.print("Saisissez le titre du livre: ");
        String titleToSearch = UserInput.userInputText();
        try {
            Book booksearched = Book.searchBookByTitle(titleToSearch);
            if (booksearched == null) {
                throw new NullPointerException("Ce livre n'est pas en stock.");
            }
            Display.print(booksearched.toString());

        }catch(NullPointerException e) {
            Display.error(e.getMessage());
        }
    }
}
