package training.afpa.CDA24060.exo;

import training.afpa.CDA24060.modules.Gui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class ArrayCreatorAnalyzer {

    public static ArrayList<Integer> arrayParsed = new ArrayList<>();

    public void saisieTailleMax(){
        Gui gui0 = new Gui();
        JFrame frame0 =  gui0.setFrame(500, 350);
        JPanel panel0 = gui0.setPanel(frame0);

        gui0.labelMaker(panel0, "Saissez la taille de votre tableau", 10);
        JTextField textFieldTAILLE_MAX = gui0.textFieldMaker(panel0,  40);
        JButton button0 = gui0.buttonMaker(panel0, "Valider", 70);

        button0.addActionListener(e -> {
            try {
                int tailleMax = Integer.parseInt(textFieldTAILLE_MAX.getText());
                saisieArray(tailleMax);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Valeur invalide",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void saisieArray(int TAILLE_MAX) {
        Gui gui10 = new Gui();
        JFrame frame10 =   gui10.setFrame(500, 350);
        JPanel panel10 = gui10.setPanel(frame10);

        int[] myArray = new int[TAILLE_MAX];
        for (int i = 0; i < TAILLE_MAX; i++) {
            boolean invalid = true;
            while(invalid) {
                try {
                    String valeur = JOptionPane.showInputDialog("Saisissez une valeur");
                    myArray[i] = Integer.parseInt(valeur);
                    invalid = false;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Valeur invalide, la valeur est interprété à 0",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        System.out.println(Arrays.toString(myArray));
        displayMessageBox(myArray);
    }



    public static void displayMessageBox(int[] array){
        Gui gui = new Gui();
        JFrame frame = gui.setFrame(500,300);
        JPanel panel = gui.setPanel(frame);

        JLabel label = gui.labelMaker(panel, "Voici votre tableau", 10);

        JTextArea textArea = gui.textAreaMaker(panel, Arrays.toString(array), 40);

        JButton button = gui.buttonMaker(panel, "Analyser",70);

        panel.add(button);
        button.addActionListener(e -> arrayAnalyser(array));

    }

    public static ArrayList arrayParser(int[] array, String select, ArrayList arrayParsed){

        int index = 0;
        for (int j : array) {
            if (select.equals("positive") && (j > 0)) {
                arrayParsed.add(j);
                index++;
            } else if (select.equals("negative") && (j < 0)) {
                arrayParsed.add(j);
                index++;
            } else if (select.equals("zero") && (j == 0)) {
                arrayParsed.add(j);
                index++;
            }
        }
        return arrayParsed;
    };



    public static String arrayListAverage(ArrayList array){
        int sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum = sum + (int)array.get(i);
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format((float) sum / array.size());
    }

    public static void arrayAnalyser(int[] array){

        //____________________POSITIVES DISPLAY________________________________
        Gui gui2 = new Gui();
        JFrame frame2 = gui2.setFrame(550,320);
        JPanel panel2 = gui2.setPanel(frame2);

        ArrayList positiveArray = arrayParser(array,"positive", arrayParsed);
        int numberPositives = positiveArray.size();
        String moyennePositiveNUmber = arrayListAverage(positiveArray);

        gui2.labelMaker(panel2, "Tableaux des entiers positifs du tableau", 10);
        gui2.textAreaMaker(panel2, positiveArray.toString(), 40);

        gui2.labelMaker(panel2, "Nombre d'entiers positifs",70);
        gui2.textAreaMaker(panel2, Integer.toString(numberPositives) , 100);

        gui2.labelMaker(panel2, "Moyenne des entiers positifs",130);
        gui2.textAreaMaker(panel2, moyennePositiveNUmber, 160);
        //_______________NEGATIVES  DISPLAY_______________________________________
        JFrame frame3 = gui2.setFrame(600,340);
        JPanel panel3 = gui2.setPanel(frame3);
        arrayParsed.clear();
        ArrayList negativeArray = arrayParser(array,"negative",arrayParsed);
        int numberNegatives = negativeArray.size();
        String moyenneNegativeNumber = arrayListAverage(negativeArray);

        gui2.labelMaker(panel3, "Tableaux des entiers négatifs du tableau", 10);
        gui2.textAreaMaker(panel3, negativeArray.toString(), 40);

        gui2.labelMaker(panel3, "Nombre d'entiers négatifs",70);
        gui2.textAreaMaker(panel3, Integer.toString(numberNegatives) , 100);

        gui2.labelMaker(panel3, "Moyenne des entiers négatifs",130);
        gui2.textAreaMaker(panel3, moyenneNegativeNumber, 160);

        //_______________ZERO DISPLAY_____________________________________________
        JFrame frame4 = gui2.setFrame(700,350);
        JPanel panel4 = gui2.setPanel(frame4);
        arrayParsed.clear();
        ArrayList zeroArray = arrayParser(array,"zero",arrayParsed);
        int numberZeros = zeroArray.size();

        gui2.labelMaker(panel4, "Tableaux des entiers égal à 0 du tableau", 10);
        gui2.textAreaMaker(panel4, zeroArray.toString(), 40);

        gui2.labelMaker(panel4, "Nombre d'entiers égal à 0",70);
        gui2.textAreaMaker(panel4, Integer.toString(numberZeros) , 100);

    }
}