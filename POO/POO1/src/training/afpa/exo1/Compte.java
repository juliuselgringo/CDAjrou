package training.afpa.exo1;

public class Compte {

    public Double solde = 0.0;


    public Compte(Double solde) {
        this.setSolde(solde);
    }

    public void setSolde(Double movement) {
        this.solde += security(movement);
    }

    public void deposer(Double versement){
        setSolde(versement);
        System.out.println("Vous avez effectué un versement de " + versement + " votre nouveau solde est de: " + solde);
    }

    public void retirer(Double retrait){
        setSolde(-retrait);
        System.out.println("Vous avez effectué un retrait de " + retrait + " votre nouveau solde est de " + this.solde);
    }

    public void afficher(){
        System.out.println("Votre solde est de: " + solde);
    }

    private Double security(Double movement){
        if(movement == 0 || movement == null) {
            System.out.println("Erreur lors de la saisie de la valeur de solde");
            return 0.0;
        }
        else{
            return movement;
        }
    }


}
