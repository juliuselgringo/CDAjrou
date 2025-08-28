package training.afpa.vue.SwingDesigner;

import training.afpa.model.Book;
import training.afpa.vue.swingGui.BookSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookMenu extends JFrame {

    private JPanel contentPane;
    private JTable table1;
    private JScrollPane tablePane;
    private JButton backButton;
    private JPanel titlePane;
    private JPanel centralPane;
    private JLabel titleLabel;
    private JPanel footerPane;
    private JButton createBookBtn;
    private JButton deleteButton;
    private JButton searchButton;
    private JPanel buttonPane;
    private JComboBox titleBox;
    private JButton refreshButton;
    private JLabel labelTitleBox;
    private JButton modifyButton;

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
        setTitleBox();
        titleBox.setVisible(false);
        labelTitleBox.setVisible(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backFunction();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        titleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titleToDelete = titleBox.getSelectedItem().toString();

                int response = JOptionPane.showConfirmDialog(null,"Etes-vous sur de vouloir supprimer: " +
                        titleToDelete,"Confirmation",JOptionPane.YES_NO_OPTION);
                if(response == JOptionPane.YES_OPTION){
                    Book.booksList.remove(Book.searchBookByTitle(titleToDelete));
                    JOptionPane.showMessageDialog(contentPane,"Le livre a été supprimer avec succès.",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                    titleBox.setVisible(false);
                    setTable();
                }
            }
        });

        createBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookSwing.createBook();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookSwing.searchABookByTitle();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookSwing.modifyBookQuantity();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTable();
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

    public void setTitleBox(){
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) titleBox.getModel();
        String[] booksList = BookSwing.createBookTitleList();
        for (String book : booksList) {
            model.addElement(book);
        }
    }

    public void backFunction() {
        this.dispose();
        parentFrame.setVisible(true);
    }


    public void deleteBook(){
        titleBox.setVisible(true);
        labelTitleBox.setVisible(true);
    }

}
