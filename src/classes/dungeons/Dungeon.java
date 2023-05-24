package classes.dungeons;

import java.util.ArrayList;
import java.util.Random;

public class Dungeon {
    private final ArrayList<Room> rooms;
    private int currentRoom = 0;

    public Dungeon() {
        this.rooms = new ArrayList<>();

        Random random = new Random();
        int numberOfRoom = random.nextInt(5) + 1;

        for (int i = 0; i < numberOfRoom - 1; i++) {
            this.rooms.add(new Room(false));
        }

        this.rooms.add(new Room(true));
    }

    public int getCurrentRoomIndex() { return this.currentRoom; }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public Room getCurrentRoom() {
        return this.rooms.get(this.currentRoom);
    }

    public int goToNextRoom() {
        if (!this.getCurrentRoom().isCleared()) {
            return -1;
        }

        if (this.currentRoom < this.rooms.size()) {
            this.currentRoom++;
            return 1;
        }

        System.out.println("You have cleared the dungeon!");
        return 0;
    }

    @Override
    public String toString() {
        return "Dungeon (" + this.rooms.size() + " rooms)";
    }
}
