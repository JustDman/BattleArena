package our.battlearena;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import our.battlearena.fighters.*;
import our.battlearena.fighters.Character;

public class CommandInterpreter {

    private Team t1 = new Team();
    private Team t2 = new Team();

    public CommandInterpreter() {
    }

    public void showMenu() {
        System.out.println("MENU");
        System.out.println("1) CREATE FIGHTERS AND TEAMS");
        System.out.println("2) START BATTLE");
        System.out.println("3) EXIT");
    }

    public void start() {
        String s = "-";
        while (!s.equals("3")) {
            showMenu();
            s = (String) Input.get(Input.STRING, "");
            switch (s) {
                case "1":
                    this.createFighter();
                    break;
                case "2":
                    this.startBattle();
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

    public void createFighter() {
        String s = "-";

        s = (String) Input.get(Input.STRING, "1v1 (1) or 2v2 (2)");
        switch (s) {
            case "1":
                System.out.println("************************TEAM 1*************************");
                createTeamsSingle(t1);
                System.out.println("************************TEAM 2*************************");
                createTeamsSingle(t2);
                break;
            case "2":
                System.out.println("************************TEAM 1*************************");
                createTeamsDuo(t1);
                System.out.println("************************TEAM 2*************************");
                createTeamsDuo(t2);
                break;
            default:
                System.out.println("Failed!");
                break;
        }

    }

    public void startBattle() {
        if (t1 != null && t2 != null)
            new Battle(t1, t2).startBattle();
    }

    public void error() {
        System.out.println("ERROR!");
    }

    public static int chooseAction(Character c) {
        boolean done = false;
        String s = "";
        System.out.println("************************SELECT ACTION*************************");
        System.out.println("1) ATTACK");
        if (c.isSpecialActive())
            System.out.println("2) DEACTIVATE SPECIAL ABILITY: " + c.getSpecial().getName());
        else
            System.out.println("2) ACTIVATE SPECIAL ABILITY: " + c.getSpecial().getName());
        while (!done) {
            s = (String) Input.get(Input.STRING, "");
            switch (s) {
                case "1":
                    return Battle.ACTION_ATTACK;
                case "2":
                    if (!c.getSpecial().requireTarget())
                        return Battle.ACTION_SPECIAL;
                    // c.setSpecial(!c.isSpecialActive(), null);
                    else
                        return Battle.ACTION_SPECIAL_TARGET;
                    // throw new NotImplementedException("Not Implemented!");
                    // c.setSpecial(!c.isSpecialActive(), enemy);
                    // return Battle.ACTION_SPECIAL;
                default:
                    System.out.println("Only 1 or 2 Allowed!");
                    break;
            }
        }
        return 0;
    }

    public static Character chooseTarget(Battle battle, Character choosing) {

        System.out.println("************************ENEMY TEAM*************************");
        Team foes = battle.getEnemyTeam();
        foes.forEach(e -> {
            if (e.isAlive())
                System.out.println(foes.getIndex(e) + ") " + e.getName() + ", " + e.getHP() + ", " + e.getClassName());
            else
                System.out.println(foes.getIndex(e) + ") " + e.getName() + ", " + "DEAD" + ", " + e.getClassName());
        });
        int index;
        do {
            index = (int) Input.get(Input.INT, "Choose a Target!", false);
        } while (index < 0 || index >= foes.size());
        return foes.get(index);
    }

    public void createTeamsSingle(Team team) {
        String s = "-";
        String name = (String) Input.get(Input.STRING, "Choose a name!");
        s = (String) Input.get(Input.STRING, "Choose a Class!\n1) Dragon\n2) Dwarf");
        switch (s) {
            case "1":
                team.add(new Dragon(name));
                break;
            case "2":
                team.add(new Dwarf(name));
                break;
            default:
                System.out.println("Failed!");
                break;
        }
    }

    public void createTeamsDuo(Team team) {
        createTeamsSingle(team);
        createTeamsSingle(team);
    }

}