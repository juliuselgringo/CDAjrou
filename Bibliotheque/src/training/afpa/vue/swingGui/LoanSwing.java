package training.afpa.vue.swingGui;

import training.afpa.model.Loan;

import javax.swing.*;
import java.time.format.DateTimeFormatter;

public class LoanSwing {

    public static String[][] createLoansMatrice(){
        String[][] loansMatrice = new String[Loan.loansList.size()][4];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int i = 0;
        for (Loan loan : Loan.loansList) {
            loansMatrice[i][0] = loan.getLoanDate().format(formatter);
            loansMatrice[i][1] = loan.getReturnDate().format(formatter);
            loansMatrice[i][2] = loan.getBook().getTitle();
            loansMatrice[i][3] = loan.getSubscriber().getEmail();
            i++;
        }
        return loansMatrice;
    }

    public static void swingMenu() {
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        Gui.labelMaker(panel,"Menu Pret",10,10);
        JButton createLoanButton = Gui.buttonMaker(panel, "Nouveau pret", 40);
        JButton returnLoanButton = Gui.buttonMaker(panel, "Retour pret", 70);
        JButton modifyLoanButton = Gui.buttonMaker(panel, "Modifer date de retour", 100);

        String[] tableHeaders = {"Date du pret", "Date de retour", "Livre emprunté", "Email emprunteur"};

        Gui.tableMaker(panel,createLoansMatrice(),tableHeaders,500,10,700,900);
        JButton refreshButton = Gui.buttonMaker(panel, "Raffraichir",930);

        refreshButton.addActionListener(e -> {
            frame.dispose();
            LoanSwing.swingMenu();
        });

        createLoanButton.addActionListener(e -> createLoan());
        returnLoanButton.addActionListener(e -> returnLoan());
        modifyLoanButton.addActionListener(e -> modifyLoan());

    }

    public static void createLoan() {
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        Gui.labelMaker(panel,"Saisissez l'email de l'emprunteur: ",10,10);
        JTextField emailField = Gui.textFieldMaker(panel,10,40);

        Gui.labelMaker(panel,"Saisissez le titre du livre: ",10,70);
        JTextField titleField = Gui.textFieldMaker(panel,10,100);

        JButton createBtn = Gui.buttonMaker(panel,"Enregistrer",130);

        createBtn.addActionListener(e -> {
            String loanerEmail = emailField.getText();
            String bookTitle = titleField.getText();
            try{
                Loan newLoan = new Loan(loanerEmail.trim(), bookTitle.trim());
                JOptionPane.showMessageDialog(null,
                        "Le pret a bien été enregistré :" + newLoan,
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }catch(NullPointerException err){
                JOptionPane.showMessageDialog(null,
                        "La saisie du livre ou de l'abonné est invalide." + err.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


    }

    public static void returnLoan() {}

    public static void modifyLoan() {}
}
