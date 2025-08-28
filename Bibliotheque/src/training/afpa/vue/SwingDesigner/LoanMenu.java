package training.afpa.vue.SwingDesigner;

import training.afpa.vue.swingGui.BookSwing;
import training.afpa.vue.swingGui.LoanSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanMenu extends JFrame {
    private JPanel contentPane;
    private JPanel centralPane;
    private JScrollPane tablePane;
    private JTable table1;
    private JPanel buttonPane;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton backButton;
    private JPanel titlePane;
    private JLabel titleLabel;
    private JPanel footerPane;
    private JFrame parentFrame;

    private static final String[] tableHeaders = {"Date du pret","Date de retour","Email abonne","Titre Livre"};

    public LoanMenu(JFrame parentFrame) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension minDim = new Dimension(1920,1000);
        setMinimumSize(minDim);
        setContentPane(contentPane);
        setVisible(true);
        pack();

        this.parentFrame = parentFrame;

        setTable();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backFunction();
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

    public void backFunction() {
        this.dispose();
        parentFrame.setVisible(true);
    }
}
