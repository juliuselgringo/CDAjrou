package training.afpa.view;

import javax.swing.*;

public class Gui {

    /**
     *
     * @param locationX int
     * @param locationY int
     * @param width int
     * @param height int
     * @return JFrame
     */
    public static JFrame setFrame(int locationX, int locationY,int width,int height){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(width, height);
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

    /**
     *
     * @param panel
     * @param sentence
     * @param positionY
     * @return
     */
    public static JLabel labelMaker(JPanel panel, String sentence,int positionX, int positionY){
        JLabel label = new JLabel(sentence);
        label.setBounds(positionX, positionY, 500, 20);
        panel.add(label);
        return label;
    }

    /**
     *
     * @param panel
     * @param sentence
     * @param positionY
     * @param height
     * @return
     */
    public static JTextArea textAreaMaker(JPanel panel, String sentence,int positionX, int positionY,int height){
        JTextArea textArea = new JTextArea(sentence);
        textArea.setBounds(positionX, positionY, 400, height);
        textArea.setEditable(false);
        panel.add(textArea);

        return textArea;
    }

    /**
     *
     * @param panel
     * @param positionY
     * @return
     */
    public static JTextField textFieldMaker(JPanel panel,int positionX, int positionY){
        JTextField textField = new JTextField();
        textField.setBounds(positionX, positionY, 300, 20);
        panel.add(textField);

        return textField;
    }

    /**
     *
     * @param panel
     * @param sentence
     * @param positionY
     * @return
     */
    public static JButton buttonMaker(JPanel panel, String sentence, int positionY){
        JButton button = new JButton(sentence);
        button.setBounds(10, positionY, 300, 20);
        panel.add(button);

        return button;
    }

}