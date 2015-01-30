/**
 * A ProduceItem is a GroceryItem priced per pound
 *
 * @author Jacqueline Zhu
 * @version 1.0 3/13/14
 */

public class ProduceItem extends GroceryItem {

    /**
     * Creates a ProduceItem with the given name and price
     *
     * @param name  the name of this GroceryItem
     * @param price the price of this GroceryItem
     */
    public ProduceItem(String name, double price) {

        super(name, price);

    }

    /**
     * Creates the String representation of this ProduceItem
     *
     * @return the String representation of this ProduceItem
     */
    public String toString() {
        String priceStr = String.format("%.2f", super.getPrice());
        return super.getName() + " @ $" + priceStr + " per pound";

    }

    /**
     * Gives a String asking for a quantity in pounds
     *
     * @return the quantity query String
     */
    public String getQuantityQuery() {

        return "How many pounds of " + super.getName() + " would you like?";

    }

    /**
     * Calculates the cost of this ProduceItem at the given quantity
     *
     * @param the quantity
     * @return the cost
     */


    public double getCostOf(int quantity) {

        double cost = quantity * super.getPrice();
        return cost;

    }

}