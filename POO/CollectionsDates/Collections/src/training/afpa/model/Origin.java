package training.afpa.model;

public class Origin {

    private String name;

    /**
     *
     * @param name String
     */
    public Origin(String name) {
        setName(name);
    }

    /**
     *
     * @return name String
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @param name String
     */
    public void setName(String name) {
        if(!name.equals("")){
            this.name = name;
        }
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
