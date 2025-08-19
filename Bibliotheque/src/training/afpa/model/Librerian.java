package training.afpa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Librerian extends Person {

    private String id;

    public Librerian(String firstName, String lastName, String id) {
        super(firstName, lastName);
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("id cannot be null or empty");
        }
        else {
            this.id = id;
        }
    }


}
