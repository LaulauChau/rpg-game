package classes.characters;

import java.util.ArrayList;

import classes.utilities.Item;

public class Player extends Character {
    private int energyPoint;
    private final ArrayList<Item> items;

    public Player(String name, int healthPoint, int attackPoint, int defensePoint, int energyPoint) {
        super(name, healthPoint, attackPoint, defensePoint);
        this.energyPoint = energyPoint;
        this.items = new ArrayList<>();
    }

    public int getEnergyPoint() {
        return energyPoint;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setEnergyPoint(int energyPoint) {
        this.energyPoint = energyPoint;
    }

    public void addItem(Item item) {
        this.items.add(item);
        System.out.println("You have obtained " + item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void displayInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < this.items.size(); i++) {
            System.out.println((i + 1) + ". " + this.items.get(i));
        }
    }

    public void getReward() {
        int healthRegenerated = (int) (this.getHealthPoint() * 1.3);
        this.setHealthPoint(Math.min(healthRegenerated, 200));
        this.addItem(Item.getRandomItem());
    }


    @Override
    public void attackOpponent(Character opponent, Integer damage) {
        // Damage is null if the player uses an ability
        if (damage == null) {
            damage = this.getAttackPoint();
        }

        damage = damage - opponent.getDefensePoint();

        opponent.setHealthPoint(opponent.getHealthPoint() - damage);
        System.out.println(this.getName() + " attacks " + opponent.getName() + " and deals " + damage + " damage!");
    }

    @Override
    public String toString() {
        return this.getName() + " (" + this.getHealthPoint() + " HP, " + this.getAttackPoint() + " ATK, " + this.getDefensePoint() + " DEF, " + this.energyPoint + " ENERGY)";
    }
}
