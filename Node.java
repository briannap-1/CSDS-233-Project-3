/**
 * @author Brianna Penkala
 * This class represents an Node
 */
public class Node {

    /** the element stored in the node */
    private int element;
    /** a reference to the next node of the list */
    private Node next;

    /**
     * The constructor
     * @param element  the element to store in the node
     * @param next  a reference to the next node of the list
     */
    public Node(int element, Node next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Returns the element stored in the node
     * @return the element stored in the node
     */
    public int getElement() {
        return element;
    }

    /**
     * Returns the next node of the list
     * @return the next node of the list
     */
    public Node getNext() {
        return next;
    }

    /**
     * Changes the node that comes after this node in the list
     * @param next the node that should come after this node in the list.
     */
    public void setNext(Node next) {
        this.next = next;
    }
}