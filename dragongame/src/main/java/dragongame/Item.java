package dragongame;

// Abstrakt klass som fungerar som grund för alla föremål i spelet
public abstract class Item {

    // Namn på föremålet
	private String name;

    // Beskrivning av föremålet
	private String description;

    // Konstruktor som skapar ett item med namn och beskrivning
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}

    // Returnerar föremålets namn
	public String getName() {
		return name;
	}

    // Returnerar föremålets beskrivning
	public String getDescription() {
		return description;
	}

    // Metod som används när ett item aktiveras/används
    // Överskuggas i subklasser som Sword, HealthPotion osv.
	public void use(Player player) {
        
	}
}

