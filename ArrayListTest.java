import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Brianna Penkala
 * This is the testing class for ArrayList
 */
class ArrayListTest {

    /** These fields initialize ArrayLists and a LinkedList for testing */
    ArrayList arrayList = new ArrayList();
    ArrayList calcArrayList = new ArrayList();
    ArrayList orderedList = new ArrayList();
    LinkedList emptyList = new LinkedList();

    /** A method that sets up the lists to be used in the testing methods* */
    @BeforeEach
    public void setUp() {
        calcArrayList.add(6);
        calcArrayList.add(8);
        calcArrayList.add(8);
        calcArrayList.add(8);
        calcArrayList.add(8);
        calcArrayList.add(9);
        calcArrayList.add(9);
        orderedList.add(1);
        orderedList.add(2);
        orderedList.add(3);
        orderedList.add(4);
        orderedList.add(5);
        emptyList.add(0);
    }

    /** This test checks the add method with positive, 0, and negative numbers*/
    @Test
    void add() {
        arrayList.add(2);
        assertEquals("2", arrayList.toString());
        arrayList.add(-4);
        assertEquals("2, -4", arrayList.toString());
        arrayList.add(0);
        assertEquals("2, -4, 0", arrayList.toString());
    }

    /** This test checks the add at an index method at first, middle, and end indices, and when there is a negative index*/
    @Test
    void otherAdd() {
        orderedList.add(10, 2);
        assertEquals("1, 2, 10, 3, 4, 5", orderedList.toString());
        orderedList.add(11, 1);
        assertEquals("1, 11, 2, 10, 3, 4, 5", orderedList.toString());
        orderedList.add(13, 0);
        assertEquals("13, 1, 11, 2, 10, 3, 4, 5", orderedList.toString());
        orderedList.add(14, 8);
        assertEquals("13, 1, 11, 2, 10, 3, 4, 5, 14", orderedList.toString());
        assertThrows(IllegalArgumentException.class, () -> { //when a negative index is given
            orderedList.add(1, -1);
        });
    }

    /** This test checks the indexOf method with integers at the front, middle, and end of the list, and non-existent integers*/
    @Test
    void indexOf() {
        assertEquals(0, orderedList.indexOf(1));
        assertEquals(2, orderedList.indexOf(3));
        assertEquals(4, orderedList.indexOf(5));
        assertEquals(-1, orderedList.indexOf(10));
    }

    /** This test checks the remove method with indices at the front, middle, and end of the list, and negative indices*/
    @Test
    void remove() {
        assertEquals(0, calcArrayList.remove(11)); //technically correct since the last value is 0 (empty)
        assertEquals("6, 8, 8, 8, 8, 9", calcArrayList.toString());
        assertEquals(8, calcArrayList.remove(1));
        assertEquals("6, 8, 8, 8, 9", calcArrayList.toString());
        assertEquals(6, calcArrayList.remove(0));
        assertEquals("8, 8, 8, 9", calcArrayList.toString());
        assertThrows(IllegalArgumentException.class, () -> { //when a negative index is given
            calcArrayList.remove(-1);
        });
    }

    /** This test checks the removeValue method with values at the front, middle, and end of the list*/
    @Test
    void removeValue() {
        assertEquals("6, 8, 8, 8, 8, 9, 9", calcArrayList.toString());
        calcArrayList.removeValue(8);
        assertEquals("6, 8, 8, 8, 9, 9", calcArrayList.toString());
        calcArrayList.removeValue(6);
        assertEquals("8, 8, 8, 9, 9", calcArrayList.toString());
        calcArrayList.removeValue(9);
        assertEquals("8, 8, 8, 9", calcArrayList.toString());
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> { //when a negative index is given
            calcArrayList.removeValue(1000);
        });
    }

    /** This test checks the removeAll method with values at the front, middle, and end of the list, and when there is only one element in the list*/
    @Test
    void removeAll() {
        calcArrayList.removeAll(100);
        assertEquals("6, 8, 8, 8, 8, 9, 9", calcArrayList.toString());
        calcArrayList.removeAll(8);
        assertEquals("6, 9, 9", calcArrayList.toString());
        calcArrayList.removeAll(9);
        assertEquals("6", calcArrayList.toString());
        calcArrayList.add(6);
        calcArrayList.add(7);
        calcArrayList.removeAll(6);
        assertEquals("7", calcArrayList.toString());
        calcArrayList.removeAll(7);
        assertEquals("", calcArrayList.toString());
    }

    /** This test checks the mean method with positive and 0 values*/
    @Test
    void mean() {
        assertEquals(8, calcArrayList.mean());
        assertEquals(0, emptyList.mean());
        assertEquals(3, orderedList.mean());
    }

    /** This test checks the variance method with positive and 0 values*/
    @Test
    void variance() {
        assertEquals(1, calcArrayList.variance());
        assertEquals(2.5, orderedList.variance());
        assertEquals(-0.0, emptyList.variance()); //should return 0, -0.0 is not real, same thing
        assertEquals(2.5, orderedList.variance());
    }

    /** This test checks the sublist method with boundaries at the front, middle, and ends of the list, with varying sizes*/
    @Test
    void sublist() { //I tried testing for the case where sublist would have zero values but it returned the address of the array, still worked though
        assertEquals("6, 8, 8, 8, 8", calcArrayList.sublist(6, 8).toString());
        assertEquals("1, 2, 3", orderedList.sublist(1, 3).toString());
        assertEquals("1", orderedList.sublist(1, 1).toString());
    }

    /** This test checks the removeNoise method with lists of varying sizes*/
    @Test
    void removeNoise() { //did not know a dataset that had omitted values, should work regardless
        assertEquals("6, 8, 8, 8, 8, 9, 9", calcArrayList.removeNoise().toString());
        calcArrayList.add(10);
        calcArrayList.add(100);
        calcArrayList.add(200);
        assertEquals("6, 8, 8, 8, 8, 9, 9, 10, 100, 200", calcArrayList.removeNoise().toString());
    }

    /** This test checks the toString method lists of varying sizes, this method is also tested throughout the rest of the tests*/
    @Test
    void testToString() {
        assertEquals("6, 8, 8, 8, 8, 9, 9", calcArrayList.toString());
        assertEquals("1, 2, 3, 4, 5", orderedList.toString());
        assertEquals("0", emptyList.toString());
    }
}