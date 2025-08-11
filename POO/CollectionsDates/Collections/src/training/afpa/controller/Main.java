package training.afpa.controller;

import training.afpa.model.Character;
import training.afpa.model.Warrior;
import training.afpa.model.Origin;

public class Main {

    public static void main(String[] args) {
        Main mmorpg = new Main();
        mmorpg.run();

    }

    public void run(){

        // création des différentes origines
        Origin elfic = new Origin("elfic");
        Origin orc = new Origin("orc");
        Origin dwarve =  new Origin("dwarve");
        Origin human = new Origin("human");

        // création de personnage
        Character joueur1 = new Character("julius",dwarve);
        Warrior guerrier1 = new Warrior("Ulfrick",human);

        // Spécialisation
        Warrior guerrier2 = new Warrior(joueur1);


        // level up
        guerrier1.setLevel();

        // out
        System.out.println(joueur1);
        System.out.println(guerrier1);
        System.out.println(guerrier2);
        System.out.println(Warrior.getWarriorsList());


    }

}
