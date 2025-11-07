package fr.juliuselgringo.sparadrap.ExceptionTracking;

/**
 * classe qui permet de suivre les erreurs de saisie, elle a pour parent la classe Exception
 */
public class InputException extends Exception {

    /**
     * constructeur par défaut
     * @param message String
     */
    public InputException(String message) {
        super(message);
    }
}
