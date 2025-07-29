package training.afpa.CDA24060.exo;

import java.util.Scanner;

public class Exo3{

    public static void resolutionQuadratique(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisissez a (pour ax^2+bx+c=0): ");
        int a =  scanner.nextInt();
        System.out.println("Saisissez b (pour ax^2+bx+c=0) : ");
        int b =  scanner.nextInt();
        System.out.println("Saisissez c (pour ax^2+bx+c=0) : ");
        int c =  scanner.nextInt();

        int determinant = (b * b) - (4 * a * c);
        float resultat;
        float resultat1;
        float resultat2;

        if(determinant < 0){
            System.out.println("Il n'y a pas de solution dans le domaine réel");
        }else {
            if(determinant == 0){
                resultat = -b / (2 * a);
                System.out.println("La solution à l'équation " + a + "x^2" + " + " + b + "x" + " + " + c + " = 0" +
                        " est : x = " + resultat);
            }
            if(determinant > 0){
                resultat1 = (float)(-b + (Math.sqrt(determinant)/2*a));
                resultat2 = (float)(-b - (Math.sqrt(determinant)/2*a));
                System.out.println("Il y a 2 solutions à l'équation " + a + "x^2" + " + " + b + "x" + " + " + c +
                        " = 0" + " : x1 = " + resultat1 + " et x2 = " + resultat2);
            }
        }
    }
}