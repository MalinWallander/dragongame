package dragongame;

public class Player {
	private Room currentRoom;
	private String name;

	// Konstruktor som skapar en spelare med ett startrum och ett namn
	public Player(Room startingRoom, String name) {
		this.currentRoom = startingRoom;
		this.name = name;
	}

	// Hämtar spelarens nuvarande rum
	public Room getCurrentRoom() {
		return currentRoom;
	}

	// Sätter spelarens namn
	public void setName(String name) {
		this.name = name;
	}

	// Hämtar spelarens namn
	public String getName() {
		return name;
	}

	// Försöker flytta spelaren i en given riktning
	public void move(String direction) {
		Room next = currentRoom.getNextRoom(direction);
		// Kolla om det finns en dörr i den riktningen och om den är låst
		if (next != null && !currentRoom.getExit(direction).isLocked()) {
			currentRoom = next;
			System.out.println("You are walking " + direction + " to " + currentRoom.getName() + ".");
			currentRoom.roomNarrative();
		} else if (next != null && currentRoom.getExit(direction).isLocked()) {
			System.out.println("This door is locked, you need a key. You cannot go " + direction + ".");
		} else {
			System.out.println("There is no door in that direction");
		}
	}
}