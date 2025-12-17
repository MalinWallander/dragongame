package dragongame;

import java.util.Map;
import java.util.Scanner;

public class GameEngine {
    // Spelare
    private Player player;

    // Konstruktor som skapar spelmotorn och initierar spelvärlden och spelaren
    public GameEngine() {
        GameWorld world = new GameWorld();
        world.setUpField();
        this.player = new Player(world.getStartingRoom(), "Hero");
    }

    // Alias → fullständiga riktningar
    private static final Map<String, String> directionAliases = Map.ofEntries(
            Map.entry("n", "north"),
            Map.entry("s", "south"),
            Map.entry("e", "east"),
            Map.entry("w", "west"),
            Map.entry("u", "up"),
            Map.entry("d", "down"),
            Map.entry("f", "forward"),
            Map.entry("enter", "forward"));

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

    // Huvudspel-loop
    public void playGame() {
        // Skapa scanner för inmatning
        Scanner scanner = new Scanner(System.in);
        // Fråga spelarens namn
        System.out.println("What is your name, adventurer? ");
        String name = scanner.nextLine().trim();
        // Sätt spelarens namn
        if (name.isEmpty()) {
            name = "Adventurer"; // fallback
        }
        player.setName(name);
        // Välkomstmeddelande
        System.out.println("Welcome to the dragongame adventure, " + name + "!");
        // Spelvariabler
        String command;
        boolean playing = true;

        // Introduktion
        player.getCurrentRoom().roomNarrative();
        // Spelloop
        while (playing) {
            // Vänta på spelarens kommando
            System.out.print("> ");
            // Läs in och bearbeta kommandot
            command = scanner.nextLine().trim().toLowerCase();
            // Kontrollera om spelaren har nått slutrummet
            if (player.getCurrentRoom().getName().equals("Exit")) {
                playing = false;
                continue;
            }
            // Hantera kommandon
            if (command.equals("stop")) {
                System.out.println("Thank you for playing!");
                playing = false;
            } else {
                String direction = parseDirection(command);
                if (direction != null) {
                    player.move(direction);
                } else {
                    System.out.println("I don't understand that command.");
                }
            }
        }
        // Stäng scanner
        scanner.close();
    }

}
