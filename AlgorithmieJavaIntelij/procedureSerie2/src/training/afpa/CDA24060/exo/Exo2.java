package training.afpa.CDA24060.exo;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Exo2 {

    public String calculMoyenne(int[] randomArray) {
        int somme = 0;
        for (int i = 0; i < randomArray.length; i++) {
            somme += randomArray[i];
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String moyenne = decimalFormat.format((float) somme / randomArray.length);
        System.out.println("La moyenne du tableau " + Arrays.toString(randomArray) + " est : " + moyenne);
        return moyenne;
    }
}