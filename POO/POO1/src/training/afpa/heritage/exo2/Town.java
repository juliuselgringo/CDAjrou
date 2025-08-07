package training.afpa.heritage.exo2;

public class Town {

    private String name;
    public int nbHab;

    /**
     * CONSTRUCTOR name
     * @param aName String
     */
    public Town(String aName){
        this.name = aName;
        this.nbHab = -1;

    }

    /**
     * CONSTRUCTOR name, nbHab
     * @param aName String
     * @param nbHab int
     */
    public Town(String aName, int nbHab){
        this.name = aName;
        if(nbHab <= 0){
            System.out.println("Un nombre d'habitant doit être positif.");
            this.nbHab = -1;
        }
        else{
            this.nbHab = nbHab;
        }
    }

    /**
     * GETTER name
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * GETTER nbHab
     * @return
     */
    public int getNbHab(){
        return this.nbHab;
    }

    /**
     * SETTER nbHab
     * @param pNbHab int
     */
    public void setNbHab(int pNbHab){
        if(pNbHab <= 0){
            System.out.println("Un nombre d'habitant doit être positif, la modification n'a pas été prise en compte.");
        }
        else{
            this.nbHab = pNbHab;
        }
    }

    /**
     * DISPLAY Town
     */
    public void displayYourself(){
        if(this.nbHab == -1){
            System.out.println("La ville de " + this.name + " a un nombre inconnu d'habitant.");
        }
        else{
            System.out.println("La ville de " + this.name + " a une population de " + this.nbHab + " habitants.");
        }

    }
}
