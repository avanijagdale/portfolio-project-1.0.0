import components.inventory.Inventory;
import components.inventory.Inventory1;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A class that models a pizza shop using three different inventory objects: one
 * for sauces, one for cheeses, and one for toppings. One quantity of an item is
 * equal to the amount of that item needed for one pizza.
 *
 */
public class PizzaShop {

    /**
     * An inventory of all available toppings(e.g. [$1] pepperoni -> 300, [$2]
     * olives -> 250; means the store can make 300 pepperoni pizzas, etc).
     */
    private Inventory toppings;

    /**
     * An inventory of all available sauces (e.g. [$2] red sauce -> 450, [$3]
     * olive oil -> 255)
     */
    private Inventory sauces;

    /**
     * An inventory of all available cheeses (e.g. [$3] shredded mozzarella ->
     * 260, [$4] fresh mozzarella -> 73)
     */
    private Inventory cheese;

    /**
     * Constructor, takes three Inventory objects as arguments.
     *
     * @param toppings
     *            all available toppings in the store
     * @param sauces
     *            all available sauces in the store
     * @param cheese
     *            all available cheese types in the store
     *
     */
    public PizzaShop(Inventory sauces, Inventory cheese, Inventory toppings) {
        this.toppings = toppings;
        this.sauces = sauces;
        this.cheese = cheese;
    }

    /**
     * Takes a pizza order that consists of one type of sauce, one type of
     * cheese, and a list of toppings; remove one of each from their respective
     * inventories.
     *
     * @param sauce
     *            type of sauce
     * @param cheese
     *            type of cheese
     * @param toppings
     *            list of toppings
     * @requires canMakePizza(sauce, cheese, toppings) is true
     */
    public void makePizza(String sauce, String cheese, String[] toppings) {
        this.sauces.decrementQuantity(sauce, 1);
        this.cheese.decrementQuantity(cheese, 1);

        for (String t : toppings) {
            this.toppings.decrementQuantity(t, 1);
        }
    }

    /**
     * The total cost of making the given pizza order.
     *
     * @param sauce
     *            type of sauce
     * @param cheese
     *            type of cheese
     * @param toppings
     *            list of toppings
     * @requires canMakePizza(sauce, cheese, toppings) is true
     * @return total price of each item in the pizza
     */
    public int costOfOrder(String sauce, String cheese, String[] toppings) {
        int price = this.sauces.getPrice(sauce) + this.cheese.getPrice(cheese);
        for (String t : toppings) {
            price += this.toppings.getPrice(t);
        }
        return price;
    }

    /**
     * Checks if there are enough ingredients in stock to make the provided
     * order (at least one of each ingredient must be available).
     *
     * @param sauce
     *            type of sauce
     * @param cheese
     *            type of cheese
     * @param toppings
     *            list of toppings
     * @return true if there are enough ingredients in the store
     */
    public boolean canMakePizza(String sauce, String cheese,
            String[] toppings) {
        boolean enoughIngredients = this.sauces.hasItem(sauce)
                && this.cheese.hasItem(cheese);
        if (enoughIngredients) {
            for (String t : toppings) {
                enoughIngredients = enoughIngredients
                        && this.toppings.hasItem(t);
            }
        }
        return enoughIngredients;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        Inventory sauce = new Inventory1();
        sauce.addNewItem("red sauce", 2, 20);

        Inventory cheese = new Inventory1();
        cheese.addNewItem("shredded mozarella", 3, 40);
        cheese.addNewItem("feta", 4, 5);

        Inventory toppings = new Inventory1();
        toppings.addNewItem("pepperoni", 3, 15);
        toppings.addNewItem("black olives", 2, 8);
        toppings.addNewItem("mushrooms", 3, 1);

        PizzaShop ps = new PizzaShop(sauce, cheese, toppings);

        SimpleWriter out = new SimpleWriter1L();
        String[] tList = { "pepperoni", "ham", "pineapple" };
        String s = "alfredo", c = "shredded mozarella";
        out.print("can make pizza with " + s + ", " + c + " and ");
        for (int i = 0; i < tList.length; i++) {
            out.print(tList[i]);
            if (i != tList.length - 1) {
                out.print(", ");
            } else {
                out.print(": ");
            }
        }
        out.println(ps.canMakePizza(s, c, tList));

        String[] tList2 = { "mushrooms", "black olives" };
        s = "red sauce";
        c = "shredded mozarella";
        out.print("can make pizza with " + s + ", " + c + " and ");
        for (int i = 0; i < tList2.length; i++) {
            out.print(tList2[i]);
            if (i != tList2.length - 1) {
                out.print(", ");
            } else {
                out.print(": ");
            }
        }
        out.println(ps.canMakePizza(s, c, tList2));
        out.print("order cost: $" + ps.costOfOrder(s, c, tList2));
        ps.makePizza(s, c, tList2);

        out.close();
    }

}
