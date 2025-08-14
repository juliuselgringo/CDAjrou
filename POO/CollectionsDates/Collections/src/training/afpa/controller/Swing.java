package training.afpa.controller;

import training.afpa.model.Origin;
import training.afpa.model.Thief;
import training.afpa.model.Warrior;
import training.afpa.model.Wizard;
import training.afpa.utility.UserInputException;
import training.afpa.view.Display;
import training.afpa.view.Gui;

import javax.swing.*;

public class Swing {

    public static void runSwing() {
        Origin elfic = new Origin("elfic");
        Origin orc = new Origin("orc");
        Origin dwarf = new Origin("dwarf");
        Origin human = new Origin("human");

        JFrame frame = Gui.setFrame(500, 500,600,600);
        JPanel panel = Gui.setPanel(frame);

        String[] jobsArray = {"Warrior", "Wizard", "Thief"};
        String[] originsArray = {"elfic", "orc", "dwarve", "human"};


        Gui.labelMaker(panel, "Choisissez votre classe : ",10 ,10);
        Gui.labelMaker(panel, Display.selection(jobsArray),10, 40);
        JTextField jobField = Gui.textFieldMaker(panel,10, 70);

        Gui.labelMaker(panel, "Saisissez votre pseudo : ",10, 100);
        JTextField pseudoField = Gui.textFieldMaker(panel,10, 130);

        Gui.labelMaker(panel, "Saisissez votre origine : ",10, 160);
        Gui.labelMaker(panel, Display.selection(originsArray), 10,190);
        JTextField originField = Gui.textFieldMaker(panel, 10,220);


        JButton buttonSave = Gui.buttonMaker(panel, "Valider", 250);

        buttonSave.addActionListener(e -> {
            String jobIndice = jobField.getText();
            String pseudo = pseudoField.getText();
            String originIndice = originField.getText();
            Origin origin = null;

            switch (originIndice) {
                case "0":
                    origin = elfic;
                    break;
                case "1":
                    origin = orc;
                    break;
                case "2":
                    origin = dwarf;
                    break;
                case "3":
                    origin = human;
                    break;
                default:
                    break;
            }

            switch (jobIndice) {
                case "0":
                    Warrior warrior1 = null;
                    try {
                        warrior1 = new Warrior(pseudo, origin);
                    } catch (UserInputException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(null, warrior1.toString() + "a été créé avec succés.", "Succes", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "1":
                    Wizard wizard1 = null;
                    try {
                        wizard1 = new Wizard(pseudo, origin);
                    } catch (UserInputException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(null, wizard1.toString() + "a été créé avec succés.", "Succes", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "2":
                    Thief thief1 = null;
                    try {
                        thief1 = new Thief(pseudo, origin);
                    } catch (UserInputException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(null, thief1.toString() + "a été créé avec succés.", "Succes", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    break;
            }

        });
    }

}
