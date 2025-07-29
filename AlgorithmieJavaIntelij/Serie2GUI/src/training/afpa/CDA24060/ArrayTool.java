package training.afpa.CDA24060;

import java.util.Random;

class ArrayTool {

    public int[] randomArray(int[] array) {
        for (int i = 0 ; i < array.length; i++) {
            Random randomizer = new Random();
            array[i] = randomizer.nextInt(100);
        }
        return array;
    }
}