package dragongame;

import java.util.Map;
import java.util.Scanner;

public class GameEngine {

    private Player player;

    public GameEngine() {
        GameWorld world = new GameWorld();
        world.setUpField();
        this.player = new Player(world.getStartingRoom(), "Adventurer");
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

    player.getCurrentRoom().roomNarrative();
    checkRoomForItems(scanner);

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
            System.out.println("You see the following items:");
            for (int i = 0; i < room.getItems().size(); i++) {
                System.out.println(i + ": " + room.getItems().get(i).getName());
            }
            System.out.println("Type the number to pick up an item, or press enter to skip:");
           String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                try {
                    int choice = Integer.parseInt(input);
                    if (choice >= 0 && choice < room.getItems().size()) {
                        Item picked = room.getItems().remove(choice);
                        player.addItem(picked);
                    } else System.out.println("Invalid choice.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            }
        }
    }
    } 