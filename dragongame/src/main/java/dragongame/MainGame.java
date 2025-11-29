package dragongame;

public class MainGame {
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		GameWorld gameWorld = new GameWorld();
		gameWorld.setUpField();
		game.playGame();
	}
}