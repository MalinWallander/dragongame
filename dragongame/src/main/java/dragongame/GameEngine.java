package dragongame;

import java.util.Map;
import java.util.Scanner;

public class GameEngine {

    private Player player;

    public GameEngine() {
        GameWorld world = new GameWorld();
        world.setUpField();
        this.player = new Player(world.getStartingRoom(), "Adventurer", 10);
    }

    // Karta för att tolka riktningar och deras förkortningar
    private static final Map<String, String> directionAliases = Map.ofEntries(
            Map.entry("n", "north"),
            Map.entry("s", "south"),
            Map.entry("e", "east"),
            Map.entry("w", "west"),
            Map.entry("u", "up"),
            Map.entry("d", "down"),
            Map.entry("f", "forward"),
            Map.entry("enter", "forward"));

    private String parseDirection(String input) {
        for (String fullDir : directionAliases.values()) {
            if (input.equals(fullDir)) {
                return fullDir;
            }
        }
        for (Map.Entry<String, String> entry : directionAliases.entrySet()) {
            if (input.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    // Huvudspel-loopen
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        System.out.println("What is your name, adventurer? ");
        System.out.print("> ");
        String name = scanner.nextLine().trim();
        // Sätt spelarens namn
        if (name.isEmpty()) {
            name = "Adventurer"; // fallback
        }
        player.setName(name);
        // Välkomstmeddelande
        System.out.println("Welcome to the dragongame adventure, " + name + "!");

        player.getCurrentRoom().roomNarrative();
        checkRoomForItems(scanner);
        checkRoomForEnemies(scanner);

        while (playing) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("stop")) {
                System.out.println("Thank you for playing!");
                break;
            }

            String direction = parseDirection(command);
            if (direction != null) {
                player.move(direction, scanner);
                checkRoomForItems(scanner);
                checkRoomForEnemies(scanner);
            } else {
                System.out.println("I don't understand that command.");
            }

            if (player.getCurrentRoom().getName().equals("Exit")) {
                System.out.println(
                        "Congratulations, " + player.getName()
                                + "! You have found the exit of the castle! Sunlight hits your face, and you can see the open world beyond. Freedom is just a step away. Press enter to conclude your adventure.");
                scanner.nextLine();
                playing = false;
            }

        }

        scanner.close();
    }

    // Check the current room for items and allow the player to pick them up
    private void checkRoomForItems(Scanner scanner) {
        Room room = player.getCurrentRoom();
        if (!room.getItems().isEmpty()) {
            for (int i = 0; i < room.getItems().size(); i++) {
                System.out.println("You see " + room.getItems().get(i).getDescription()
                        + " Type p to pick it up. Or press enter to ignore it.");
            }
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                if (input.equals("p") || input.equals("P")) {
                    Item picked = room.getItems().remove(0);
                    player.addItem(picked);
                    if (picked instanceof Sword) {
                        picked.use(player);
                        System.out.println(
                                "You picked up the sword! Your attack power is now " + player.getAttackPower() + ".");
                    } else {
                        System.out.println(
                                picked.getName() + " has been added to your inventory. Where do you want to go next?");
                    }
                } else
                    System.out.println("Invalid input.");
            } else {
                System.out.println("You chose not to pick up the item. Where do you want to go next?");
            }
        }
    }

    private void checkRoomForEnemies(Scanner scanner) {
        Room room = player.getCurrentRoom();
        if (!room.getEnemy().isEmpty()) {
            for (int i = 0; i < room.getEnemy().size(); i++) {
                System.out.println("You encounter " + room.getEnemy().get(i).getDescription()
                        + " Prepare for battle! Press a to attack.");
            }
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    if (input.equals("a")) {
                        Enemy enemy = room.getEnemy().get(0);

                        while (enemy.getHealth() > 0 && player.getHealth() > 0) {
                            player.attack(enemy);

                            if (enemy.getHealth() > 0) {
                                enemy.attack(player);

                                // Offer healing when health drops to exactly 2 or below (but still alive)
                                if (player.getHealth() > 0 && player.getHealth() <= 2) {
                                    if (player.hasItem("Health potion")) {
                                        System.out.println("WARNING: Your health is critically low!");
                                        System.out.println(
                                                "Press h to use a health potion, or press enter to continue fighting.");
                                        System.out.print("> ");
                                        String healChoice = scanner.nextLine().trim();

                                        if (healChoice.equals("h")) {
                                            player.heal(10);
                                            System.out.println(
                                                    "You used a health potion! Current health: " + player.getHealth());
                                        } else {
                                            System.out.println("You bravely continue fighting at low health!");
                                        }
                                    } else {
                                        System.out.println("You're critically wounded with no potions left! (Health: "
                                                + player.getHealth() + ")");
                                        System.out.println("You must fight on!");
                                    }
                                }
                            }
                        }
                        if (player.getHealth() > 0) {
                            room.removeEnemy(enemy);
                        } else {
                            System.out.println("You have been defeated by the " + enemy.getName() + ". Game over.");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Invalid input.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            }
        }
    }
}