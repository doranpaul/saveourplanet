/**
 * 
 */
package saveOurPlanet2;

import java.util.Scanner;

/**
 * @author nathan This class holds the getters and setters for the property
 *         object.
 */
public class Property {
	// property name
	private String propertyName;
	// property price to buy
	private int propertyPrice;
	// property upgrade cost
	private int propertyUpgradeCost;
	// cost of rent when player lands on property
	private int propertyRent;
	// cost of rent when player lands on upgraded property
	private int upgradedRent;
	// player who owns property
	private Player owner;
	//property position on board
	private int propertyPosition;

	/**
	 * Constructor with args
	 * 
	 * @param propertyName
	 * @param propertyPrice
	 * @param propertyRent
	 * @param upgradedRent
	 * @param owner
	 */
	public Property(String propertyName, int propertyPrice, int propertyUpgradeCost, int propertyRent, int upgradedRent,
			Player owner, int propertyPosition) {
		super();
		this.propertyName = propertyName;
		this.propertyPrice = propertyPrice;
		this.propertyUpgradeCost = propertyUpgradeCost;
		this.propertyRent = propertyRent;
		this.upgradedRent = upgradedRent;
		this.owner = owner;
		this.propertyPosition = propertyPosition;
	}

	/**
	 * Getter for property name
	 * 
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Setter for property name
	 * 
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Getter for poperty price
	 * 
	 * @return the propertyPrice
	 */
	public int getPropertyPrice() {
		return propertyPrice;
	}

	/**
	 * Setter for property price
	 * 
	 * @param propertyPrice the propertyPrice to set
	 */
	public void setPropertyPrice(int propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public int getPropertyUpgradeCost() {
		return propertyUpgradeCost;
	}

	public void setPropertyUpgradeCost(int propertyUpgradeCost) {
		this.propertyUpgradeCost = propertyUpgradeCost;
	}

	/**
	 * Getter for property rent
	 * 
	 * @return the propertyRent
	 */
	public int getPropertyRent() {
		return propertyRent;
	}

	/**
	 * Setter for property rent
	 * 
	 * @param propertyRent the propertyRent to set
	 */
	public void setPropertyRent(int propertyRent) {
		this.propertyRent = propertyRent;
	}

	/**
	 * Getter for upgraded rent
	 * 
	 * @return the upgradedRent
	 */
	public int getUpgradedRent() {
		return upgradedRent;
	}

	/**
	 * Setter for upgraded rent
	 * 
	 * @param upgradedRent the upgradedRent to set
	 */
	public void setUpgradedRent(int upgradedRent) {
		this.upgradedRent = upgradedRent;
	}

	/**
	 * Gets the owner Allows the player to be set as the owner upon buying the
	 * property
	 * 
	 * @return
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Sets the owner Allows the player to be set as the owner upon buying the
	 * property
	 * 
	 * @param owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Gets the property position on the board
	 * @return
	 */
	public int getPropertyPosition() {
		return propertyPosition;
	}

	/**
	 * Sets the property position on the board
	 * @param propertyPosition
	 */
	public void setPropertyPosition(int propertyPosition) {
		this.propertyPosition = propertyPosition;
	}

	/**
	 * Method within the property class which allows a player to buy a property
	 * 
	 * @param player
	 */
	public void buyProperty(Player player) {
		//where the owner of property object is null
		if (owner == null) {
			//if player has sufficient resources
			if (player.getozonePatches() >= propertyPrice) {
				owner = player;
				player.pay(propertyPrice);
				player.getProperties().add(propertyName);
				System.out.println("Owner is now: " + player.getName());
			} else {
				//if not enough resources
				System.out.println("You do not have enough O-Zone Patches to purchase this property");
			}
		} else {
			//if property is already owned
			System.out.println("This property is already owned by " + owner.getName());
		}
	}

	/**
	 * This method allows a player to upgrade a property 
	 * If the player does not own the property they cannot upgrade it
	 * If a player does not have enough OZone Patches, they cannot upgrade it
	 * @param player
	 */
	public void upgradeProperty(Player player) {
		//if the owner is not the player currently playing
		if (owner != player) {
			System.out.println("You do not own this property");
			return;
		}
//if the player has less resources than the cost of the upgrade
		if (owner == player && player.getozonePatches() < propertyUpgradeCost) {
			System.out.println("You do not have enough O-Zone Patches to upgrade this property");
			return;
		}
		
		System.out.println("Do you want to upgrade " + propertyName + "? Type 'Y' or 'N'");
		
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		
		if(userInput.equals("Y") || userInput.equals("y")) {
//setting the new property rent to the upgraded rent
		propertyRent = upgradedRent;
		//player pays the upgrade cost
		player.pay(propertyUpgradeCost);
		//displays players current resources after upgrade
		System.out.println(
				"You have successfully upgraded your property. You have " + player.getozonePatches() + " Ozone Patches remaining.");
	} else if(userInput.equals("N") || userInput.equals("n")) {
		System.out.println("Maybe you can upgrade it next time...");
	}
}
}
