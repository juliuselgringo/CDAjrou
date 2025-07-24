class MyArray {
    public int [] myNumbers = {155, 20, 32, 1, 5, 19, 50, 25};

    public void insertionSort() {
        for(int i = 0; i < (myNumbers.length-1); i++) {
             for(int j = i+1; j < myNumbers.length; j++) {
                 if(myNumbers[j] < myNumbers[i]) {
                     int minValue = myNumbers[j];
                     myNumbers[j] = myNumbers[i];
                     myNumbers[i] = minValue;
                 }
             }
        }
        System.out.println("Tableau trié par insertion sort : ");
        for (int i = 0; i < myNumbers.length; i++) {
            System.out.print(myNumbers[i] + " ");
        }
    }

    public void bubbleSort() {

        boolean permutation;
        do {
            permutation = false;
            for (int i = 0; i < this.myNumbers.length - 1; i++) {
                if (myNumbers[i] > myNumbers[i + 1]) {
                    int temp = myNumbers[i];
                    myNumbers[i] = myNumbers[i + 1];
                    myNumbers[i + 1] = temp;
                    permutation = true;
                }
            }
        }while(permutation);

        System.out.println("\nTableau trié par bubble sort : ");
        for (int i = 0; i < myNumbers.length; i++) {
            System.out.print(myNumbers[i] + " ");
        }
    }

    public void selectionSort() {

        int i = 0;
        while (i < myNumbers.length) {
            int j = i + 1;
            int min = i;
            while(j < myNumbers.length) {
                if(myNumbers[j] < myNumbers[min]){
                    min = j;
                }
                else{
                    j = j + 1;
                }
            }
            if(min != i){
                int temp = myNumbers[i];
                myNumbers[i]=myNumbers[min];
                myNumbers[min]=temp;
            }
            i++;
        }

        System.out.println("\nTableau trié par selection sort : ");
        for (int f = 0; f < myNumbers.length; f++) {
            System.out.print(myNumbers[f] + " ");

        }
        System.out.print("\n");
    }



    public void quickSort() {
        int high = myNumbers.length-1;
        quick(myNumbers, 0, high);
        System.out.println("\nTableau trié par quick sort : ");
        for (int f = 0; f < myNumbers.length; f++) {
            System.out.print(myNumbers[f] + " ");

        }
        System.out.print("\n");
    }

    public void quick(int [] array, int low, int high) {
        if(low < high) {
            int pivot = partition(array, low, high);

            quick(array, low, pivot - 1);
            quick(array, pivot + 1, high);
        }
    }

    public int partition(int [] array, int low, int high) {
        int pivot = array[high];
        int i = low-1;
        for(int j = low; j < high; j++) {
            if(array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}