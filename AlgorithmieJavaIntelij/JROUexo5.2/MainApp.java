class  MainApp {

    public static int[] myArray = {22, 13, 22, 44, 99, 60, 44, 22, 13, 60, 44, 13};
    public static int[] counterArray = new int[100];

    public static void main(String[] args) {

        for (int i = 0; i < counterArray.length; i++) {
            int counter = 0;
            for (int j = 0; j < myArray.length; j++) {

                if (i == myArray[j]) {
                    counter++;
                }
            }
            counterArray[i] = counter;

        }

        for(int i = 0; i < counterArray.length; i++) {
            if (counterArray[i] != 0) {
                System.out.println(i + " : " + counterArray[i]);
            }

        }
    }
}