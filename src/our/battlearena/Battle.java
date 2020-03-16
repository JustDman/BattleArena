package our.battlearena;

import java.util.Iterator;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import our.battlearena.fighters.Character;

public class Battle {

    private final Team team1;
    private final Team team2;

    public Battle(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        init();
    }

    private Team activeTeam;
    // private Character activeFighter;

    public void init() {
        activeTeam = RNG.getOneTeam(team1, team2);
    }

    public static final int ACTION_NONE = 0;
    public static final int ACTION_ATTACK = 1;
    public static final int ACTION_SPECIAL = 2;
    public static final int ACTION_SPECIAL_TARGET = 3;

    public void startBattle() {
        // do stuff
        while (team1.isAtLeastOneAlive() && team2.isAtLeastOneAlive()) {

            activeTeam = getEnemyTeam();

            Iterator<Character> it = activeTeam.getMemberIterator();
            while (it.hasNext()) {
                Character activeFighter = it.next();
                int action = ACTION_NONE;
                try {
                    action = CommandInterpreter.chooseAction(activeFighter);
                } catch (NotImplementedException e) {
                    e.printStackTrace();
                }
                Character target = null;
                if (action == ACTION_ATTACK || action == ACTION_SPECIAL_TARGET)
                    target = CommandInterpreter.chooseTarget(this, activeFighter);
                switch (action) {
                    case ACTION_ATTACK:
                        activeFighter.attack(target);
                        break;
                    case ACTION_SPECIAL_TARGET:
                    case ACTION_SPECIAL:
                        activeFighter.setSpecial(!activeFighter.isSpecialActive(), target);
                        break;
                    case ACTION_NONE:
                    default:
                        break;
                }

                //

            }

            System.out.println("************************TEAM 1*************************");
            team1.forEach(e -> printCharInfo(e));

            System.out.println("************************TEAM 2*************************");
            team2.forEach(e -> printCharInfo(e));

        }
        if (team1.isAtLeastOneAlive())
            System.out.println("TEAM 1 WON!");
        else
            System.out.println("TEAM 2 WON!");
        return;

    }

    public void printCharInfo(Character e) {
        System.out.println("Class: " + e.getClassName());
        System.out.println("Name: " + e.getName());
        System.out.println("HP: " + e.getHP() + "/100");
        System.out.println("Special Status: " + e.isSpecialActive() + " \"" + e.getSpecial().getName() + "\"");
    }

    public Team getEnemyTeam() {
        if (activeTeam.equals(team1)) {
            return team2;
        }
        return team1;
    }

}