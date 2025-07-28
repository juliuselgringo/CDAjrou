package training.afpa.cda24060;

import java.util.ArrayList;

class ArrayModifier{

    /**
     *
     * @param arrayOrigin
     * @param arrayDestination
     */
    public void pickAndPut(ArrayList<Integer> arrayOrigin, ArrayList<Integer> arrayDestination){
        arrayDestination.add(arrayOrigin.get(arrayOrigin.size()-1));
        arrayOrigin.remove(arrayOrigin.size()-1);
    }

}