package training.afpa.model;

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
    public Character (String pseudo, Origin origin) {
        setPseudo(pseudo);
        this.origin = origin;
        this.healthPoint = 10;
        this.level = 1;
    }

    /**
     * SETTER pseudo
     * @param pseudo String
     */
    public void setPseudo(String pseudo) {
        if (!pseudo.equals("")) {
            this.pseudo = pseudo;
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
    protected void setHealthPoint() {
        this.healthPoint += 5;
    }

    /**
     * GETTER HP
     * @return healthPoint int
     */
    protected int getHealthPoint() {
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
