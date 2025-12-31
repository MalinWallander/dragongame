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
        }

        scanner.close();
    }

    // Check the current room for items and allow the player to pick them up
    private void checkRoomForItems(Scanner scanner) {
        Room room = player.getCurrentRoom();
        if (!room.getItems().isEmpty()) {
            for (int i = 0; i < room.getItems().size(); i++) {
                System.out.println("You see " + room.getItems().get(i).getDescription() + " Type p to pick it up.");
            }
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    if (input.equals("p")) {
                        Item picked = room.getItems().remove(0);
                        player.addItem(picked);
                    } else
                        System.out.println("Invalid input.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    private void checkRoomForEnemies(Scanner scanner) {
        Room room = player.getCurrentRoom();
        if (!room.getEnemy().isEmpty()) {
            for (int i = 0; i < room.getEnemy().size(); i++) {
                System.out.println("You encouunter " + room.getEnemy().get(i).getDescription()
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
                            }
                        }
                        if (player.getHealth() > 0) {
                            System.out.println("You have defeated the " + enemy.getName() + "!");
                            room.removeEnemy(enemy);
                            System.out.println("Your current health: " + player.getHealth()
                                    + " Maybe you want to heal yourself? Type h to use a health potion.");
                            System.out.print("> ");
                            String healInput = scanner.nextLine().trim();
                            if (healInput.equals("h") && player.hasItem("Health potion")) {
                                player.heal(10);
                            } else if (healInput.equals("h")) {
                                System.out.println("You don't have a health potion to use. Go explore and find one!");
                            } else {
                                System.out.println("You chose not to heal. Where do you want to go next?");
                            }
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