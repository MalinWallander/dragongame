package dragongame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private Room currentRoom;
    private String name;
    private List<Item> inventory = new ArrayList<>();
    private Integer health;

    // Konstruktor som skapar en spelare med ett startrum och ett namn
    public Player(Room startingRoom, String name, Integer health) {
        this.currentRoom = startingRoom;
        this.name = name;
        this.health = health;
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

    public Integer getHealth() {
        return health;
    }

    // Lägger till ett item i spelarens inventory
    public void addItem(Item item) {
        inventory.add(item);
        System.out.println(item.getName() + " has been added to your inventory. Which direction do you want to go?");
    }

    // Tar bort ett item från spelarens inventory
    public void removeItem(String itemName) {
        inventory.remove(itemName);
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    // Visar spelarens inventory
    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory:");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println(i + 1 + ": " + inventory.get(i).getName());
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
            System.out.println("This door is locked. Let's see if your inventory has a key to unlock it.");
            showInventory();

            if (!hasItem("Key")) {
                System.out
                        .println(
                                "You don't have any key in your inventory. Explore the rooms to find a key. Where do you want to go?");
                return;
            } else if (hasItem("Key")) {
                System.out.println(
                        "You have a key in your inventory. Press o to use a key to unlock the door, or any other key to not use it.");
                System.out.print("> ");
                String input = scanner.nextLine().trim();
                if (!input.isEmpty() && input.equals("o")) {
                    System.out.println("You chose to use a key to unlock the door.");
                    door.unlock();
                    move(direction, scanner);
                } else {
                    System.out.println("You chose not to use a key. Where do you want to go next?");
                    return;
                }
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void heal(int amount) {
        this.health += amount;
        System.out.println("You have been healed by " + amount + " points. Current health: " + this.health);
        removeItem("Health potion");
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
        System.out.println("You took " + damage + " damage. Current health: " + this.health);
    }

    public void attack(Enemy enemy) {
        System.out.println("You attack the " + enemy.getName() + "!");
        enemy.takeDamage(2); // Fixed damage for simplicity
        if (enemy.getHealth() <= 0) {
            currentRoom.removeEnemy(enemy);
        } else {
            System.out.println("The " + enemy.getName() + " has " + enemy.getHealth() + " health left.");
        }
    }

}
