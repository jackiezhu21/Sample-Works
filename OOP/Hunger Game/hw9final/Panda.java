/**
 * Represents a Panda: an Animal that is an endangered species
 * @author Daniel Hay
 * @version 1.0
 */
public class Panda extends Animal implements EndangeredSpecies {
    private final int wantedLevel = Nature.RAND.nextInt(2) + 1;
    private final int id = Nature.RAND.nextInt(Integer.MAX_VALUE);
    /**
     * Creates an instance of Panda
     * @param home Home of Panda
     */
    public Panda(Home home) {
        super("panda-100.png", home);
    }
    /**
     * Compares two pandas; overrides equals() method
     * @param o Object to be compared to
     * @return {@literal true} if their id's are the same
     */
    public boolean equals(Object o) {
        return super.equals(o) && this.id == ((Panda) o).id;
    }
    /**
     * Returns wanted level of player
     * @return wantedLevel int representation of how many endangered Animals
     *      the player has hunted
     */
    public int getWantedLevel() {
        return wantedLevel;
    }
    /**
     * overrides hashCode()
     * @return int representation of variable
     */
    public int hashCode() {
        return super.hashCode() + this.id;
    }
}
