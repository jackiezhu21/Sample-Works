/**
 * ShoppingCart is a resizeable array that
 * holds the cutomer's selected cartItems
 *
 * @author Jacqueline Zhu
 * @version 1.0 3/13/14
 */

public class ShoppingCart {

    private ShoppingCartItem[] cartItems;

    public ShoppingCart() {
        cartItems = new ShoppingCartItem[10];
    }

    /**
     * Allows new ShoppingCartItems to be added into the cart
     *
     * @param the ShoppingCartItem to be added
     */
    public void add(ShoppingCartItem item) {
        boolean doubleCart = false;
        for (int i = 0; i < cartItems.length; i++) {
            if (cartItems[i] == null) {
                cartItems[i] = item;
                i = cartItems.length;
                doubleCart = false;
            } else {
                doubleCart = true;
            }
        }
        if (doubleCart) {
            int newSize = cartItems.length * 2;
            ShoppingCartItem[] tempCart = new ShoppingCartItem[newSize];
            for (int j = 0; j < cartItems.length; j++) {
                tempCart[j] = cartItems[j];
            }
            cartItems = tempCart;
            for (int k = 0; k < cartItems.length; k++) {
                if (cartItems[k] == null) {
                    cartItems[k] = item;
                    k = cartItems.length;
                }
            }
        }
    }

    /**
     * Removes given ShoppingCartItem from the ShoppingCart
     *
     * @param the ShoppingCartItem to be removed
     */
    public ShoppingCartItem remove(ShoppingCartItem item) {
        for (int i = 0; i < cartItems.length; i++) {
            if (item.equals(cartItems[i])) {
                cartItems[i] = null;
                for (int j = i; j < cartItems.length; j++) {
                    if (j < (cartItems.length - 1)) {
                        cartItems[j] = cartItems[j + 1];
                    } else {
                        cartItems[j] = null;
                    }
                }

                return item;
            }
        }
        return null;
    }

    /**
     * Overrides Object's toString() method
     */
    @Override
    public String toString() {
        String finalStr = "";
        for (int i = 0; i < cartItems.length; i++) {
            if (cartItems[i] != null) {
                finalStr += cartItems[i].toString() + "\n";
            }
        }
        return finalStr;
    }

    /**
     * Gives the total cost of all the items in the cart
     */
    public String getTotalCost() {
        double cost = 0.0;
        for (int i = 0; i < cartItems.length; i++) {
            if (cartItems[i] != null) {
                cost += cartItems[i].calculateCost();
            }
        }
        return String.format("%.2f", cost);
    }

    /**
     * Checks if an item with that name exists in the cart
     *
     * @param the name of the ShoppingCartItem
     */
    public boolean itemExists(String name) {
        for (int i = 0; i < cartItems.length; i++) {
            if (cartItems[i] != null) {
                if (name.equals(cartItems[i].getItem().getName())) {
                    return true;
                }
            }
        }
        return false;
    }

}