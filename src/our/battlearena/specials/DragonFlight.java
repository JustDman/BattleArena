package our.battlearena.specials;

import our.battlearena.RNG;
import our.battlearena.fighters.Character;

public class DragonFlight extends Special {

    public DragonFlight() {
        name = "DragonFlight";
    }

    int decrease = 0;

    @Override
    public void onSpecialActivate(Character self, Character enemy) {
        decrease = RNG.getRandom(5, 10);
        // self.setAttackRange(self.getAttackMin() - random, self.getAttackMax() +
        // random);
        self.setAbsorbtion(10);
    }

    @Override
    public void onSpecialDeactivate(Character self, Character enemy) {
        // self.setAttackRange(20, 25);
        decrease = 0;
        self.takeDamage(10, null);
    }

    @Override
    public int modifyAttackValue(int value, Character self, Character enemy) {
        return value - decrease;
    }

}