package dragongame;

// Goblin är en typ av fiende och ärver från Enemy-klassen
public class Goblin extends Enemy {

    // Konstruktor för Goblin
    // Skickar namn, hälsa, attackstyrka och beskrivning till Enemy-konstruktorn
    public Goblin(String name, int health, int attackPower, String description) {
        super(name, health, attackPower, description);
    }

    // Överskuggar attack-metoden från Enemy
    // Bestämmer hur goblinen attackerar spelaren
    @Override
    public void attack(Player player) {
        System.out.println(
            getName() + " hacks you with a dagger, dealing " 
            + getAttackPower() + " damage!"
        );

        // Spelaren tar skada motsvarande goblinens attackstyrka
        player.takeDamage(getAttackPower());
    }

    // Överskuggar takeDamage-metoden från Enemy
    @Override
    public void takeDamage(int damage) {

        // Anropar Enemy:s takeDamage för att minska hälsan
        super.takeDamage(damage);

        // Om goblinen dör efter attacken
        if (!isAlive()) {
            System.out.println(
                "You have defeated the " + getName() 
                + "! Where do you want to go next?"
            );
        }
    }
}

