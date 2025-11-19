package fr.juliuselgringo.sparadrap.controller;

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

        /*
        System.out.println("AFFICHE TOUT LES CONTACTS: ");
        System.out.println(ContactCRUD.selectAllContact(con));
        */

        /*
        System.out.println("\nRECHERCHE PAR ID DANS CONTACT: ");
        System.out.println(ContactCRUD.selectContactById(con, 4));
        */

        /*
        System.out.println("\nInsertion d'un nouveau contact: ");
        Contact contactToInsert = new Contact("69 rue de la pisse chaude", "11000","Carcassonne","06 12 18 11 13","testinsert@test.com");
        contactCRUD.insertNewContact(con, contactToInsert);
        */

        /*
        System.out.println("\nModifier un contact: ");
        Contact contactToUpdate = ContactCRUD.selectContactById(con, 11);
        contactToUpdate.setAddress("69 rue de la piste chaude");
        ContactCRUD.updateContact(con,contactToUpdate);
        */

        /*
        System.out.println("\nsupprimer un contact: ");
        Contact contactToDelete = ContactCRUD.selectContactById(con, 11);
        ContactCRUD.deleteContact(con, contactToDelete );
        */


        System.out.println("\naffiche toute les mutuelles: ");
        System.out.println(MutualCRUD.selectAllMutual(con));



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
            Mutual harmonie75 = new Mutual("Harmonie Mutuelle", harmo75, 0.75);

            Contact mgen69 = new Contact("25 Av de la Mutualite", "69000",
                    "Lyon", "04 56 78 90 12", "contact@mgen.fr");
            Mutual mgenLyon = new Mutual("Mgen", mgen69, 0.80);
            /*
            Mutual.mutualsList.add(mgenLyon);
            Mutual.mutualsList.add(harmonie75);
*/
            //__________________________DOCTEURS____________________________________
            Contact jeDup75 = new Contact("12 Rue de Paris", "75000", "Paris",
                    "01 23 45 67 89", "jean.dupont@medecin.fr");
            Doctor jeDupParis = new Doctor("Jean", "Dupont", jeDup75, "12345678901");

            Contact maMar69 = new Contact("45 Av des Champs", "69000", "Lyon",
                    "04 56 78 90 12", "marie.martin@medecin.fr");
            Doctor maMarLyon = new Doctor("Marie", "Martin", maMar69, "23456789012");

            Contact jeDuc71 = new Contact("12 Rue de Pffft", "71000", "Pfffft",
                    "06 23 45 67 89", "jeannot.ducont@medecin.fr");
            Doctor JeDuc = new Doctor("Jeannot", "Ducont", jeDuc71, "12345678901");

            Contact maMar68 = new Contact("45 Av des Choux", "68000", "Colmar",
                    "02 56 78 90 12", "mario.martais@medecin.fr");
            new Doctor("Mario", "Martais", maMar68, "13456789012");
            /*
            Doctor.doctorsList.add(jeDupParis);
            Doctor.doctorsList.add(maMarLyon);
            Doctor.doctorsList.add(JeDuc);
*/
            //________________________CLIENTS__________________________________________
            Contact alLef75 = new Contact("12 Rue de Paris", "75000", "Paris",
                    "01 23 45 67 89", "alice.lefevre@mail.fr");
            Customer alLefParis = new Customer("Alice", "Lefevre", alLef75, "275126432109848",
                    "15-07-1985", harmonie75, jeDupParis);

            Contact maPet69 = new Contact("34 Av des Camps", "69000", "Lyon",
                    "04 56 78 90 12", "marc.petit@mail.fr");
            Customer maPetLyon = new Customer("Marc", "Petit", maPet69, "185076432109818",
                    "20-12-1975", mgenLyon, maMarLyon);

            Contact jaBour54 = new Contact("12 Rue de Paris", "54000", "Nancy",
                    "03 23 45 67 89", "jacques.bourdin@mail.fr");
            Customer jaBourNancy = new Customer("Jacques", "Bourdin", jaBour54, "175126432109848",
                    "15-01-1982",harmonie75, jeDupParis);

            Contact maPet60 = new Contact("34 Av des Champs", "60000", "Chaipas",
                    "04 56 78 90 12", "marc.petit@mail.fr");
            Customer maPet = new Customer("Marianne", "Petoncourt", maPet60, "285076432109818",
                    "20-12-1975", mgenLyon, maMarLyon);
            /*
            Customer.customersList.add(jaBourNancy);
            Customer.customersList.add(alLefParis);
            Customer.customersList.add(maPetLyon);
            Customer.customersList.add(maPet);
*/

            //_______________________MEDICAMENTS___________________________
            Drug dafalgan = new Drug("Dafalgan", "Analgesiques et Anti-inflammatoires", 9.99, "03-12-2024", 50, false);
            Drug amoxicilline = new Drug("Amoxicilline", "Antibiotiques et Antibacteriens", 12.50, "15-01-2025", 30, true);
            Drug ventoline = new Drug("Ventoline", "Immunologie et Allergologie", 15.75, "20-01-2025", 20, true);
            Drug levothyrox = new Drug("Levothyrox", "Endocrinologie", 8.90, "05-02-2025", 90, false);
            Drug dolirhume = new Drug("Dolirhume", "Analgesiques et Anti-inflammatoires", 6.49, "30-06-2025", 40, false);
            Drug seroplex = new Drug("Seroplex", "Neurologie", 22.30, "10-08-2025", 28, true);
            Drug smecta = new Drug("Smecta", "Gastro-enterologie et hepatologie", 4.20, "01-02-2025", 60, false);
            Drug lisinopril = new Drug("Lisinopril", "Cardiologie", 7.80, "18-07-2025", 30, true);
            Drug doliprane = new Drug("Doliprane", "Analgesiques et Anti-inflammatoires", 5.99, "10-02-2025", 50, false);
            Drug zyrtec = new Drug("Zyrtec", "Immunologie et Allergologie", 10.25, "25-06-2025", 15, false);
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
            purchase2.setPrescrition(prescription2);
            purchase2.setPurchaseDrugsQuantity(dafalgan, 1);
            purchase2.setPurchaseDrugsQuantity(amoxicilline, 1);
            purchase2.setPurchaseDrugsQuantity(smecta, 1);
            purchase2.setPurchaseDetails();

            Purchase purchase3 = new Purchase(true);
            Prescription prescription3 = new Prescription("12-08-2025", 1, 1);
            purchase3.setPrescrition(prescription3);
            purchase3.setPurchaseDrugsQuantity(dafalgan, 1);
            purchase3.setPurchaseDrugsQuantity(amoxicilline, 1);
            purchase3.setPurchaseDrugsQuantity(smecta, 1);
            purchase3.setPurchaseDetails();

            Purchase purchase4 = new Purchase("07-07-2025", true);
            Prescription prescription4 = new Prescription("22-06-2025", 1, 1);
            purchase4.setPrescrition(prescription4);
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
