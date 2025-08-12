package training.afpa.controller;

import training.afpa.model.Origin;
import training.afpa.model.Thief;
import training.afpa.model.Warrior;
import training.afpa.model.Wizard;
import training.afpa.view.Display;
import training.afpa.view.Gui;

import javax.swing.*;

public class Swing {

    public static void runSwing() {
        Origin elfic = new Origin("elfic");
        Origin orc = new Origin("orc");
        Origin dwarf = new Origin("dwarf");
        Origin human = new Origin("human");

        JFrame frame = Gui.setFrame(500, 500);
        JPanel panel = Gui.setPanel(frame);

        String[] jobsArray = {"Warrior", "Wizard", "Thief"};
        String[] originsArray = {"elfic", "orc", "dwarve", "human"};


        Gui.labelMaker(panel, "Choisissez votre classe : ", 10);
        Gui.labelMaker(panel, Display.selection(jobsArray), 40);
        JTextField jobField = Gui.textFieldMaker(panel, 70);

        Gui.labelMaker(panel, "Saisissez votre pseudo : ", 100);
        JTextField pseudoField = Gui.textFieldMaker(panel, 130);

        Gui.labelMaker(panel, "Saisissez votre origine : ", 160);
        Gui.labelMaker(panel, Display.selection(originsArray), 190);
        JTextField originField = Gui.textFieldMaker(panel, 220);


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
                    Warrior warrior1 = new Warrior(pseudo, origin);
                    JOptionPane.showMessageDialog(null, warrior1.toString() + "a été créé avec succés.", "Succes", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "1":
                    Wizard wizard1 = new Wizard(pseudo, origin);
                    JOptionPane.showMessageDialog(null, wizard1.toString() + "a été créé avec succés.", "Succes", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "2":
                    Thief thief1 = new Thief(pseudo, origin);
                    JOptionPane.showMessageDialog(null, thief1.toString() + "a été créé avec succés.", "Succes", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    break;
            }

        });
    }

}
