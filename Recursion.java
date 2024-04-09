/**
 * @author Brianna Penkala
 * This class contains methods that use recursion
 */
public class Recursion {

    /** A field that initializes a LinkedList */
    private final LinkedList list = new LinkedList();

    /** A method that calculates the factorial of the input
     * @param n the integer to be calculated
     * @return the sum of the factorial
     * @throws IllegalArgumentException when the inputted integer is negative
     * */
    public int sumDigits(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        int sum;
        if (n == 1 || n == 0)
            sum = n;
        else
            sum = n + sumDigits(n - 1);
        return sum;
    }

    /** A method that calculates the greatest common denominator
     * @param a the first integer to be considered
     * @param b the second integer to be considered
     * @return the greatest common denominator
     * */
    public int gcd(int a, int b) {
        int remainder = a % b;
        int whole = a / b;
        if (a == 0)
            return b;
        if (whole == 0)
            return remainder;
        if (remainder == 0)
            return whole;
        else
            return gcd(b, remainder);
    }

    /** A method that calculates the greatest common denominator
     * @param str the string to be analyzed
     * @return true if the string is a palindrome, false otherwise
     * */
    public boolean isPalindrome(String str) {
        if (str.length() <= 1)
            return true;
        if (str.charAt(0) == str.charAt(str.length() - 1))
            return isPalindrome(str.substring(1, str.length() - 1));
        return false;
    }

    /** A method that swaps every two nodes in a LinkedList
     * @param head the head of the LinkedList to be rearranged
     * @return the first node of the rearranged list
     * */
    public Node swapNodesInPairs(Node head) {
        Node pointer = head;
        if (pointer.getNext() != null) {
            list.add(pointer.getNext().getElement());
            list.add(pointer.getElement());
            return swapNodesInPairs(head.getNext().getNext());
        }
        else {
            list.add(pointer.getElement());
            return list.getFirstNode();
        }
    }

    /** A method that calculates the binomial coefficient
     * @param n the number of elements
     * @param k the number of items to be chosen
     * @return the binomial coefficient
     * @throws IllegalArgumentException when the an input is negative
     * */
    public int binomial(int n, int k) {
        if (n < 0 || k < 0)
            throw new IllegalArgumentException();
        if (k == 0 || k == n)
            return 1;
         return binomial(n - 1, k - 1) + binomial(n - 1, k);
    }
}
