package dragongame;

public class Dragon extends Enemy {

    public Dragon(String name, int health, int attackPower, String description) {
        super(name, health, attackPower, description);
    }
    @Override public void attack(Player player) {
      System.out.println(getName() + " breathes fire at you, dealing " + getAttackPower() + " damage!");   player.takeDamage(getAttackPower());
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive()) {
            System.out.println("You have defeated the " + getName() + "!");
        }
    }
}
