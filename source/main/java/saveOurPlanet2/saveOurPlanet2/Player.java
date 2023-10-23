/**
 * 
 */
package saveOurPlanet2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will set, get and store player information such as name,
 * ozonepatches, position, properties and counter. It holds a print method for a
 * players owned properties.
 * 
 * @author nathan
 *
 */
public class Player {

	private ArrayList<String> properties = new ArrayList<String>();
	private String name;
	private int ozonePatches;
	private int position;
	private int counter = 0;
	private boolean jailStatus;

	/**
	 * This method is getter of player name
	 * 
	 * @return name of player
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method is getter of player Ozone Patches
	 * 
	 * @return money of player
	 */
	public int getozonePatches() {
		return ozonePatches;
	}

	/**
	 * This method is setter of player money
	 */
	public void setozonePatches(int ozonePatches) {
		this.ozonePatches = ozonePatches;
	}

	/**
	 * This method is getter of player position
	 * 
	 * @return position of player
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * This method is setter of player position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * This method is getter of player counter
	 * 
	 * @return jail counter for player
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * This method is setter of player counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isJailStatus() {
		return jailStatus;
	}

	/**
	 * This method is setter of jail status. If set to false nothing occurs, if set
	 * to true then `landJail` from `jail` is called, with the player being passed
	 * in as an argument
	 * 
	 * @param jailStatus
	 */
	public void setJailStatus(boolean jailStatus) {
		if (jailStatus && !this.jailStatus) {
			this.jailStatus = true;
			Jail.landJail(this);
		} else {
			this.jailStatus = jailStatus;
		}
	}

	public boolean getJailStatus() {
		return jailStatus;
	}

	/**
	 * This method is getter of player's properties
	 * 
	 * @return property array list for player which he/she had
	 */
	public ArrayList<String> getProperties() {
		return properties;
	}

	/**
	 * This method is setter of player's properties
	 */
	public void setProperty(String property) {
		properties.add(property);
	}

	/**
	 * This method is constructor of player and initializing Players
	 * 
	 * @param name     name of this player
	 * @param money    money of this player
	 * @param position position of this player
	 */
	public Player(String name, int ozonePatches, int position, int counter, boolean jailStatus) {
		this.name = name;
		this.ozonePatches = ozonePatches;
		this.position = position;
		this.counter = counter;
		this.setJailStatus(jailStatus);
	}

	/*
	 * Method to add ozone patches to player
	 */
	public void addOzonePatches(int amount) {
		ozonePatches += amount;
	}

	/**
	 * Method to allow player to pay ozone patches
	 * 
	 * @param amount
	 */
	public void pay(int amount) {
		ozonePatches -= amount;
	}

	/**
	 * Moves a player around the board
	 * 
	 * @param steps
	 * @param board
	 */
	public void move(int steps, Board board) {
		int oldPosition = position;

		if (position == Board.JAIL_SQUARE && this.jailStatus) {
			Scanner scanner = new Scanner(System.in);
			String userChoice;
			System.out.println("\nYou are still in Jail! Want a chance to get out?? Type `Yes` or `No` and hit enter.");
			userChoice = scanner.nextLine();

			if (userChoice.equalsIgnoreCase("YES")) {
				Jail.landJail(this);
			} else {
				System.out.println("Guess you'll remain in jail!");
				this.pay(Jail.JAIL_AMOUNT / 3);
				Player.displayOzonePatches(this);
				return;
			}
		}

		// this is where the revolution is controlled
		position = (position + steps) % board.getNumSquares();
		if (position < oldPosition) {
			System.out.println(
					name + " completed one revolution, and collected " + PassGo.getPassGoAmount() + " Ozone Patches!");
			// Amount of Ozone Patches when rounding is controlled by a constant in the
			// `PassGo` class
			ozonePatches += PassGo.getPassGoAmount();
			displayOzonePatches(this);

		}
		Square currentSquare = board.getSquare(position);
		currentSquare.doAction(this);

	}

	/**
	 * This method returns a string of the players owned properties and ozone
	 * patches separated by a new line for each property on the list
	 */
	public static String showProperties(Player player) {
		StringBuilder sb = new StringBuilder();

		sb.append("Properties owned by ").append(player.getName()).append(":");
		for (String property : player.getProperties()) {
			sb.append("\n- ").append(property);
		}
		return sb.toString();
	}

	/**
	 * Method to display all ozone patches
	 * 
	 * @param player
	 */
	public static void displayOzonePatches(Player player) {
		System.out.println("Remaining Ozone-Patches: " + player.getozonePatches());

	}

	/**
	 * Generates all info to string
	 */
	@Override
	public String toString() {
		return "Players [name=" + name + ", oZonePatches=" + ozonePatches + ", position=" + position + ", counter="
				+ counter + ", property=" + properties + "]";
	}

}
