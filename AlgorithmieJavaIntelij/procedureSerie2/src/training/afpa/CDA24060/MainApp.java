package training.afpa.CDA24060;

import training.afpa.CDA24060.exo.*;
import training.afpa.CDA24060.modules.PrintScan;

class MainApp {

    public static void main(String[] args) {
        int menu;

        PrintScan ps = new PrintScan();
        ps.printEZ("Sélectionner l'exercice à lancer : ");
        ps.printEZ("1/) tableau aléatoire et recherche valeur max.");
        ps.printEZ("2/) tableau aléatoire et calcul de moyenne.");
        ps.printEZ("3/) créateur de tableau et analyzer.");
        ps.printEZ("4/) table de multiplication.");
        ps.printEZ("5/) calculatrice.");
        ps.printEZ("6/) tableau aléatoire et tri bubbleSort");
        menu = ps.scanINT();

        switch(menu) {
            case 1:
                ArrayRandomMaxSearch exo1 = new ArrayRandomMaxSearch();
                exo1.searchMax();
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
                MultiplicationTables exo4 = new MultiplicationTables();
                exo4.tableMUltiplication();
                break;

            case 5:
                Calculator exo5 = new Calculator();
                exo5.saisieOperandes();
                break;

            case 6:
                ArrayRandomSorter exo6 = new ArrayRandomSorter();
                exo6.arrayDisplay();
                break;

            default:
                break;
        }

    }

}