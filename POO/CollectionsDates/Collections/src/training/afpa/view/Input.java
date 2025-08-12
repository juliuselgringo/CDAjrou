package training.afpa.view;

import training.afpa.model.Origin;
import training.afpa.model.Thief;
import training.afpa.model.Warrior;
import training.afpa.model.Wizard;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {

    private static Scanner scanner =  new Scanner(System.in);

    /**
     * INPUT DATE
     * @return LocaleDate
     */
    public static LocalDate inputDate(){
        LocalDate inputDate =  null;
        Display.printString("Saisissez une date de création de la Squad (AAAA-MM-JJ): ) : ");
        boolean invalid = true;
        while(invalid){
            try{
                inputDate = LocalDate.parse(scanner.nextLine());
                invalid = false;
            }
            catch(DateTimeParseException ex){
                System.err.println("Le format est invalide, il doit correspondre à AAAA-MM-JJ");
            }
        }

        return inputDate;
    }

    /**
     * INPUT WARRIOR
     * @return Warrior
     */
    public static Warrior inputWarrior(){
        Display.printString("Saisissez le pseudo de vortre guerrier : ");
        String pseudo = scanner.nextLine();
        Origin origin = null;
        Display.printString("Saisissez l'origine de vortre guerrier : \n" + Display.printOriginSelection());
        int originInt = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < Origin.originsList.size(); i++){
            if(originInt == i){
                origin = (Origin)Origin.originsList.get(i);
            }
        }

        return new Warrior(pseudo,origin);
    }

    /**
     * INPUT WIZARD
     * @return Wizard
     */
    public static Wizard inputWizard(){
        Display.printString("Saisissez le pseudo de vortre mage : ");
        String pseudo = scanner.nextLine();
        Origin origin = null;
        Display.printString("Saisissez l'origine de vortre mage : \n" + Display.printOriginSelection());
        int originInt = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < Origin.originsList.size(); i++){
            if(originInt == i){
                origin = (Origin)Origin.originsList.get(i);
            }
        }

        return new Wizard(pseudo,origin);
    }

    /**
     * INPUT THIEF
     * @return Thief
     */
    public static Thief inputThief(){
        Display.printString("Saisissez le pseudo de vortre voleur : ");
        String pseudo = scanner.nextLine();
        Origin origin = null;
        Display.printString("Saisissez l'origine de vortre voleur : \n" + Display.printOriginSelection());
        int originInt = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < Origin.originsList.size(); i++){
            if(originInt == i){
                origin = (Origin)Origin.originsList.get(i);
            }
        }

        return new Thief(pseudo,origin);
    }
}
