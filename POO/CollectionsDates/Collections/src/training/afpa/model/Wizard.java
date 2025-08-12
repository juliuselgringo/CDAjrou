package training.afpa.model;

import java.util.ArrayList;

public class Wizard extends Character {

    private int intelligence;
    private static ArrayList<Wizard> wizardsList= new ArrayList();

    /**
     * CONSTRUCTOR
     * @param pseudo String
     * @param origin Origin
     */
    public Wizard(String pseudo, Origin origin) {
        super(pseudo, origin);
        this.intelligence = 15;
        wizardsList.add(this);
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
