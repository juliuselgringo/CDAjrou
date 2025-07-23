class MyArray {
    public int [] myNumbers = {155, 20, 32, 1, 5, 19, 50, 25};

    public void insertionSort() {
        for(int i = 0; i < (myNumbers.length-1); i++) {
             for(int j = i+1; j < myNumbers.length; j++) {
                 if(myNumbers[j] > myNumbers[i]) {
                     int minValue = myNumbers[j];
                     myNumbers[j] = myNumbers[i];
                     myNumbers[i] = minValue;
                 }
             }
        }
        for (int i = 0; i < myNumbers.length; i++) {
            System.out.println(myNumbers[i]);
        }
    }

}