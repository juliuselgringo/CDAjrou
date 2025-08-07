package training.afpa.heritage.exo2;

public class Main {

    public static void main(String[] args) {

        Town v1 = new Town("Lyon",1500000);
        Town v2 = new Town("Bobigny");

        Capital c1 = new Capital("Paris", "France", 10000000);
        Capital c2 = new Capital("Ouagadougou", "Burkina-Faso");

        v1.displayYourself();
        v2.displayYourself();
        c1.displayYourself();
        c2.displayYourself();
    }
}
