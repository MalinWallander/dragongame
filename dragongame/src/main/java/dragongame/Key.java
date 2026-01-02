package dragongame;

public class Key extends Item {

    public Key(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        Door lockedDoor = player.getCurrentRoom().getLockedDoor();

        if (lockedDoor == null) {
            System.out.println("There is no locked door here.");
            return;
        }

        lockedDoor.unlock();
        player.removeItem(this);
        System.out.println("You unlocked the door with the key and can now try to walk in this direction!");
    }
}
