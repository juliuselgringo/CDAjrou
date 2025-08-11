package training.afpa.model;

import java.util.ArrayList;

public class Wizard extends Character {

    private int intelligence;
    private static ArrayList<Wizard> wizardList= new ArrayList();

    /**
     * CONSTRUCTOR
     * @param pseudo String
     * @param origin Origin
     */
    public Wizard(String pseudo, Origin origin) {
        super(pseudo, origin);
        this.intelligence = 15;
        wizardList.add(this);
    }

    /**
     * CONSTRUCTOR
     * @param joueur Character
     */
    public Wizard(Character joueur){
        super(joueur.pseudo,joueur.origin);
        this.intelligence = 15;
        wizardList.add(this);
    }

    

}
