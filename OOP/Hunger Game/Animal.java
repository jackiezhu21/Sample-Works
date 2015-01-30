import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

/**
 * The Animal class is the abstract base class for all animals
 * Animals have a Home where they are born and always come back to
 * Their center is their actual (graphical) location
 * their upperLeft is the point at the upperleft of the image.
 * They have a confort distance maxDist.  They don't like being farther
 * from home than that.
 * @author Daniel Hay
 * @version 1.0
 */
public abstract class Animal implements Drawable {
    private BufferedImage icon;
    private Point centre;
    private Point upperLeft;
    private int width, height;
    private Direction dir = Direction.OUT;
    private final double maxDist;
    private Home home;
    private boolean isDead = false;
    private boolean isHidden = true, isMoving = false;
    /**
     * Constructs a new Animal object
     * @param imageName the filename of the Animal's icon
     * @param home the Animal's birthplace and residence
     */
    public Animal(String imageName, Home home) {
        this.icon = GamePanel.getIcon(imageName);
        this.home = home;
        this.centre = new Point(home.getCenter());
        this.width = icon.getWidth();
        this.height = icon.getHeight();
        this.upperLeft = GamePanel.upperLeft(centre, width, height);
        this.maxDist = Nature.RAND.nextInt(351) + 150;
    }
    /**
     * Returns true if the Animal is moving
     * @return {@literal true} if the Animal is moving
     */
    public boolean isMoving() {
        return isMoving;
    }
    /**
     * Updates instance variable if the Animal is moving
     * @param isMoving {@literal true} if the Animal is moving
     */
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    /**
     * Teleports the Animal back to its home
     */
    public void teleport() {
        setLocation(home.getCenter());
    }
    /**
     * Updates the location of the Animal
     */
    private void updateLocation() {
        upperLeft = GamePanel.upperLeft(centre, width, height);
    }
    /**
     * Sets the location of the Animal at an exact point
     * @param p Point where Animal should be
     */
    public void setLocation(Point p) {
        centre = new Point(p);
        updateLocation();
    }
    /**
     * Tranlates the location of the Animal
     * @param x x-coordinate of location
     * @param y y-coordinate of location
     */
    public void translate(int x, int y) {
        centre.translate(x, y);
        updateLocation();
    }
    /**
     * Returns location of Animal
     * @return centre the location Point of the center
     * of the Animal
     */
    public Point getLoc() {
        return centre;
    }
    /**
     * Returns upperLeft Point
     * @return upperLeft Point at upper left
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * Returns width of animal icon
     * @return width number of pixels wide
     */
    public int getWidth() {
        return width;
    }
    /**
     * Returns height of animal icon
     * @return height number of pixels high
     */
    public int getHeight() {
        return height;
    }
    /**
     * Returns home of Animal
     * @return home Home of the animal
     */
    public Home getHome() {
        return home;
    }
    /**
     * Updates home of Animal
     * @param home Home instance variable
     */
    public void setHome(Home home) {
        this.home = home;
    }
    /**
     * Returns true if player is dead
     * @return isDead {@literal true} if Animal is dead
     */
    public boolean isDead() {
        return isDead;
    }
    /**
     * Hides the Animal
     */
    public void hide() {
        isHidden = true;
    }
    /**
     * Shows the Animal (it stops hiding)
     */
    public void show() {
        isHidden = false;
    }
    /**
     * Returns true if Animal is hiding in home
     * @return isHidden {@literal true} if Animal is hiding
     */
    public boolean isHidden() {
        return isHidden;
    }
    /**
     * Returns maximum distance Animal is away from home
     * @return maxDist the amount of distance
     */
    public double getMaxDist() {
        return maxDist;
    }
    /**
     * Returns direction Animal is going
     * @return dir Direction, IN / OUT
     */
    public Direction getDir() {
        return dir;
    }
    /**
     * Sets direction of Animal
     * @param dir Direction IN / OUT
     */
    public void setDir(Direction dir) {
        this.dir = dir;
    }
    /**
     * Determines whether this Animal is hit by a shot
     * If this Animal is indeed hit, the Animal dies
     * @param scope the scope of the shot
     * @return {@literal true} if the Animal is hit
     */
    public boolean isHit(Rectangle scope) {
        Rectangle body = new Rectangle(upperLeft, new Dimension(width, height));
        if (!body.intersects(scope)) {
            return false;
        }
        Rectangle rect = scope.intersection(body);
        rect.translate(-upperLeft.x, -upperLeft.y);
        int[] img = icon.getRGB(rect.getLocation().x, rect.getLocation().y,
                                (int) rect.getWidth(), (int) rect.getHeight(),
                                null, 0, width);
        int count = 0;
        for (int i : img) {
            if (i != 0) {
                ++count;
            }
            if (count > 3) {
                isDead = true;
                return true;
            }
        }
        return false;
    }
    @Override
    /**
     * Determines if this Animal is equal to a specified Object
     * @param obj the Object being tested for equality
     * @return {@literal true} if this Animal is equal to the Object
     */
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Animal)) {
            return false;
        }
        return (this.centre.equals(((Animal) obj).centre)
                && this.getClass().equals(obj.getClass()));
    }

    /**
     * Overrides hashCode() and returns int
     * @return int
     */
    public int hashCode() {
        return this.centre.hashCode();
    }
    /**
     * Draw Animal in HungerGame
     * @param g Graphics representation of Animal
     */
    public void draw(Graphics g) {
        home.draw(g);
        g.drawImage(icon, upperLeft.x, upperLeft.y, null, null);
    }
}
