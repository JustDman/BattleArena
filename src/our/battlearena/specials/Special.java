package our.battlearena.specials;

import our.battlearena.fighters.Character;

public class Special {

    private String name = "Special";
    private boolean isActive = false;

    private boolean requireTarget = false;

    public Special() {

    }

    /**
     * Gets called when the Special ability is activated
     */
    public void onSpecialActivate(Character self, Character currentEnemy) {

    }

    /**
     * Gets called when the Special ability is deactivated
     * 
     */
    public void onSpecialDeactivate(Character self, Character currentEnemy) {

    }

    /**
     * If this special ability is active
     * @return special is active
     */
    public boolean isActive() {
        return isActive;
    }

    public boolean requireTarget() {
        return requireTarget;
    }

    /**
     * Activate
     * @param active should it be activated?
     * @param self The Character this ability belongs to
     * @param enemy The Characters enemy
     */
    public void setActive(boolean active, Character self, Character enemy) {
        isActive = active;
        if (isActive)
            onSpecialActivate(self, enemy);
        else
            onSpecialDeactivate(self, enemy);

    }

    /**
     * Modifies outgoin Attack value self attacks -> victim
     * Gets called everytime an attack is about to happen
     * @param value The attacks current damage value
     * @param self The Character attacking
     * @param victim The character under attack
     * @return The new attacks damage value
     */
    public int modifyAttackValue(int value, Character self, Character victim) {
        return value;
    }

    /**
     * Modifies incoming Attack value enemy attacks -> self
     * Gets called everytime an attack is about to happen
     * @param value The attacks current damage value
     * @param self The character under attack
     * @param enemy The character attacking
     * @return The new attacks damage value
     */
    public int modifyTakeDamageValue(int value, Character self, Character enemy) {
        return value;
    }

    /**
     * Gets the name of this special ability
     * @return the name of this special ability
     */
    public String getName() {
        return name;
    }

}