package dragongame;

import java.util.Map;
import java.util.Scanner;

public class Game {

    private Player player;

    // Alias → fullständiga riktningar
    private static final Map<String, String> directionAliases = Map.ofEntries(
        Map.entry("n", "north"),
        Map.entry("s", "south"),
        Map.entry("e", "east"),
        Map.entry("w", "west"),
        Map.entry("u", "up"),
        Map.entry("d", "down"),
        Map.entry("f", "forward"),
        Map.entry("enter", "forward")
    );

    // Översätter spelarkommandon → fullständig riktning
    private String parseDirection(String input) {

    // Om spelaren skriver exakt riktningen ("east")
        for (String fullDir : directionAliases.values()) {
            if (input.equals(fullDir)) {
                return fullDir;
            }
        }

    // Om spelaren skriver kort ("e", "ea", "eas")
        for (Map.Entry<String, String> entry : directionAliases.entrySet()) {
            if (input.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }

        return null; // Ogiltigt kommando
    }

    public void setUpField() {
        Room entrance = new Room("Entrance",
            "You stand in the castle's main entry hall. Cold wind sweeps through the cracked stone doorway...");
        Room greatHall = new Room("Great Hall",
            "A massive dining table dominates the room...");
        Room library = new Room("Library",
            "Towering shelves filled with ancient tomes surround you...");
        Room armory = new Room("Armory",
            "Swords, shields, and ornate armor line the walls...");
        Room kitchen = new Room("Kitchen",
            "Iron pots still simmer as if someone left in a hurry...");
        Room throneHall = new Room("Throne Hall",
            "A grand chamber crowned by a dusty throne...");
        Room watchTower = new Room("Watch Tower",
            "From this high vantage point, you can see the surrounding lands...");
        Room highTower = new Room("High Tower",
            "The highest point of the castle offers a panoramic view...");
        Room prisonChambers = new Room("Prison Chambers",
            "Dark, damp cells line the walls...");
        Room sorceryChamber = new Room("Sorcery Chamber",
            "Arcane symbols are etched into the stone floor...");
        Room catacombs = new Room("Catacombs",
            "Endless tunnels stretch into darkness...");
        Room exit = new Room("Exit",
            "You have found the exit! Sunlight pours in...");
        Room start = new Room("Start",
            "Welcome to the dragon adventure game!...");

        // Doors
        entrance.setExit("east", new Door(greatHall, "east"));
        greatHall.setExit("west", new Door(entrance, "west"));
        entrance.setExit("south", new Door(library, "south"));
        library.setExit("north", new Door(entrance, "north"));
        entrance.setExit("up", new Door(watchTower, "up"));
        watchTower.setExit("down", new Door(entrance, "down"));
        greatHall.setExit("south", new Door(armory, "south"));
        armory.setExit("north", new Door(greatHall, "north"));
        greatHall.setExit("north", new Door(kitchen, "north"));
        kitchen.setExit("south", new Door(greatHall, "south"));
        armory.setExit("west", new Door(library, "west"));
        library.setExit("east", new Door(armory, "east"));
        armory.setExit("up", new Door(highTower, "up"));
        highTower.setExit("down", new Door(armory, "down"));
        kitchen.setExit("east", new Door(throneHall, "east"));
        throneHall.setExit("west", new Door(kitchen, "west"));
        throneHall.setExit("down", new Door(catacombs, "down"));
        catacombs.setExit("up", new Door(throneHall, "up"));
        armory.setExit("down", new Door(catacombs, "down"));
        sorceryChamber.setExit("north", new Door(prisonChambers, "north"));
        prisonChambers.setExit("south", new Door(sorceryChamber, "south"));
        prisonChambers.setExit("east", new Door(catacombs, "east"));
        catacombs.setExit("west", new Door(prisonChambers, "west"));
        start.setExit("forward", new Door(entrance, "forward"));
        sorceryChamber.setExit("west", new Door(exit, "west"));

        player = new Player(entrance, "Adventurer");
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        String command;

        player.getCurrentRoom().roomNarrative();

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("stop")) {
                System.out.println("Thank you for playing!");
                break;
            }

            String direction = parseDirection(command);

            if (direction != null) {
                player.move(direction);
            } else {
                System.out.println("I don't understand that command.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setUpField();
        game.playGame();
    }
}
