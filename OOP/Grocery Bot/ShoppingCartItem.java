/**
 * ShoppingCartItem represents a grocery item that has been added to a
 * shopping cart in some quantity
 *
 * @author Jacqueline Zhu
 * @version 1.0 3/13/14
 */

public class ShoppingCartItem {

    private int quantity;
    private Buyable item;

    /**
     * Creates a ShoppingCartItem with the given item and quantity
     *
     * @param the item of type Buyable
     * @param the quantity of the item
     */
    public ShoppingCartItem(Buyable item, int quantity) {
        this.quantity = quantity;
        this.item = (GroceryItem) item;
    }

    /**
     * Corresponding definition of hashCode() to go with
     * equals() method
     */
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Sets a new value for the amount of the ShoppingCartItem
     *
     * @param quantity of the ShoppingCartItem
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the total cost of the ShoppingCartItem
     * according to the price and quantity
     */
    public double calculateCost() {
        return item.getCostOf(quantity);
    }

    /**
     * Overrides Object's toString method.
     *
     */
    @Override
    public String toString() {
        String finalStr = item.toString() + "\n";
        finalStr += "\tx" + quantity + " = $"
            + String.format("%.2f", item.getCostOf(quantity));
        return finalStr;
    }

    /**
     * Overrides Object's equals method
     *
     */
    @Override
    public boolean equals(Object o) {
        if (null == o) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof ShoppingCartItem)) {
            return false;
        }
        ShoppingCartItem g = (ShoppingCartItem) o;
        return (this.quantity == g.quantity
                && this.item.equals(g.item));
    }

    /**
     * Gets the ShoppingCartItem
     */
    public GroceryItem getItem() {
        return (GroceryItem) item;
    }

}