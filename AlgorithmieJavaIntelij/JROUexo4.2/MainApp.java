class MainApp {
    public static void main(String[] args) {
        MyArray insertionSortedArray = new MyArray();
        insertionSortedArray.insertionSort();
        MyArray bubbleSortedArray = new MyArray();
        bubbleSortedArray.bubbleSort();
        MyArray selectionSortedArray = new MyArray();
        selectionSortedArray.selectionSort();
        MyArray quickSortedArray = new MyArray();
        quickSortedArray.quickSort();
    }
}