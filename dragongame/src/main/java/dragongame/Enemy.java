package dragongame;

// Abstrakt klass som fungerar som en mall för alla fiender i spelet
public abstract class Enemy {

	 // Variabler som alla fiender har
	private String name;
	private int health;
	private int attackPower;
	private String description;

// Konstruktor som används när en fiende skapas
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

	 // Metod som minskar fiendens hälsa när den tar skada
	public void takeDamage(int damage) {
		health -= damage;

		 // Ser till att hälsan inte blir mindre än 0
		if (health < 0) {
			health = 0;
		}
}

// Abstrakt metod som måste implementeras i alla subklasser (t.ex. Dragon)
    // Bestämmer hur fienden attackerar spelaren
	public abstract void attack(Player player);
	 

	 public String getDescription() {
        return description;
    }

	public boolean isAlive() {
		return health > 0;
	}
}
