package components.inventory;
import components.map.Map;
import components.map.Map1L;

/**
 * Inventory represented as a Map that maps items to their quantities.
 *
 * @convention $this.rep maps items to an integer quantity, and each item is
 *             represented by a string for item name and an integer for unit
 *             price
 * @correspondence this = entries($this.rep)
 */
public class Inventory1 extends InventorySecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Item class for inventory items.
     */
    public static final class Item {

        /**
         * Name of item.
         */
        private String name;

        /**
         * Unit price for item.
         */
        private int price;

    }

    /**
     * Items included in this, mapped to their quantity.
     */
    private Map<Item, Integer> rep;

    /**
     * Helper method that returns the inventory item with the given name.
     *
     * @param name
     *            the name of the item to find
     * @return the item in this.rep whose name field is equal to the argument
     *         passed
     */
    private Item findItemFromName(String name) {
        Item i = new Item();
        for (Map.Pair<Item, Integer> p : this.rep) {
            String s = p.key().name;
            if (name.equals(s)) {
                i = p.key();
            }
        }
        return i;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Map1L<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Inventory1() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public Inventory newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public void transferFrom(Inventory source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Inventory1 : ""
                + "Violation of: source is of dynamic type Map2<?,?>";

        Inventory1 localSource = (Inventory1) source;
        this.rep = localSource.rep;
        localSource.createNewRep();

    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public void addNewItem(String name, int price, int quantity) {
        assert !this.hasItem(name) : "Violation of: name is not in this";
        assert price >= 0 : "Violation of: price >= 0";
        assert quantity > 0 : "Violation of price > 0";

        Item i = new Item();
        i.name = name;
        i.price = price;
        this.rep.add(i, quantity);

    }

    @Override
    public void setQuantity(String name, int quantity) {
        assert this.hasItem(name) : "Violation of: name is in this";
        assert quantity > 0 : "Violation of: quantity > 0";

        Item i = this.findItemFromName(name);
        this.rep.remove(i);
        this.rep.add(i, quantity);

    }

    @Override
    public int getQuantity(String name) {
        assert this.hasItem(name) : "Violation of: name is in this";

        Item i = this.findItemFromName(name);
        int q = this.rep.value(i);
        return q;
    }

    @Override
    public void setPrice(String name, int price) {
        assert this.hasItem(name) : "Violation of: name is in this";
        assert price >= 0 : "Violation of: price >= 0";

        Item i = this.findItemFromName(name);
        Map.Pair<Item, Integer> r = this.rep.remove(i);
        i.price = price;
        this.rep.add(i, r.value());

    }

    @Override
    public int getPrice(String name) {
        assert this.hasItem(name) : "Violation of: name is in this";

        Item i = this.findItemFromName(name);
        return i.price;
    }

    @Override
    public int removeAll(String name) {
        assert this.hasItem(name) : "Violation of: name is in this";

        Item i = this.findItemFromName(name);
        Map.Pair<Item, Integer> p = this.rep.remove(i);
        return p.value();
    }

    @Override
    public boolean hasItem(String name) {
        boolean itemFound = false;
        for (Map.Pair<Item, Integer> p : this.rep) {
            itemFound = itemFound || name.equals(p.key().name);
        }
        return itemFound;
    }

    @Override
    public String[] items() {
        String[] itemList = new String[this.rep.size()];

        int i = 0;
        for (Map.Pair<Item, Integer> p : this.rep) {
            itemList[i] = p.key().name;
            i++;
        }
        return itemList;
    }

    @Override
    public int size() {
        return this.rep.size();
    }

}
