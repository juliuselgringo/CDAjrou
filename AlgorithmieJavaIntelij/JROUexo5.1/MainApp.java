import java.util.ArrayList;

class MainApp {
    public static int[] myArray = {22, 13, 22, 44, 99, 60, 44, 22, 13, 60, 44, 13};
    public static ArrayList<String> myList =  new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < myArray.length; i++) {
            int numRepetition = 1;
            for (int j = i+1; j < myArray.length; j++) {
                if(myArray[i] == myArray[j]) {
                    numRepetition++;
                }
            }
            myList.add("Elément " + myArray[i] + " * " + numRepetition);
        }
        for (String s : myList) {
            System.out.println(s);
        }
    }
}