# RPG Game
This is an immersive, terminal-based role-playing game (RPG) where the user can create a character and explore various dungeons filled with monsters and bosses. The game supports multiple character classes and features a turn-based battle system.

This project was realized as part of the Object-Oriented Programming course at [EFREI Paris](https://eng.efrei.fr/).

## Installation

Follow these steps to install the game on your computer:

### Prerequisites
- [Java JDK](https://www.oracle.com/java/technologies/downloads/)
- [Git](https://git-scm.com/downloads)

### Clone the Repository
Clone the repository to your computer using the following command:
```
git clone https://github.com/LaulauChau/rpg-game.git
```

### Compile the Source Code
Navigate to the project directory and compile the source code using the following command:
```
cd rpg-game
javac -d bin $(find . -name "*.java")
```

### Run the Game
Run the game using the following command:
```
java -cp bin Main
```

## How to Play

### Character Creation
The player can create a character choosing from one of the following classes:
- Warrior: These are powerful melee fighters, excelling in close-quarter battles.
- Mage: Masters of the arcane, these characters use magic to destroy their enemies.
- Tank: These characters have high defense and health, taking on the enemy's attacks to protect their allies.

### Exploring Dungeons
After creating your character, you'll venture into various dungeons. These dangerous areas are filled with hostile monsters and a dungeon boss. Each dungeon must be cleared before moving on to the next.

### Battle System
Combat is turn-based, you can choose to attack, use an ability or an item. Defeating monsters regenerate 30% of your current health and grant you a random object.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.