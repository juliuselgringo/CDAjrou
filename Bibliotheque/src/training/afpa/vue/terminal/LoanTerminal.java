package training.afpa.vue.terminal;

import training.afpa.model.Loan;

public class LoanTerminal {

    /**
     * CHERCHER UN PRET AVEC EMAIL ABONNE
     *
     * @return Loan
     */
    public static Loan searchLoanBySubscriberEmail() {
        Display.print("Saisissez l'email de l'emprunteur: ");
        String loanerEmail = UserInput.userInputText();
        Loan loanFound = null;
        for (Loan loan : Loan.loansList) {
            if (loan.getSubscriber().getEmail().equals(loanerEmail.trim())) {
                loanFound = loan;
            }
        }
        try {
            if (loanFound == null) {
                throw new NullPointerException("Il n'y a pas de pret qui correspond à cette requete.");
            }
        } catch (NullPointerException e) {
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
                createNewLoan();
                break;
            case "2":
                returnLoan();
                break;
            case "3":
                Display.print(Loan.loansList.toString());
                break;
            case "4":
                modifyLoanReturnDate();
                break;
            default:
                break;
        }
    }

    /**
     * CREER UN PRET
     */
    public static void createNewLoan() {
        Display.print("Saisissez l'email de l'emprunteur: ");
        String loanerEmail = UserInput.userInputEmail();
        Display.print("Saisissez le titre du livre: ");
        String bookTitle = UserInput.userInputText();
        try {
            Loan newLoan = new Loan(loanerEmail.trim(), bookTitle.trim());
            Display.print(newLoan.toString());
        } catch (NullPointerException e) {
            Display.error("La saisie du livre ou de l'abonné est invalide.");
        }

    }

    /**
     * RETOUR DE PRET
     */
    public static void returnLoan() {
        Loan returnLoan = null;
        try {
            returnLoan = searchLoanBySubscriberEmail();
            Loan.loansList.remove(returnLoan);
            returnLoan.getBook().setQuantity(returnLoan.getBook().getQuantity() + 1);
            Display.print(returnLoan.toString());
            Display.print("Retourné avec succès.");
        } catch (NullPointerException e) {
            Display.error(e.getMessage());
        }

    }

    /**
     * MODIFICATION DATE DE RETOUR DU PRET
     */
    public static void modifyLoanReturnDate() throws Exception {
        Loan loanToModify = null;
        try {
            loanToModify = searchLoanBySubscriberEmail();
            Display.print(loanToModify.toString());
            Display.print("Saisissez le nombre de jour de prologation: ");
            int dayToAdd = UserInput.userInputInt();
            loanToModify.setReturnDate(loanToModify.getReturnDate().plusDays(dayToAdd));
            Display.print(loanToModify.toString());

        } catch (NullPointerException e) {
            Display.error(e.getMessage());
        }
    }

}
