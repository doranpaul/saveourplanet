package saveOurPlanet2Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import saveOurPlanet2.Board;
import saveOurPlanet2.Square;

class BoardTest {

    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    /**
     * Tests that board object has been created with the correct number of squares for game
     */
    @Test
    public void testGetNumSquares() {
        assertEquals(25, board.getNumSquares());
    }

    /**
     * Tests that the getter for the square object is returning the expected number
     */
    @Test
    public void testGetSquarePosition() {
        Square square = board.getSquare(5);
        assertEquals(5, square.getSquarePosition());
    }
}

