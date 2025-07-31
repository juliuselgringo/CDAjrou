package training.afpa.CDA24060.modules;

import javax.swing.*;

public class Gui {

    public JFrame setFrame(int locationX, int locationY){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 300);
        frame.setLocation(locationX, locationY);
        frame.setVisible(true);

        return frame;
    }

    public JPanel setPanel(JFrame frame){
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        return panel;
    }

    public JLabel labelMaker(JPanel panel, String sentence, int positionY){
        JLabel label = new JLabel(sentence);
        label.setBounds(10, positionY, 300, 20);
        panel.add(label);
        return label;
    }

    public JTextArea textAreaMaker(JPanel panel, String sentence, int positionY){
        JTextArea textArea = new JTextArea(sentence);
        textArea.setBounds(10, positionY, 300, 20);
        textArea.setEditable(false);
        panel.add(textArea);

        return textArea;
    }

    public JTextField textFieldMaker(JPanel panel, int positionY){
        JTextField textField = new JTextField();
        textField.setBounds(10, positionY, 300, 20);
        panel.add(textField);

        return textField;
    }

    public JButton buttonMaker(JPanel panel, String sentence, int positionY){
        JButton button = new JButton(sentence);
        button.setBounds(10, positionY, 300, 20);
        panel.add(button);

        return button;
    }

}