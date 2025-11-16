package dragongame;

import java.util.Scanner;

public class Game {
	private Player player;

	public void setUpField() {
		Room entrance = new Room("Entrence",
				"You stand in the castle's main entry hall. Cold wind sweeps through the cracked stone doorway, and flickering torches cast long shadows.");
		Room greatHall = new Room("Great Hall",
				"A massive dining table dominates the room. Melted wax drips from tall candles, and faint echoes of old banquets linger in the air.");
		Room library = new Room("Library",
				"Towering shelves filled with ancient tomes surround you. A faint whisper seems to drift between the pages.");
		Room armory = new Room("Armory",
				"Swords, shields, and ornate armor line the walls. Some pieces look well-polished, as if recently used.");
		Room kitchen = new Room("Kitchen",
				"Iron pots still simmer as if someone left in a hurry. The scent of herbs, smoke, and something slightly burnt hangs in the room.");
		Room throneHall = new Room("Throne Hall",
				"A grand chamber crowned by a dusty throne. The air feels heavy, as though unseen eyes are watching from the shadows.");
		Room watchTower = new Room("Watch Tower",
				"From this high vantage point, you can see the surrounding lands. The wind howls, carrying distant sounds of wildlife and rustling leaves.");
		Room highTower = new Room("High Tower",
				"The highest point of the castle offers a panoramic view. The sky stretches endlessly, and the world below seems both distant and inviting.");
		Room prisonChambers = new Room("Prison Chambers",
				"Dark, damp cells line the walls. The air is thick with the scent of mildew and despair, and faint echoes of past prisoners seem to linger.");
		Room sorceryChamber = new Room("Sorcery Chamber",
				"Arcane symbols are etched into the stone floor, glowing faintly. Shelves filled with mystical artifacts and ancient scrolls surround you.");
		Room catacombs = new Room("Catacombs",
				"Endless tunnels stretch into darkness, lined with skulls and bones. The air is cold, and every sound echoes eerily. You see a faint light in the distance.");

		Door entranceToGreatHall = new Door(greatHall, "east");
		Door greatHallToEntrance = new Door(entrance, "west");
		Door entranceToLibrary = new Door(library, "south");
		Door libraryToEntrance = new Door(entrance, "north");
		Door entrenceToWatchTower = new Door(watchTower, "up");
		Door watchTowertoEntrance = new Door(entrance, "down");
		Door greatHallToArmory = new Door(armory, "south");
		Door armoryToGreatHall = new Door(greatHall, "north");
		Door greatHalltoKitchen = new Door(kitchen, "north");
		Door kitchenToGreatHall = new Door(greatHall, "south");
		Door armoryToLibrary = new Door(library, "west");
		Door libraryToArmory = new Door(armory, "east");
		Door armoryToHighTower = new Door(highTower, "up");
		Door highTowerToArmory = new Door(armory, "down");
		Door kitchenToThroneHall = new Door(throneHall, "east");
		Door throneHallToKitchen = new Door(kitchen, "west");
		Door throneHallToCatacombs = new Door(catacombs, "down");
		Door catacombsToThroneHall = new Door(throneHall, "up");
		Door armoryToCatacombs = new Door(catacombs, "down");
		Door catacombsToArmory = new Door(library, "up");
		Door sorceryChamberToPrisonChambers = new Door(prisonChambers, "north");
		Door prisonChambersToSorceryChamber = new Door(sorceryChamber, "south");
		Door prisonChambersToCatacombs = new Door(catacombs, "east");
		Door catacombsToPrisonChambers = new Door(prisonChambers, "west");

		entrance.setExit("öster", entranceToGreatHall);

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