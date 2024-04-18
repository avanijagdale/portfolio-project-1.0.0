import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.inventory.Inventory;
import components.inventory.Inventory1;
import components.map.Map;
import components.map.Map1L;

/**
 * Customized JUnit test fixture for Inventory secondary methods.
 */
public class InventoryTest {

    /**
     * Test incrementQuantity method.
     */
    @Test
    public void testIncrement() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 1);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 1, 6);

        test.incrementQuantity("apples", 5);

        assertEquals(6, test.getQuantity("apples"));
        assertEquals(copy, test);
    }

    /**
     * Test decrementQuantity method.
     */
    @Test
    public void testDecrement() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 4);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 1, 2);

        test.decrementQuantity("apples", 2);

        assertEquals(2, test.getQuantity("apples"));
        assertEquals(copy, test);
    }

    /**
     * Test decrementQuantity method with an example where all quantities of an
     * item are removed.
     */
    @Test
    public void testDecrementAll() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 4);
        test.addNewItem("oranges", 1, 2);

        Inventory copy = new Inventory1();
        copy.addNewItem("oranges", 1, 2);

        test.decrementQuantity("apples", 4);

        assertEquals(copy, test);
    }

    /**
     * Test totalItems method.
     */
    @Test
    public void testTotalItems() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 10);
        test.addNewItem("oranges", 2, 7);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 1, 10);
        copy.addNewItem("oranges", 2, 7);

        assertEquals(17, test.totalItems());
        assertEquals(copy, test);
    }

    /**
     * Test totalCost method.
     */
    @Test
    public void testTotalCost() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 10);
        test.addNewItem("oranges", 2, 7);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 1, 10);
        copy.addNewItem("oranges", 2, 7);

        assertEquals(24, test.totalCost());
        assertEquals(copy, test);
    }

    /**
     * Test shoppingList method.
     */
    @Test
    public void testShoppingList() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 10);
        test.addNewItem("oranges", 2, 7);

        Inventory testCopy = new Inventory1();
        testCopy.addNewItem("apples", 1, 10);
        testCopy.addNewItem("oranges", 2, 7);

        Inventory need = new Inventory1();
        need.addNewItem("apples", 1, 15);
        need.addNewItem("bananas", 3, 4);

        Inventory needCopy = new Inventory1();
        needCopy.addNewItem("apples", 1, 15);
        needCopy.addNewItem("bananas", 3, 4);

        Map<String, Integer> testMap = new Map1L<>();
        test.shoppingList(need, testMap);

        Map<String, Integer> expected = new Map1L<>();
        expected.add("bananas", 4);
        expected.add("apples", 5);

        assertEquals(testCopy, test);
        assertEquals(needCopy, need);
        assertEquals(expected, testMap);

    }

}
