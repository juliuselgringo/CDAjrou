package training.afpa.model;

public class Librerian extends Person {

    private String id;

    /**
     * CONSTRUCTOR
     * @param firstName String
     * @param lastName String
     * @param id String
     */
    public Librerian(String firstName, String lastName, String id) {
        super(firstName, lastName);
        setId(id);
    }

    /**
     * GETTER ID
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * SETTER ID
     * @param id String
     */
    public void setId(String id) {
        id = id.trim();
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("id cannot be null or empty");
        }
        else {
            this.id = id;
        }
    }

    /**
     * TO STRING
     * @return Sring
     */
    @Override
    public String toString() {
        return "Librerian{First name: " + this.getFirstName() +
                ", Last name: " + this.getLastName() +
                ", id: " + this.getId() + '}';
    }

}
