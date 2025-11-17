package dragongame;

import java.util.Map;
import java.util.HashMap;

public class Room {
	private String name;
	private String description;
	private Map<String, Door> exits = new HashMap<>();

	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		this.exits = new HashMap<>();
	}

	public void setExit(String direction, Door door) {
		exits.put(direction, door);
	}

	public Door getExit(String direction) {
		return exits.get(direction);
	}

	public Room getNextRoom(String direction) {
		return exits.get(direction).getLeadsTo();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void roomNarrative() {
		System.out.println(description);
	}

}