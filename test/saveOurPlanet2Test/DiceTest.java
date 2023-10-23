package saveOurPlanet2Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import saveOurPlanet2.Dice;

class DiceTest {
	
	/**
	 * Test class showing that the return of calling the roll() method is between 1 and 6 inclusive as required.
	 */
	@Test
    public void testRoll() {
        int result = Dice.roll();
        assertTrue(result >= 1 && result <= 6);
    }
}
