package training.afpa.vue.SwingDesigner;

import training.afpa.vue.swingGui.BookSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookMenu extends JFrame {

    private JPanel contentPane;
    private JTable table1;
    private JPanel principalPane;
    private JScrollPane tablePane;
    private JButton backButton;
    JFrame parentFrame;

    private static final String[] tableHeaders = {"Titre","Auteur","ISBN","Disponibilite","Quantite"};

    public BookMenu(JFrame parentFrame) {
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
        String[][] booksMatrice = BookSwing.createBooksMatrice();
        table1 = new JTable(booksMatrice, tableHeaders);
        tablePane.setViewportView(table1);
        revalidate();
        repaint();
    }

    public void backFunction() {
        this.dispose();
        parentFrame.setVisible(true);
    }
}
