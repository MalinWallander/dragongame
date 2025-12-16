package dragongame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	private Room currentRoom;
	private String name;
	private List<Item> inventory = new ArrayList<>();

	// Konstruktor som skapar en spelare med ett startrum och ett namn
	public Player(Room startingRoom, String name) {
		this.currentRoom = startingRoom;
		this.name = name;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public Room getLockedRoom() {
		return currentRoom;
	}

	public String getName() {
		return name;
	}


// Lägger till ett item i spelarens inventory
public void addItem(Item item) {
    inventory.add(item);
    System.out.println(item.getName() + " has been added to your inventory.");
}

// Tar bort ett item från spelarens inventory
public void removeItem(Item item) {
    inventory.remove(item);
}

// Visar spelarens inventory
public void showInventory() {
    if (inventory.isEmpty()) {
        System.out.println("Your inventory is empty.");
    } else {
        System.out.println("Your inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(i + ": " + inventory.get(i).getName());
        }
    }
}

	// Försöker flytta spelaren i en given riktning
	public void move(String direction, Scanner scanner) {
    Door door = currentRoom.getExit(direction);
    Room next = currentRoom.getNextRoom(direction);

    if (next == null) {
        System.out.println("There is no door in that direction");
        return;
    }

    if (!door.isLocked()) {
        // Dörren är öppen
        currentRoom = next;
        System.out.println("You are walking " + direction + " to " + currentRoom.getName() + ".");
         currentRoom.roomNarrative();
    } else {
        // Dörren är låst → fråga spelaren efter item
        System.out.println("This door is locked. Choose an item from your inventory to unlock it:");
       chooseItemToUnlock(door, scanner);
    }
}



// metod för att välja item från inventory för att låsa upp dörr

  private void chooseItemToUnlock(Door door, Scanner scanner) {

        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }

        System.out.println("Choose an item to use:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(i + ": " + inventory.get(i).getName());
        }

        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 0 || choice >= inventory.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Item item = inventory.get(choice);
        item.use(this);
    }
}
