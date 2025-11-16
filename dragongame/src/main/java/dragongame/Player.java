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

	public String getName() {
		return name;
	}

	public void move(String direction) {
		Room next = currentRoom.getNextRoom(direction);

		if (next != null) {
			currentRoom = next;
			System.out.println("You are walking " + direction + " to " + currentRoom.getName() + ".");
			currentRoom.roomNarrative();
		} else {
			System.out.println("There is no door in that direction");
		}
	}
}