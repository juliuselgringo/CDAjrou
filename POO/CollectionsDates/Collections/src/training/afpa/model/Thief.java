package training.afpa.model;

import java.util.ArrayList;

public class Thief extends Character implements Comparable<Thief> {

    private int agility;
    private static ArrayList<Thief> thiefsList= new ArrayList();

    /**
     * CONSTRUCTOR
     * @param pseudo String
     * @param origin Origin
     */
    public Thief(String pseudo, Origin origin) {
        super(pseudo, origin);
        this.agility = 15;
        thiefsList.add(this);
    }

//    /**
//     * CONSTRUCTOR
//     * @param joueur Character
//     */
//    public Thief(Character joueur){
//        super(joueur.pseudo,joueur.origin);
//        this.agility = 15;
//        thiefsList.add(this);
//    }

    /**
     * GETTER agility
     * @return agility int
     */
    public int getagility() {
        return this.agility;
    }

    /**
     * SETTER agility
     */
    public void setagility() {
        this.agility += 5;
    }

    /**
     * GETTER thiefsList
     * @return String
     */
    public static String getthiefsList(){
        return thiefsList.toString();
    }

    @Override
    public void setLevel() {
        this.level++;
        this.setHealthPoint();
        this.setagility();
    }

    @Override
    public String toString(){
        return "\n" + this.getPseudo() + " is a " + this.getOrigin() + " thief level " + this.getLevel() +
                "\nagility " + this.getagility() + " // HP " + this.getHealthPoint() + "\n";
    }

    @Override
    public int compareTo(Thief o) {
        int value = this.getPseudo().compareTo(o.getPseudo());
        if (value != 0) {
            return value;
        }
        value = Integer.compare(this.getagility(), o.getagility());
        if (value != 0) {
            return value;
        }
        return Integer.compare(this.getLevel(), o.getLevel());
    }
}
