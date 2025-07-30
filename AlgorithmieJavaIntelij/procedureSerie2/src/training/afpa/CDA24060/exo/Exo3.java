package training.afpa.CDA24060.exo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.*;

public class Exo3{

    public static ArrayList<Integer> arrayParsed = new ArrayList<>();

    public static int saisieTailleMax(){
        System.out.println("Saisissez la taille de votre tableau : ");
        Scanner scanner = new Scanner(System.in);
        int TAILLE_MAX = 0;
            try {
                TAILLE_MAX = scanner.nextInt();

            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "afaerg","aefargv",JOptionPane.INFORMATION_MESSAGE);
                scanner.next();
            }

        return TAILLE_MAX;
    }

    public static int[] saisieArray(int TAILLE_MAX) {
        Scanner scanner = new Scanner(System.in);
        int[] myArray = new int[TAILLE_MAX];
        for (int i = 0; i < TAILLE_MAX; i++) {
            System.out.println("Saissez la valeur à l'index " + i + " de votre tableau : ");
            myArray[i] = scanner.nextInt();
        }
        System.out.println("Voici votre tableau : " + Arrays.toString(myArray));
        return myArray;
    }

    public  static JFrame setFrame(int locationX, int locationY){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocation(locationX, locationY);
        frame.setVisible(true);

        return frame;
    }

    public static JPanel setPanel(JFrame frame){
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        return panel;
    }

    public void DisplayMessageBox(int[] array){
        JFrame frame = setFrame(500,300);
        JPanel panel = setPanel(frame);

        JLabel label = new JLabel("Voici votre tableau");
        label.setBounds(10, 10, 300, 20);
        panel.add(label);

        JTextArea textArea = new JTextArea(Arrays.toString(array));
        textArea.setEditable(false);
        textArea.setBounds(10, 40, 270, 20);
        panel.add(textArea);

        JButton button = new JButton("Analyser");
        button.setBounds(10, 70, 270, 20);
        panel.add(button);
        button.addActionListener(e -> {
            arrayAnalyser(array);
        });

    }

    public static ArrayList arrayParser(int[] array, String select, ArrayList arrayParsed){

        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (select.equals("positive") && (array[i] > 0)) {
                arrayParsed.add(array[i]);
                index++;
            }else if (select.equals("negative") && (array[i] < 0)) {
                arrayParsed.add(array[i]);
                index++;
            }else if (select.equals("zero") && (array[i] == 0)) {
                arrayParsed.add(array[i]);
                index++;
            }
        }
        return arrayParsed;
    };

    public static void labelMaker(JPanel panel, String sentence, int positionY){
        JLabel label = new JLabel(sentence);
        label.setBounds(10, positionY, 300, 20);
        panel.add(label);
    }

    public static void textAreaMaker(JPanel panel, String sentence, int positionY){
        JLabel label = new JLabel(sentence);
        label.setBounds(10, positionY, 300, 20);
        panel.add(label);
    }

    public static int sumArrayList(ArrayList array){
        int sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum = sum + (int)array.get(i);
        }
        return sum;
    }

    public void arrayAnalyser(int[] array){

        //____________________POSITIVES DISPLAY________________________________
        JFrame frame2 = setFrame(550,320);
        JPanel panel2 = setPanel(frame2);

        ArrayList positiveArray = arrayParser(array,"positive", arrayParsed);
        int numberPositives = positiveArray.size();
        String moyennePositiveNUmber = Integer.toString(sumArrayList(positiveArray) / numberPositives);

        labelMaker(panel2, "Tableaux des entiers positifs du tableau", 10);
        textAreaMaker(panel2, positiveArray.toString(), 40);

        labelMaker(panel2, "Nombre d'entiers positifs",70);
        textAreaMaker(panel2, Integer.toString(numberPositives) , 100);

        labelMaker(panel2, "Moyenne des entiers positifs",130);
        textAreaMaker(panel2, moyennePositiveNUmber, 160);
        //_______________NEGATIVES  DISPLAY_______________________________________
        JFrame frame3 = setFrame(600,340);
        JPanel panel3 = setPanel(frame3);
        arrayParsed.clear();
        ArrayList negativeArray = arrayParser(array,"negative",arrayParsed);
        int numberNegatives = negativeArray.size();
        String moyenneNegativeNumber = Integer.toString(sumArrayList(negativeArray) / numberNegatives);

        labelMaker(panel3, "Tableaux des entiers négatifs du tableau", 10);
        textAreaMaker(panel3, negativeArray.toString(), 40);

        labelMaker(panel3, "Nombre d'entiers négatifs",70);
        textAreaMaker(panel3, Integer.toString(numberNegatives) , 100);

        labelMaker(panel3, "Moyenne des entiers négatifs",130);
        textAreaMaker(panel3, moyenneNegativeNumber, 160);

        //_______________ZERO DISPLAY_____________________________________________
        JFrame frame4 = setFrame(700,350);
        JPanel panel4 = setPanel(frame4);
        arrayParsed.clear();
        ArrayList zeroArray = arrayParser(array,"zero",arrayParsed);
        int numberZeros = zeroArray.size();
        String moyenneZeroNumber = Integer.toString(sumArrayList(zeroArray)/numberZeros);

        labelMaker(panel4, "Tableaux des entiers égal à 0 du tableau", 10);
        textAreaMaker(panel4, zeroArray.toString(), 40);

        labelMaker(panel4, "Nombre d'entiers égal à 0",70);
        textAreaMaker(panel4, Integer.toString(numberNegatives) , 100);

    }
}