package training.afpa.CDA24060.exo;


import training.afpa.CDA24060.modules.PrintScan;

public class Calculator {

    public static PrintScan Ps = new PrintScan();

    public static double calcul(double operande1, String operator, double operande2){
        double result = 0;

        switch(operator){
            case "+":
                result= operande1 + operande2;
                break;
            case "-":
                result = operande1 - operande2;
                break;
            case "*":
                result = operande1 * operande2;
                break;
            case  "/":
                if(operande2 == 0){
                    Ps.printEZ("Erreur : division par zéro impossible");
                }
                result = operande1 / operande2;
                break;
            default:
                Ps.printEZ("Erreur : problème de saisie!");
                break;
        }
        return result;

    }

    public static void saisieOperandes(){
        boolean start = true;
        String operator;
        double operande1;
        double operande2;
        double result = 0;

        while(start){
            Ps.printEZ("Saisissez le premier nombre de votre calcul: ");
            operande1 = Ps.scanDouble();

            Ps.printEZ("Saisissez un opérateur (+ - * /) : ");
            operator = Ps.scanString();

            Ps.printEZ("Saisissez le deuxième nombre de votre calcul: ");
            operande2 = Ps.scanDouble();

            result = calcul(operande1, operator, operande2);
            Ps.printEZ("Le résultat de " + operande1 + " " + operator + " " + operande2 + " est " + result);

            Ps.printEZ("Voulez vous continuer (O/N) : ");
            String reponse = Ps.scanString();
            start = reponse.equals("O") ? true : false;

        }
    }

}