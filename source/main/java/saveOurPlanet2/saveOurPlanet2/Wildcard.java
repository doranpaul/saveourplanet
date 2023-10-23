package saveOurPlanet2;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Wildcard {

	// cointoss patches
	public static final int COINTOSS_WIN_PATCHES = 10;
	public static final int COINTOSS_LOSE_PATCHES = 10;

	// number generator patches
	public static final int NUMBER_GENERATOR_WIN_PATCHES = 40;
	public static final int NUMBER_GENERATOR_LOSE_PATCHES = 10;

//	2 mini games based on a random number generator

	public static void wildCard(Player player) {

		Random gameGenerator = new Random();
		int miniGameSelector;

		// Heades or tails requirements
		Scanner userInput = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		int userHeadsOrTails;
		int bankerHeadsOrTails;
		Random assignBankerHeadsOrTails = new Random();

		// random number guesser requirements
		Random randomNumberGenerator = new Random();
		int bankerRandomNumber;
		Scanner numberGuesserScanner = new Scanner(System.in);
		int userNumberGuess;
		// create a random int and assign to miniGameSelector

		miniGameSelector = gameGenerator.nextInt(2) + 1;

		// switch statement to determine the mini game

		System.out.println("You've landed on a wildcard square!!");
		System.err.println("Loading mini game...");

		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switch (miniGameSelector) {

		// cointoss minigame
		case 1:

			try {
				System.out.println("COINTOSS");

				// user selects their option
				System.out.println("Press 1 for Heads or 2 for Tails");
				userHeadsOrTails = userInput.nextInt();

				// assign the banker a heads or tails
				bankerHeadsOrTails = assignBankerHeadsOrTails.nextInt(2) + 1;

				System.out.println("Type 'flip' to flip a coin, guess correctly and you could win some ozone Patches!");
				sc.next();



					if (userHeadsOrTails == bankerHeadsOrTails && userHeadsOrTails == 1) {
						System.out.println("You guessed Heads");
						System.out.println("Flipping coin");
						System.out.println(".......");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						System.err.println("Its Heads! You Win!! " + COINTOSS_WIN_PATCHES + " ozone Patches");

						// call to add ozone patches
						// addozonepatches()
						// COINTOSS_WIN_PATCHES

					} else if (userHeadsOrTails == bankerHeadsOrTails && userHeadsOrTails == 2) {
						System.out.println("You guessed Tails");
						System.out.println("Flipping coin");
						System.out.println(".......");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						System.err.println("Its Tails! You Win!! " + COINTOSS_WIN_PATCHES + " ozone Patches");

						// call to add ozone patches
						// addozonepatches()
						// COINTOSS_WIN_PATCHES

					} else if (userHeadsOrTails != bankerHeadsOrTails && userHeadsOrTails == 1) {
						System.out.println("You guessed Heads");
						System.out.println("Flipping coin");
						System.out.println(".......");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						System.err.println("It's Tails :( \n  You Lose " + COINTOSS_LOSE_PATCHES + " ozone Patches");

						// call to takeaway ozone patches
						// deduct
						// COINTOSS_LOSE_PATCHES

					} else if (userHeadsOrTails != bankerHeadsOrTails && userHeadsOrTails == 2) {
						System.out.println("You guessed Tails");
						System.out.println("Flipping coin");
						System.out.println(".......");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
						System.err.println("It's Heads :( \n  You Lose " + COINTOSS_LOSE_PATCHES + " ozone Patches");

						// call to takeaway ozone patches
						// deduct
						// COINTOSS_LOSE_PATCHES

					}
				
			} catch (InputMismatchException exception) {
				System.out.println("Sorry! Make sure to type either '1' or '2'! Better luck next time");

			}

			break;

		// number guesser minigame
		case 2:

			try {
				System.out.println("NUMBER GUESSER");

				System.out.println("Guess a number between 1 and 10 correctly for a chance to win ozone patches!");

				// get user guess and assign banker random number
				userNumberGuess = numberGuesserScanner.nextInt();
				bankerRandomNumber = randomNumberGenerator.nextInt(10) + 1;

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (userNumberGuess == bankerRandomNumber) {
					System.out.println("You guessed Correctly!!");
					System.out.println("The number was : " + bankerRandomNumber);
					System.err.println("You win " + NUMBER_GENERATOR_WIN_PATCHES + " patches");

					// call to add ozone patches
					// addozonepatches()
					// NUMBER_GENERATOR_WIN_PATCHES

				} else if (userNumberGuess != bankerRandomNumber) {
					System.out.println("You guessed incorrectly :(");
					System.out.println("The number was : " + bankerRandomNumber);
					System.err.println("You lose " + NUMBER_GENERATOR_LOSE_PATCHES + " ozone patches");

					// call to takeaway ozone patches
					// deduct
					// NUMBER_GENERATOR_LOSE_PATCHES

				} else if (userNumberGuess < 1 && userNumberGuess > 10) {
					throw new IllegalArgumentException("Oops must be a number between 1 and 10! Better luck next time");
				}

			} catch (InputMismatchException exception) {
				System.out.println("Oops must be a number between 1 and 10! Better luck next time!");

			} catch (IllegalArgumentException illegalarguementException) {
				System.out.println(illegalarguementException.getMessage());
			}

			break;
		}
	}
}
