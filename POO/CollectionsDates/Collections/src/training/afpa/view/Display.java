package training.afpa.view;

import training.afpa.model.Origin;

public class Display {

    public static void printInt(int toPrint){
        System.out.println(toPrint);
    }

    public static void printString(String toPrint){
        System.out.println(toPrint);
    }

    public static void printSeparator(String toPrint){
        System.out.println("_________________" + toPrint + "_________________");
    }

    public static String printOriginSelection(){
        String stringToPrint = "";

        for(int i = 0; i < Origin.originsList.size(); i++){
            stringToPrint += i + " : " + Origin.originsList.get(i).toString() + " / ";
        };

        return stringToPrint;
    }

    public static String selection(String[] array){
        String stringToReturn = "";
        for(int i = 0; i < array.length; i++){
            stringToReturn += i + " : " + array[i] + " / ";
        }
        return stringToReturn;
    }
}
