package training.afpa.heritage.exo2;

public class Capital extends Town {

    private String country;

    /**
     * CONSTRUCTOR name, country
     * @param aName String
     * @param aCountry
     */
    public Capital(String aName, String aCountry){
        super(aName);
        this.country = aCountry;
    }

    /**
     * CONSTRUCTOR name, country, nbHab
     * @param aName String
     * @param aCountry String
     * @param aNbHab int
     */
    public Capital(String aName, String aCountry, int aNbHab){
        super(aName, aNbHab);
        this.country = aCountry;
    }

    /**
     * GETTER country
     * @return String
     */
    public String getCountry(){
        return country;
    }

    /**
     * SETTER country
     * @param aCountry
     */
    public void setCountry(String aCountry){
        this.country = aCountry;
    }

    @Override
    public void displayYourself(){
        String nbHabToDisplay;
        if(this.getNbHab() == -1){
            nbHabToDisplay = "inconnu";
        }
        else{
            nbHabToDisplay = Integer.toString(this.getNbHab());
        }
        System.out.println("\nVille : " + this.getName() + "\n" +
                "Nombre d'habitants : " + nbHabToDisplay + "\n" +
                "Capitale de : " + this.country);
    }


}
