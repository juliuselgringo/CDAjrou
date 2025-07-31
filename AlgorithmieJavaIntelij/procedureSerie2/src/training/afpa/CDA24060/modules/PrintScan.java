package training.afpa.CDA24060.modules;

import java.util.Scanner;

public class PrintScan {

    public static void printEZ(String myString){
        System.out.println(myString);
    }

    public static int scanINT(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String scanString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static double scanDouble(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

}