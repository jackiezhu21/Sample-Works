/**
 * A BoxedGood is a DryGood priced per box
 *
 * @author Jacqueline Zhu
 * @version 1.0 3/13/14
 */

public class BoxedGood extends DryGood {

    /**
     * Creates a DryGood with the given name and price
     *
     * @param name  the name of this DryGood
     * @param price the price of this DryGood
     */
    public BoxedGood(String name, double price) {

        super(name, price);

    }

    /**
     * Creates the String representation of this DryGood
     *
     * @return the String representation of this DryGood
     */
    public String toString() {
        String priceStr = String.format("%.2f", super.getPrice());
        return super.getName() + " @ $" + priceStr + " per box";

    }

    /**
     * Gives a String asking for a quantity in number of boxes
     *
     * @return the quantity query String
     */
    public String getQuantityQuery() {

        return "How many boxes of " + super.getName() + " would you like?";

    }

    /**
     * Calculates the cost of this DryGood at the given quantity
     *
     * @param the quantity
     * @return the cost
     */

    public double getCostOf(int quantity) {

        double cost = (quantity * super.getPrice());
        return cost;

    }

}