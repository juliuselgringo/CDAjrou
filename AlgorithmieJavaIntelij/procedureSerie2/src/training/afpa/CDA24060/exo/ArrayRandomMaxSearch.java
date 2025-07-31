package training.afpa.CDA24060.exo;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class ArrayRandomMaxSearch {

    public static int[] arrayRandomizer() {
        Scanner scanner = new Scanner(System.in);
        int TAILLE;
        System.out.println("Saisissez un entier pour générer un tableau : ");
        TAILLE = scanner.nextInt();
        int[] randomArray = new int[TAILLE];
        Random rand = new Random();

        for (int i = 0; i < TAILLE; i++) {
            randomArray[i] = rand.nextInt(100);
        }

        System.out.println("Tableau généré : " + Arrays.toString(randomArray));
        return randomArray;
    }

    public static void searchMax () {
        int [] randomArray = arrayRandomizer();
        int max = 0;
        for (int i = 0; i < randomArray.length; i++) {
            if(randomArray[i] > max) {
                max = randomArray[i];
            }


        }

        System.out.println("Valeur maximum du tableau : " + max);
    }
}