public class Quester {

    private String name;
    private int health;

    public Quester(String name, int startingHealth) {
        this.name = name;
        health = startingHealth;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void updateHealth(int n) {
        health += n;
    }

    public boolean isAlive() {
        if (health > 0) {
            return true;
        }
        return false;
    }
}