package classes.characters;

import java.util.Objects;

public class Monster extends Character {
    public Monster(String name, int healthPoint, int attackPoint, int defensePoint) {
        super(name, healthPoint, attackPoint, defensePoint);
    }

    public void attackOpponent(Character opponent) {
        // If the boss has below 30% of its health point, it will regenerate its health point by 30% of its max health point
        if (this.getName().equals("Boss") && this.getHealthPoint() <= 0.3 * 100) {
            int healthRegenerated = (int) (this.getHealthPoint() * 0.3);
            this.setHealthPoint(Math.min(this.getHealthPoint() + healthRegenerated, 100));
        }

        int damage = this.getAttackPoint() - opponent.getDefensePoint();

        opponent.setHealthPoint(opponent.getHealthPoint() - damage);
        System.out.println(this.getName() + " attacks " + opponent.getName() + " and deals " + damage + " damage!");
    }
}
