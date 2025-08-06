package training.afpa.conceptDeBase.exo1;

public class AppMain {

    public static void main(String[] args) {

        Compte monCompte = new Compte(152.3);
        monCompte.afficher();
        monCompte.deposer(50.0);
        monCompte.retirer(10.0);
        TestCompte testCompte = new TestCompte();

        testCompte.testerCompte(monCompte);

    }
}
