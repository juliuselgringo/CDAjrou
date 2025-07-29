package training.afpa.CDA24060.exo;

import java.util.Scanner;

public class Exo6 {

    public static int calculFactorielles(int valeur){
        int resultat = 1;
        for(int i = 1; i <= valeur; i++){
            resultat = resultat * i;
        }
        return resultat;
    }

    public static void trouverFactorielle(){
        int[][] tableauFactorielles =  new int[10][10];
        int x;
        int indiceColonne = 0;

        while(indiceColonne <= 9){
            tableauFactorielles[0][indiceColonne] = indiceColonne + 1;
            indiceColonne++;
        }

        indiceColonne = 0;
        while(indiceColonne <= 9){
            tableauFactorielles[1][indiceColonne] = calculFactorielles(indiceColonne+1);
            indiceColonne++;
        }

        for(int i = 0; i < 10; i++){
            System.out.print(tableauFactorielles[0][i] + " = " + tableauFactorielles[1][i]);
            System.out.println();
        }


        System.out.println("Pour quelle valeur entière souhaitez vous calculer la factorielle ? (entre 1 et 10) : ");
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();

        if(x<1 || x>10){
            System.out.println("La valeur doit être comprise entre 1 et 10");
        }else{
            int resultat = tableauFactorielles[1][x-1];
            System.out.println("La factorielle de " + x + " est " + resultat);
        }
    }

}