package training.afpa.controller;

import training.afpa.model.*;
import training.afpa.utility.UserInputException;
import training.afpa.view.Display;
import training.afpa.view.Gui;
import training.afpa.view.Input;

import javax.swing.*;

public class SwingV2 {

    public static void run() throws UserInputException {
        worldGeneration();

        JFrame frame = Gui.setFrame(500, 500,600,600);
        JPanel panel = Gui.setPanel(frame);

        JTextArea menu = Gui.textAreaMaker(panel, Display.menu,10, 10,200);
        JLabel labelChoice = Gui.labelMaker(panel,"Saissez le chiffre de votre choix : ",10,230);
        JTextField menuChoice = Gui.textFieldMaker(panel,10,260);

        JButton validBtn = Gui.buttonMaker(panel,"Valider",290);

        validBtn.addActionListener(e -> {
            String choice = menuChoice.getText();

            switch (choice) {
                case "0":
                    JOptionPane.showMessageDialog(null,"Merci d'avoir joué! A bientôt.","Information",JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                    break;
                case "1":
                    Input.inputWarriorGUI();
                    break;
                case "2":
                    Input.inputWizardGUI();
                    break;
                case "3":
                    Input.inputThiefGUI();
                    break;
                case "4":
                    JPanel panelWarrior = Gui.setPanel(Gui.setFrame(300, 300,600,700));
                    Gui.textAreaMaker(panelWarrior, Warrior.getWarriorsList(),10,10,500);
                    break;
                case "5":
                    JPanel panelWizard = Gui.setPanel(Gui.setFrame(300, 300,600,700));
                    Gui.textAreaMaker(panelWizard, Wizard.getwizardsList(),10,10,500);
                    break;
                case "6":
                    JPanel panelThief = Gui.setPanel(Gui.setFrame(300, 300,600,700));
                    Gui.textAreaMaker(panelThief, Thief.getthiefsList(),10,10,500);
                    break;
                case "7":
                    Squad.selectSquadMatesGUI();
                    break;
                case "8":
                    JPanel panelSquad = Gui.setPanel(Gui.setFrame(300, 300,600,700));
                    Gui.textAreaMaker(panelSquad, Squad.getSquadsList(),10,10,500);
                    break;
                default:
                    break;
            }

        });
    }

    private static void worldGeneration() throws UserInputException {
        Origin elfic = new Origin("elfic");
        Origin.getOriginsMap("elfic", elfic);
        Origin orc = new Origin("orc");
        Origin.getOriginsMap("orc", orc);
        Origin dwarf =  new Origin("dwarf");
        Origin.getOriginsMap("dwarf", dwarf);
        Origin human = new Origin("human");

        Warrior jul = new Warrior("jul",elfic);
        Wizard jojo = new Wizard("jojo",orc);
        Thief picsous = new Thief("picsous",human);
    }
}
