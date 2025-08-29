package training.afpa.vue.SwingDesigner;

import training.afpa.model.Loan;
import training.afpa.vue.swingGui.LoanSwing;
import training.afpa.vue.swingGui.SubscriberSwing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoanMenu extends JFrame {
    private JPanel contentPane;
    private JPanel centralPane;
    private JScrollPane tablePane;
    private JTable table1;
    private JPanel buttonPane;
    private JButton createButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton backButton;
    private JPanel titlePane;
    private JLabel titleLabel;
    private JPanel footerPane;
    private JComboBox loanBox;
    private JLabel labelLoanBox;
    private JButton refreshButton;
    private JLabel labelEmailBox;
    private JComboBox emailBox;
    private JButton delButton;
    private JFrame parentFrame;

    private static final String[] tableHeaders = {"Date du pret","Date de retour","Email abonne","Titre Livre"};

    public LoanMenu(JFrame parentFrame) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        setContentPane(contentPane);
        setVisible(true);
        pack();

        this.parentFrame = parentFrame;
        setTable();
        setEmailBox();
        setLoanBox();
        labelLoanBox.setVisible(false);
        labelEmailBox.setVisible(false);
        emailBox.setVisible(false);
        loanBox.setVisible(false);
        delButton.setVisible(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backFunction();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoanSwing.createLoan();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnLoan();
            }
        });

        emailBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelLoanBox.setVisible(true);
                loanBox.setVisible(true);
                setLoanBox();
                delButton.setVisible(true);
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTable();
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loan loanReturn = (Loan)loanBox.getSelectedItem();
                int response = JOptionPane.showConfirmDialog(null,
                        "Etes vous sur de vouloir valider le retour :" + loanReturn,
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if(response == JOptionPane.YES_OPTION){
                    Loan.loansList.remove(loanReturn);
                    JOptionPane.showMessageDialog(null, "Le retour du pret a bien été enregistré.",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                setTable();
            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoanSwing.modifyLoan();
            }
        });
    }

    public void setTable(){
        String[][] loansMatrice = LoanSwing.createLoansMatrice();
        table1 = new JTable(loansMatrice, tableHeaders);
        tablePane.setViewportView(table1);
        revalidate();
        repaint();
    }

    public void setLoanBox(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) loanBox.getModel();
        ArrayList<Loan> subscribersLoan =  new ArrayList<>();
        for (Loan loan : Loan.loansList) {
            if (loan.getSubscriber().getEmail().equals(emailBox.getSelectedItem())) {
                subscribersLoan.add(loan);
            }
        }
        for (Loan loan : subscribersLoan){
            loanBox.addItem(loan);
        }

    }

    public void setEmailBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) emailBox.getModel();
        String[] emailList = SubscriberSwing.createSubscribersEmailList();
        for(String email : emailList){
            model.addElement(email);
        }
    }

    public void backFunction() {
        this.dispose();
        parentFrame.setVisible(true);
    }

    public void returnLoan() {
        emailBox.setVisible(true);
        labelEmailBox.setVisible(true);
    }
}
