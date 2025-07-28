package training.afpa.cda24060;

import java.util.ArrayList;
import java.util.Arrays;

class Rules{

    /**
     *
     * @param arrayToTest
     */
    public void pileTest(ArrayList<Integer> arrayToTest) {
        System.out.println(arrayToTest.toString());
        int arraySize = arrayToTest.size();
        if(arraySize > 0) {
            for (int i = 0; i < (arraySize-1); i++) {
                if (arrayToTest.get(i) < arrayToTest.get(i + 1)) {
                    System.err.println("Vous ne pouvez pas empiler un disque plus grand que le disque du dessous.");
                }
            }
        }
    }

    public void victoire(ArrayList<Integer> arrayToTest) {
        int Csize = arrayToTest.size();
        if(Csize == 3 && arrayToTest.get(0) == 100 && arrayToTest.get(1) == 50 && arrayToTest.get(2) == 25) {
            System.out.println("Bravo!!! Vous avez gagnez.");
        }
    }

}