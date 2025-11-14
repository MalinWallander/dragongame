package dragongame;

public class Player {
	private Room currentRoom;
	private String name;

	public Player(Room startingRoom, String name) {
		this.currentRoom = startingRoom;
		this.name = name;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void move(String direction) {
		Room next = currentRoom.getNextRoom(direction);

		if (next != null) {
			currentRoom = next;
			System.out.println("Du går " + direction + " till " + currentRoom.getName() + ".");
			currentRoom.roomNarrative();
		} else {
			System.out.println("Det finns ingen utgång i den riktningen.");
		}
	}
}