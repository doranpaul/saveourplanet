package saveOurPlanet2;

import java.util.Scanner;

public class Square {
	private int squarePosition;
	private Square_Type squareType;
	private Player player;
	private Property property;

	/**
	 * @param squarePosition
	 * @param squareType
	 */
	public Square(int squarePosition, Square_Type squareType) {
		super();
		this.squarePosition = squarePosition;
		this.squareType = squareType;
	}

	public Square_Type getSquareType() {
		return squareType;
	}

	public void setSquareType(Square_Type squareType) {
		this.squareType = squareType;
	}

	public int getSquarePosition() {
		return squarePosition;
	}

	public void setSquarePosition(int squarePosition) {
		this.squarePosition = squarePosition;
	}

	/**
	 * This method controls the players action when they land on a property square,
	 * jail square or wildcard square
	 * 
	 * @param player
	 */
	public void doAction(Player player) {
		// prints player has landed on a position
		System.out.println(player.getName() + " landed on square: " + squarePosition + ".");
		// if player lands on a property square, then property menu and info is
		// displayed
		for (Property properties : Board.properties) {
			if (properties.getPropertyPosition() == squarePosition) {
				System.out.println("Showing details for: " + properties.getPropertyName());
				property = properties;
				showPropertyMenu(player);
				// forces player to pay rent if they land on an owned property
				if (property.getOwner() != null && property.getOwner() != player) {
					System.out.println("This property is owned by " + property.getOwner().getName());
					int rent = property.getPropertyRent();
					System.out.println("You must pay " + property.getOwner().getName() + " rent of: " + rent);
					player.pay(rent);
					property.getOwner().addOzonePatches(rent);
					System.out.println("You have paid " + property.getPropertyRent() + " rent to: "
							+ property.getOwner().getName());
					Player.displayOzonePatches(player);
				}
				// will display if player lands on a property that they already own.
				else if (property.getOwner() == player) {
					System.out.println("Phew, you landed on your own property, no rent is due.");
					property.upgradeProperty(player);
				}
				return;
			}
		}

		if (squarePosition == Board.JAIL_SQUARE) {
			player.setJailStatus(true);

		} else if (squarePosition == Board.WILDCARD_SQUARE_1 || squarePosition == Board.WILDCARD_SQUARE_2) {
			Wildcard.wildCard(player);
		}

	}

	/**
	 * sets property
	 * 
	 * @param property
	 */
	public void setProperty(Property property) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets player
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to return property if it is not null
	 * 
	 * @return
	 */
	public boolean hasProperty() {
		return property != null;
	}

	/**
	 * Method to display the details of a property and allow the player to interact
	 * with, buy and pay rent to a property.
	 * 
	 * @param player
	 */
	public void showPropertyMenu(Player player) {
		// displaying details
		System.out.println("You have landed on " + property.getPropertyName() + ".");
		System.out.println("Price : " + property.getPropertyPrice());
		System.out.println("Rent  : " + property.getPropertyRent());
		System.out.println("Upgrade Price : " + property.getPropertyUpgradeCost());
		System.out.println("Upgraded Rent : " + property.getUpgradedRent());

		// if the owner is null then allow player to purchase property
		if (property.getOwner() == null) {
			System.out.println("You have " + player.getozonePatches() + " Patches remaining, do you want to purchase "
					+ property.getPropertyName() + "? Type Y for yes or N for No.");

			// importing scanner to allow player to interact with the method
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			// if player wants to buy then let them buy using the buy property method in the
			// Property class
			if (input.equals("y") || input.equals("Y")) {
				property.buyProperty(player);
				System.out.println(player.getName() + " Has purchased " + property.getPropertyName() + "\nfor "
						+ property.getPropertyPrice() + " Ozone-Patches.");
				System.out.print(Player.showProperties(player));
				System.out.println();
				Player.displayOzonePatches(player);
				System.out.println();
				// if not then move on
			} else if (input.equals("n") || input.equals("N")) {
				System.out.println("You have chosen not to purchase " + property.getPropertyName() + "\n for "
						+ property.getPropertyPrice());
			}
		}

	}
}
