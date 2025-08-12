package training.afpa.view;

import javax.swing.*;

public class Gui {

    /**
     * JFRAME
     * @param locationX int
     * @param locationY int
     * @return
     */
    public static JFrame setFrame(int locationX, int locationY){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocation(locationX, locationY);
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
        frame.add(panel);
        panel.setLayout(null);

        return panel;
    }

    public static JLabel labelMaker(JPanel panel, String sentence, int positionY){
        JLabel label = new JLabel(sentence);
        label.setBounds(10, positionY, 500, 20);
        panel.add(label);
        return label;
    }

    public static JTextArea textAreaMaker(JPanel panel, String sentence, int positionY){
        JTextArea textArea = new JTextArea(sentence);
        textArea.setBounds(10, positionY, 300, 20);
        textArea.setEditable(false);
        panel.add(textArea);

        return textArea;
    }

    public static JTextField textFieldMaker(JPanel panel, int positionY){
        JTextField textField = new JTextField();
        textField.setBounds(10, positionY, 300, 20);
        panel.add(textField);

        return textField;
    }

    public static JButton buttonMaker(JPanel panel, String sentence, int positionY){
        JButton button = new JButton(sentence);
        button.setBounds(10, positionY, 300, 20);
        panel.add(button);

        return button;
    }

}