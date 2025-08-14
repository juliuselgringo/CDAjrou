package training.afpa.model;

import training.afpa.utility.UserInputException;
import training.afpa.view.Display;
import training.afpa.view.Gui;
import training.afpa.view.Input;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Squad {

    private String name;
    private LocalDate creationDate;

    private Warrior warrior;
    private Wizard wizard;
    private Thief thief;

    private static ArrayList<Squad> squadsList = new ArrayList<Squad>();

    /**
     * CONSTRUCTOR
     * @param name String
     * @param player1 Warrior
     * @param player2 Wizard
     * @param player3 Thief
     */
    public Squad(String name, Warrior player1, Wizard player2, Thief player3) {
        setName(name);
        setDate();
        this.warrior = player1;
        this.wizard = player2;
        this.thief = player3;
        squadsList.add(this);
    }

    /**
     * CONSTRUCTOR
     * @param name String
     * @param player1 Warrior
     * @param player2 Wizard
     * @param player3 Thief
     * @param creationDate LocalDate
     */
    public Squad(String name, Warrior player1, Wizard player2, Thief player3, LocalDate creationDate) {
        setName(name);
        this.creationDate = creationDate;
        this.warrior = player1;
        this.wizard = player2;
        this.thief = player3;
        squadsList.add(this);
    }

    /**
     * SETTER name
     * @param name String
     */
    private void setName(String name) {
        if (!name.equals("")) {
            this.name = name;
        }
    }

    /**
     * GETTER name
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * SETTER creationDate
     */
    private void setDate() {
        this.creationDate = LocalDate.now();
    }

    /**
     * GETTER creationDate
     * @return
     */
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    /**
     * GETTER squadsList
     * @return String
     */
    public static String getSquadsList(){
        return squadsList.toString();
    }

    /**
     * TO STRING
     * @return
     */
    @Override
    public String toString() {
        return "Squad{" + "name=" + name + ", creationDate=" + creationDate + "\n" +
                this.warrior.toString() + "\n" +
                this.wizard.toString() + "\n" +
                this.thief.toString() + "\n";
    }

    /**
     * SAISIE SQUAD ET PERSONNAGE
     */
    public static void createNewSquadFromScratch() throws UserInputException {
        Display.printString("Saisissez le nom de la squad : ");
        String squadName = new Scanner(System.in).nextLine();

        LocalDate inputCreationDateSquadTest = Input.inputDate();
        Display.printString(inputCreationDateSquadTest.toString());

        Warrior squadWarrior = Input.inputWarrior();
        Display.printString(squadWarrior.toString());
        Wizard squadWizard = Input.inputWizard();
        Display.printString(squadWizard.toString());
        Thief squadThief = Input.inputThief();
        Display.printString(squadThief.toString());

        Squad newSquad = new Squad(squadName, squadWarrior, squadWizard, squadThief, inputCreationDateSquadTest);
        Display.printString("Votre nouvelle squad a bien été créé : " + newSquad.toString());
    }

    /**
     * SQUAD MAKER GUI SELECTER TEAM MATE
     */
    public static void selectSquadMatesGUI(){

        JPanel panel = Gui.setPanel(Gui.setFrame(300, 300,600,700));

        JTextArea Warriors = Gui.textAreaMaker(panel, Warrior.warriorsMap.toString(),10,10,300);
        JTextArea Wizards = Gui.textAreaMaker(panel, Wizard.wizardsMap.toString(),420,10,300);
        JTextArea Thiefs = Gui.textAreaMaker(panel, Thief.thiefsMap.toString(),840,10,300);
        JLabel warriorLabel = Gui.labelMaker(panel,"Saisissez le pseudo du guerrier de votre squad",10,330);
        JLabel wizardLabel = Gui.labelMaker(panel,"Saisissez le pseudo du mage de votre squad",410,330);
        JLabel thiefLabel = Gui.labelMaker(panel,"Saisissez le pseudo du voleur de votre squad",820,330);
        JTextField warriorField = Gui.textFieldMaker(panel,10,360);
        JTextField wizardField = Gui.textFieldMaker(panel,420,360);
        JTextField thiefField = Gui.textFieldMaker(panel,840,360);
        JTextField squadNameField = Gui.textFieldMaker(panel,10,390);


        JButton validBtn = Gui.buttonMaker(panel, "Valider",410);

        validBtn.addActionListener(e -> {
            Warrior warriorSquad = null;
            Wizard wizardSquad = null;
            Thief thiefSquad = null;

            String squadName = squadNameField.getText();
            String warriorPseudo = warriorField.getText();
            String wizardPseudo = wizardField.getText();
            String thiefPseudo = thiefField.getText();

            for (Warrior warrior : Warrior.warriorsList){
                if (warrior.getPseudo().equals(warriorPseudo)){
                    warriorSquad = warrior;
                }
            }
            for (Wizard wizard : Wizard.wizardsList){
                if (wizard.getPseudo().equals(wizardPseudo)){
                    wizardSquad = wizard;
                }
            }
            for (Thief thief : Thief.thiefsList){
                if (thief.getPseudo().equals(thiefPseudo)){
                    thiefSquad = thief;
                }
            }

            Squad newSquad = new Squad(squadName,warriorSquad, wizardSquad,thiefSquad);

        });
    }

}
