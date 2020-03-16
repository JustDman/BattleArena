package our.battlearena.specials;

import our.battlearena.RNG;
import our.battlearena.fighters.Character;

public class DwarfHead extends Special {

    public DwarfHead() {
        name = "Headbutt";
    }

    @Override
    public void onSpecialActivate(Character self, Character currentEnemy) {
        if (self.getHP() >= 50)
            self.setSpecial(false, null);
    }

    @Override
    public int modifyAttackValue(int value, Character self, Character victim) {
        if (self.isSpecialActive()) {
            int chance = RNG.getRandom(1, 10);
            if (self.getHP() <= 10 && chance <= 7)
                return value * 2;
            else if (self.getHP() <= 20 && chance <= 5)
                return value * 2;
            else if (self.getHP() <= 50 && chance <= 3)
                return value * 2;
            else
                return value / 2;
        }
        return value;
    }
}