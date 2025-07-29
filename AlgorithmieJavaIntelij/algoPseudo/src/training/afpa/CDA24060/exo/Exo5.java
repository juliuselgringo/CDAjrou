package training.afpa.CDA24060.exo;

import java.util.Scanner;

public class Exo5 {

    public static int[] sortArray = {0, 12, 15, 22, 24};

    public static int rechercheDichotomique(int[] array, int x) {
        int indiceBas = 0;
        int indiceHaut = array.length;
        int indiceMilieu = (indiceBas + indiceHaut) / 2;
        boolean trouve = false;

        while (trouve == false && indiceBas <= indiceHaut) {
            if(x < array[indiceMilieu]) {
                indiceHaut = indiceMilieu;
            }else{
                if(x > array[indiceMilieu]) {
                    indiceBas = indiceMilieu;
                }else{
                    trouve = true;
                }
            }
            indiceMilieu = (indiceBas + indiceHaut) / 2;
        }

        if(trouve == true){
            return (indiceMilieu);
        }else{
            return (-1);
        }
    }

    public static void recherche(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez un entier à rechercher dans le tableau : ");
        int x = sc.nextInt();

        int position = rechercheDichotomique(sortArray, x);

        if(position == -1){
            System.out.println("x n'est pas dans le tableau");
        }else{
            System.out.println("x est à la position : " + position);
        }
    }
}