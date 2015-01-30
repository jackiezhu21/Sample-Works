/**
 * This interface represents an endangered species type
 * @author Daniel Hay
 * @version 1.0
 */
public interface EndangeredSpecies {
    /**
     * Returns the wanted level associated with the Endangered Species
     * @return int of wanted level
     */
    int getWantedLevel();
    /**
     * Compares two of the same species; overrides equals() method
     * @param o Object to be compared to
     */
    boolean equals(Object o);
}
