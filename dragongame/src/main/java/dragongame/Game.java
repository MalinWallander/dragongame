package dragongame;

import java.util.Scanner;

public class Game {
	private Player player;

	public void setUpField() {
		Room entrance = new Room("Entrance",
				"You stand in the castle's main entry hall. Cold wind sweeps through the cracked stone doorway, and flickering torches cast long shadows. You have a door to the east (e), a passageway to the south (s), and a winding staircase leading up (u). Type where you want to go.");
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
		Door catacombsToArmory = new Door(armory, "up");
		Door sorceryChamberToPrisonChambers = new Door(prisonChambers, "north");
		Door prisonChambersToSorceryChamber = new Door(sorceryChamber, "south");
		Door prisonChambersToCatacombs = new Door(catacombs, "east");
		Door catacombsToPrisonChambers = new Door(prisonChambers, "west");

		entrance.setExit("east", entranceToGreatHall);
		greatHall.setExit("west", greatHallToEntrance);
		entrance.setExit("south", entranceToLibrary);
		library.setExit("north", libraryToEntrance);
		entrance.setExit("up", entrenceToWatchTower);
		watchTower.setExit("down", watchTowertoEntrance);
		greatHall.setExit("south", greatHallToArmory);
		armory.setExit("north", armoryToGreatHall);
		greatHall.setExit("north", greatHalltoKitchen);
		kitchen.setExit("south", kitchenToGreatHall);
		armory.setExit("west", armoryToLibrary);
		library.setExit("east", libraryToArmory);
		armory.setExit("up", armoryToHighTower);
		highTower.setExit("down", highTowerToArmory);
		kitchen.setExit("east", kitchenToThroneHall);
		throneHall.setExit("west", throneHallToKitchen);
		throneHall.setExit("down", throneHallToCatacombs);
		catacombs.setExit("west", catacombsToArmory);
		catacombs.setExit("north", catacombsToThroneHall);
		armory.setExit("down", armoryToCatacombs);
		sorceryChamber.setExit("north", sorceryChamberToPrisonChambers);
		prisonChambers.setExit("south", prisonChambersToSorceryChamber);
		prisonChambers.setExit("west", prisonChambersToCatacombs);
		catacombs.setExit("east", catacombsToPrisonChambers);

		player = new Player(entrance, "Adventurer");

	}

	public void playGame() {
		Scanner scanner = new Scanner(System.in);
		String command;
		boolean playing = true;

		System.out.println(
				"Welcome to the dragon adventure game! You are standing at the entrance of an old castle, mostly in ruins. Your mission is to find the exit on the other side. If you for any reason want to stop playing, type 'stop'. Good luck!");
		player.getCurrentRoom().roomNarrative();

		while (playing) {
			System.out.print("> ");
			command = scanner.nextLine().trim().toLowerCase();

			if (command.equals("stop")) {
				System.out.println("Thank you for playing!");
				break;
			} else if (command.startsWith(command)) {
				String direction = command;
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