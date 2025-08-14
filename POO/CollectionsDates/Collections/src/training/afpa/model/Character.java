package training.afpa.model;

import training.afpa.utility.UserInputException;

public class Character {

    protected String pseudo;
    protected Origin origin;
    protected int healthPoint;
    protected int level;

    /**
     *  CONSTRUCTOR
     * @param pseudo String
     * @param origin Origin
     */
    public Character (String pseudo, Origin origin) throws UserInputException {
        setPseudo(pseudo.trim());
        this.origin = origin;
        this.healthPoint = 10;
        this.level = 1;
    }

    /**
     * SETTER pseudo
     * @param pseudo String
     */
    public void setPseudo(String pseudo) throws UserInputException {
        if (!pseudo.trim().isEmpty() || pseudo == null) {
            this.pseudo = pseudo.trim();
        }
        else{
            System.err.println("Le pseudo ne peut être vide");
            throw new UserInputException("Le pseudo ne peut être vide");
        }
    }

    /**
     * GETTER pseudo
     * @return String
     */
    public String getPseudo() {
        return this.pseudo;
    }

    /**
     * SETTER origin
     * @param origin Origin
     */
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    /**
     *  GETTER origin
     * @return origin
     */
    public Origin getOrigin() {
        return this.origin;
    }

    protected int getLevel() {
        return this.level;
    }

    /**
     *  SETTER HP
     */
    public void setHealthPoint() {
        this.healthPoint += 5;
    }

    /**
     * GETTER HP
     * @return healthPoint int
     */
    public int getHealthPoint() {
        return this.healthPoint;
    }
    /**
     *  SETTER level
     */
    protected void setLevel() {
        this.level++;
    }

    @Override
    public String toString() {
        return this.getPseudo() + " is a "  + this.getOrigin();
    }
}
