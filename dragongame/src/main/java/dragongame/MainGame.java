package dragongame;

// MainGame är startklassen för spelet
// Det är här programmet börjar köras
public class MainGame {

    // main-metoden körs automatiskt när programmet startas
	public static void main(String[] args) {

        // Skapar ett nytt GameEngine-objekt
        // GameEngine innehåller själva spellogiken
		GameEngine game = new GameEngine();

        // Startar spelet
		game.playGame();
	}
}
