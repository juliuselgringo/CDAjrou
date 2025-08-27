package training.afpa.vue.SwingDesigner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JPanel contentPane;
    private JLabel titleLabel;
    private JButton loanButton;
    private JButton subscriberButton;
    private JTable table1;
    private JPanel titlePanel;
    private JButton exitButton;
    private JButton bookButton;
    private JScrollPane scrollPane;
    private JPanel centralPanel;
    private JPanel footerPanel;



    public MenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_HORIZ);
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
    }

    public void closeFunction() {
        System.exit(0);
    }

    public void bookMenu() {
        BookMenu bookMenu = new BookMenu(this);
        this.dispose();
    }

}
