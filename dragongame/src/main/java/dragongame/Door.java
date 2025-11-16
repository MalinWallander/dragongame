package dragongame;

public class Door {

	private Room leadsTo;
	private String direction;

	public Door(Room leadsTo, String direction) {
		this.leadsTo = leadsTo;
		this.direction = direction;
	}

	public Room getLeadsTo() {
		return leadsTo;
	}

	public String getDirection() {
		return direction;
	}
}