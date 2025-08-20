package training.afpa.vue;

import java.util.Scanner;

public class UserInput {

    public static Scanner in = new Scanner(System.in);

    /**
     * INPUT TEXT
     * @return String
     */
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

    /**
     * INPUT INT
     * @return int
     */
    public static int userInputInt(){
        Boolean startIn = true;
        int userInt = 0;
        while(startIn){
            userInt = in.nextInt();
            in.nextLine();
            if(userInt < 0){
                Display.error("La quatité ne peut être inférieur à zéro.");
                startIn = true;
            }else{
                break;
            }
        }
        return userInt;
    }

    /**
     * INPUT MENU
     * @return String
     */
    public static String menuSelection(){
        Display.print("Saisissez le chiffre correspondant à votre choix: ");
        String userInput = in.nextLine();
        return userInput;
    }
}
