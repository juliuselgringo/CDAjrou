package training.afpa.model;

import training.afpa.view.Display;
import training.afpa.view.Input;

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
        return "Squad{" + "name=" + name + ", creationDate=" + creationDate + '}';
    }

    /**
     * SAISIE SQUAD ET PERSONNAGE
     */
    public static void createNewSquadFromScratch() {
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

}
