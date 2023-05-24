package classes.game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import classes.dungeons.Dungeon;
import classes.dungeons.Room;
import classes.characters.Player;
import classes.characters.Monster;
import classes.utilities.Item;
import classes.utilities.Ability;

public class Game {
    Scanner scanner = new Scanner(System.in);

    public void initGame() {
        System.out.println("Welcome to the dungeon!");
        Player player = createPlayer(this.scanner);
        ArrayList<Dungeon> dungeons = initDungeons();

        for (int i = 0; i < dungeons.size(); i++) {
            Dungeon dungeon = dungeons.get(i);
            System.out.println("Entering dungeon " + (i + 1) + "...");
            System.out.println("This dungeon contains " + dungeon.getRooms().size() + " rooms!");
            exploreDungeon(dungeon, player, this.scanner);

            if (player.isDead()) {
                break;
            }
        }

        if (!player.isDead()) {
            System.out.println("You have cleared all dungeons!");
        }
    }

    public static Player createPlayer(Scanner scanner) {
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        Player player = null;

        do {
            System.out.println("Choose your class:");
            System.out.println("1. Warrior");
            System.out.println("2. Mage");
            System.out.println("3. Tank");

            try {
                int choice = scanner.nextInt();

                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice!");
                } else {
                    switch (choice) {
                        case 1 -> {
                            player = new Player(name, 100, 20, 10, 5);
                            System.out.println("You have chosen the Warrior class!");
                        }
                        case 2 -> {
                            player = new Player(name, 50, 10, 5, 20);
                            System.out.println("You have chosen the Mage class!");
                        }
                        case 3 -> {
                            player = new Player(name, 200, 10, 20, 0);
                            System.out.println("You have chosen the Tank class!");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid choice!");
                scanner.nextLine();
            }
        } while (player == null);

        System.out.println(player);
        return player;
    }

    public static ArrayList<Dungeon> initDungeons() {
        ArrayList<Dungeon> dungeons = new ArrayList<>();
        Random random = new Random();

        int numberOfDungeons = random.nextInt(3) + 1;

        for (int i = 0; i < numberOfDungeons; i++) {
            dungeons.add(new Dungeon());
        }

        return dungeons;
    }

    public static boolean exploreRoom(Room room, Player player, Scanner scanner) {
        int turnNumber = 1;
        System.out.println("You have been attacked by " + room.getMonsters().size() + " monsters!");

        while (!room.isCleared()) {
            if (player.isDead()) {
                System.out.println("Game over!");
                break;
            }

            System.out.println("\n===== Turn " + turnNumber + " =====");

            System.out.println(player);
            room.displayRoom();
            System.out.println();

            if (turnNumber % 2 == 0) {
                for (Monster monster : room.getMonsters()) {
                    monster.attackOpponent(player, null);
                }
            } else {
                int playerAction = getPlayerChoice(scanner);
                computePlayerChoice(playerAction, player, room, scanner);
            }

            turnNumber++;
        }

        return room.isCleared() && !player.isDead();
    }

    public static void exploreDungeon(Dungeon dungeon, Player player, Scanner scanner) {
        do {
            Room currentRoom = dungeon.getCurrentRoom();
            System.out.println("You are now in room " + (dungeon.getCurrentRoomIndex() + 1) + "!");
            boolean cleared = exploreRoom(currentRoom, player, scanner);

            if (cleared) {
                System.out.println("You have cleared the room!");
                player.getReward();
                int result = dungeon.goToNextRoom();

                if (result == 0) {
                    break;
                }
            } else {
                System.out.println("You have died!");
                break;
            }
        } while (dungeon.getCurrentRoomIndex() < dungeon.getRooms().size());

        if (!player.isDead()) {
            System.out.println("You have cleared the dungeon!");
        }
    }

    public static int getPlayerChoice(Scanner scanner) {
        int choice = 0;

        do {
            System.out.println("What do you want to do?");
            System.out.println("1. Attack");
            System.out.println("2. Use an item");
            System.out.println("3. Use an ability");

            try {
                choice = scanner.nextInt();

                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice!");
                scanner.nextLine();
            }
        } while (choice < 1 || choice > 3);

        return choice;
    }

    public static void computePlayerChoice(int choice, Player player, Room room, Scanner scanner) {
        switch (choice) {
            case 1 -> {
                Monster monster = room.chooseMonster(scanner);
                player.attackOpponent(monster, null);
                room.removeMonster(monster);
            }
            case 2 -> {
                Item item = Item.chooseItem(player, scanner);
                Item.useItem(item, player, scanner);
            }
            case 3 -> {
                Ability ability = Ability.chooseAbility(scanner);

                if (ability == Ability.FIREBALL) {
                    Monster monster = room.chooseMonster(scanner);
                    Ability.useAbility(ability, player, monster);
                    room.removeMonster(monster);
                } else {
                    Ability.useAbility(ability, player, null);
                }
            }
        }
    }
}
