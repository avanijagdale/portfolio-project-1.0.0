package components.inventory;
import components.map.Map;

/**
 * Inventory enhanced with secondary methods.
 *
 */
public interface Inventory extends InventoryKernel {

    /**
     * Increases the quantity of an item in this by the specified amount.
     *
     * @param name
     *            name of quantity
     * @param q
     *            number of the item to add
     * @updates this
     * @requires name is in this && q > 0
     * @ensures this.getQuantity(name) = #this.getQuantity(name) + x
     */
    void incrementQuantity(String name, int q);

    /**
     * Decreases the quantity of an item in this by the specified amount,
     * removes the item entirely if given quantity is equal to available
     * quantity.
     *
     * @param name
     *            name of quantity
     * @param q
     *            number of the item to remove
     * @updates this
     * @requires name is in this && q > 0 && q <= initial quantity
     * @ensures this.getQuantity(name) = #this.getQuantity(name) - x, or this =
     *          #this \ name if q = #this.getQuantity(name)
     */
    void decrementQuantity(String name, int q);

    /**
     * Reports how many items are in this.
     *
     * @return the total of the quantity of each item in this
     * @ensures method returns the sum of each items quantity
     */
    int totalItems();

    /**
     * Reports how much each item in this costs in total.
     *
     * @return the total price of all items in this
     * @ensures method returns the sum of each items quantity multiplied by its
     *          unit price
     */
    int totalCost();

    /**
     * Populates a map of all the items that either are in other but not in
     * this, or that this does not have enough of.
     *
     * @param other
     *            the inventory of needed items
     * @param toBuy
     *            the item -> quantity needed map
     * @updates toBuy
     * @requires other is not null && toBuy is not null && toBuy is empty
     * @ensures toBuy maps the names of all items that this does not have enough
     *          of to the quantity needed to match how many of that item are in
     *          other
     */
    void shoppingList(Inventory other, Map<String, Integer> toBuy);

}
