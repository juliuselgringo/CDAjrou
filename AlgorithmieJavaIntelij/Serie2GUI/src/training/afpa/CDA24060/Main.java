package training.afpa.CDA24060;

import javax.swing.*;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static int[] myArray;

    public static void main(String[] args) {
        JFrame appFrame = new JFrame("Exercices Serie 2");
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(400, 300);

        appFrame.setVisible(true);

        JPanel appPanel = new JPanel();
        appFrame.add(appPanel);
        placeComponents(appPanel);
    }

    public static void placeComponents(JPanel appPanel) {
        appPanel.setLayout(null);

        JLabel lengthLabel = new JLabel("Taille du tableau : ");
        lengthLabel.setBounds(10, 10, 200, 20);
        appPanel.add(lengthLabel);

        JTextField lengthField = new JTextField(10);
        lengthField.setBounds(150, 10, 200, 20);
        appPanel.add(lengthField);

        JButton randomButton = new JButton("Créer");
        randomButton.setBounds(150, 40, 200, 20);
        appPanel.add(randomButton);

        JTextArea arrayDisplay = new JTextArea();
        arrayDisplay.setBounds(10, 70, 300, 20);
        appPanel.add(arrayDisplay);

        JButton searchMaxButton = new JButton("Search max");
        searchMaxButton.setBounds(150, 100, 200, 20);

        randomButton.addActionListener(e -> {
            int length;
            if(!lengthField.getText().isEmpty()) {
                try{
                    length = Integer.parseInt(lengthField.getText());
                }
                catch(NumberFormatException nfe){
                    arrayDisplay.setText("Le champ doit contenir un nombre!");
                    throw new NumberFormatException("Le champ doit contenir un nombre!");
                }
            }else{
                length = 0;
            }


            if (length > 0) {
                ArrayTool arrayCreator = new ArrayTool();
                myArray = new int[length];
                myArray = arrayCreator.randomArray(myArray);

                String arrayAsString = Arrays.toString(myArray);
                System.out.println(arrayAsString);

                arrayDisplay.setText(arrayAsString);
                appPanel.revalidate();
                appPanel.repaint();
            }else {
                arrayDisplay.setText("La taille du tableau doit être superieur à 0.");
            }
        });


    }
}