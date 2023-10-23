package saveOurPlanet2Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saveOurPlanet2.Board;
import saveOurPlanet2.Player;

class PlayerTest {

	Player player;
	ArrayList<String> properties;
	Board board;
	
	@BeforeEach
	void setUp() throws Exception {
		properties = new ArrayList<String>();
		properties.add("Solar");
		player = new Player("John", 15, 4, 4, false);
		player.setProperty("Solar");
		board = new Board();
	}
	
	/**
	 * Test getter for name property
	 */
	@Test
	public void testGetName() {
		assertEquals("John", player.getName());
	}
	
	/**
	 * Test getter and setter for ozone patches property
	 */
	@Test
	public void testGetSetOzonePatches() {
		player.setozonePatches(15);
		assertEquals(15, player.getozonePatches());
	}
	
	/**
	 * Test getter and setter for position property
	 */
	@Test
	public void testGetSetPosition() {
		player.setPosition(5);
		assertEquals(5, player.getPosition());
	}
	
	/**
	 * Test getter and setter for counter property
	 */
	@Test
	public void testGetSetCounter() {
		player.setCounter(5);
		assertEquals(5, player.getCounter());
	}
	
	/**
	 * Test getter and setter for property 
	 */
	@Test
	public void testProperties() {
		assertEquals("[Solar]", player.getProperties().toString());
	}
	
	/**
	 * Test "move" method
	 */
	@Test
	public void testMove() {
		
		int steps = 4;
        player.move(steps, board);
        int expectedPosition = steps + player.getCounter();
        assertEquals(expectedPosition, player.getPosition());

        steps = 4;
        player.move(steps, board);
        expectedPosition = (expectedPosition + steps) % board.getNumSquares();
        assertEquals(expectedPosition, player.getPosition());
        }
//
//	@Test
//	public void testMoveOutOfJail() {
//		// Create a new player object and set their jail status to true
//		Player player = new Player("Alice", 0, 0, 0, false);
//
//		// Mock user input for scanner
//		String input = "YES\n";
//		System.setIn(new ByteArrayInputStream(input.getBytes()));
//
//		// Call the move method with steps = 1 and a mock board
//		player.move(1, new Board());
//
//		// Assert that the player's jail status is false and their position is now 10 (the next square after jail)
//		assertFalse(player.isJailStatus());
//		assertEquals(10, player.getPosition());
//	}

	/**
	 * Test showProperties method from player class
	 */
	@Test
	public void testGetProperties() {
		
	    player.setProperty("Wind");
	    String expectedProperties = "Solar, Wind";
	    assertEquals(expectedProperties, player.getProperties().toString().replaceAll("\\[|\\]", ""));

	    player.setProperty("Solar");
	    expectedProperties += ", Solar";
	    assertEquals(expectedProperties, player.getProperties().toString().replaceAll("\\[|\\]", ""));
	}
	
	/**
	 * Test jail status method.
	 */
	@Test
	public void testGetSetJailStatus() {
		player.setJailStatus(true);
		assertTrue(player.getJailStatus());
		
		player.setJailStatus(false);
		assertFalse(player.getJailStatus());
	}
	
	/**
	 *Test isJailStatus() method 
	 */
	@Test
	public void testIsJailStatus() {
		assertFalse(player.isJailStatus());

		player.setJailStatus(true);
		assertTrue(player.isJailStatus());
	}
	
	@Test
	public void testDisplayOzonePatches() {
        player.setozonePatches(0);
        Player.displayOzonePatches(player);
    }
	
	
	/**
	 * Test addOzonePatches() method
	 */
	@Test
	public void testAddOzonePatches() {
		player.addOzonePatches(5);
		assertEquals(20, player.getozonePatches());
	}
	
	/**
	 * Test pay() method
	 */
	@Test
	public void testPay() {
		player.setozonePatches(15);
		player.pay(5);
		
		assertEquals(10, player.getozonePatches());
	}
	/**
	 * Test showProperties() method
	 */
	@Test
	public void testShowProperties() {
		
		String expectedOutput = "Properties owned by John:\n-"
				+ " Solar";

		assertEquals(expectedOutput, Player.showProperties(player));
	}
	
	/**
	 * Test toString() method
	 */
	@Test
	public void testToString() {
		String expectedString = "Players [name=John, oZonePatches=15, position=4, counter=4, property=[Solar]]";
		String actualString = player.toString();
		assertEquals(expectedString, actualString);
	}
}