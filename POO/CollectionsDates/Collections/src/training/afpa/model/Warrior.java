package training.afpa.model;

import java.util.ArrayList;
import java.util.List;

public class Warrior extends Character {

    private int strength;
    private static ArrayList<Warrior> warriorsList = new ArrayList();

    /**
     * CONSTRUCTOR
     * @param pseudo String
     * @param origin Origin
     */
    public Warrior(String pseudo, Origin origin) {
        super(pseudo,origin);
        this.strength = 15;
        warriorsList.add(this);
    }

    /**
     * CONSTRUCTOR
     * @param joueur Character
     */
    public Warrior(Character joueur){
        super(joueur.pseudo,joueur.origin);
        this.strength = 15;
        warriorsList.add(this);
    }

    /**
     * GETTER strength
     * @return strength int
     */
    public int getstrength() {
        return this.strength;
    }

    /**
     * SETTER strength
     */
    public void setstrength() {
        this.strength += 5;
    }

    /**
     *
     * @return String
     */
    public static String getWarriorsList(){
        return warriorsList.toString();
    }

    @Override
    public void setLevel() {
        this.level++;
        this.setHealthPoint();
        this.setstrength();
    }

    @Override
    public String toString(){
        return "\n" + this.getPseudo() + " is a " + this.getOrigin() + " Warrior level " + this.getLevel() +
                "\nstrength " + this.getstrength() + " // HP " + this.getHealthPoint() + "\n";
    }
}
