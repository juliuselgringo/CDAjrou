package training.afpa.cda24060;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class MainApp {
    public static ArrayList<Integer> A = new ArrayList<>(Arrays.asList(100, 50, 25));
    public static ArrayList<Integer> B = new ArrayList<>(3);
    public static ArrayList<Integer> C = new ArrayList<>(3);

    public static void main(String[] args) {
        gameHanoiTowers();
    }

    public static void gameHanoiTowers() {
        boolean startGame = true;

        while (startGame = true) {
            Rules Test1 = new Rules();
            System.out.println("Tour A");
            Test1.pileTest(A);
            System.out.println("Tour B");
            Test1.pileTest(B);
            System.out.println("Tour C");
            Test1.pileTest(C);
            Test1.victoire(C);


            Scanner userInput = new Scanner(System.in);
            System.out.println("Saisir la tour à débiter (ou Q pour quitter): ");
            String arrayOrigin = userInput.nextLine();
            System.out.println("Saisir la tour à créditer (ou enter pour quitter): ");
            String arrayDestination = userInput.nextLine();

            ArrayModifier deplacement = new ArrayModifier();

            if (arrayOrigin.equals("A")) {
                if (arrayDestination.equals("B")) {
                    deplacement.pickAndPut(A, B);
                } else if (arrayDestination.equals("C")) {
                    deplacement.pickAndPut(A, C);
                }
            } else if (arrayOrigin.equals("B")) {
                if (arrayDestination.equals("A")) {
                    deplacement.pickAndPut(B, A);
                } else if (arrayDestination.equals("C")) {
                    deplacement.pickAndPut(B, C);
                }
            } else if (arrayOrigin.equals("C")) {
                if (arrayDestination.equals("A")) {
                    deplacement.pickAndPut(C, A);
                } else if (arrayDestination.equals("B")) {
                    deplacement.pickAndPut(C, B);
                }
            } else if (arrayOrigin.equals("Q")) {
                System.out.println("Merci d'avoir joué!!!");
                startGame = false;
            }


        }
    }
}