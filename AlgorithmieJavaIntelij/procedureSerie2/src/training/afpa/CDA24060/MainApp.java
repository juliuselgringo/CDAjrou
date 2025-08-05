package training.afpa.CDA24060;

import training.afpa.CDA24060.exo.*;
import training.afpa.CDA24060.modules.PrintScan;

class MainApp {

    public static void main(String[] args) {
        int menu;
        boolean start = true;
        while (start) {
            
            PrintScan.printEZ("Sélectionner l'exercice à lancer : ");
            PrintScan.printEZ("1/) tableau aléatoire et recherche valeur max.");
            PrintScan.printEZ("2/) tableau aléatoire et calcul de moyenne.");
            PrintScan.printEZ("3/) créateur de tableau et analyzer.");
            PrintScan.printEZ("4/) table de multiplication.");
            PrintScan.printEZ("5/) calculatrice.");
            PrintScan.printEZ("6/) tableau aléatoire et tri bubbleSort");
            PrintScan.printEZ("0/) QUITTER l'application.");

            menu = PrintScan.scanINT();


            switch (menu) {
                case 1:
                    ArrayRandomMaxSearch.searchMax();
                    break;

                case 2:
                    Averager exo2 = new Averager();
                    exo2.calculMoyenne();
                    break;

                case 3:
                    ArrayCreatorAnalyzer exo3 = new ArrayCreatorAnalyzer();
                    exo3.saisieTailleMax();
                    break;

                case 4:
                    MultiplicationTables.tableMUltiplication();
                    break;

                case 5:
                    Calculator.saisieOperandes();
                    break;

                case 6:
                    ArrayRandomSorter.arrayDisplay();
                    break;

                case 0:
                    PrintScan.printEZ("Merci d'avoir utilisé cette application incroyable. Bonne journée.");
                    start = false;
                    break;

                default:
                    PrintScan.printEZ("Vous avez fais une erreur dans le choix du menu!");
                    break;
            }
        }

    }

}