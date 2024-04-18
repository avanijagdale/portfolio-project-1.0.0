import components.inventory.Inventory;
import components.inventory.Inventory1;
import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to demo the inventory component.
 *
 */
public final class InventoryDemo {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private InventoryDemo() {

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        // inventory that stores the ingredients for a guacamole recipe
        Inventory guac = new Inventory1();
        guac.addNewItem("avocado", 6, 3);
        guac.addNewItem("lime", 2, 1);
        guac.addNewItem("red onions", 1, 1);
        guac.addNewItem("tomatoes", 3, 2);
        guac.addNewItem("jalapeno", 3, 1);
        guac.addNewItem("cilantro", 1, 1);

        // get a list of available ingredients from the user
        Inventory userPantry = new Inventory1();

        out.print("add your available ingredients? 'y' or 'n': ");
        String input = in.nextLine();
        while (!input.equals("n")) {
            out.println();

            out.print("ingredient name: ");
            String ingr = in.nextLine();
            out.print("cost for one: ");
            int price = in.nextInteger();
            out.print("how many do you have? ");
            int quantity = in.nextInteger();

            userPantry.addNewItem(ingr, price, quantity);

            out.print("more to add? 'y' or 'n': ");
            input = in.nextLine();
        }
        out.println();

        // compare user inventory to pre-built inventory of guac ingredients
        Map<String, Integer> buy = new Map1L<>();
        userPantry.shoppingList(guac, buy);

        if (buy.size() > 0) {
            out.println("what to buy for guac recipe: ");
            for (Map.Pair<String, Integer> p : buy) {
                out.println(p.key() + " -- " + p.value());
            }

            out.println();
            out.println("go shopping!");
        }

        // have user update their ingredient list until they have enough for the recipe
        while (buy.size() > 0) {
            out.println();

            out.print("ingredient to buy: ");
            String ingr = in.nextLine();
            out.print("how many are you buying? ");
            int quantity = in.nextInteger();
            if (userPantry.hasItem(ingr)) {
                userPantry.incrementQuantity(ingr, quantity);
            } else {
                out.print("cost of one: ");
                int price = in.nextInteger();
                userPantry.addNewItem(ingr, price, quantity);
            }

            buy.clear();
            userPantry.shoppingList(guac, buy);
        }
        out.println();

        // "make" the guac (remove all guac ingredients from user pantry)
        out.print("you now have enough ingredients for the guac recipe! ");
        out.print("hit enter to make it.");
        in.nextLine();

        for (String s : guac.items()) {
            userPantry.decrementQuantity(s, guac.getQuantity(s));
        }

        // report remaining ingredients in user inventory
        out.println();
        out.println("here's what you have left in your pantry: ");

        for (String s : userPantry.items()) {
            out.println(s + " -- " + userPantry.getQuantity(s));
        }

        // report total cost of making the guac
        out.println("you bought " + guac.totalItems()
                + " ingredients for a total of $ " + guac.totalCost()
                + "to make the guac.");

        in.close();
        out.close();
    }

}
