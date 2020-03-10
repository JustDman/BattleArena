package our.battlearena;

import java.util.ArrayList;
import java.util.Iterator;

import our.battlearena.fighters.Character;

public class Team {

    private ArrayList<Character> members = new ArrayList<>();

    private int currentFighterIndex = 0;

    public Team(Character member) {
        members.add(member);
    }

    public Team add(Character c) {
        if (!members.contains(c))
            members.add(c);
        return this;
    }

    public Character next() {
        if (currentFighterIndex >= (members.size() - 1))
            currentFighterIndex = 0;
        else
            currentFighterIndex++;
        return members.get(currentFighterIndex);
    }

    /**
     * If atleast one Character on a Team is still alive
     * @return If atleast one Character on a Team is alive
     */
    public boolean isAtleastOneAlive() {
        for (Character c : members) {
            if (c.isAlive())
                return true;
        }
        return false;
    }

    public Iterator<Character> getMemberIterator() {
        return members.iterator();
    }

}