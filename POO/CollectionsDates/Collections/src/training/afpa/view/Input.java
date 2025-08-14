package training.afpa.view;

import training.afpa.model.Origin;
import training.afpa.model.Thief;
import training.afpa.model.Warrior;
import training.afpa.model.Wizard;
import training.afpa.utility.UserInputException;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
    public static Warrior inputWarrior() throws UserInputException {
        Display.printString("Saisissez le pseudo de votre guerrier : ");
        boolean invalid = true;
        String pseudo = "";
        while(invalid){
            try{
                pseudo = scanner.nextLine();
                if(pseudo.isEmpty()){
                    throw new UserInputException("Ce champ ne peut être vide!");
                }
                invalid = false;
            }
            catch(UserInputException uie){
                System.err.println("Saisie : " + uie.getMessage());
            }
        }
        
        Origin origin = null;
        Display.printString("Saisissez l'origine de votre guerrier : \n" + Display.printOriginSelection());
        invalid = true;
        int originInt = 0;
        while(invalid) {
            try{
                originInt = scanner.nextInt();
                scanner.nextLine();
                invalid = false;
            }catch(Exception e){
                scanner.nextLine();
                System.err.println("Saisie : " + e.getMessage());
            }
        if(!invalid){
            invalid = true;
            for (int i = 0; i < Origin.originsList.size(); i++) {
                try {
                    if (originInt == i) {
                        origin = Origin.originsList.get(i);
                        invalid = false;
                    }
                    throw new UserInputException("Votre saisie doit être un chiffre correspondant à l'origine souhaité.");
                } catch (UserInputException uie) {
                    System.err.println("Saisie : " + uie.getMessage());
                }

            }
        }

        }        
        return new Warrior(pseudo.trim(),origin);
    }
    
    /**
     * INPUT WARRIOR SWING
     */
    public static void inputWarriorGUI(){
        JPanel panel = Gui.setPanel(Gui.setFrame(400,400,600,600));
        Gui.textAreaMaker(panel,Display.originSelection(),10,10,200);
        Gui.labelMaker(panel,"Saisissez le pseudo de votre guerrier : ",10,240);
        JTextField pseudoField = Gui.textFieldMaker(panel,10,270);
        
        JLabel labelOrigin = Gui.labelMaker(panel,"Saisissez l'origine de votre guerrier : ",10,300);
        JTextField originChoice = Gui.textFieldMaker(panel,10,330);
        JButton validBtn = Gui.buttonMaker(panel,"Valider",360);

        validBtn.addActionListener(e -> {
            String originString =  originChoice.getText();
            String pseudoString = pseudoField.getText();
            Origin origin = null;
            ArrayList array = Origin.originsList;
            for(int i = 0; i < array.size(); i++){

                if(originString.toString().equals(array.get(i).toString())){
                    origin = Origin.originsList.get(i);

                }

            }
            Warrior warrior = null;
            try {
                warrior = new Warrior(pseudoString,origin);
            } catch (UserInputException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(Warrior.getWarriorsList());
            JOptionPane.showMessageDialog(null,warrior.toString(),"Information",JOptionPane.INFORMATION_MESSAGE);
        });
    }

    /**
     * INPUT WIZARD
     * @return Wizard
     */
    public static Wizard inputWizard() throws UserInputException {
        Display.printString("Saisissez le pseudo de votre mage : ");
        String pseudo = scanner.nextLine();
        Origin origin = null;
        Display.printString("Saisissez l'origine de votre mage : \n" + Display.printOriginSelection());
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
     * INPUT WIZARD SWING
     */
    public static void inputWizardGUI(){
        JPanel panel = Gui.setPanel(Gui.setFrame(400,400,600,600));
        JTextArea textArea = Gui.textAreaMaker(panel,Display.originSelection(),10,10,200);
        JLabel labelPseudo = Gui.labelMaker(panel,"Saisissez le pseudo de votre mage : ",10,240);
        JTextField pseudoField = Gui.textFieldMaker(panel,10,270);

        JLabel labelOrigin = Gui.labelMaker(panel,"Saisissez l'origine de votre mage : ",10,300);
        JTextField originChoice = Gui.textFieldMaker(panel,10,330);
        JButton validBtn = Gui.buttonMaker(panel,"Valider",360);

        validBtn.addActionListener(e -> {
            String originString =  originChoice.getText();
            String pseudoString = pseudoField.getText();
            Origin origin = null;
            ArrayList array = Origin.originsList;
            for(int i = 0; i < array.size(); i++){

                if(originString.toString().equals(array.get(i).toString())){
                    origin = Origin.originsList.get(i);

                }

            }
            Wizard wizard = null;
            try {
                wizard = new Wizard(pseudoString,origin);
            } catch (UserInputException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(Wizard.getwizardsList());
            JOptionPane.showMessageDialog(null,wizard.toString(),"Information",JOptionPane.INFORMATION_MESSAGE);
        });
    }


    /**
     * INPUT THIEF
     * @return Thief
     */
    public static Thief inputThief() throws UserInputException {
        Display.printString("Saisissez le pseudo de votre voleur : ");
        String pseudo = scanner.nextLine();
        Origin origin = null;
        Display.printString("Saisissez l'origine de votre voleur : \n" + Display.printOriginSelection());
        int originInt = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < Origin.originsList.size(); i++){
            if(originInt == i){
                origin = (Origin)Origin.originsList.get(i);
            }
        }
        return new Thief(pseudo,origin);
    }

    /**
     * INPUT THIEF SWING
     */
    public static void inputThiefGUI(){
        JPanel panel = Gui.setPanel(Gui.setFrame(400,400,600,600));
        JTextArea textArea = Gui.textAreaMaker(panel,Display.originSelection(),10,10,200);
        JLabel labelPseudo = Gui.labelMaker(panel,"Saisissez le pseudo de votre voleur : ",10,240);
        JTextField pseudoField = Gui.textFieldMaker(panel,10,270);

        JLabel labelOrigin = Gui.labelMaker(panel,"Saisissez l'origine de votre voleur : ",10,300);
        JTextField originChoice = Gui.textFieldMaker(panel,10,330);
        JButton validBtn = Gui.buttonMaker(panel,"Valider",360);

        validBtn.addActionListener(e -> {
            String originString =  originChoice.getText();
            String pseudoString = pseudoField.getText();
            Origin origin = null;
            ArrayList array = Origin.originsList;
            for(int i = 0; i < array.size(); i++){

                if(originString.toString().equals(array.get(i).toString())){
                    origin = Origin.originsList.get(i);

                }

            }
            Thief thief = null;
            try {
                thief = new Thief(pseudoString,origin);
            } catch (UserInputException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(Thief.getthiefsList());
            JOptionPane.showMessageDialog(null,thief.toString(),"Information",JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static int inputIntMenuChoice(){
        Display.printString("Saisissez le chiffre correspondant à votre choix : ");
        int inputToReturn = scanner.nextInt();
        scanner.nextLine();
        return inputToReturn;
    }
}
