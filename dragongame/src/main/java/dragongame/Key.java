package dragongame;

// Key är ett föremål (Item) som kan användas för att låsa upp dörrar
public class Key extends Item {

    // Konstruktor för Key
    // Skickar namn och beskrivning vidare till Item-konstruktorn
    public Key(String name, String description) {
        super(name, description);
    }

    // Överskuggar use-metoden från Item
    // Används när spelaren försöker använda nyckeln
    @Override
    public void use(Player player) {

        // Hämtar den låsta dörren i spelarens nuvarande rum (om det finns någon)
        Door lockedDoor = player.getCurrentRoom().getLockedDoor();

        // Om det inte finns någon låst dörr i rummet
        if (lockedDoor == null) {
            System.out.println("There is no locked door here.");
            return;
        }

        // Låser upp dörren
        lockedDoor.unlock();

        // Tar bort nyckeln från spelarens inventory efter användning
        player.removeItem(this.getName());

        // Meddelar spelaren att dörren nu är upplåst
        System.out.println(
            "You unlocked the door with the key and can now try to walk in this direction!"
        );
    }
}

