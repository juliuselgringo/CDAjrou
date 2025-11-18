package fr.juliuselgringo.sparadrap.controller;

import fr.juliuselgringo.sparadrap.DAO.ContactDAO;
import fr.juliuselgringo.sparadrap.DAO.MutualDAO;

import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.view.ProgramSwing;

import java.io.IOException;

/**
 * classe principale qui gère le programme
 */
public class Main {

    /**
     * constructeur par défaut
     */
    public Main() {}

    /**
     * Méthode qui lance le démarrage du programme
     * @param args String[][]
     * @throws IOException String
     * @throws InputException String
     */
    public static void main(String[] args) throws IOException, InputException {

        //Main.testDAO();

        ProgramSwing.generalMenu();

    }

    private static void testDAO() throws IOException, InputException {

        /*
        ContactDAO contactDAO = new ContactDAO();
        System.out.println(contactDAO.getAll());
        contactDAO.closeConnection();
        */

        MutualDAO mutualDAO = new MutualDAO();
        System.out.println(mutualDAO.getAll());
        mutualDAO.closeConnection();
    }

}
