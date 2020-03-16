package our.battlearena.fighters;

import our.battlearena.specials.DwarfHead;

public class Dwarf extends Character {

    public Dwarf(String name) {
        super(name, 15, 25, new DwarfHead());
        className = "Dwarf";
    }
}