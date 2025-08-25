package training.afpa.model;

import training.afpa.vue.terminal.Display;
import training.afpa.vue.terminal.UserInput;

import java.util.ArrayList;
import java.util.Comparator;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private Boolean available;
    private int quantity;
    public static ArrayList<Book> booksList = new ArrayList<Book>();

    /**
     * CONSTRUCTOR
     * @param title String
     * @param author String
     * @param isbn String
     * @param quantity int
     */
    public Book(String title, String author, String isbn,int quantity) {
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setQuantity(quantity);
        booksList.add(this);
        booksList.sort(Comparator.comparing(Book::getTitle));
    }

    /**
     * GETTER title
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * SETTER title
     * @param title String
     */
    public void setTitle(String title) {
        title = title.trim();
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title invalide");
        }
        this.title = title;
    }

    /**
     * GETTER author
     * @return String
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * SETTER author
     * @param author String
     */
    public void setAuthor(String author) {
        author = author.trim();
        if (author == null || author.isEmpty()) {
            throw  new IllegalArgumentException("author invalide");
        }
        this.author = author;
    }

    /**
     * GETTER isbn
     * @return String
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * SETTER isbn
     * @param isbn String
     */
    public void setIsbn(String isbn) {
        isbn = isbn.trim();
        if (isbn == null || isbn.isEmpty()) {
            throw  new IllegalArgumentException("isbn invalide");
        }
        this.isbn = isbn;
    }

    /**
     * GETTER available
     * @return Boolean
     */
    public Boolean getAvailable() {
        return this.available;
    }

    /**
     * SETTER available
     * @param available Boolean
     */
    private void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * GETTER quantity
     * @return int
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * SETTER quantity
     * @param quantity int
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity invalide");
        } else if (quantity == 0) {
            this.setAvailable(false);
            this.quantity = 0;
        } else {
            this.quantity = quantity;
            this.setAvailable(true);
        }
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        return "\nBook{Title: " + this.getTitle() +
                ", Author: " + this.getAuthor() +
                ", ISBN: " + this.getIsbn() +
                ", Available: " + this.getAvailable() +
                ", Quantity: " + this.getQuantity() + "}\n";
    }

    /**
     * CHERCHER UN LIVRE PAR TITRE
     * @param title String
     * @return Book
     */
    public static Book searchBookByTitle(String title) {
        Book bookFound = null;
        for (Book book : booksList) {
            if (book.getTitle().equals(title)) {
                bookFound = book;
            }
        }

        return bookFound;
    }

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
                Book.createNewBook();
                break;
            case "2":
                Book.removeBook();
                break;
            case "3":
                Display.print(booksList.toString());
                break;
            case "4":
                Book.modifyBookQuantity();
                break;
            case  "5":
                Book.consultBooksStock();
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
            if(searchBookByTitle(newTitle) != null){
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
        Book bookToremove = searchBookByTitle(newTitleToRemove);
        if(bookToremove != null){
            booksList.remove(bookToremove);
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
        Book bookToModify = searchBookByTitle(titleToSearch);
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
