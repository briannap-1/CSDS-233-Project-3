import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Brianna Penkala
 * This is the testing class for Recursion
 */
class RecursionTest {

    /** A field that initializes an instance of the recursion class */
    Recursion recursion = new Recursion();
    /** A field that initializes another instance of the recursion class */
    Recursion recursionTwo = new Recursion();
    /** A field that initializes a linked list */
    LinkedList list = new LinkedList();

    /** A method that sets up a LinkedList to be used in the testing methods* */
    @BeforeEach
    public void setUp() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
    }

    /** This test checks the sumDigits method with positive, 0, and negative numbers*/
    @Test
    void sumDigits() {
        assertEquals(5050, recursion.sumDigits(100));
        assertEquals(10, recursion.sumDigits(4));
        assertEquals(0, recursion.sumDigits(0));
        assertThrows(IllegalArgumentException.class, () -> recursion.sumDigits(-1));
    }
    /** This test checks the gcd method with whole, 0, and a zero in the denominator*/
    @Test
    void gcd() {
        assertEquals(6, recursion.gcd(270, 192));
        assertEquals(10, recursion.gcd(0, 10));
        assertEquals(2, recursion.gcd(2, 4));
        assertThrows(ArithmeticException.class, () -> recursion.gcd(5, 0));
    }

    /** This test checks the isPalindrome method with long, short, and one letter words*/
    @Test
    void isPalindrome() {
        assertTrue(recursion.isPalindrome("mom"));
        assertFalse(recursion.isPalindrome("apple"));
        assertTrue(recursion.isPalindrome("a"));
        assertTrue(recursion.isPalindrome("neveroddoreven"));
    }

    /** This test checks the swapNodesInPairs method with large, medium, and small numbers of values*/
    @Test
    void swapNodesInPairs() {
        Node firstNode = list.getFirstNode();   //these tests show the results of the tests when the method returns a LinkedList- easier to see that it works
        Node test = recursion.swapNodesInPairs(firstNode); //note this method is only meant to work with at least 3 elements, cannot swap otherwise
        assertEquals("2, 1, 4, 3, 5", LinkedList.toString(test));
        LinkedList otherList = list.sublist(1, 3);
        Node secondNode = otherList.getFirstNode();
        Node test2 = recursionTwo.swapNodesInPairs(secondNode);
        assertEquals("2, 1, 3", LinkedList.toString(test2));
    }

    /** This test checks the binomial method with whole, 0, negative values*/
    @Test
    void binomial() {
        assertEquals(5006386, recursion.binomial(59, 54));
        assertEquals(35, recursion.binomial(7, 3));
        assertEquals(1, recursion.binomial(0,0)); //0! = 1
        assertThrows(IllegalArgumentException.class, () -> recursion.binomial(-1, -1));
    }
}