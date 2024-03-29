import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Dimension;
import java.awt.BorderLayout;
/**
 * Represents a panel of controls with buttons and status items
 * that control and show status of the game.
 * @author Daniel Hay
 * @version 1.0
 */
public class ControlPanel extends JPanel {
    private JProgressBar hungerBar;
    private final int hungerMax = 200;
    private int hunger = hungerMax;
    private JProgressBar wantedBar;
    private int wantedLevel = 0;
    private final int arrestLevel = 10;
    /**
     * Creates an instance of ControlPanel.
     */
    public ControlPanel() {
        setPreferredSize(new Dimension(300, 75));
        setLayout(new BorderLayout());
        hungerBar = new JProgressBar(0, hungerMax);
        wantedBar = new JProgressBar(0, arrestLevel);
        wantedBar.setValue(0);
        wantedBar.setStringPainted(true);
        wantedBar.setString("Wanted");
        hungerBar.setValue(hungerMax);
        hungerBar.setStringPainted(true);
        hungerBar.setString("Hunger");
        add(hungerBar, BorderLayout.WEST);
        add(wantedBar, BorderLayout.EAST);
        setVisible(true);
    }
    /**
     * Adds a JButton in the Middle of the Panel
     * @param jb the button to be added
     */
    public void addButtonCenter(JButton jb) {
        this.add(jb, BorderLayout.CENTER);
    }
    /**
     * Returns true if the player has died of starvation.
     * @return {@literal true} if the player is dead
     */
    public boolean isDead() {
        return hunger <= 0;
    }
    /**
     * Increments the wantel level of the player
     * @param inc amount to increase the wanted level
     */
    public void incWanted(int inc) {
        wantedLevel += inc;
        if (wantedLevel > arrestLevel) {
            wantedLevel = arrestLevel;
        }
        wantedBar.setValue(wantedLevel);
        wantedBar.update(wantedBar.getGraphics());
    }
    /**
     * Returns true if the player has been arrested
     * @return {@literal true} if the player has been arrested
     */
    public boolean isArrested() {
        return wantedLevel >= arrestLevel;
    }
    /**
     * Increments the hunger level of the player
     * @param n the amount of hunger to increase
     */
    public void incHunger(int n) {
        hunger += n;
        if (hunger > hungerMax) {
            hunger = hungerMax;
        } else if (hunger < 0) {
            hunger = 0;
        }
        hungerBar.setValue(hungerMax - hunger);
        hungerBar.update(hungerBar.getGraphics());
    }
}