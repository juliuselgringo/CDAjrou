package training.afpa.CDA24060.exo;

import training.afpa.CDA24060.modules.PrintScan;

public class ArrayRandomSorter {

    public static PrintScan ps = new PrintScan();

    public static void arrayDisplay(){

        ps.printEZ("Votre tableau créer aléatoirement : ");
        ArrayRandomMaxSearch exo1 = new ArrayRandomMaxSearch();
        int[] newArray = exo1.arrayRandomizer();
        arraySort(newArray);
    }

    public static void arraySort(int[] array) {
        boolean permutation;
        do {
            permutation = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    permutation = true;
                }
            }
        }while(permutation);

        System.out.println("\nTableau trié par bubble sort : ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }


}