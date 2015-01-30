/**
 * This type is given to anything that can be eated for nutrition
 * @author Daniel Hay
 * @version 1.0
 */
public interface Edible {
    /**
     * Returns the nutritional value of the Edible
     * @return int of nutrional value
     */
    int getFoodPoints();
    /**
     * Compares two of the same species; overrides equals() method
     * @param o Object to be compared to
     */
    boolean equals(Object o);
}
