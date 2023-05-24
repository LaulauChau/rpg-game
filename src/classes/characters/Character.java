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

    public abstract void attackOpponent(Character opponent, Integer damage);

    public boolean isDead() {
        return this.healthPoint <= 0;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.healthPoint + " HP, " + this.attackPoint + " ATK, " + this.defensePoint + " DEF)";
    }


}
