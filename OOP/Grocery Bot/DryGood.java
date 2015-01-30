/**
 * A DryGood is a Grocery item priced per unit
 *
 * @author Jacqueline Zhu
 * @version 1.0 3/13/14
 */

public class DryGood extends GroceryItem {

    /**
     * Creates a DryGood with the given name and price
     *
     * @param name  the name of this DryGood
     * @param price the price of this DryGood
     */
    public DryGood(String name, double price) {

        super(name, price);

    }

    /**
     * Creates the String representation of this DryGood
     *
     * @return the String representation of this DryGood
     */
    public String toString() {
        String priceStr = String.format("%.2f", super.getPrice());
        return super.getName() + " @ $" + priceStr + " per unit";

    }

    /**
     * Gives a String asking for a quantity in number of boxes
     *
     * @return the quantity query String
     */
    public String getQuantityQuery() {

        return "How many units of " + super.getName() + "would you like?";

    }

    /**
     * Calculates the cost of this DryGood at the given quantity
     *
     * @param the quantity
     * @return the cost
     */

    public double getCostOf(int quantity) {

        double cost = quantity * getPrice();
        return cost;

    }

}