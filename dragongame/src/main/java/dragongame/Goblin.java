package dragongame;

public class Goblin extends Enemy {

    public Goblin(String name, int health, int attackPower, String description) {
        super(name, health, attackPower, description);
    }

    @Override
    public void attack(Player player) {
        System.out.println(getName() + " hacks you with a dagger, dealing " + getAttackPower() + " damage!");
        player.takeDamage(getAttackPower());
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive()) {
            System.out.println("You have defeated the " + getName() + "! Where do you want to go next?");
        }
    }
}
