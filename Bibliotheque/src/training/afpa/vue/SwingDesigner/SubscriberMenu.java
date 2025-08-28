package training.afpa.vue.SwingDesigner;

import training.afpa.model.Subscriber;
import training.afpa.vue.swingGui.SubscriberSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubscriberMenu extends JFrame {

    private JPanel contentPane;
    private JPanel centralPane;
    private JScrollPane tablePane;
    private JTable table1;
    private JPanel buttonPane;
    private JButton createButton;
    private JButton deleteButton;
    private JButton modifyLastNameButton;
    private JButton backButton;
    private JPanel titlePane;
    private JLabel titleLabel;
    private JPanel footerPane;
    private JButton modifyEmailButton;
    private JButton refreshButton;
    private JComboBox emailBox;
    private JLabel emailLabel;
    private JFrame parentFrame;

    private static String[] tableHeaders = {"prénom","nom","email","date d'inscription"};

    public SubscriberMenu(JFrame parentFrame) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension minDim = new Dimension(1920,1000);
        setMinimumSize(minDim);
        setContentPane(contentPane);
        setVisible(true);
        pack();

        this.parentFrame = parentFrame;
        setTable();
        setEmailBox();
        emailBox.setVisible(false);
        emailLabel.setVisible(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backFunction();
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubscriberSwing.createSubscriber();
            }
        });


        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSubscriber();
            }
        });

        emailBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subscriberEmailToRemove = (String)emailBox.getSelectedItem();
                Subscriber subscriberToRemove = Subscriber.searchSubscriberByEmail(subscriberEmailToRemove);
                if (subscriberToRemove != null) {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Etes-vous sur de vouloir supprimer cet abonné?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        Subscriber.subscribersList.remove(subscriberToRemove);
                        JOptionPane.showMessageDialog(null,
                                "L'abonne a été supprimer avec succes!",
                                "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                        setTable();
                    }

                } else {
                    JOptionPane.showMessageDialog(null,
                            "L'abonne n'existe pas!",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        modifyLastNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubscriberSwing.modifySubscriberLastName();
            }
        });

        modifyEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubscriberSwing.modifySubscriberEmail();
            }
        });

    }

    public void setTable(){
        String[][] subscribersMatrice = SubscriberSwing.createSubscribersMatrice();
        table1 = new JTable(subscribersMatrice, tableHeaders);
        tablePane.setViewportView(table1);
        revalidate();
        repaint();
    }

    public void backFunction() {
        this.dispose();
        parentFrame.setVisible(true);
    }

    public void setEmailBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) emailBox.getModel();
        String[] emailList = SubscriberSwing.createSubscribersEmailList();
        for(String email : emailList){
            model.addElement(email);
        }
    }

    public void refreshButton() {
        setTable();
    }

    public void deleteSubscriber(){
        emailBox.setVisible(true);
        emailLabel.setVisible(true);
    }
}
