package classes.characters;

public abstract class Character {
    private final String name;
    private int healthPoint;
    private int attackPoint;
    private int defensePoint;

    public Character(String name, int healthPoint, int attackPoint, int defensePoint) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    /**
     * Attack an opponent.
     *
     * @param opponent to be attacked
     * @param damage to be dealt
     *               if null, the damage will be the player's attack point
     *               if not null, the damage will be the parameter (used for abilities)
     */
    public void attackOpponent(Character opponent, Integer damage) {
        if (damage == null) {
            damage = this.attackPoint;
        }

        damage = damage - opponent.defensePoint;

        opponent.setHealthPoint(opponent.getHealthPoint() - damage);
        System.out.println(this.name + " attacks " + opponent.name + " and deals " + damage + " damage!");
    }

    public boolean isDead() {
        return this.healthPoint <= 0;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.healthPoint + " HP, " + this.attackPoint + " ATK, " + this.defensePoint + " DEF)";
    }


}
