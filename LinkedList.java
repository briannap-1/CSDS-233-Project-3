/**
 * @author Brianna Penkala
 * This class represents a LinkedList
 */
public class LinkedList {

    /** A field that initializes a node */
    private Node firstNode;

    /** This is the constructor for the LinkedList that initializes the firstNode to null*/
    public LinkedList() {
        firstNode = null;
    }

    /** This method returns the firstNode of the list*/
    public Node getFirstNode() {
        return this.firstNode;
    }

    /** This method sets the firstNode of the list*/
    public void setFirstNode(Node node) {
        this.firstNode = node;
    }

    /** This method returns the first element of the list*/
    public int getFirstElement() {
        return this.getFirstNode().getElement();
    }

    /** A method that adds an integer to the end of the list
     * @param n the integer to be added to the list
     * */
    public void add(int n) {
        Node pointer = getFirstNode();
        if (firstNode == null) {
            firstNode = new Node(n, null);
        } else {
            while (pointer.getNext() != null)
                pointer = pointer.getNext();
            pointer.setNext(new Node(n, null));
        }
    }

    /** A method that adds an integer to an index in the list
     * @param n the integer to be added to the list
     * @param index the index that specifies where n will be added
     * @throws IllegalArgumentException when the index is not in the list
     * */
    public void add(int n, int index) {
        Node pointer = getFirstNode();
        int count = 1;
        Node save = null;
        if (index < 0)
            throw new IllegalArgumentException();
        while (pointer.getNext() != null && count < index) {
            count++;
            pointer = pointer.getNext();
        }
        if (pointer.getNext() != null)
            save = pointer.getNext();
        pointer.setNext(new Node(n, save));
    }

    /** A method that searches for an integer in a list
     * @param n the integer to be found
     * @return the index of the element
     * */
    public int indexOf(int n) {
        Node pointer = getFirstNode();
        int index = -1;
        int count = 0;
        boolean inList = false;
        while (pointer.getNext() != null) {
            if (pointer.getElement() == n)
                index = count;
            count++;
            pointer = pointer.getNext();
        }
        if (pointer.getElement() == n)
            index = count;
        return index;
    }

    /** A method that removes an integer at an index in a list
     * @param index the index to be removed
     * @return the removed element
     * */
    public int remove(int index) {
        Node pointer = getFirstNode();
        int count = 1; //so it is one ahead
        int removedValue = 0;
        Node newNext = null;
        if (index > this.length())
            throw new IllegalArgumentException();
        if (this.length() == 1) { //if there is only one node in the list
            removedValue = pointer.getElement();
            setFirstNode(null);
        }
        if (index == 0) { //if the first element is removed
            removedValue = pointer.getElement();
            setFirstNode(pointer.getNext());
        }
        else {
            while (pointer.getNext() != null && count < index) { //to get to the removable object
                count++;
                pointer = pointer.getNext();
            }
            removedValue = pointer.getNext().getElement();
            if (pointer.getNext().getNext() != null) {
                newNext = pointer.getNext().getNext();
                pointer.setNext(newNext);
            }
            else
                pointer.setNext(null);

        }
        return removedValue;
    }

    /** A method that removes a value in a list
     * @param n the integer to be removed
     * */
    public void removeValue(int n) {
        Node pointer = getFirstNode();
        Node newNext = null;
        Node save = pointer.getNext();
        if (pointer.getElement() == n) {
            pointer.setNext(null);
            setFirstNode(save);
        }
        else {
            while (pointer.getNext() != null && pointer.getNext().getElement() != n) { //to get to the removable object
                pointer = pointer.getNext();
            } //once the element is reached
            newNext = pointer.getNext().getNext();
            pointer.setNext(newNext);
        }
    }

