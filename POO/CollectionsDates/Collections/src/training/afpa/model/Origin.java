package training.afpa.model;

import java.util.ArrayList;

public class Origin {

    private String name;

    public static ArrayList<Origin> originsList = new ArrayList<Origin>();

    /**
     * CONSTRUCTOR
     * @param name String
     */
    public Origin(String name) {
        setName(name);
        originsList.add(this);
    }

    /**
     * GETTER name
     * @return name String
     */
    public String getName() {
        return this.name;
    }

    /**
     * SETTER name
     * @param name String
     */
    public void setName(String name) {
        if(!name.equals("")){
            this.name = name;
        }
    }

    /**
     * GETTER originsList
     * @return String
     */
    public static String getOriginsList(){
        return originsList.toString();
    }

    /**
     * TO STRING
     * @return String
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
