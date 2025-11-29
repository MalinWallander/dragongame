package dragongame;

import java.util.Map;
import java.util.Scanner;

public class GameEngine {

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

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean playing = true;

        player.getCurrentRoom().roomNarrative();

        while (playing) {
            System.out.print("> ");
            command = scanner.nextLine().trim().toLowerCase();
            if (player.getCurrentRoom().getName().equals("Exit")) {
                playing = false;
                continue;
            }

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

        scanner.close();
    }

}
