package training.afpa.CDA24060.exo;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Averager {

    public String calculMoyenne() {
        ArrayRandomMaxSearch rndArr = new ArrayRandomMaxSearch();
        int[] randomArray = rndArr.arrayRandomizer();
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