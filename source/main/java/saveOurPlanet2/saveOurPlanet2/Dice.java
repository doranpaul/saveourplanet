package saveOurPlanet2;

import java.util.Random;

/**
 * Class that deals with dice, allowing turns to be taken by players.
 * 
 * @author user
 *
 */
public class Dice {
	private static final Random random = new Random();

	/**
	 * Performs a roll for the player, returning a random int between 1 and the
	 * specified dice limit
	 * 
	 * @return
	 */
	public static int roll() {
		// TODO - add a constant to allow for setting of the dice limit, change number
		// of dice to just 1 dice instead of two
		System.out.println(".");
		try {
			Thread.currentThread();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("..");
		int die1 = random.nextInt(6) + 1;
		
		return die1;
	}
}