package training.afpa.conceptDeBase.exo1;

public class Compte {

    public Double solde = 0.0;

    /**
     *
     * @param solde (Double)
     */
    public Compte(Double solde) {
        this.setSolde(solde);
    }

    /**
     *
     * @param movement (Double)
     */
    public void setSolde(Double movement) {
        this.solde += security(movement);
    }

    /**
     *
     * @param versement (Double)
     */
    public void deposer(Double versement){
        setSolde(versement);
        System.out.println("Vous avez effectué un versement de " + versement + " votre nouveau solde est de: " + solde);
    }

    /**
     *
     * @param retrait (Double)
     */
    public void retirer(Double retrait){
        setSolde(-retrait);
        System.out.println("Vous avez effectué un retrait de " + retrait + " votre nouveau solde est de " + this.solde);
    }

    /**
     *
     */
    public void afficher(){
        System.out.println("Votre solde est de: " + solde);
    }

    /**
     *
     * @param movement (Double)
     * @return Double
     */
    private Double security(Double movement){
        if(movement == 0) {
            System.out.println("Erreur lors de la saisie de la valeur de solde");
            return 0.0;
        }
        else{
            return movement;
        }
    }


}
