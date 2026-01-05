package dragongame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Klassen Player representerar spelaren i spelet
public class Player {

    private Room currentRoom;

    private String name;

    private List<Item> inventory = new ArrayList<>();

    private Integer health;

    private Integer attackPower;

    // Konstruktor som skapar en spelare med ett start-rum, namn och hälsa
    public Player(Room startingRoom, String name, Integer health) {
        this.currentRoom = startingRoom;
        this.name = name;
        this.health = health;
        this.attackPower = 1; // Standard attack power utan vapen
        this.inventory = new ArrayList<>();
    }

    // Returnerar rummet spelaren befinner sig i
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Returnerar nuvarande rum (används ev. för låsta rum)
    public Room getLockedRoom() {
        return currentRoom;
    }

    // Returnerar spelarens namn
    public String getName() {
        return name;
    }

    // Returnerar spelarens hälsa
    public Integer getHealth() {
        return health;
    }

    // Returnerar spelarens attackstyrka
    public Integer getAttackPower() {
        return attackPower;
    }

    // Sätter spelarens attackstyrka (t.ex. när man plockar upp ett svärd)
    public void setAttackPower(Integer attackPower) {
        this.attackPower = attackPower;
    }

    // Lägger till ett item i spelarens inventory
    public void addItem(Item item) {
        inventory.add(item);
    }

    // Tar bort ett item från inventory baserat på namn
    public void removeItem(String itemName) {
        inventory.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    // Kontrollerar om spelaren har ett visst item
    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    // Visar spelarens inventory i konsolen
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

    // Försöker flytta spelaren i en viss riktning
    public void move(String direction, Scanner scanner) {

        // Hämtar dörren och nästa rum i vald riktning
        Door door = currentRoom.getExit(direction);
        Room next = currentRoom.getNextRoom(direction);

        // Om det inte finns något rum i den riktningen
        if (next == null) {
            System.out.println("There is no door in that direction");
            return;
        }

        // Om dörren inte är låst
        if (!door.isLocked()) {

            // Flyttar spelaren till nästa rum
            currentRoom = next;
            System.out.println("You are walking " + direction + " to " + currentRoom.getName() + ".");
            currentRoom.roomNarrative();

        } else {
            // Dörren är låst
            System.out.println("This door is locked. Let's see if your inventory has a key to unlock it.");
            showInventory();

            // Om spelaren inte har någon nyckel
            if (!hasItem("Key")) {
                System.out.println(
                        "You don't have any key in your inventory. Explore the rooms to find a key. Where do you want to go?");
                return;

            // Om spelaren har en nyckel
            } else {
                System.out.println(
                        "You have a key in your inventory. Press o to use a key to unlock the door, or any other key to not use it.");
                System.out.print("> ");

                String input = scanner.nextLine().trim();

                // Spelaren väljer att använda nyckeln
                if (!input.isEmpty() && input.equals("o")) {
                    System.out.println("You chose to use a key to unlock the door.");
                    door.unlock();

                    // Försöker gå igen efter att dörren låsts upp
                    move(direction, scanner);

                } else {
                    // Spelaren väljer att inte använda nyckeln
                    System.out.println("You chose not to use a key. Where do you want to go next?");
                    return;
                }
            }
        }
    }

    // Ändrar spelarens namn
    public void setName(String name) {
        this.name = name;
    }

    // Helar spelaren och tar bort en health potion från inventory
    public void heal(int amount) {
        this.health += amount;
        System.out.println("You have been healed by " + amount + " points. Current health: " + this.health);
        removeItem("Health potion");
    }

    // Spelaren tar skada
    public void takeDamage(int damage) {
        this.health -= damage;

        // Ser till att hälsan inte går under 0
        if (this.health < 0) {
            this.health = 0;
        }

        System.out.println("You took " + damage + " damage. Current health: " + this.health);
    }

    // Spelaren attackerar en fiende
    public void attack(Enemy enemy) {
        System.out.println(
                "You attack the " + enemy.getName() + " and deal " + attackPower + " damage!"
        );

        // Skadar fienden baserat på spelarens attackstyrka
        enemy.takeDamage(attackPower);

        // Om fienden dör tas den bort från rummet
        if (enemy.getHealth() <= 0) {
            currentRoom.removeEnemy(enemy);
        } else {
            System.out.println(
                    "The " + enemy.getName() + " has " + enemy.getHealth() + " health left."
            );
        }
    }
}

