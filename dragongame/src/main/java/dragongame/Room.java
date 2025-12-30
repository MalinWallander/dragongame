package dragongame;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
	private String name;
	private String description;
	private Map<String, Door> exits = new HashMap<>();
	private List<Item> items = new ArrayList<>();
	private List<Enemy> enemies = new ArrayList<>();
	

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


	public Door getLockedDoor() {
    for (Door door : exits.values()) {
        if (door.isLocked()) {
            return door;
        }
    }
    return null;
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

	public void addItem(Item item) {
		items.add(item);
	}

	public List<Item> getItems() {
		return items;
	}

	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}

	public List<Enemy> getEnemy() {
		return enemies;
	}
}