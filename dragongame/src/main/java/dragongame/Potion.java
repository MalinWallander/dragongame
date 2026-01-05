package dragongame;

// Potion är ett föremål som kan användas för att hela spelaren
public class Potion extends Item {

    // Konstruktor för Potion
    // Skickar namn och beskrivning vidare till Item-konstruktorn
    public Potion(String name, String description) {
        super(name, description);
    }

    // Överskuggar use-metoden från Item
    // När spelaren använder potionen återfår den hälsa
    @Override
    public void use(Player player) {

        // Helar spelaren med 10 hälsopoäng
        player.heal(10);
    }
}

