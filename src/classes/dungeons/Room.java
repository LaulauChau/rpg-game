package classes.dungeons;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import classes.characters.Monster;

public class Room {
    private final ArrayList<Monster> monsters;

    public Room(Boolean isBossRoom) {
        if (isBossRoom) {
            this.monsters = new ArrayList<>();
            this.monsters.add(new Monster("Boss", 100, 20, 10));
        } else {
            this.monsters = new ArrayList<>();
            Random random = new Random();
            int numberOfMonsters = random.nextInt(3) + 1;
            for (int i = 0; i < numberOfMonsters; i++) {
                this.monsters.add(new Monster("Monster " + (i + 1), 50, 10, 5));
            }
        }
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void removeMonster(Monster monster) {
        if (monster.isDead()) {
            System.out.println(monster.getName() + " is dead!");
            this.monsters.remove(monster);
        }
    }

    public void displayRoom() {
        for (int i = 0; i < this.monsters.size(); i++) {
            System.out.println((i + 1) + ". " + this.monsters.get(i));
        }
    }

    public boolean isCleared() {
        return this.monsters.size() == 0;
    }

    public Monster chooseMonster(Scanner scanner) {
        Monster choosenMonster = null;

        if (this.monsters.size() == 1) {
            choosenMonster = this.monsters.get(0);
        }

        while (choosenMonster == null) {
            this.displayRoom();
            System.out.println("Which monster do you want to attack?");
            int choice = scanner.nextInt();

            if (choice < 0 || choice > this.monsters.size()) {
                System.out.println("Invalid choice!");
            } else {
                choosenMonster = this.monsters.get(choice - 1);
            }
        };

        return choosenMonster;
    }
}
