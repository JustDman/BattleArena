package our.battlearena;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

import our.battlearena.fighters.Character;

public class Team {

    private ArrayList<Character> members = new ArrayList<>();

    private int currentFighterIndex = 0;

    public Team() {

    }

    public Team(Character member) {
        members.add(member);
    }

    public Team add(Character c) {
        if (!members.contains(c))
            members.add(c);
        return this;
    }

    /**
     * Select the next Character on this Team
     * 
     * @return next Character
     */
    public Character next() {
        if (currentFighterIndex >= (members.size() - 1))
            currentFighterIndex = 0;
        else
            currentFighterIndex++;
        return members.get(currentFighterIndex);
    }

    public int getIndex(Character c) {
        for (int i = 0; i < size(); i++) {
            if (members.get(i) == c)
                return i;
        }
        return -1;
    }

    public boolean contains(Character c) {
        return members.contains(c);
    }

    public Character get(int index) {
        return members.get(index);
    }

    /**
     * If atleast one Character on a Team is still alive
     * 
     * @return If atleast one Character on a Team is alive
     */
    public boolean isAtLeastOneAlive() {
        for (Character c : members) {
            if (c.isAlive())
                return true;
        }
        return false;
    }

    public void forEach(Consumer<? super Character> action) {
        members.forEach(action);
    }

    public Iterator<Character> getMemberIterator() {
        return members.iterator();
    }

    public int size() {
        return members.size();
    }

}