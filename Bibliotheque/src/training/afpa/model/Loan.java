package training.afpa.model;

import training.afpa.vue.Display;
import training.afpa.vue.UserInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Loan {

    private LocalDate loanDate;
    private LocalDate returnDate;
    public Subscriber subscriber;
    public Book book;
    public static ArrayList<Loan> loansList =  new ArrayList<Loan>();

    /**
     * CONSTRUCTOR
     * @param subscriberEmail String
     * @param bookTitle String
     */
    public Loan(String subscriberEmail, String bookTitle) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //this.loanDate = LocalDate.now().format(formatter);
        this.loanDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(7);
        setSubscriber(subscriberEmail);
        setBook(bookTitle);
        if(this.subscriber != null && this.book != null ){
            loansList.add(this);
            this.book.setQuantity(this.book.getQuantity() - 1);
        }
        else{
            Display.error("Erreur: saisie du prêt incorrecte");
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
     * SETTER book
     * @param bookTitle String
     */
    public void setBook(String bookTitle) {
        Book searchedBook = Book.searchBookByTitle(bookTitle.trim());
        if(searchedBook.getAvailable()){
            this.book = searchedBook;
        }
        else{
            this.book = null;
        }
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "\nPret{Date du pret: " + this.loanDate.format(formatter) +
                ", Date de retour: " + this.returnDate.format(formatter) +
                ", Titre: " + this.book.getTitle() +
                ", Emprunteur: " + this.subscriber.getFirstName() + " " +
                this.subscriber.getLastName() + "}\n";
    }

    public static void createNewLoan(){
        Display.print("Saisissez l'email de l'emprunteur: ");
        String loanerEmail = UserInput.userInputText();
        Display.print("Saisissez le titre du livre: ");
        String bookTitle = UserInput.userInputText();
        try{
            new Loan(loanerEmail, bookTitle);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
