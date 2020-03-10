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

    public void startBattle() {
        // do stuff
        while (team1.isAtleastOneAlive() && team2.isAtleastOneAlive()) {

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

                CommandInterpreter.chooseTarget(activeFighter, getEnemyTeam());

            }

        }
    }

    public Team getEnemyTeam() {
        if (activeTeam.equals(team1)) {
            return team2;
        }
        return team1;
    }

}