package training.afpa.vue.swingGui;

import training.afpa.model.Loan;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LoanSwing {

    private static final String[] tableHeaders = {"Date du pret","Date de retour","Email abonne","Titre Livre"};

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

        Gui.tableMaker(panel,createLoansMatrice(),tableHeaders,500,10,700,900);
        JButton refreshButton = Gui.buttonMaker(panel, "Raffraichir",930);

        refreshButton.addActionListener(e -> {
            frame.dispose();
            LoanSwing.swingMenu();
        });

        createLoanButton.addActionListener(e -> createLoan());
        returnLoanButton.addActionListener(e -> returnLoan());
        modifyLoanButton.addActionListener(e -> modifyLoan());

        JButton backButton = Gui.buttonMaker(panel,"Retour",160);
        backButton.addActionListener(e -> frame.dispose());
    }

    public static void createLoan() {
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        String[] subscribersEmailList = SubscriberSwing.createSubscribersEmailList();
        String[] booksTitleList = BookSwing.createBookTitleList();

        Gui.labelMaker(panel,"Selectionner l'email de l'abonne: ",10,10);
        JComboBox<String> subscriberEmailBox = Gui.comboBoxMaker(panel, subscribersEmailList,10,40);
        Gui.labelMaker(panel,"Selectionner le titre du livre: ",10,70);
        JComboBox<String> bookTitleBox = Gui.comboBoxMaker(panel, booksTitleList,10,100);

        subscriberEmailBox.addActionListener(e -> {
            String subscriberEmail = subscriberEmailBox.getSelectedItem().toString();

            bookTitleBox.addActionListener(ev -> {
                String bookTitle = bookTitleBox.getSelectedItem().toString();

                JButton createBtn = Gui.buttonMaker(panel,"Enregistrer",130);

                createBtn.addActionListener(eve -> {
                    try{
                        Loan newLoan = new Loan(subscriberEmail, bookTitle);
                        JOptionPane.showMessageDialog(null,
                                "Le pret a bien été enregistré :" + newLoan,
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }catch(NullPointerException err){
                        JOptionPane.showMessageDialog(null,
                                "La saisie du livre ou de l'abonné est invalide." + err.getMessage(),
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                });
            });
        });

        JButton backButton = Gui.buttonMaker(panel,"Retour",190);
        backButton.addActionListener(e -> frame.dispose());

    }

    public static void returnLoan() {
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        String[] subscribersEmailList = SubscriberSwing.createSubscribersEmailList();
        JComboBox emailBox = Gui.comboBoxMaker(panel, subscribersEmailList,10,10);

        JButton searchButton = Gui.buttonMaker(panel,"Rechercher",40);

        ArrayList<Loan> subscribersLoan =  new ArrayList<>();

        searchButton.addActionListener(e ->{
            for (Loan loan : Loan.loansList) {
                if (loan.getSubscriber().getEmail().equals(emailBox.getSelectedItem())) {
                    subscribersLoan.add(loan);
                }
            }

            JComboBox loanBox = Gui.comboBoxMaker(panel, 10,100);
            for (Loan loan : subscribersLoan){
                loanBox.addItem(loan);
            }

            loanBox.addActionListener(e1 -> {
                Loan loanReturn = (Loan)loanBox.getSelectedItem();
                int response = JOptionPane.showConfirmDialog(null,
                        "Etes vous sur de vouloir valider le retour :" + loanReturn,
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if(response == JOptionPane.YES_OPTION){
                    Loan.loansList.remove(loanReturn);
                    JOptionPane.showMessageDialog(panel, "Le retour du pret a bien été enregistré.",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }else{
                    frame.dispose();
                    returnLoan();
                }
            });

        });

        JButton backButton = Gui.buttonMaker(panel,"Retour",190);
        backButton.addActionListener(e -> frame.dispose());
    }

    public static void modifyLoan() {
        JFrame frame = Gui.setFrame();
        JPanel panel = Gui.setPanel(frame);

        String[] subscribersEmailList = SubscriberSwing.createSubscribersEmailList();
        JComboBox emailBox = Gui.comboBoxMaker(panel, subscribersEmailList,10,10);

        JButton searchButton = Gui.buttonMaker(panel,"Rechercher",40);

        ArrayList<Loan> subscribersLoan =  new ArrayList<>();

        searchButton.addActionListener(e ->{
            for (Loan loan : Loan.loansList) {
                if (loan.getSubscriber().getEmail().equals(emailBox.getSelectedItem())) {
                    subscribersLoan.add(loan);
                }
            }

            JComboBox loanBox = Gui.comboBoxMaker(panel, 10,100);
            for (Loan loan : subscribersLoan){
                loanBox.addItem(loan);
            }

            loanBox.addActionListener(e1 -> {
                try {
                    int addDaysLoanReturnDate = Integer.parseInt(JOptionPane.showInputDialog(panel,
                            "Saisissez le nombre de jour de prologation du pret:"));
                    if (addDaysLoanReturnDate > 0) {
                        Loan loanToModify = (Loan)loanBox.getSelectedItem();
                        try {
                            loanToModify.setReturnDate(loanToModify.getReturnDate().plusDays(addDaysLoanReturnDate));
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        JOptionPane.showMessageDialog(panel, loanToModify, "Information", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }else {
                        JOptionPane.showMessageDialog(panel,"Saisie Invalide","Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel,"Saisie Invalide","Erreur",JOptionPane.ERROR_MESSAGE);
                }

            });

        });

        JButton backButton = Gui.buttonMaker(panel,"Retour",190);
        backButton.addActionListener(e -> frame.dispose());
    }
}
