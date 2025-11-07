package fr.juliuselgringo.sparadrap.utility;

import javax.swing.*;
import java.awt.*;

/**
 * classe qui permet de simplifier l'appel de méthode Swing pour l'interface graphique
 */
public class Gui {

    /**
     * constructeur par défaut
     */
    public Gui (){}

    /**
     * CREER UN FULL SCREEN
     * @return JFrame
     */
    public static JFrame setFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension minDim = new Dimension(700,300);
        frame.setMinimumSize(minDim);
        frame.setVisible(true);

        return frame;
    }

    /**
     * CREER UN POP UP
     * @param width int
     * @param height int
     * @return JFrame
     */
    public static JFrame setPopUpFrame(int width, int height){
        JFrame frame = new JFrame();
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    /**
     * JPANEL
     * @param frame JFrame
     * @return JPanel
     */
    public static JPanel setPanel(JFrame frame){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(119,103,176));
        frame.add(panel);
        panel.setLayout(null);

        return panel;
    }

    /**
     * créer un label
     * @param panel JPanel
     * @param sentence String
     * @param positionX int
     * @param positionY int
     * @return JLabel
     */
    public static JLabel labelMaker(JPanel panel, String sentence,int positionX, int positionY){
        JLabel label = new JLabel(sentence);
        label.setBounds(positionX, positionY, 500, 20);
        label.setForeground(Color.WHITE);
        panel.add(label);

        return label;
    }

    /**
     * créer un champs texte
     * @param panel JPanel
     * @param sentence String
     * @param positionX int
     * @param positionY int
     * @param width int
     * @param height int
     * @return JTextArea
     */
    public static JTextArea textAreaMaker(JPanel panel, String sentence,int positionX, int positionY,int width, int height){
        JTextArea textArea = new JTextArea(sentence);
        textArea.setBounds(positionX, positionY, width, height);
        textArea.setEditable(false);
        panel.add(textArea);

        return textArea;
    }

    /**
     * créer un champ texte avec une scroll barre
     * @param panel Jpanel
     * @param sentence String
     * @param positionX int
     * @param positionY int
     * @param width int
     * @param height int
     * @return JTextArea
     */
    public static JTextArea textAreaMakerScroll(JPanel panel, String sentence,int positionX, int positionY,int width, int height){
        JTextArea textArea = new JTextArea(sentence);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(positionX, positionY, width, height);
        textArea.setEditable(false);
        panel.add(scrollPane);

        return textArea;
    }

    /**
     * créer un champ de saisie
     * @param panel JPanel
     * @param positionX int
     * @param positionY int
     * @return JTextField
     */
    public static JTextField textFieldMaker(JPanel panel,int positionX, int positionY){
        JTextField textField = new JTextField();
        textField.setBounds(positionX, positionY, 300, 20);
        panel.add(textField);

        return textField;
    }

    /**
     * créer un bouton
     * @param panel JPanel
     * @param sentence String
     * @param positionY int
     * @return JButton
     */
    public static JButton buttonMaker(JPanel panel, String sentence, int positionY){
        JButton button = new JButton(sentence);
        button.setBounds(10, positionY, 300, 20);
        panel.add(button);
        return button;
    }

    /**
     * créer un tableau
     * @param panel JPanel
     * @param matrice String[][]
     * @param tableHeaders String[]
     * @param x int
     * @param y int
     * @param width int
     * @param height int
     * @return JTable
     */
    public static JTable tableMaker(JPanel panel, String[][] matrice, String[] tableHeaders,int x,int y,int width, int height){

        JTable table = new JTable(matrice, tableHeaders);

        table.setBackground(new Color(68, 71, 90));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(x,y, width,height);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        return table;

    }

    /**
     * créer une combo box
     * @param panel JPanel
     * @param list String[]
     * @param positionX int
     * @param positionY int
     * @return JComboBox
     */
    public static JComboBox<String> comboBoxMaker(JPanel panel, String[] list, int positionX, int positionY){
        JComboBox<String> comboBox = new JComboBox<>(list);
        comboBox.setBounds(positionX, positionY, 300, 20);
        panel.add(comboBox);

        return comboBox;
    }

    /**
     * créer une combo box non typée
     * @param panel JPanel
     * @param positionX int
     * @param positionY int
     * @param width int
     * @return JComboBox
     */
    public static JComboBox comboBoxMaker(JPanel panel, int positionX, int positionY, int width){
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(positionX, positionY, width, 20);
        panel.add(comboBox);

        return comboBox;
    }

}