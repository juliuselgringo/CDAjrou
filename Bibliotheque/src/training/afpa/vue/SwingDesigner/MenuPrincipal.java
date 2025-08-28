package training.afpa.vue.SwingDesigner;

import training.afpa.model.Loan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JPanel contentPane;
    private JPanel centralPane;
    private JScrollPane tablePane;
    private JTable table1;
    private JPanel buttonPane;
    private JPanel titlePane;
    private JLabel titleLabel;
    private JPanel footerPane;
    private JButton loanButton;
    private JButton subscriberButton;
    private JButton exitButton;
    private JButton bookButton;


    public MenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension minDim = new Dimension(1920,1000);
        setMinimumSize(minDim);
        setContentPane(contentPane);

        setVisible(true);
        pack();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFunction();
            }
        });
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookMenu();
            }
        });
        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        subscriberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subscriberMenu();
            }
        });
        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loanMenu();
            }
        });
    }

    public void closeFunction() {
        System.exit(0);
    }

    public void bookMenu() {
        BookMenu bookMenu = new BookMenu(this);
        this.dispose();
    }

    public void subscriberMenu() {
        SubscriberMenu subscriberMenu = new SubscriberMenu(this);
    }

    public void loanMenu() {
        LoanMenu loanMenu = new LoanMenu(this);
    }
}
