package our.battlearena.fighters;

import our.battlearena.specials.DragonFlight;

public class Dragon extends Character {

    public Dragon(String name) {
        super(name, 20, 25, new DragonFlight());
        className = "Dragon";
    }
}