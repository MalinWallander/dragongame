package dragongame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameWorld {

	private List<Room> rooms = new ArrayList<>();
	private Room start;

	private void registerRooms(Room... roomArray) {

		rooms.addAll(Arrays.asList(roomArray));
	}

	public void setUpField() {
		Room entrance = new Room("Entrance",
				"You are standing in the castle's main entry hall. Cold wind sweeps through the cracked stone doorway, and flickering torches cast long shadows. You have a door to the east (e), a passageway to the south (s), and a winding staircase leading up (u). Type where you want to go.");
		Room greatHall = new Room("Great Hall",
				"A massive dining table dominates the room. Melted wax drips from tall candles, and faint echoes of old banquets linger in the air. There are doors in every direction: west (w) to the entrance, north (n) to the kitchen, south (s) to the armory and east (e) to the throne hall. Where do you want to go?");
		Room library = new Room("Library",
				"Towering shelves filled with ancient tomes surround you. A faint whisper seems to drift between the pages. You see a door to the north (n) leading back to the entrance and a door to the east (e) leading to the armory. Where do you want to go?");
		Room armory = new Room("Armory",
				"Swords, shields, and ornate armor line the walls. Some pieces look well-polished, as if recently used. There is a door to the north (n) leading to the great hall, a door to the west (w) leading to the library, a staircase going up (u) to a high tower, and a dark passageway going down (d) to the catacombs. What is your next move?");
		Room kitchen = new Room("Kitchen",
				"Iron pots still simmer as if someone left in a hurry. The scent of herbs, smoke, and something slightly burnt hangs in the room. You see a door to the south (s) leading back to the great hall and a door to the east (e) leading to the throne hall. Type where you want to go");
		Room throneHall = new Room("Throne Hall",
				"A grand chamber crowned by a dusty throne. The air feels heavy, as though unseen eyes are watching from the shadows. There is a door to the west (w) leading to the kitchen and a scary looking staircase going down (d) to the catacombs. Where do you want to go?");
		Room watchTower = new Room("Watch Tower",
				"From this high vantage point, you can see the surrounding lands. The wind howls, carrying distant sounds of wildlife and rustling leaves. A staircase leads back down (d) to the entrance. Are you ready to go down?");
		Room highTower = new Room("High Tower",
				"The highest point of the castle offers a panoramic view. The sky stretches endlessly, and the world below seems both distant and inviting. A staircase leads back down (d) to the armory. Do you want to go down?");
		Room prisonChambers = new Room("Prison Chambers",
				"Dark, damp cells line the walls. The air is thick with the scent of mildew and despair, and faint echoes of past prisoners seem to linger. You see a door to the south (s) leading to the sorcery chamber and a door to the west (w) leading to the catacombs. Where do you want to go?");
		Room sorceryChamber = new Room("Sorcery Chamber",
				"Arcane symbols are etched into the stone floor, glowing faintly. Shelves filled with mystical artifacts and ancient scrolls surround you. A door to the north (n) leads to the prison chambers and a door to the east (e) leads to the exit. Are yo ready to escape the castle or do you want to continue exploring?");
		Room catacombs = new Room("Catacombs",
				"Endless tunnels stretch into darkness, lined with skulls and bones. The air is cold, and every sound echoes eerily. You see a faint light in the distance. There is a staircase going north (n) to the throne hall, a door to the east (e) leading to the prison chambers, and a door to the west (w) leading to the armory. Where do you want to go?");
		Room exit = new Room("Exit",
				"You have found the exit of the castle! Sunlight hits your face, and you can see the open world beyond. Freedom is just a step away. Well played, adventurer! Press enter to conclude your adventure.");
		start = new Room("Start",
				"Welcome to the dragon adventure game! You are standing at the entrance of an old castle, mostly in ruins. Your mission is to find the exit on the other side. Press f or type 'forward' to start. If you for any reason want to stop playing, type 'stop'. Good luck!");

		registerRooms(entrance, greatHall, library, armory, kitchen, throneHall,
				watchTower, highTower, prisonChambers, sorceryChamber, catacombs, exit, start);

		entrance.setExit("east", new Door(greatHall, "east"));
		greatHall.setExit("west", new Door(entrance, "west"));
		entrance.setExit("south", new Door(library, "south"));
		library.setExit("north", new Door(entrance, "north"));
		entrance.setExit("up", new Door(watchTower, "up"));
		watchTower.setExit("down", new Door(entrance, "down"));
		greatHall.setExit("south", new Door(armory, "south"));
		armory.setExit("north", new Door(greatHall, "north"));
		greatHall.setExit("north", new Door(kitchen, "north"));
		greatHall.setExit("east", new Door(throneHall, "east"));
		kitchen.setExit("south", new Door(greatHall, "south"));
		armory.setExit("west", new Door(library, "west"));
		library.setExit("east", new Door(armory, "east"));
		armory.setExit("up", new Door(highTower, "up"));
		highTower.setExit("down", new Door(armory, "down"));
		kitchen.setExit("east", new Door(throneHall, "east"));
		throneHall.setExit("west", new Door(kitchen, "west"));
		throneHall.setExit("down", new Door(catacombs, "down"));
		catacombs.setExit("north", new Door(throneHall, "north"));
		armory.setExit("down", new Door(catacombs, "down"));
		sorceryChamber.setExit("north", new Door(prisonChambers, "north"));
		prisonChambers.setExit("south", new Door(sorceryChamber, "south"));
		prisonChambers.setExit("east", new Door(catacombs, "east"));
		catacombs.setExit("east", new Door(prisonChambers, "east"));
		catacombs.setExit("west", new Door(armory, "west"));
		start.setExit("forward", new Door(entrance, "forward"));
		sorceryChamber.setExit("east", new Door(exit, "east"));
	}

	public Room getStartingRoom() {
		return start;
	}

}
