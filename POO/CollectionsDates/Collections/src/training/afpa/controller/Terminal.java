package training.afpa.controller;

import training.afpa.model.*;
import training.afpa.view.Display;

public class Terminal {

    public static void runTerminal(){

        // ________________________________________création des différentes origines
        Origin elfic = new Origin("elfic");
        Origin orc = new Origin("orc");
        Origin dwarf =  new Origin("dwarf");
        Origin human = new Origin("human");

        // __________________________________________création de personnage
        Warrior joueur1 = new Warrior("julius", dwarf);
        Warrior joueur2 = new Warrior("Ulfrick",human);
        Warrior joueur3 = new Warrior("jojohns",elfic);

        Wizard joueur4 = new Wizard("Aldebaran",orc);

        Thief joueur5 = new Thief("picsous",dwarf);
        Thief joueur6 = new Thief("pic assiette",dwarf);
        // _____________________________________________level up
        joueur1.setLevel();

        //_______________________________________________OUTPUT__________________________

        //_______________WARRIORS____________________
        Display.printSeparator("Warriors");

        System.out.println(Warrior.getWarriorsList());
        System.out.println(joueur1.getClass());
        System.out.println(joueur2.getClass());

        //_____________WIZARDS_____________________
        Display.printSeparator("Wizards");

        System.out.println(Wizard.getwizardsList());

        //______________THIEFS________________
        Display.printSeparator("Thiefs");

        Display.printString(Thief.getthiefsList());

        //___________SQUADS___________________________
        Display.printSeparator("Squads");

        Squad squad1 = new Squad("Frime Team",joueur1,joueur4,joueur5);
        Display.printString(Squad.getSquadsList());

        //_____________CREATION SQUAD ET PERSO_______________________
        Display.printSeparator("Squads from Scratch");
        Squad.createNewSquadFromScratch();
        Display.printString(Squad.getSquadsList());

        //_____________COMPARAISON AVEC == _________________________
        Display.printSeparator("Comparaison === ");

        System.out.println(joueur1 == joueur1);
        System.out.println(joueur1 == joueur2);

        //_____________COMPARAISONS DE GUERRIERS_________________
        Display.printSeparator("Comparaisons guerriers");

        Display.printInt(joueur1.compareTo(joueur3));
        Display.printInt(joueur1.compareTo(joueur1));

        //_____________COMPARAISON DE VOLEURS _______________________
        Display.printSeparator("Comparaisons de voleurs");

        Display.printInt(joueur5.compareTo(joueur6));
    }
}
