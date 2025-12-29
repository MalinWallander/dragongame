package dragongame;

import java.util.Map;
import java.util.HashMap;

public class Room {
	private String name;
	private String description;
	private Map<String, Door> exits = new HashMap<>();

	// Konstruktor som skapar ett rum med namn och beskrivning
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		this.exits = new HashMap<>();
	}

	// Lägger till en utgång i en viss riktning med en specifik dörr
	public void setExit(String direction, Door door) {
		exits.put(direction, door);
	}

	// Hämtar dörren i en viss riktning
	public Door getExit(String direction) {
		return exits.get(direction);
	}

	// Returnerar nästa rum som dörren leder till om en dörr finns i riktningen
	public Room getNextRoom(String direction) {
		Door door = exits.get(direction);
		if (door != null) {
			return door.getLeadsTo();
		} else
			return null;
	}

	// Hämtar rummets namn och beskrivning
	public String getName() {
		return name;
	}

	// Hämtar rummets beskrivning
	public String getDescription() {
		return description;
	}

	// Skriver ut rummets beskrivning
	public void roomNarrative() {
		System.out.println(description);
	}

}