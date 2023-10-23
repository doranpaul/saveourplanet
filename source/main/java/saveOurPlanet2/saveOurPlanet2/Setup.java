/**
 * 
 */
package saveOurPlanet2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author pauld the setup class provides the methods for the SaveOurPlanet
 *         class to set up each game and also provides the game play aspect for
 *         the game in the "play()" method.
 *
 */
public class Setup {

//	constants
	private static final int STARTING_OZONE_PATCHES = 100;

//	 instance vars
	private int currentPlayerIndex = 0;
	static int numPlayers;
	private static ArrayList<Player> playersList = new ArrayList<>();
	private static SortedMap<Integer, String> orderRolls = new TreeMap<>();
	private Board board;
	private static boolean gameOver = false;

	/**
	 * Registers an amount of players chosen by the user by creating player objects.
	 * Creates board
	 * 
	 * @param numPlayers - int, number of players to register
	 * @return
	 */
	public void SaveOurPlanet(int numPlayers) {

		Scanner scanner = new Scanner(System.in);

		// ensures that the number of players is within range, re-prompts if not
		do {

			System.out.println("Enter number of players (2-4):");
			numPlayers = scanner.nextInt();

			if (numPlayers < 2 || numPlayers > 4) {
				System.out.println("Invalid number of players. Please enter a number between 2 and 4.");
			}

		} while (numPlayers < 2 || numPlayers > 4);
		System.out.println("Okay! Lets name " + numPlayers + " players and decide their order! ");

		// loop for adding player names
		Setup.numPlayers = numPlayers;

		// populates a sorted map of player rolls against names
		addToOrderMap();

		// creates the players in the correct order according to roll value
		// NOTE - Duplicates still need checked!
		createPlayers();

		System.out.println("Starting game for " + numPlayers + " players!");
		System.out.println("\n\n\n\n");

		try {
			Thread.currentThread();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.board = new Board();
	}

	/**
	 * Begins the dice rolling procedure after players have been registered, handles
	 * bankruptcy
	 */
	public void play() {
		System.out.println("Welcome to the Save Our Planet Game!");
		System.out.println();
		Scanner scanner = new Scanner(System.in);

		while (!gameOver) {
			Player currentPlayer = playersList.get(this.currentPlayerIndex);
			System.out.println("\n\nIt's " + currentPlayer.getName() + "'s turn.");
			System.out.println("Hit Enter to roll the dice!");

			String input = scanner.nextLine();
			if (input.equals(input)) {
				int roll = Dice.roll();
				System.out.println(currentPlayer.getName() + " rolled a " + roll + ".");
				currentPlayer.move(roll, board);

				if (currentPlayer.getozonePatches() <= 0) {
					removeBankruptPlayers();
				}
				if (playersList.size() == 1) {
					Player winner = playersList.get(0);
					System.out.println(winner.getName() + " is the winner!!! GAME OVER!!");
					gameOver = true;
				} else {
					nextPlayer();

				}
			}
		}
	}

	/**
	 * Sets the next player in the dice rolling process
	 */
	private void nextPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
	}

	/**
	 * Adds players to map based on their dice roll
	 */
	public void addToOrderMap() {

		// creating sets to avoid duplicates (no duplicates in a set)
		Set<String> playerNames = new HashSet<>();
		Set<Integer> rolls = new HashSet<>();

		//
		for (int loop = 1; loop <= numPlayers; loop++) {

			String playerName;
			int roll;
			System.out.println("Enter player " + loop + " name :");
			Scanner sc = new Scanner(System.in);

			// this re-prompts the player if a duplicate name is entered
			while (true) {
				playerName = sc.nextLine();
				if (playerNames.contains(playerName)) {
					System.out.println("That name is already taken. Please enter a different name:");
				} else {
					playerNames.add(playerName);
					break;
				}
			}

			System.out.println("Rolling to determine playing order...");
			try {
				Thread.currentThread();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// this automatically (and silently) rolls the dice again if a duplicate is
			// found, thus preventing them from occurring.
			do {

				roll = Dice.roll();

			} while (rolls.contains(roll));

			rolls.add(roll);
			System.out.println(playerName + " rolled a " + roll + "!\n");
			orderRolls.put(roll, playerName);
		}

	}

	/*
	 * This method will iterate through the playersList, and check for bankrupt
	 * players, leading to removal from this array
	 */
	public void removeBankruptPlayers() {

		Iterator<Player> iterator = playersList.iterator();
		while (iterator.hasNext()) {
			Player player = iterator.next();
			if (player.getozonePatches() <= 0) {
				System.out.println(player.getName() + " has no more Ozone Patches left and has gone bankrupt!");
				iterator.remove();
				numPlayers--;
			}
		}
	}

	/**
	 * Creates player objects and adds them to arraylist based on their roll.
	 */
	public void createPlayers() {

		for (int loop = 1; loop <= numPlayers; loop++) {

			// the name to be added to the player
			String name;
			// assigns the name to the highest roll in the map
			name = orderRolls.get(orderRolls.lastKey());
			// removes highest player roll from map
			orderRolls.remove(orderRolls.lastKey());

			int counter;
			// assigning the player counter/turn number before instantiating
			counter = loop;

			Player player = new Player(name, STARTING_OZONE_PATCHES, 0, counter, gameOver);
			playersList.add(player);

		}

		try {
			Thread.currentThread();
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}