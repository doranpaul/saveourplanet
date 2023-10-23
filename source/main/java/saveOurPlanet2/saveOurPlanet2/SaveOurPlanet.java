package saveOurPlanet2;


/**
 * Class with main method that begins our game, handles registering players,
 * rolling dice and starting game
 * 
 * @author Paul
 *
 */

public class SaveOurPlanet {
//	 instance vars

	static int numPlayers;


	/**
	 * Where our game begins, the main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Setup setup = new Setup();
		
		setup.SaveOurPlanet(numPlayers);
		setup.play();
		
	}
}
