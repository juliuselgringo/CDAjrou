package training.afpa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Origin {

    private String name;

    public static ArrayList<Origin> originsList = new ArrayList<Origin>();
    private static Map<String, Origin> originsMap = new HashMap<String, Origin>();

    /**
     * CONSTRUCTOR
     * @param name String
     */
    public Origin(String name) {
        setName(name);
        originsList.add(this);
        originsMap.put(name, this);
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
        if(!name.isEmpty()){
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
     * @param name  String
     * @param elfic
     */
    public static Map<String, Origin> getOriginsMap(String name, Origin elfic){
        return originsMap;
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
