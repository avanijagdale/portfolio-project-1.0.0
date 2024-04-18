package components.inventory;
import components.standard.Standard;

/**
 * Inventory kernel component with primary methods.
 *
 */
public interface InventoryKernel extends Standard<Inventory> {

    /**
     * Adds a new item to this, then sets its unit price and initial quantity.
     *
     * @param name
     *            the name of the item to be added
     * @param price
     *            unit price of the item
     * @param quantity
     *            initial quantity of the item
     * @updates this
     * @requires name is not in this && q > 0 && price >= 0
     * @ensures this contains the given quantity of an item with the given name
     *          and price
     */
    void addNewItem(String name, int price, int quantity);

    /**
     * Updates the quantity of the item with the given name.
     *
     * @param name
     *            name of the item whose quantity is being changed
     * @param quantity
     *            new quantity of item
     * @updates this
     * @requires name is in this && quantity > 0
     * @ensures this.getQuantity(name) = quantity
     */
    void setQuantity(String name, int quantity);

    /**
     * Reports how many of the item with the given name are in this.
     *
     * @param name
     *            the name of the item whose quantity is being reported
     * @return the item quantity
     * @requires name is in this
     * @ensures method returns the quantity associated with the given item
     */
    int getQuantity(String name);

    /**
     * Updates the unit price of the item with the given name.
     *
     * @param name
     *            name of the item whose price is being changed
     * @param price
     *            new price of the item
     * @requires name is in this && price >= 0
     * @ensures this.getPrice(name) = price
     */
    void setPrice(String name, int price);

    /**
     * Reports the unit price of the item with the given name.
     *
     * @param name
     *            the name of the item whose price is being reported
     * @return the price of the item
     * @requires name is in this
     * @ensures method returns the correct unit price of the given item
     */
    int getPrice(String name);

    /**
     * Removes all items with the given name from this.
     *
     * @param name
     *            name of item being removed
     * @updates this
     * @return the quantity of the item that was previously in this
     * @requires name is in this
     * @ensures this = #this \ name
     */
    int removeAll(String name);

    /**
     * Reports whether an item with the given name is in this.
     *
     * @param name
     *            the name of the item being checked for.
     * @return whether or not name is in this
     * @ensures hasItem = true if name is in this and false if not.
     */
    boolean hasItem(String name);

    /**
     * Returns a list of each item name in this.
     *
     * @return an array containing the name of each distinct item in this
     * @ensures all elements of the returned array are names of items in this &&
     *          |items| = |this|
     */
    String[] items();

    /**
     * Reports size of this.
     *
     * @return the number of unique items in this
     * @ensures size = |this|
     */
    int size();

}
