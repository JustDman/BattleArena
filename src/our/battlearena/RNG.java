package our.battlearena;

import java.util.concurrent.ThreadLocalRandom;

import our.battlearena.fighters.Character;

public class RNG {

    /**
     * Get a random int between two values
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return Value between Minimum and Maximum (inclusive)
     */
    public static int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Return one of the two Characters (50% chance)
     * @param obj1 First Character
     * @param obj2 Second Character
     * @return one randomly chosen Character
     */
    public static Character getOneCharacter(Character obj1, Character obj2) {
        int erg = getRandom(0, 1);
        if (erg == 1) {
            return obj1;
        }
        return obj2;
    }

    public static Team getOneTeam(Team obj1, Team obj2) {
        int erg = getRandom(0, 1);
        if (erg == 1) {
            return obj1;
        }
        return obj2;
    }

}