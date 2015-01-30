/**
 * Represents a Panda: an Animal that is an endangered species
 * @author Jacqueline Zhu
 * @version 1.0
 */
public class Whale extends Animal implements EndangeredSpecies {
    private final int wantedLevel = Nature.RAND.nextInt(2) + 1;
    private final int id = Nature.RAND.nextInt(Integer.MAX_VALUE);
    /**
     * Creates an instance of a Whale
     * @param home Home of Whale
     */
    public Whale(Home home) {
        super("humpbackwhale.png", home);
    }
    /**
     * Compares two Whales; overrides equals() method
     * @param o Object to be compared to
     * @return {@literal true} if their id's are the same
     */
    public boolean equals(Object o) {
        return super.equals(o) && this.id == ((Whale) o).id;
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
