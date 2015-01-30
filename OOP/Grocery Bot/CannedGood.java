/**
 * A CannedGood is a Grocery item priced per can,
 * but always on sale, "Buy 2 get 1 free."
 *
 * @author Jacqueline Zhu
 * @version 1.0 3/13/14
 */

public class CannedGood extends GroceryItem {

    /**
     * Creates a CannedGood with the given name and price
     *
     * @param name  the name of this CannedGood
     * @param price the price of this CannedGood
     */
    public CannedGood(String name, double price) {

        super(name, price);

    }

    /**
     * Creates the String representation of this ProduceItem
     *
     * @return the String representation of this ProduceItem
     */
    public String toString() {
        String p = String.format("%.2f", super.getPrice());
        return super.getName() + " @ $" + p + " per can, buy 2 get 1 free";

    }

    /**
     * Gives a String asking for a quantity in number of cans
     *
     * @return the quantity query String
     */
    public String getQuantityQuery() {

        return "How many cans of " + super.getName()
                    + " would you like? Buy 2, get 1 free!";

    }

    /**
     * Calculates the cost of this CannedGood at the given quantity
     *
     * @param the quantity
     * @return the cost
     */

    public double getCostOf(int quantity) {

        int freeCans = quantity / 3;
        double cost = (quantity - freeCans) * super.getPrice();
        return cost;

    }

}