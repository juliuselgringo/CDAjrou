package training.afpa.controller;

import training.afpa.model.Origin;
import training.afpa.model.Thief;
import training.afpa.model.Warrior;
import training.afpa.model.Wizard;
import training.afpa.utility.UserInputException;
import training.afpa.view.Display;
import training.afpa.view.Input;

public class ProgrammeTerminal {

    public static void run() throws UserInputException {
        boolean start = true;
        worldGeneration();
        while(start){
            Display.menuPrincipal();
            int menuSelection = Input.inputIntMenuChoice();
            switch (menuSelection) {
                case 0:
                    Display.printString("Merci d'avoir joué! A bientôt.");
                    start=false;
                    break;
                case 1:
                    Warrior warrior = Input.inputWarrior();
                    Display.printString(warrior.toString());
                    break;
                case 2:
                    Wizard wizard = Input.inputWizard();
                    Display.printString(wizard.toString());
                    break;
                case 3:
                    Thief thief = Input.inputThief();
                    Display.printString(thief.toString());
                    break;
                case 4:
                    Display.printString(Warrior.getWarriorsList());
                    break;
                case 5:
                    Display.printString(Wizard.getwizardsList());
                    break;
                case 6:
                    Display.printString(Thief.getthiefsList());
                    break;
                default:
                    start=false;
                    break;
            }

        }


    }

    private static void worldGeneration(){
        Origin elfic = new Origin("elfic");
        Origin.getOriginsMap("elfic", elfic);
        Origin orc = new Origin("orc");
        Origin.getOriginsMap("orc", orc);
        Origin dwarf =  new Origin("dwarf");
        Origin.getOriginsMap("dwarf", dwarf);
        Origin human = new Origin("human");
    }
}
