package classes.utilities;

import java.util.Scanner;

import classes.characters.Player;
import classes.characters.Character;

public enum Ability {
    FIREBALL("Fireball", 0, 15, 0, 0, 5),
    HEAL("Heal", 10, 0, 0, 0, 5),
    SHIELD("Shield", 0, 0, 5, 0, 5),
    MEDITATE("Meditate", 0, 0, 0, 10, 5);

    private final String name;
    private final int healthBonus;
    private final int attackBonus;
    private final int defenseBonus;
    private final int energyBonus;
    private final int energyCost;

    Ability(String name, int healthBonus, int attackBonus, int defenseBonus, int energyBonus, int energyCost) {
        this.name = name;
        this.healthBonus = healthBonus;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.energyBonus = energyBonus;
        this.energyCost = energyCost;
    }

    public static Ability chooseAbility(Scanner scanner) {
        Ability choosenAbility = null;

        do {
            System.out.println("Abilities:");
            for (int i = 0; i < Ability.values().length; i++) {
                System.out.println((i + 1) + ". " + Ability.values()[i]);
            }
            System.out.println("Which ability do you want to use?");

            try {
                int choice = scanner.nextInt();

                if (choice <= 0 || choice > Ability.values().length) {
                    System.out.println("Invalid choice!");
                } else {
                    choosenAbility = Ability.values()[choice - 1];
                }
            } catch (Exception e) {
                System.out.println("Invalid choice!");
                scanner.nextLine();
            }
        } while (choosenAbility == null);

        return choosenAbility;
    }

    public static void useAbility(Ability ability, Player user, Character target) {
        System.out.println("You have used " + ability.name + "!");

        switch (ability) {
            case FIREBALL -> {
                user.attackOpponent(target, ability.attackBonus);
                user.setEnergyPoint(Math.max(user.getEnergyPoint() - ability.energyCost, 0));
            }
            case HEAL -> {
                user.setHealthPoint(Math.min(user.getHealthPoint() + ability.healthBonus, 200));
                user.setEnergyPoint(Math.max(user.getEnergyPoint() - ability.energyCost, 0));
            }
            case SHIELD -> {
                user.setDefensePoint(user.getDefensePoint() + ability.defenseBonus);
                user.setEnergyPoint(Math.max(user.getEnergyPoint() - ability.energyCost, 0));
            }
            case MEDITATE -> {
                user.setEnergyPoint(Math.min(user.getEnergyPoint() + ability.energyBonus, 100));
                user.setEnergyPoint(Math.max(user.getEnergyPoint() - ability.energyCost, 0));
            }
        }
    }

    @Override
    public String toString() {
        return this.name + " (" + this.healthBonus + " HP, " + this.attackBonus + " ATK, " + this.defenseBonus + " DEF, " + this.energyBonus + " ENERGY, " + this.energyCost + " COST)";
    }
}
