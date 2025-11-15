package dragongame;

import java.util.Scanner;

public class Game {
	private Player player;

	public void setUpField() {
		Room entrance = new Room("Entrence", "You stand in the castle's main entry hall. Cold wind sweeps through the cracked stone doorway, and flickering torches cast long shadows.");
		Room greatHall = new Room("Great Hall", "A massive dining table dominates the room. Melted wax drips from tall candles, and faint echoes of old banquets linger in the air.");
		Room library = new Room("Library", "Towering shelves filled with ancient tomes surround you. A faint whisper seems to drift between the pages.");
		Room armory = new Room("Armory", "Swords, shields, and ornate armor line the walls. Some pieces look well-polished, as if recently used."); 
	    Room kitchen = new Room ("Kitchen", "Iron pots still simmer as if someone left in a hurry. The scent of herbs, smoke, and something slightly burnt hangs in the room." ); 
		Room throneHall = new Room ("Throne Hall", "A grand chamber crowned by a dusty throne. The air feels heavy, as though unseen eyes are watching from the shadows."
);
 

		Door entranceToGreatHall = new Door(greatHall, "öster");
		Door entranceToLibrary = new Door(library, "söder");
		Door entrenceToArmory = new Door(armory, "öster");
		Door entrenceToArmory = new Door(armory, "south");
		Door door4 = new Door(entrance, "norr");

		entrance.setExit("öster", entranceToGreatHall);
		entrance.setExit("söder", door2);
		greatHall.setExit("väster", door3);
		library.setExit("norr", door4);

		player = new Player(entrance, "Äventyrare");

	}

	public void playGame() {
		Scanner scanner = new Scanner(System.in);
		String command;
		boolean playing = true;

		System.out.println("Välkommen till Dragon Game!");
		player.getCurrentRoom().roomNarrative();

		while (playing) {
			System.out.print("> ");
			command = scanner.nextLine().trim().toLowerCase();

			if (command.equals("sluta")) {
				System.out.println("Tack för att du spelade!");
				break;
			} else if (command.startsWith("gå ")) {
				String direction = command.substring(4);
				player.move(direction);
			} else {
				System.out.println("Okänt kommando.");
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