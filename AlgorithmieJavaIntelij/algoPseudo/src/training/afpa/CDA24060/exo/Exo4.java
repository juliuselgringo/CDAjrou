package training.afpa.CDA24060.exo;

import java.util.Scanner;

public class Exo4 {

    /**
     *
     * @param x (int)
     * @param y (int)
     */
    public static int calculPuissance(int x, int y) {
        int compteur = 1;
        int resultat = 1;

        while (compteur <= y) {
            resultat = resultat * x;
            compteur++;
        }
        return (resultat);
    }


    public static void puissance(){;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisissez x (pour x^y)");
        int userX = scanner.nextInt();
        System.out.println("Saisissez y (pour x^y)");
        int userY = scanner.nextInt();

        if(userY == 0){
            System.out.println("Le résultat est 1.");
        }else{
            int resultat = calculPuissance(userX, userY);
            System.out.println("Le résultat est " + resultat);
        }
    }
}