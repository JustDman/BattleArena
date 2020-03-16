package our.battlearena.fighters;

import our.battlearena.RNG;
import our.battlearena.specials.Special;

public class Character {

    protected String name;
    protected String className = "Character";
    protected int hp = 100;
    protected int attackMin;
    protected int attackMax;
    protected boolean special = false;
    protected Special specialAbility;
    private Character lastEnemyAttacked;
    private Character lastEnemyHitBy;

    protected int absorbtion = 0;

    public Character(String name, int attackMin, int attackMax, Special special) {
        this.name = name;
        this.attackMin = attackMin;
        this.attackMax = attackMax;
        if (special == null)
            special = new Special();
        this.specialAbility = special;
    }

    /**
     * Gets this Characters Name
     * 
     * @return this Characters Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets this Characters Class Name
     * 
     * @return this Characters Class Name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets this Characters HP
     * 
     * @return this Characters HP value
     */
    public int getHP() {
        return hp;
    }

    public int getAttackMin() {
        return attackMin;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public int[] getAttackRange() {
        return new int[] { attackMin, attackMax };
    }

    /**
     * Is this Characters special ability currently active?
     * 
     * @return is Special Ability active.
     */
    public boolean isSpecialActive() {
        return specialAbility.isActive();
    }

    /**
     * Returns the special ability class.
     * 
     * @return Special
     */
    public Special getSpecial() {
        return specialAbility;
    }

    public boolean isAlive() {
        if (this.hp > 0)
            return true;
        return false;
    }

    public boolean isDead() {
        return !isAlive();
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void setAbsorbtion(int abs) {
        absorbtion = abs;
    }

    public int getAbsorbtion() {
        return absorbtion;
    }

    public void setAttackRange(int min, int max) {
        attackMin = min;
        attackMax = max;
    }

    /**
     * Attacks an enemy
     * 
     * @param enemy The Character to attack
     * @return attack value.
     */
    public int attack(Character enemy) {
        this.lastEnemyAttacked = enemy;
        int damage = specialAbility.modifyAttackValue(RNG.getRandom(getAttackMin(), getAttackMax()), this, enemy);
        enemy.takeDamage(damage, enemy);
        return damage;
                
    }

    /**
     * Reduces a Characters HP (with absobtion handled) absorbtion takes damage
     * before actual HP are lost!
     * 
     * @param hp    the ammount the hp/absorbtion has to be reduced by
     * @param enemy the Character attacking
     * @return true if the Character died
     */
    public boolean takeDamage(int hp, Character enemy) {
        this.lastEnemyHitBy = enemy;
        return applyDamage(specialAbility.modifyTakeDamageValue(hp, this, enemy));
    }

    /**
     * Reduces a Characters HP (with absobtion handled) absorbtion takes damage
     * before actual HP are lost!
     * 
     * @param hp the ammount the hp/absorbtion has to be reduced by
     * @return true if the Character died
     */
    private boolean applyDamage(int hp) {
        if (absorbtion > 0) {
            absorbtion -= hp;

            if (absorbtion < 0) {
                setHP(this.hp + absorbtion);
                absorbtion = 0;
            }
        } else if (absorbtion <= 0) {
            setHP(this.hp - hp);
        }
        return isDead();
    }

    /**
     * Reduces a Characters HP (with absobtion handled) absorbtion takes damage
     * before actual HP are lost!
     * 
     * @param hp        the ammount the hp/absorbtion has to be reduced by
     * @param enemy     The attacking enemy
     * @param penetrate true if the absorbtion value should be ignored
     * @return true if the Character died
     */
    public boolean takeDamage(int hp, Character enemy, boolean penetrate) {
        int _abs = absorbtion;
        if (penetrate)
            absorbtion = 0;
        boolean res = takeDamage(hp, enemy);
        absorbtion = _abs;
        return res;
    }

    /**
     * De/Activate this Characters special ability.
     * 
     * @param special true to activate this Characters Special Ability
     * @param enemy   The enemy affected by the special ability (may be null)
     */
    public void setSpecial(boolean special, Character enemy) {
        if (enemy != null)
            this.lastEnemyAttacked = enemy;
        specialAbility.setActive(special, this, enemy);
    }

    /**
     * 
     * @param enemy The enemy affected by the special ability (may be null)
     */
    public void toggleSpecial(Character enemy) {
        setSpecial(!special, enemy);
    }
}