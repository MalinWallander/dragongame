package dragongame;

public class Potion extends Item{
 public Potion(String name, String description) {
        super(name, description);
    }
    @Override
    public void use(Player player) {
        player.heal(10);
        System.out.println("You used a " + getName() + " and restored 10 health points.");
    }
}
