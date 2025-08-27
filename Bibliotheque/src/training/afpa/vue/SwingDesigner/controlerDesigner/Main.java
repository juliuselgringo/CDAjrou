package training.afpa.vue.SwingDesigner.controlerDesigner;

import training.afpa.controler.MainApp;
import training.afpa.vue.SwingDesigner.MenuPrincipal;

public class Main {

    public static void main(String[] args) {
        try{
            MainApp.libraryCreation();
        }catch(Exception e){
            System.err.println("Erreur dans le programme d'initialisation: " + e.getMessage());
            System.exit(1);
        }
        MenuPrincipal mainMenu = new MenuPrincipal();
    }
}
