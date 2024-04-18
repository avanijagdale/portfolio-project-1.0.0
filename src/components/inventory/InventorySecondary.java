package components.inventory;
import components.map.Map;

/**
 * Layered implementations of secondary methods for Inventory.
 */
public abstract class InventorySecondary implements Inventory {
    /*
     * Public members ---------------------------------------------------------
     */

    /*
     * Common methods (from Object) -------------------------------------------
     */

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("( ");
        int i = 0;
        for (String s : this.items()) {
            result.append("[");
            String item = "$" + this.getPrice(s) + "] " + s + " -> "
                    + this.getQuantity(s);
            result.append(item);
            i++;
            if (i < this.items().length) {
                result.append(", ");
            }
        }
        result.append(" )");
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Inventory : ""
                + "Violation of: dynamic type of obj implements Inventory";

        if (this == obj) {
            return true;
        }

        Inventory other = (Inventory1) obj;

        boolean eq = other.size() == this.size();

        int index = 0;
        String[] items = this.items();
        while (eq && index < items.length) {
            String i = items[index];
            if (other.hasItem(i)) {
                eq = other.getQuantity(i) == this.getQuantity(i)
                        && other.getPrice(i) == this.getPrice(i);
            } else {
                eq = false;
            }
            index++;
        }

        return eq;
    }

    @Override
    public int hashCode() {
        return this.size();
    }

    /*
     * Other non-kernel methods -----------------------------------------------
     */

    @Override
    public void incrementQuantity(String name, int q) {
        assert this.hasItem(name) : "Violation of: name is in this";
        assert q > 0 : "Violation of: q > 0";

        int current = this.getQuantity(name);
        this.setQuantity(name, current + q);
    }

    @Override
    public void decrementQuantity(String name, int q) {
        assert this.hasItem(name) : "Violation of: name is in this";
        assert q > 0 : "Violation of: q > 0";
        assert q <= this
                .getQuantity(name) : "Violation of: q <= initial quantity";

        int current = this.getQuantity(name);
        if (q == current) {
            this.removeAll(name);
        } else {
            this.setQuantity(name, current - q);
        }
    }

    @Override
    public int totalItems() {
        int total = 0;
        for (String i : this.items()) {
            total += this.getQuantity(i);
        }
        return total;
    }

    @Override
    public int totalCost() {
        int total = 0;
        for (String i : this.items()) {
            total += this.getQuantity(i) * this.getPrice(i);
        }
        return total;
    }

    @Override
    public void shoppingList(Inventory other, Map<String, Integer> toBuy) {
        assert other != null : "Violation of: other is not null";
        assert toBuy != null : "Violation of: toBuy is not null";
        assert toBuy.size() == 0 : "Violation of : toBuy is empty";

        for (String i : other.items()) {
            int q = other.getQuantity(i);
            if (!this.hasItem(i)) {
                toBuy.add(i, q);
            } else {
                int current = this.getQuantity(i);
                if (current < q) {
                    toBuy.add(i, q - current);
                }
            }
        }
    }
}
