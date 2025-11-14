package dragongame;

import java.util.Scanner;

public class Game {
	private Player player;

	public void setUpField() {
		Room entrance = new Room("Hall", "Du är i en stor hall med höga tak.");
		Room greatHall = new Room("Kök", "Du är i ett kök fullt av dofter.");
		Room library = new Room("Trädgård", "Du är i en vacker trädgård med blommor.");

		Door entranceToGreatHall = new Door(greatHall, "öster");
		Door entranceToLibrary = new Door(library, "söder");
		Door door2 = new Door(library, "söder");
		Door door3 = new Door(entrance, "väster");
		Door door4 = new Door(entrance, "norr");

		entrance.setExit("öster", entranceToGreatHall);
		entrance.setExit("söder", door2);
		greatHall.setExit("väster", door3);
		library.setExit("norr", door4);

		player = new Player(entrance, "Äventyrare");

	}

	public void playGame() {
		Scanner scanner = new Scanner(System.in);
		String command;
		boolean playing = true;

		System.out.println("Välkommen till Dragon Game!");
		player.getCurrentRoom().roomNarrative();

		while (playing) {
			System.out.print("> ");
			command = scanner.nextLine().trim().toLowerCase();

			if (command.equals("sluta")) {
				System.out.println("Tack för att du spelade!");
				break;
			} else if (command.startsWith("gå ")) {
				String direction = command.substring(4);
				player.move(direction);
			} else {
				System.out.println("Okänt kommando.");
			}
		}

		scanner.close();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setUpField();
		game.playGame();
	}
}