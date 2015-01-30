import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * GroceryBot manages the user's "shopping cart" and handles the
 * checkout process for goods, and provides a "receipt.txt" file.
 *
 * @author Jacqueline Zhu
 * @version 1.0 3/13/14
 */

public class GroceryBot {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            GroceryItem[] inventory = new GroceryItem[3];
            GroceryItem apples = new ProduceItem("Red Apples", 2.00);
            GroceryItem cereal = new BoxedGood("Admiral Crunch Cereal", 4.00);
            GroceryItem soup = new CannedGood("Some Soup", 5.00);
            inventory[0] = apples;
            inventory[1] = cereal;
            inventory[2] = soup;
            String inventoryStr = "";
            for (int i = 0; i < inventory.length; i++) {
                inventoryStr += (i + 1) + ": " + inventory[i].toString() + "\n";
            }

            ShoppingCart currentCart = new ShoppingCart();

            boolean stopLoop = false;

            while (!stopLoop) {

                System.out.println("What would you like to do?");
                System.out.println("1: Add item to cart");
                System.out.println("2: Remove item from cart");
                System.out.println("3: List cart contents");
                System.out.println("4: Checkout and Exit");
                System.out.print("Enter option number: ");


                int input = scan.nextInt();

                System.out.print("\n");

                if (input == 1) {

                    System.out.println("We carry the following products:");
                    System.out.print(inventoryStr);
                    System.out.print("Enter the number of the product you'd "
                                        + "like to add or -1 to cancel: ");

                    int tempInput = scan.nextInt();

                    System.out.print("\n");

                    if (tempInput == -1) {
                        input = 0;
                    } else if (tempInput > inventory.length
                                || tempInput == 0
                                || tempInput < -1) {
                        System.out.println("Sorry, "
                            + "that's not a valid option.");
                    } else {
                        GroceryItem cartitem = inventory[tempInput - 1];
                        System.out.println(cartitem.getQuantityQuery());
                        int num = scan.nextInt();
                        System.out.print("\n");
                        ShoppingCartItem addedItem =
                            new ShoppingCartItem(cartitem, num);
                        currentCart.add(addedItem);
                        System.out.println("Added item:");
                        System.out.println(cartitem.toString());
                        System.out.print("\tx" + num + " = ");
                        String cost =
                            String.format("%.2f", cartitem.getCostOf(num));
                        System.out.printf("$" + cost + "\n\n");
                        input = 0;
                    }
                }

                if (input == 2) {

                    System.out.println("Here are your cart contents:");
                    System.out.println(currentCart.toString());
                    System.out.println("Enter the product you want to remove:");
                    Scanner specialscan = new Scanner(System.in);
                    String itemName = specialscan.nextLine();
                    System.out.print("\n");

                    if (currentCart.itemExists(itemName)) {
                        System.out.print("Enter the quantity "
                            + "you want to remove:");
                        System.out.print("\n");
                        input = scan.nextInt();
                        System.out.print("\n");
                        for (int i = 0; i < inventory.length; i++) {
                            if (inventory[i].getName().equals(itemName)) {
                                Buyable tempItem = inventory[i];
                                ShoppingCartItem removable =
                                    new ShoppingCartItem(tempItem, input);
                                currentCart.remove(removable);
                            }
                        }

                        System.out.println("Here are your cart contents:");
                        System.out.println(currentCart.toString());
                    } else {
                        System.out.println("\nI'm sorry, "
                            + "I'm afraid I can't do that. "
                            + "No such item found...");
                    }


                }
                if (input == 3) {
                    System.out.println("Here are your cart contents:");
                    System.out.println(currentCart.toString());
                }

                if (input == 4) {


                    System.out.print("Thank you! "
                        + "Please take your receipt (see Receipt.txt)\n");
                    String totalCost = currentCart.getTotalCost();
                    String fileData = "Grocery Store Receipt\n\n";
                    fileData += "Here are your purchases:\n";
                    fileData += currentCart.toString();
                    fileData += "\nTotal = $" + totalCost;
                    fileData += "\n\nThank you!";

                    try {

                        File file = new File("Receipt.txt");
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileWriter fileWriter =
                            new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bufferedWriter =
                            new BufferedWriter(fileWriter);
                        bufferedWriter.write(fileData.replace("\n", "\r\n"));
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stopLoop = true;
                }
                if (input < -1 || input > 4) {
                    System.out.println("Sorry that's not a valid option.");
                }
                input = 0;
            }

        } catch (Exception e) {
            System.out.println("\nSorry that's not a valid option.\n");
        }


    }
}