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
        return "\nPret{Date du pret: " + this.loanDate.format(formatter) +
                ", Date de retour: " + this.returnDate.format(formatter) +
                ", Titre: " + this.book.getTitle() +
                ", Emprunteur: " + this.subscriber.getFirstName() + " " +
                this.subscriber.getLastName() + "}\n";
    }

    /**
     * CHERCHER UN PRET AVEC EMAIL ABONNE
     * @return Loan
     */
    public static Loan searchLoanBySubscriberEmail(){
        Display.print("Saisissez l'email de l'emprunteur: ");
        String loanerEmail = UserInput.userInputText();
        Loan loanFound = null;
        for(Loan loan : loansList){
            if(loan.getSubscriber().getEmail().equals(loanerEmail.trim())){
                loanFound = loan;
            }
        }
        try {
            if (loanFound == null) {
                throw new NullPointerException("Il n'y a pas de pret qui correspond à cette requete.");
            }
        }
        catch(NullPointerException e){
            Display.error(e.getMessage());
        }
        return loanFound;
    }

    /**
     * MENU PRET
     */
    public static void loanMenu() throws Exception {
        Display.loanMenu();
        String LoanSelection = UserInput.menuSelection();
        switch (LoanSelection) {
            case "0":
                break;
            case "1":
                Loan.createNewLoan();
                break;
            case "2":
                Loan.returnLoan();
                break;
            case "3":
                Display.print(Loan.loansList.toString());
                break;
            case "4":
                Loan.modifyLoanReturnDate();
                break;
            default:
                break;
        }
    }
    /**
     * CREER UN PRET
     */
    public static void createNewLoan(){
        Display.print("Saisissez l'email de l'emprunteur: ");
        String loanerEmail = UserInput.userInputEmail();
        Display.print("Saisissez le titre du livre: ");
        String bookTitle = UserInput.userInputText();
        try{
            Loan newLoan = new Loan(loanerEmail.trim(), bookTitle.trim());
            Display.print(newLoan.toString());
        }catch(NullPointerException e){
            Display.error("La saisie du livre ou de l'abonné est invalide.");
        }

    }

    /**
     * RETOUR DE PRET
     */
    public static void returnLoan(){
        Loan returnLoan = null;
        try {
            returnLoan = Loan.searchLoanBySubscriberEmail();
            loansList.remove(returnLoan);
            returnLoan.getBook().setQuantity(returnLoan.getBook().getQuantity() + 1);
            Display.print(returnLoan.toString());
            Display.print("Retourné avec succès.");
        }
        catch(NullPointerException e){
            Display.error(e.getMessage());
        }

    }

    /**
     * MODIFICATION DATE DE RETOUR DU PRET
     */
    public static void modifyLoanReturnDate() throws Exception {
        Loan loanToModify = null;
        try{
            loanToModify = Loan.searchLoanBySubscriberEmail();
            Display.print(loanToModify.toString());
            Display.print("Saisissez le nombre de jour de prologation: ");
            int dayToAdd = UserInput.userInputInt();
            loanToModify.setReturnDate(loanToModify.getReturnDate().plusDays(dayToAdd));
            Display.print(loanToModify.toString());

        }
        catch(NullPointerException e){
            Display.error(e.getMessage());
        }

    }


}