    /** A method that removes all occurrences of a value in a list
     * @param n the integer to be removed
     * */
    public void removeAll(int n) {
        Node pointer = getFirstNode();
        int index = 0;
        boolean lastRemoved = false;
        boolean specialCase = false;
        if (pointer.getElement() == n && pointer.getNext() == null) {//when there is only one element in the list
            setFirstNode(null);
            specialCase = true;
        }
        while (!specialCase && pointer.getNext() != null) { //to remove element
            while (pointer.getElement() == n && !lastRemoved) {
                remove(index);
                if (pointer.getNext() != null)
                    pointer = pointer.getNext();
                else
                    lastRemoved = true;
            }
            if (pointer.getNext() != null){
                pointer = pointer.getNext();
                index++;
            }
        }
    }

    /** A method that calculates the mean of a list
     * @return the mean of the list
     * */
    public double mean() {
        Node pointer = getFirstNode();
        double mean = 0.0;
        int numberElements = 0;
        while (pointer != null) {
            mean += pointer.getElement();
            numberElements++;
            if (pointer.getNext() != null)
                pointer = pointer.getNext();
            else
                break;
        }
        return mean / numberElements;
    }

    /** A method that calculates the variance of a list
     * @return the variance of the list
     * */
    public double variance() {
        Node pointer = getFirstNode();
        double mean = mean();
        double variance = 0.0;
        int numberElements = 0;
        while (pointer.getNext() != null) {
            variance += (pointer.getElement() - mean) * (pointer.getElement() - mean);
            numberElements++;
            pointer = pointer.getNext();
        }
        return variance / (numberElements - 1);
    }

    /** A method that creates a sublist of a list based on inputted boundaries (inclusive)
     * @param lower the lower bound of the sublist
     * @param upper the upper bound of the sublist
     * @return the sublist
     * */
    public LinkedList sublist(int lower, int upper) {
        Node pointer = getFirstNode();
        LinkedList newList = new LinkedList();
        while (pointer.getNext() != null) {
            if (pointer.getElement() <= upper && pointer.getElement() >= lower) //if within the range
                newList.add(pointer.getElement());
            pointer = pointer.getNext();
        }
        if (pointer.getElement() == upper) //to include the last element if appropriate
            newList.add(pointer.getElement());
        return newList;
    }

    /** A method that creates a list that only contains elements within three standard deivations of the mean
     * @return the list with noise removed
     * */
    public LinkedList removeNoise() {
        LinkedList newList = new LinkedList();
        double standardDeviations = 3 * Math.sqrt(variance());
        double mean = mean();
        Node pointer = getFirstNode();
        while (pointer.getNext() != null) {
            if ((pointer.getElement() <  standardDeviations + mean) && (pointer.getElement() > mean - standardDeviations)) //if within the range of three SDs
                newList.add(pointer.getElement());
            pointer = pointer.getNext();
        }
        return newList;
    }

    /** A method that returns the length of a list
     * * @return the length of the list
     * */
    public int length() {
        Node pointer = getFirstNode();
        int count = 1;
        while (pointer.getNext() != null) {
            count++;
            pointer = pointer.getNext();
        }
        return count;
    }

    /** A method that displays all of the elements in the list
     * @return the string of elements
     * */
    public String toString() {
        Node pointer = getFirstNode();
        StringBuilder builder = new StringBuilder();
        if (getFirstNode() == null)
            return "";
        else {
            while (pointer.getElement() != 0 && pointer.getNext() != null) {
                builder.append(pointer.getElement());
                builder.append(", ");
                pointer = pointer.getNext();
            }
            builder.append(pointer.getElement());
            return builder.toString();
        }
    }

    /** A method that displays all of the elements in the list with a Node head as input
     * @return the string of elements
     * */
    public static String toString(Node head) {
        Node pointer = head;
        StringBuilder builder = new StringBuilder();
        if (head == null)
            return "";
        else {
            while (pointer.getElement() != 0 && pointer.getNext() != null) {
                builder.append(pointer.getElement());
                builder.append(", ");
                pointer = pointer.getNext();
            }
            builder.append(pointer.getElement());
            return builder.toString();
        }
    }
}