package training.afpa.model;

import training.afpa.utility.UserInputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warrior extends Character implements Comparable<Warrior> {

    private int strength;
    public static ArrayList<Warrior> warriorsList = new ArrayList();
    public static Map<String, Warrior> warriorsMap = new HashMap();

    /**
     * CONSTRUCTOR
     * @param pseudo String
     * @param origin Origin
     */
    public Warrior(String pseudo, Origin origin) throws UserInputException {
        super(pseudo,origin);
        this.strength = 15;
        warriorsList.add(this);
        warriorsMap.put(pseudo, this);

    }

//    /**
//     * CONSTRUCTOR
//     * @param joueur Character
//     */
//    public Warrior(Character joueur){
//        super(joueur.pseudo,joueur.origin);
//        this.strength = 15;
//        warriorsList.add(this);
//    }

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
     * GETTER warriorsList
     * @return String
     */
    public static String getWarriorsList(){
        return warriorsList.toString();
    }

    @Override
    public int compareTo(Warrior o) {
        int value = this.getPseudo().compareTo(o.getPseudo());
        if (value != 0) {
            return value;
        }
        value = Integer.compare(this.getLevel(), o.getLevel());
        if (value != 0) {
            return value;
        }
        return Integer.compare(this.getstrength(), o.getstrength());
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
