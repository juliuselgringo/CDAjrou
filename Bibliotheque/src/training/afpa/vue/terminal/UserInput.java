package training.afpa.vue.terminal;

import java.util.InputMismatchException;
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
        return userText.trim();
    }

    /**
     * INPUT INT
     * @return int
     */
    public static int userInputInt(){
        Boolean startIn = true;
        int userInt = 0;
        while(startIn){
            try {
                userInt = in.nextInt();

                if (userInt < 0) {
                    Display.error("La quatité ne peut être inférieur à zéro.");
                    startIn = true;
                } else {
                    break;
                }
            }
            catch (InputMismatchException e) {
                Display.error("saisie invalide");
                break;
            }
            finally {
                in.nextLine();
            }
        }
        return userInt;
    }

    /**
     * INPUT EMAIL
     * @return String
     */
    public static String userInputEmail(){
        String REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        Boolean startIn = true;
        String emailInput = "";
        while(startIn){
            emailInput = in.nextLine();
            if(emailInput.trim() == null|| emailInput.trim().isEmpty() || !emailInput.trim().matches(REGEX)){
                Display.error("L'email saisi est invalide");
                startIn = true;
            }else{
                break;
            }
        }
        return emailInput.trim();
    }

    /**
     * INPUT MENU
     * @return String
     */
    public static String menuSelection(){
        Display.print("Saisissez le chiffre correspondant à votre choix: ");
        String userInput = in.nextLine();
        return userInput.trim();
    }
}
