package training.afpa.conceptDeBase.exo1;

public class TestCompte {

    /**
     *
     * @param compte
     */
    public void testerCompte(Compte compte){
        Double soldeBefore = compte.solde;
        compte.deposer(50.0);
        compte.retirer(50.0);
        if(soldeBefore.equals(compte.solde)){
            System.out.println("Le compte fonctionne correctement.");
        }
        else{
            System.out.println("ATTENTION! Le suivi du compte présente un disfonctionnement.\n" +
                    soldeBefore + " / " + compte.solde);
        }
    }
}
