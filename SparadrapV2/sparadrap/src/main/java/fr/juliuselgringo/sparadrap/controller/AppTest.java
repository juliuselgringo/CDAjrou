package fr.juliuselgringo.sparadrap.controller;

import fr.juliuselgringo.sparadrap.DAO.CustomerDAO;
import fr.juliuselgringo.sparadrap.DB.ConnectionDB;
import fr.juliuselgringo.sparadrap.DB.MutualCRUD;
import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.*;

import java.sql.Connection;

public class AppTest {

    public static void main(String[] args) throws Exception {

        // AppTest.developpmentDataInput();
        // ProgramSwing.generalMenu();
        AppTest.testConnectionDB();
    }

    /**
     * méthode qui permet de tester les fonctionnalités de la DB
     * @throws InputException
     */
    private static void testConnectionDB() throws InputException{

        Connection con = ConnectionDB.propertiesConnection();
        CustomerDAO customerDAO = new CustomerDAO();
        System.out.println(customerDAO.getAll());


        ConnectionDB.closeConnectionDB(con);

    }

    /**
     * méthode qui permet d'implémenter des valeur pour le développement de l'application
     * @throws InputException String
     */
    private static void developpmentDataInput() throws InputException {
        try {
            //________________________MUTUELLES______________________________________
            Contact harmo75 = new Contact("10 Rue de la Sante", "75000",
                    "Paris", "01 23 45 67 89", "contact@harmonie.fr");
            Mutual harmonie75 = new Mutual("Harmonie Mutuelle", 1, 0.75);

            Contact mgen69 = new Contact("25 Av de la Mutualite", "69000",
                    "Lyon", "04 56 78 90 12", "contact@mgen.fr");
            Mutual mgenLyon = new Mutual("Mgen", 2, 0.80);
            /*
            Mutual.mutualsList.add(mgenLyon);
            Mutual.mutualsList.add(harmonie75);
*/
            //__________________________DOCTEURS____________________________________
            Contact jeDup75 = new Contact("12 Rue de Paris", "75000", "Paris",
                    "01 23 45 67 89", "jean.dupont@medecin.fr");
            Doctor jeDupParis = new Doctor("Jean", "Dupont", 3, "12345678901");

            Contact maMar69 = new Contact("45 Av des Champs", "69000", "Lyon",
                    "04 56 78 90 12", "marie.martin@medecin.fr");
            Doctor maMarLyon = new Doctor("Marie", "Martin", 4, "23456789012");

            Contact jeDuc71 = new Contact("12 Rue de Pffft", "71000", "Pfffft",
                    "06 23 45 67 89", "jeannot.ducont@medecin.fr");
            Doctor JeDuc = new Doctor("Jeannot", "Ducont", 5, "12345678901");

            Contact maMar68 = new Contact("45 Av des Choux", "68000", "Colmar",
                    "02 56 78 90 12", "mario.martais@medecin.fr");
            new Doctor("Mario", "Martais", 6, "13456789012");
            /*
            Doctor.doctorsList.add(jeDupParis);
            Doctor.doctorsList.add(maMarLyon);
            Doctor.doctorsList.add(JeDuc);
*/
            //________________________CLIENTS__________________________________________
            Contact alLef75 = new Contact("12 Rue de Paris", "75000", "Paris",
                    "01 23 45 67 89", "alice.lefevre@mail.fr");
            Customer alLefParis = new Customer("Alice", "Lefevre", 7, "275126432109848",
                    "15-07-1985", 1, 1);

            Contact maPet69 = new Contact("34 Av des Camps", "69000", "Lyon",
                    "04 56 78 90 12", "marc.petit@mail.fr");
            Customer maPetLyon = new Customer("Marc", "Petit", 8, "185076432109818",
                    "20-12-1975", 2, 2);

            Contact jaBour54 = new Contact("12 Rue de Paris", "54000", "Nancy",
                    "03 23 45 67 89", "jacques.bourdin@mail.fr");
            Customer jaBourNancy = new Customer("Jacques", "Bourdin", 9, "175126432109848",
                    "15-01-1982", 1, 1);

            Contact maPet60 = new Contact("34 Av des Champs", "60000", "Chaipas",
                    "04 56 78 90 12", "marc.petit@mail.fr");
            Customer maPet = new Customer("Marianne", "Petoncourt", 10, "285076432109818",
                    "20-12-1975", 2, 2);
            /*
            Customer.customersList.add(jaBourNancy);
            Customer.customersList.add(alLefParis);
            Customer.customersList.add(maPetLyon);
            Customer.customersList.add(maPet);
*/

            //_______________________MEDICAMENTS___________________________
            Drug dafalgan = new Drug("Dafalgan", 1, 9.99, "03-12-2024", 50, false);
            Drug amoxicilline = new Drug("Amoxicilline", 2, 12.50, "15-01-2025", 30, true);
            Drug ventoline = new Drug("Ventoline", 13, 15.75, "20-01-2025", 20, true);
            Drug levothyrox = new Drug("Levothyrox", 9, 8.90, "05-02-2025", 90, false);
            Drug dolirhume = new Drug("Dolirhume", 1, 6.49, "30-06-2025", 40, false);
            Drug seroplex = new Drug("Seroplex", 15, 22.30, "10-08-2025", 28, true);
            Drug smecta = new Drug("Smecta", 10, 4.20, "01-02-2025", 60, false);
            Drug lisinopril = new Drug("Lisinopril", 6, 7.80, "18-07-2025", 30, true);
            Drug doliprane = new Drug("Doliprane", 1, 5.99, "10-02-2025", 50, false);
            Drug zyrtec = new Drug("Zyrtec", 13, 10.25, "25-06-2025", 15, false);
            /*
            Drug.drugsList.add(dafalgan);
            Drug.drugsList.add(amoxicilline);
            Drug.drugsList.add(ventoline);
            Drug.drugsList.add(levothyrox);
            Drug.drugsList.add(dolirhume);
            Drug.drugsList.add(seroplex);
            Drug.drugsList.add(smecta);
            Drug.drugsList.add(lisinopril);
            Drug.drugsList.add(doliprane);
            Drug.drugsList.add(zyrtec);
*/

            //_______________________________ACHATS_________________________________________
            Purchase purchase1 = new Purchase(false);
            purchase1.setPurchaseDrugsQuantity(dafalgan, 10);
            purchase1.setPurchaseDrugsQuantity(amoxicilline, 1);
            purchase1.setPurchaseDrugsQuantity(smecta, 1);
            purchase1.setPurchaseDetails();


            Purchase purchase2 = new Purchase(true);
            Prescription prescription2 = new Prescription("29-08-2025", 2, 2);
            purchase2.setPrescritionId(prescription2.getPrescriptionId());
            purchase2.setPurchaseDrugsQuantity(dafalgan, 1);
            purchase2.setPurchaseDrugsQuantity(amoxicilline, 1);
            purchase2.setPurchaseDrugsQuantity(smecta, 1);
            purchase2.setPurchaseDetails();

            Purchase purchase3 = new Purchase(true);
            Prescription prescription3 = new Prescription("12-08-2025", 1, 1);
            purchase3.setPrescritionId(prescription3.getPrescriptionId());
            purchase3.setPurchaseDrugsQuantity(dafalgan, 1);
            purchase3.setPurchaseDrugsQuantity(amoxicilline, 1);
            purchase3.setPurchaseDrugsQuantity(smecta, 1);
            purchase3.setPurchaseDetails();

            Purchase purchase4 = new Purchase("07-07-2025", true);
            Prescription prescription4 = new Prescription("22-06-2025", 1, 1);
            purchase4.setPrescritionId(prescription4.getPrescriptionId());
            purchase4.setPurchaseDrugsQuantity(dafalgan, 1);
            purchase4.setPurchaseDrugsQuantity(amoxicilline, 1);
            purchase4.setPurchaseDrugsQuantity(smecta, 1);
            purchase4.setPurchaseDrugsQuantity(zyrtec, 1);
            purchase4.setPurchaseDetails();
            /*
            Purchase.purchasesHistory.add(purchase1);
            Purchase.purchasesHistory.add(purchase2);
            Purchase.purchasesHistory.add(purchase3);
            Purchase.purchasesHistory.add(purchase4);
*/

        }catch(InputException ie) {
            System.err.println("Erreur au chargement des données.");
            ie.printStackTrace();
            System.exit(1);
        }
    }
}
