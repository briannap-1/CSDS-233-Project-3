import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Brianna Penkala
 * This is the testing class for LinkedList
 */
class LinkedListTest {

    /** These fields initialize LinkedLists for testing */
    LinkedList list;
    LinkedList mathList;
    LinkedList subTest;
    LinkedList emptyList;
    LinkedList subTestTwo;

    /** A method that sets up the lists to be used in the testing methods* */
    @BeforeEach
    public void setUp() {
        list = new LinkedList();
        mathList = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        mathList.add(6);
        mathList.add(8);
        mathList.add(8);
        mathList.add(8);
        mathList.add(8);
        mathList.add(9);
        mathList.add(9);
        subTest = mathList.sublist(6, 8);
        emptyList = new LinkedList();
        emptyList.add(0);
    }

    /** This test checks the getFirstNode method* */
    @Test
    void getFirstNode() {
        assertEquals(1, list.getFirstElement()); //showing the element in the first node since it is more readable
        assertEquals(6, mathList.getFirstElement());
    }

    /** This test checks the add method with positive, 0, and negative numbers*/
    @Test
    void add() {
        assertEquals("1, 2, 3, 4", list.toString());
        assertEquals("6, 8, 8, 8, 8, 9, 9", mathList.toString());
        mathList.add(-2);
        assertEquals("6, 8, 8, 8, 8, 9, 9, -2", mathList.toString());
        list.add(0);
        assertEquals("1, 2, 3, 4, 0", list.toString());
    }

    /** This test checks the add at an index method at first, middle, and end indices, and when there is a negative index*/
    @Test
    void otherAdd() {
        list.add(6, 1);
        assertEquals("1, 6, 2, 3, 4", list.toString());
        list.add(7, 5);
        assertEquals("1, 6, 2, 3, 4, 7", list.toString());
        list.add(8, 0);
        assertThrows(IllegalArgumentException.class, () -> { //when a negative index in given
            list.add(1, -1);
        });
    }

    /** This test checks the indexOf method with integers at the front, middle, and end of the list, and non-existent integers*/
    @Test
    void indexOf() {
        assertEquals(1, list.indexOf(2));
        assertEquals(0, list.indexOf(1));
        assertEquals(3, list.indexOf(4));
        assertEquals(-1, list.indexOf(10));
    }

    /** This test checks the remove method with integers at the front, middle, and end of the list*/
    @Test
    void remove() {
        assertEquals("1, 2, 3, 4", list.toString());
        assertEquals(3, list.remove(2));
        assertEquals("1, 2, 4", list.toString()); //proof of removal
        assertEquals(1, list.remove(0));
        assertEquals("2, 4", list.toString());
        assertEquals(4, list.remove(1));
        assertEquals("2", list.toString());
    }

    /** This test checks the removeValue method with integers at the front, middle, and end of the list*/
    @Test
    void removeValue() {
        list.removeValue(2);
        assertEquals("1, 3, 4", list.toString());
        list.removeValue(4);
        assertEquals("1, 3", list.toString());
        list.removeValue(1);
        assertEquals("3", list.toString());
    }

    /** This test checks the removeAll method with integers at the front, middle, and end of the list*/
    @Test
    void removeAll() {
        mathList.removeAll(100);
        assertEquals("6, 8, 8, 8, 8, 9, 9", mathList.toString());
        mathList.removeAll(8);
        assertEquals("6, 9, 9", mathList.toString());
        mathList.removeAll(9);
        assertEquals("6", mathList.toString());
        mathList.removeAll(6);
        assertEquals("", mathList.toString());
    }

    /** This test checks the mean method with positive and 0 values*/
    @Test
    void mean() {
        assertEquals(8, mathList.mean());
        assertEquals(0, emptyList.mean());
        assertEquals(2.5, list.mean());
    }

    /** This test checks the variance method with positive and 0 values*/
    @Test
    void variance() {
        assertEquals(1, mathList.variance());
        assertEquals(-0.0, emptyList.variance()); //should be 0, same thing
        assertEquals(1.375, list.variance());
    }

    /** This test checks the sublist method with boundaries at the front, middle, and ends of the list, with varying sizes*/
    @Test
    void sublist() {
        subTest = mathList.sublist(6, 8);
        assertEquals("6, 8, 8, 8, 8", subTest.toString());
        subTestTwo = list.sublist(2, 4);
        assertEquals("2, 3, 4", subTestTwo.toString());
    }

    /** This test checks the removeNoise method with lists of varying sizes*/
    @Test
    void removeNoise() { //did not know a dataset that had omitted values, should work regardless
        assertEquals("6, 8, 8, 8, 8, 9", mathList.removeNoise().toString());
        mathList.add(10);
        mathList.add(100);
        mathList.add(200);
        assertEquals("6, 8, 8, 8, 8, 9, 9, 10, 100", mathList.removeNoise().toString());
    }

    /** This test checks the toString method lists of varying sizes, this method is also tested throughout the rest of the tests*/
    @Test
    void testToString() {
        assertEquals("1, 2, 3, 4", list.toString());
        assertEquals("6, 8, 8, 8, 8, 9, 9", mathList.toString());
    }

    //The other toString method (with the node parameter) is tested in the swapNodesInPairs method. It works.

    /** This test checks the length method*/
    @Test
    void length() {
        assertEquals(4, list.length());
        assertEquals(7, mathList.length());
        assertEquals(1, emptyList.length());
    }
}