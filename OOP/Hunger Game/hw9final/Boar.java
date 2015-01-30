/**
 * This class represents a wild Boar.  It is edible.
 * @author Daniel Hay
 * @version 1.0
 */
public class Boar extends Animal implements Edible {
    private static final int FOODPOINTS = Nature.RAND.nextInt(6) + 25;
    private final int id = Nature.RAND.nextInt(Integer.MAX_VALUE);
    /**
     * Creates an instance of a Boar
     * @param home Home of Boar
     */
    public Boar(Home home) {
        super("boar-100.png", home);
    }
    /**
     * Returns the nutritional value of a Cow
     * @return FOODPOINTS total int of food points
     */
    public int getFoodPoints() {
        return FOODPOINTS;
    }
    /**
     * Compares two Boars; overrides equals() method
     * @param o Object to be compared to
     * @return {@literal true} if their id's are the same
     */
    public boolean equals(Object o) {
        return super.equals(o) && this.id == ((Boar) o).id;
    }
    /**
     * overrides hashCode()
     * @return int representation of variable
     */
    public int hashCode() {
        return super.hashCode() + this.id;
    }
}
