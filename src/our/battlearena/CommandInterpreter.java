package our.battlearena;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import our.battlearena.fighters.Character;

public class CommandInterpreter {

    public CommandInterpreter() {
    }

    public void showMenu() {
        System.out.println("MENU");
        System.out.println("1) SHOW");
        System.out.println("2) CALC");
        System.out.println("3) EXIT");
    }

    public void start() {
        String s = "-";
        while (!s.equals("3")) {
            showMenu();
            s = (String) Input.get(Input.STRING, "");
            switch (s) {
                case "1":
                    this.show();
                    break;
                case "2":
                    this.calc();
                    break;
                case "3":
                    break;
                default:
                    this.error();
                    break;
            }
        }
        Input.close();
    }

    public void show() {
        System.out.println("SHOW");
    }

    public void calc() {
        System.out.println("CALC 1+1 = 2");
    }

    public void error() {
        System.out.println("ERROR!");
    }

    public static int chooseAction(Character c) throws NotImplementedException {
        boolean done = false;
        String s = "";
        System.out.println("************************SELECT ACTION*************************");
        System.out.println("1) ATTACK");
        if (c.isSpecialActive())
            System.out.println("2) DEACTIVATE SPECIAL ABILITY");
        else
            System.out.println("2) ACTIVATE SPECIAL ABILITY");
        s = (String) Input.get(Input.STRING, "");
        while (!done) {
            switch (s) {
                case "1":
                    return Battle.ACTION_ATTACK;
                case "2":
                    if (!c.getSpecial().requireTarget())
                        c.setSpecial(!c.isSpecialActive(), null);
                    else
                        throw new NotImplementedException("yeet");
                    // c.setSpecial(!c.isSpecialActive(), enemy);
                    return Battle.ACTION_SPECIAL;
                default:
                    System.out.println("Only 1 or 2 Allowed!");
                    break;
            }
        }
    }

    public Character chooseTarget(Character choosing, Team toChooseFrom) {

        toChooseFrom

        return null;
    }

}