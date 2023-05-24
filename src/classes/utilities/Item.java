package classes.utilities;

import java.util.Random;
import java.util.Scanner;

import classes.characters.Player;

public enum Item {
    SWORD("Sword", 0, 5, 0, 0),
    SHIELD("Shield", 0, 0, 5, 0),
    HEALTH_POTION("Health Potion", 10, 0, 0, 0),
    ENERGY_POTION("Energy Potion", 0, 0, 0, 10);

    private final String name;
    private final int healthBonus;
    private final int attackBonus;
    private final int defenseBonus;
    private final int energyBonus;

    Item(String name, int healthBonus, int attackBonus, int defenseBonus, int energyBonus) {
        this.name = name;
        this.healthBonus = healthBonus;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.energyBonus = energyBonus;
    }

    public static Item chooseItem(Player player, Scanner scanner) {
        Item choosenItem = null;

        do {
            player.displayInventory();
            System.out.println("Which item do you want to use?");

            try {
                int choice = scanner.nextInt();

                if (choice <= 0 || choice > player.getItems().size()) {
                    System.out.println("Invalid choice!");
                } else {
                    choosenItem = player.getItems().get(choice - 1);
                }
            } catch (Exception e) {
                System.out.println("Invalid choice!");
                scanner.nextLine();
            }
        } while (choosenItem == null);

        return choosenItem;
    }

    public static void useItem(Item item, Player user, Scanner scanner) {
        System.out.println("You have used " + item.name + "!");

        user.setHealthPoint(Math.min(user.getHealthPoint() + item.healthBonus, 100));
        user.setAttackPoint(user.getAttackPoint() + item.attackBonus);
        user.setDefensePoint(user.getDefensePoint() + item.defenseBonus);
        user.setEnergyPoint(Math.min(user.getEnergyPoint() + item.energyBonus, 100));

        user.removeItem(item);
    }

    public static Item getRandomItem() {
        Random random = new Random();
        return Item.values()[random.nextInt(Item.values().length)];
    }

    @Override
    public String toString() {
        return this.name + " (" + this.healthBonus + " HP, " + this.attackBonus + " ATK, " + this.defenseBonus + " DEF, " + this.energyBonus + " ENERGY)";
    }
}
