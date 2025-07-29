package training.afpa.CDA24060.exo;

import java.util.Scanner;

public class Exo1{
    public void tantQue(){
        System.out.println("Saisissez votre nombre entier (tant que) : ");
        Scanner scanner = new Scanner(System.in);
        int valeurSaisie = scanner.nextInt();
        int resultat = 0;
        int compteur = 1;

        while(compteur <= valeurSaisie){
            resultat += compteur;
            compteur++;
        };

        System.out.println("Le resultat est : " + resultat);
    }

    public void repeter(){
        System.out.println("Saisissez votre nombre entier (répéter) : ");
        Scanner scanner = new Scanner(System.in);
        int valeurSaisie = scanner.nextInt();
        int resultat = 0;
        int compteur = 1;

        do{
            resultat += compteur;
            compteur++;
        }while(compteur <= valeurSaisie);

        System.out.println("Le resultat est : " + resultat);
    }

    public void pour(){
        System.out.println("Saisissez votre nombre entier (pour) : ");
        Scanner scanner = new Scanner(System.in);
        int valeurSaisie = scanner.nextInt();
        int resultat = 0;


        for(int i = 0; i <= valeurSaisie; i++){
            resultat += i;
        }

        System.out.println("Le resultat est : " + resultat);
    }

}