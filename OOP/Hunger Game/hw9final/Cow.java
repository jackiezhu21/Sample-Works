/**
 * Represents a Cow: an Animal that is edible
 * @author Jacqueline Zhu
 * @version 1.0
 */
public class Cow extends Animal implements Edible {
    private static final int FOODPOINTS = Nature.RAND.nextInt(6) + 25;
    private final int id = Nature.RAND.nextInt(Integer.MAX_VALUE);
    /**
     * Creates an instance of Cow
     * @param home Home of Cow
     */
    public Cow(Home home) {
        super("cow.png", home);
    }
    /**
     * Returns the nutritional value of a Cow
     * @return FOODPOINTS total int of food points
     */
    public int getFoodPoints() {
        return FOODPOINTS;
    }
    /**
     * Compares two Cowa; overrides equals() method
     * @param o Object to be compared to
     * @return {@literal true} if their id's are the same
     */
    public boolean equals(Object o) {
        return super.equals(o) && this.id == ((Cow) o).id;
    }
    /**
     * overrides hashCode()
     * @return int representation of variable
     */
    public int hashCode() {
        return super.hashCode() + this.id;
    }
}