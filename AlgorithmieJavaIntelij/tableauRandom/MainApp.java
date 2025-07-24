import java.util.Random;

class MainApp {
    public static int[] randomArray = new int[11];
    public static void main(String[] args) {
        Random r = new Random();

        for(int i = 0; i <=10; i++ ) {
            int x = r.nextInt(100);
            randomArray[i] = x;
        }

        for(int i = 0; i < randomArray.length; i++ ) {
            System.out.println(randomArray[i]);
        }
    }
}