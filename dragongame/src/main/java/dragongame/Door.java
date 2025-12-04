package dragongame;

public class Door {

	private Room leadsTo;
	private String direction;
	private boolean isLocked = false;

	public Door(Room leadsTo, String direction, boolean isLocked) {
		this.leadsTo = leadsTo;
		this.direction = direction;
		this.isLocked = isLocked;
	}

	public Room getLeadsTo() {
		return leadsTo;
	}

	public String getDirection() {
		return direction;
	}

	public boolean isLocked() {
		return isLocked;
	}
}