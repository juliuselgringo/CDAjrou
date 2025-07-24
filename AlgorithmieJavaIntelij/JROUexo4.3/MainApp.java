import java.util.Scanner;

class MainApp {
    public static int [] haystack = {0,12,20,55,72,84,99};

    public static void main(String[] args) {
        Scanner numberToSearch = new Scanner(System.in);
        System.out.println("Saisissez le nombre à rechercher: ");
        int needle = numberToSearch.nextInt();
        SearchNeedle newSearch = new SearchNeedle();
        newSearch.searchIn(needle, haystack);
    }

}