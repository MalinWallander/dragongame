package dragongame;

public class Sword extends Item {
    public Sword(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        player.setAttackPower(2);
    }
}
