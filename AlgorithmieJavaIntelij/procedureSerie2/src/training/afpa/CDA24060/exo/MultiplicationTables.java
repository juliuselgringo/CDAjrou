package training.afpa.CDA24060.exo;

import training.afpa.CDA24060.modules.PrintScan;

import java.util.Arrays;

public class MultiplicationTables {



    public static void tableMUltiplication(){

        int lignes = 11;
        int colonnes = 11;
        int[][] tableauMultiplication = new int[lignes][colonnes];
        boolean start = true;

        for (int i = 1; i < lignes; i++) {
            for (int j = 1; j < colonnes; j++) {
                tableauMultiplication[i][j] = i*j;
            }
        }
        for (int[] table : tableauMultiplication) {
            System.out.println(Arrays.toString(table));
        }

        while (start) {
            int X = 0;
            int Y = 0;
            PrintScan Ps = new PrintScan();

            do{
                Ps.printEZ("Entrez un entier X entre 1 et 9 : ");
                X = Ps.scanINT();
                if(X > 0 && X <= 10){
                    X = X;
                    break;
                }else{
                    Ps.printEZ("Votre saisie pour X est incorrecte, veuillez entrer un chiffre entre 1 et 9 : ");
                }
            }while(X < 0 && X >= 10);

            do{
                Ps.printEZ("Entrez un entier Y entre 1 et 9 : ");
                Y = Ps.scanINT();
                if(Y > 0 && Y <= 10){
                    Y = Y;
                    break;
                }else{
                    Ps.printEZ("Votre saisie pour Y est incorrecte, veuillez entrer un chiffre entre 1 et 9 : ");
                }
            }while(Y < 0 && Y >= 10);

            int produit = tableauMultiplication[X][Y];
            Ps.printEZ("Le produit de X x Y est : " + produit);
            Ps.printEZ("Voulez vous continuer (Oui/Non) : ");
            String reponse = Ps.scanString();
            if(reponse.equals("Oui")){
                start = true;
            }else{
                start = false;
            }
            Ps.printEZ(reponse);
            Ps.printEZ(String.valueOf(start));

        }
    }


}