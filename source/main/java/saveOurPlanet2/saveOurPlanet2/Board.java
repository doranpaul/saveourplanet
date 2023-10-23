package saveOurPlanet2;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that deals with our game board. Includes methods that deal with
 * different squares on the board
 * 
 * @author user
 *
 */
public class Board {
	// setting business rule for total number of squares on board to 25
	private static final int NUM_SQUARES = 25;
	// creating an instance var list of type Square to store the squares
	private List<Square> squares;
//creating an instance list of type property to store the property
	public static List<Property> properties;
	public static final int JAIL_SQUARE = 6;
	public static final int WILDCARD_SQUARE_1 = 2;
	public static final int WILDCARD_SQUARE_2 = 10;
	/**
	 * Creating board according to number of squares set above. Utilises `Square`
	 * class to instantiate Square objects
	 */
	public Board() {
		this.squares = new ArrayList<>();
		for (int i = 0; i < NUM_SQUARES; i++) {
			squares.add(new Square(i, null));
		}

		this.properties = new ArrayList<>();
		// creating properties
		// might be useful to add propertyType as enum for Hydro/Wind/Geothermal/Solar
		// name/price/upgradeprice/rent/upgradedrent/owner/board position - NB* Owner set to null as no
		// properties start out already owned NB - Geo/Hydro csot 20 & Solar/Wind cost 10
		properties.add(new Property("Hydro Plant", 20, 40, 10, 20, null, 4));//hydro
		properties.add(new Property("Hydro Dam", 20, 40, 10, 20, null, 5));//hydro
		properties.add(new Property("Wind Turbine", 10, 20, 5, 10, null, 11));//wind
		properties.add(new Property("Geo Geyser", 20, 40, 10, 20, null, 13));//geothermal
		properties.add(new Property("Windmill", 10, 20, 5, 10, null, 17));//wind
		properties.add(new Property("Solar Farm", 10, 20, 5, 10, null, 14));//solar
		properties.add(new Property("Geothermal Plant", 20, 40, 10, 20, null, 19));//geothermanl
		properties.add(new Property("Solar Panel Company", 10, 20, 5, 10, null, 21));//solar
		properties.add(new Property("Rechargable Battery Plant", 10, 20, 5, 10, null, 22));//solar
		properties.add(new Property("Wind Turbine Sea Plant", 10, 20, 5, 10, null, 24));//wind

		//Enhanced for loop to add eacg property to its assigned square position.
		for (Property property : properties) {
			squares.get(property.getPropertyPosition()).setProperty(property);
		}
	}

	// this method adds a player to the square position
	public void addPlayer(int position, Player player) {
		squares.get(position).setPlayer(player);
	}

	// This method returns the square position
	public Square getSquare(int position) {
		return squares.get(position);
	}
	
	public int getNumSquares() {
		return NUM_SQUARES;
	}
}

//update 3
