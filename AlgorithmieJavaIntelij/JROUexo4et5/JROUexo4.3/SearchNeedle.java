import java.util.Arrays;

class SearchNeedle {
    public int [] newArray;

    public void searchIn(int needle,int [] haystack) {
        int size = haystack.length;
        if(size > 1) {
            int middle = size / 2;
            if (haystack[middle] == needle) {
                System.out.println("Le nombre saisi est dans le tableau: " + needle);
                return;
            }
            if (haystack[middle] < needle) {
                newArray = Arrays.copyOfRange(haystack, middle, size);
                searchIn(needle, newArray);
            }
            if (haystack[middle] > needle) {
                newArray = Arrays.copyOfRange(haystack, 0, middle);
                searchIn(needle, newArray);
            }
        }
        else{
            System.out.println("Le nombre saisi n'est pas dans le tableau.");
        }


    }
}