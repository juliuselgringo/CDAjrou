package training.afpa.model;

import training.afpa.utility.UserInputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wizard extends Character {

    private int intelligence;
    public static ArrayList<Wizard> wizardsList= new ArrayList();
    public static Map<String, Wizard> wizardsMap= new HashMap();

    /**
     * CONSTRUCTOR
     * @param pseudo String
     * @param origin Origin
     */
    public Wizard(String pseudo, Origin origin) throws UserInputException {
        super(pseudo, origin);
        this.intelligence = 15;
        wizardsList.add(this);
        wizardsMap.put(pseudo, this);
    }

//    /**
//     * CONSTRUCTOR
//     * @param joueur Character
//     */
//    public Wizard(Character joueur){
//        super(joueur.pseudo,joueur.origin);
//        this.intelligence = 15;
//        wizardsList.add(this);
//    }

    /**
     * GETTER intelligence
     * @return intelligence int
     */
    public int getintelligence() {
        return this.intelligence;
    }

    /**
     * SETTER intelligence
     */
    public void setintelligence() {
        this.intelligence += 5;
    }

    /**
     *
     * @return String
     */
    public static String getwizardsList(){
        return wizardsList.toString();
    }

    @Override
    public void setLevel() {
        this.level++;
        this.setHealthPoint();
        this.setintelligence();
    }

    @Override
    public String toString(){
        return "\n" + this.getPseudo() + " is a " + this.getOrigin() + " wizard level " + this.getLevel() +
                "\nintelligence " + this.getintelligence() + " // HP " + this.getHealthPoint() + "\n";
    }

}
