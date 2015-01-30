import java.awt.Graphics;
/**
 * This interface represents a pure type given to every drawable class
 * @author Daniel Hay
 * @version 1.0
 */
public interface Drawable {
    /**
     * Draw image in HungerGames
     * @param g Graphics representation of object
     */
    void draw(Graphics g);
}
