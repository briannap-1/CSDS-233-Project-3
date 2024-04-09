import java.lang.Math;

/**
 * @author Brianna Penkala
 * This class represents an ArrayList
 */
public class ArrayList {

    /** A field that initializes an arrayList */
    int[] arrayList = new int[10];
    /** A field that counts the number of items in the list */
    int numberItems = 0;

    /** A method that adds an integer to the end of the list
     * @param n the integer to be added to the list
     * */
    public void add(int n) {  //actual arrayList will make a new array automatically, but they didn't specify that for this
        int i = 0;
        while (arrayList[i] != 0)
            i++; //stops loop when 0 (empty space) is reached
        arrayList[i] = n;
        numberItems++;
    }

    /** A method that adds an integer to an index in the list
     * @param n the integer to be added to the list
     * @param index the index that specifies where n will be added
     * @throws IllegalArgumentException when the index is not in the list
     * */
    public void add(int n, int index) {
        if (index < 0)
            throw new IllegalArgumentException();
        if (index > arrayList.length)
            add(n);
        else {
            for (int i = arrayList.length - 1; i > index; i--) {
                arrayList[i] = arrayList[i - 1];
            }
            arrayList[index] = n;
            numberItems++;
        }
    }

    /** A method that searches for an integer in a list
     * @param n the integer to be found
     * @return the index of the element
     * */
    public int indexOf(int n) { //counts up so if there are multiple instances the first will be returned
        int elementIndex = -1;
        for (int i = 0; i < arrayList.length && elementIndex == -1; i++) {
            if (arrayList[i] == n)
                elementIndex = i;
        }
        return elementIndex;
    }

    /** A method that removes an integer at an index in a list
     * @param index the index to be removed
     * @return the removed element
     * */
    public int remove(int index) {
        int save = 0;
        if (index < 0)
            throw new IllegalArgumentException();
        if (index > arrayList.length) {
            save = arrayList[arrayList.length - 1];
            arrayList[arrayList.length - 1] = 0;
            numberItems--;
            return save;
        }
        else {
            save = arrayList[index];
            for (int i = index + 1; i < arrayList.length - 1; i++) {
                arrayList[i - 1] = arrayList[i];
            }
            numberItems--;
        }
        return save;
    }

    /** A method that removes a value in a list
     * @param n the integer to be removed
     * */
    public void removeValue(int n) {
        int i = 0;
        while (i < arrayList.length && arrayList[i] != n)
            i++;
        if (arrayList[i] == n) {
            for (int j = i + 1; j < arrayList.length - 1; j++)
                arrayList[j - 1] = arrayList[j];
            }
        numberItems--;
    }

    /** A method that removes all occurrences of a value in a list
     * @param n the integer to be removed
     * */
    public void removeAll(int n) {
        int i = 0;
        for (int element : arrayList) {
            while (arrayList[i] == n) {
                removeValue(element);
            }
            i++;
        }
    }

    /** A method that calculates the mean of a list
     * @return the mean of the list
     * */
    public double mean() {
        double mean = 0.0;
        for (int j : arrayList) {
            mean += j;
        }
        mean = mean / numberItems;
        return mean;
    }

    /** A method that calculates the variance of a list
     * @return the variance of the list
     * */
    public double variance() {
        double mean = mean();
        double variance = 0.0;
        for (int i = 0; i <= numberItems - 1; i++) {
            variance += (arrayList[i] - mean) * (arrayList[i] - mean);
        }
        variance = variance / (numberItems - 1);
        return variance;
    }

    /** A method that creates a sublist of a list based on inputted boundaries (inclusive)
     * @param lower the lower bound of the sublist
     * @param upper the upper bound of the sublist
     * @return the sublist
     * */
    public ArrayList sublist(int lower, int upper) {
        ArrayList newArrayList = new ArrayList();
        for (int i = 0; i < arrayList.length; i++) {
            if (arrayList[i] >= lower && arrayList[i] <= upper) //if within the range
                newArrayList.add(arrayList[i]);
        }
        return newArrayList;
    }

    /** A method that creates a list that only contains elements within three standard deviations of the mean
     * @return the list with noise removed
     * */
    public ArrayList removeNoise() {
        ArrayList newList = new ArrayList(); //check if this or the other one is actually using the API
        double standardDeviations = 3 * Math.sqrt(variance());
        double mean = mean();
        for (int i = 0; i < numberItems; i++) {
            if ((arrayList[i] < standardDeviations + mean) && (arrayList[i] > mean - standardDeviations)) //if within the range of three SDs
                newList.add(arrayList[i]);
        }
        return newList;
    }

    /** A method that displays all of the elements in the list
     * @return the string of elements
     * */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numberItems; i++) {
            builder.append(arrayList[i]);
            if (i + 1 != numberItems)
                builder.append(", ");
        }
        return builder.toString();
    }
}