package training.afpa.vue;

import java.util.Scanner;

public class UserInput {

    public static Scanner in = new Scanner(System.in);

    public static String userInputText(){
        Boolean startIn = true;
        String userText = null;
        while(startIn){
            userText = in.nextLine();
            userText = userText.trim();
            if(userText.equals("")) {
                Display.error("Saisie texte : saisie invalide");
                startIn = true;
            }else{
                break;
            }
        }
        return userText;
    }

    public static String menuSelection(){
        Display.print("Saisissez le chiffre correspondant à votre choix: ");
        String userInput = in.nextLine();
        return userInput;
    }
}
