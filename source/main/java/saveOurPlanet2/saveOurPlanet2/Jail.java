/**
 * 
 */
package saveOurPlanet2;

import java.util.Scanner;

/**
 * @author Jack
 *
 */

public class Jail {

	public static final int JAIL_AMOUNT = 30;

	// jail squares to be stored in the `Board` class, this will be the actions
	// performed when landing on `Jail`. If a player lands in jail, then the
	// `jailStatus` boolean is set to true.

	public static void landJail(Player player) {

		String userChoice;

		Scanner scanner = new Scanner(System.in);
		// player lands, has option to pay fine, and be out of jail
		System.out.println("Uh oh, in Jail! Pay " + JAIL_AMOUNT + " Ozone Patches to be released and end your turn.");
		System.out.println("If you do not pay, you will remain in jail with no way out! Slowly letting your Ozone Patches deplete!");
		System.out.println("Do you want to pay the fine?? Type `Yes` or `No` to continue.");

		userChoice = scanner.nextLine();

		if (player.getozonePatches() > JAIL_AMOUNT) {
			if (userChoice.equalsIgnoreCase("YES")) {

				// Pay fine and exit Jail
				System.out.println(player.getName() + " has paid the fine of " + JAIL_AMOUNT
						+ " and has been released from jail!");
				player.pay(JAIL_AMOUNT);
				Player.displayOzonePatches(player);
				player.setJailStatus(false);

				return;

			} else if (userChoice.equalsIgnoreCase("NO")) {

				System.out.println("You are still in Jail, better pay that fine soon!");
				System.out.println("Your Ozone patches have depleted by " + JAIL_AMOUNT/3 + " patches.");
				player.pay(JAIL_AMOUNT/3);
				Player.displayOzonePatches(player);
				return;

			}
			
		}

	}
}
