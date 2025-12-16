package dragongame;

public abstract class Enemy {
	private String name;
	private int health;
	private int attackPower;
	private String description;


	public Enemy(String name, int health, int attackPower, String description) {
    this.name = name;
    this.health = health;
    this.attackPower = attackPower;
    this.description = description;
}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void takeDamage(int damage) {
		health -= damage;
		if (health < 0) {
			health = 0;
		}
	}

	 public String getDescription() {
        return description;
    }

	public boolean isAlive() {
		return health > 0;
	}
}
