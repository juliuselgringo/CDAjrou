package training.afpa.CDA24060;

import training.afpa.CDA24060.exo.Exo1;
import training.afpa.CDA24060.exo.Exo2;
import training.afpa.CDA24060.exo.Exo3;
import training.afpa.CDA24060.exo.Exo4;

import javax.swing.*;

class MainApp {

    //public static int[] randomArray;
    //public static int moyenne;
    public static int TAILLE_MAX;
    public static int[] myArray;


    public static void main(String[] args) {

        Exo1 exo1 = new Exo1();
        //randomArray = exo1.searchMax(randomArray);

        Exo2 exo2 = new Exo2();
        //moyenne = exo2.calculMoyenne(randomArray);

        Exo3 exo3 = new  Exo3();
        TAILLE_MAX = exo3.saisieTailleMax();
        myArray = exo3.saisieArray(TAILLE_MAX);


        exo3.DisplayMessageBox(myArray);

        Exo4 exo4 = new Exo4();

    }

}