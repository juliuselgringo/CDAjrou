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


}
