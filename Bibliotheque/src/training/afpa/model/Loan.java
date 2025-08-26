package training.afpa.model;

import training.afpa.vue.terminal.Display;
import training.afpa.vue.terminal.UserInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Loan {

    private LocalDate loanDate;
    private LocalDate returnDate;
    private Subscriber subscriber;
    private Book book;
    public static ArrayList<Loan> loansList =  new ArrayList<Loan>();

    /**
     * CONSTRUCTOR
     * @param subscriberEmail String
     * @param bookTitle String
     */
    public Loan(String subscriberEmail, String bookTitle) {
        this.loanDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(7);
        setSubscriber(subscriberEmail);
        setBook(bookTitle);
        if (this.subscriber != null && this.book != null) {
            loansList.add(this);
            this.book.setQuantity(this.book.getQuantity() - 1);
        }
    }

    /**
     * GETTER loanDate
     * @return LocaleDate
     */
    public LocalDate getLoanDate() {
        return this.loanDate;
    }

    /**
     * GETTER returnDate
     * @return LocaleDate
     */
    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    /**
     * SETTER returnDate
     */
    public void setReturnDate(LocalDate newReturnDate) throws Exception {
        if(newReturnDate.isAfter(LocalDate.now())){
            this.returnDate = newReturnDate;
        }
        else{
            throw new Exception("La date de retour n'est pas valide");
        }
    }

    /**
     * GETTER subscriber
     * @return Subscriber
     */
    public Subscriber getSubscriber() {
        return subscriber;
    }

    /**
     * SETTER subscriber
     * @param subscriberEmail
     */
    public void setSubscriber(String subscriberEmail) {
        this.subscriber = Subscriber.searchSubscriberByEmail(subscriberEmail.trim());
    }

    /**
     * GETTER book
     * @return Book
     */
    public Book getBook() {
        return book;
    }

    /**
     * SETTER book
     * @param bookTitle String
     */
    public void setBook(String bookTitle) {
        try {
            Book searchedBook = Book.searchBookByTitle(bookTitle.trim());
            if (searchedBook.getAvailable()) {
                this.book = searchedBook;
            }
            else{
                throw new Exception("Le livre n'est plus en stock.");
            }
        }
        catch(Exception e){
            Display.error( e.getMessage());
        }
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "pret{Date du pret: " + this.loanDate.format(formatter) +
                ", Date de retour: " + this.returnDate.format(formatter) +
                ", Titre: " + this.book.getTitle() +
                ", Emprunteur: " + this.subscriber.getEmail() + "}\n";
    }

}
