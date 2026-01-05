package dragongame;

// Sword är ett föremål som ökar spelarens attackstyrka
public class Sword extends Item {

    // Konstruktor som skickar namn och beskrivning till Item
    public Sword(String name, String description) {
        super(name, description);
    }

    // Överskuggar use-metoden från Item
    // När spelaren använder svärdet ökas attackstyrkan
    @Override
    public void use(Player player) {
        // Sätter spelarens attackPower till 2 (standard var 1)
        player.setAttackPower(2);
        System.out.println(
            "You wield the sword! Your attack power has increased to " + player.getAttackPower() + "."
        );
    }
}
