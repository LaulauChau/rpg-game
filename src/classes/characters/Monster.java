package classes.characters;

import java.util.Random;

public class Monster extends Character {
    public Monster(String name, int healthPoint, int attackPoint, int defensePoint) {
        super(name, healthPoint, attackPoint, defensePoint);
    }

    @Override
    public void attackOpponent(Character opponent, Integer damage) {
        // Generate either 0 or 1
        Random random = new Random();
        int randomInt = random.nextInt(2);

        // The boss has a 50% chance to regenerate 30% of its health point
        if (this.getName().equals("Boss") && randomInt == 1) {
            int healthRegenerated = (int) (this.getHealthPoint() * 0.3);
            this.setHealthPoint(Math.min(this.getHealthPoint() + healthRegenerated, 100));

            System.out.println(this.getName() + " regenerates " + healthRegenerated + " health point!");
            return;
        }

        damage = this.getAttackPoint() - opponent.getDefensePoint();

        opponent.setHealthPoint(opponent.getHealthPoint() - damage);
        System.out.println(this.getName() + " attacks " + opponent.getName() + " and deals " + damage + " damage!");
    }
}
