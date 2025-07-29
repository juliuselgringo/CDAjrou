package training.afpa.CDA24060.exo;

import java.util.Scanner;

public class Exo2{

    public void factoTantQue(){
        System.out.println("Saisissez un entier pour obtenir sa factorielle (tant que) : ");
        Scanner scaner = new Scanner(System.in);
        int SaisieFacto = scaner.nextInt();
        int compteur = SaisieFacto;
        int resultat = 1;
        
        if(compteur == 1 || compteur == 0){
            System.out.println("La factorielle de " + SaisieFacto + " est " + resultat);
        }else{
            while (compteur > 1){
                resultat = resultat * compteur;
                compteur--;
            }
            System.out.println("La factorielle de " + SaisieFacto + " est " + resultat);
        }
    }

    public void factoRepeter(){
        System.out.println("Saisissez un entier pour obtenir sa factorielle (répéter) : ");
        Scanner scaner = new Scanner(System.in);
        int SaisieFacto = scaner.nextInt();
        int compteur = SaisieFacto;
        int resultat = 1;

        if(compteur == 1 || compteur == 0){
            System.out.println("La factorielle de " + SaisieFacto + " est " + resultat);
        }else{
            do{
                resultat = resultat * compteur;
                compteur--;
            }while (compteur >= 1);
            System.out.println("La factorielle de " + SaisieFacto + " est " + resultat);
        }
    }

    public void factoPour(){
        System.out.println("Saisissez un entier pour obtenir sa factorielle (pour) : ");
        Scanner scaner = new Scanner(System.in);
        int SaisieFacto = scaner.nextInt();
        int compteur = SaisieFacto;
        int resultat = 1;

        if(compteur == 1 || compteur == 0){
            System.out.println("La factorielle de " + SaisieFacto + " est " + resultat);
        }else{
            for(int i = compteur; i >= 1; i--){
                resultat = resultat * i;
            }
            System.out.println("La factorielle de " + SaisieFacto + " est " + resultat);
        }
    }

}