package dragongame;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Klassen Room representerar ett rum i spelet
public class Room {

    // Namn på rummet
    private String name;

    // Beskrivning av rummet
    private String description;

    // Karta över utgångar: riktning -> dörr
    private Map<String, Door> exits = new HashMap<>();

    // Lista med items som finns i rummet
    private List<Item> items = new ArrayList<>();

    // Lista med fiender som finns i rummet
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

    // Hämtar dörren i en viss riktning
    public Door getExit(String direction) {
        return exits.get(direction);
    }

    // Returnerar nästa rum som dörren leder till om den finns
    public Room getNextRoom(String direction) {
        Door door = exits.get(direction);
        if (door != null) {
            return door.getLeadsTo();
        } else
            return null;
    }

    // Returnerar första låsta dörren i rummet (om någon finns)
    public Door getLockedDoor() {
        for (Door door : exits.values()) {
            if (door.isLocked()) {
                return door;
            }
        }
        return null;
    }

    // Returnerar rummets namn
    public String getName() {
        return name;
    }

    // Returnerar rummets beskrivning
    public String getDescription() {
        return description;
    }

    // Skriver ut rumsbeskrivningen i konsolen
    public void roomNarrative() {
        System.out.println(description);
    }

    // Lägger till ett item i rummet
    public void addItem(Item item) {
        items.add(item);
    }

    // Returnerar alla items i rummet
    public List<Item> getItems() {
        return items;
    }

    // Lägger till en fiende i rummet
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    // Tar bort en fiende från rummet (t.ex. när den dödas)
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    // Returnerar alla fiender i rummet
    public List<Enemy> getEnemy() {
        return enemies;
    }
}
