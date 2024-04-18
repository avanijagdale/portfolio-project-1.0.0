import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.inventory.Inventory;
import components.inventory.Inventory1;
import components.set.Set;
import components.set.Set1L;

/**
 * Customized JUnit test fixture for Inventory kernel methods.
 */
public class Inventory1Test {

    /**
     * Test addNewItem method.
     */
    @Test
    public void testAddNewItem() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 2, 3);

        assertEquals(true, test.hasItem("apples"));
        assertEquals(3, test.getQuantity("apples"));
        assertEquals(2, test.getPrice("apples"));
    }

    /**
     * Test addNewItem method and add multiple items.
     */
    @Test
    public void testAddNewItem2() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 2, 3);
        test.addNewItem("oranges", 4, 5);

        assertEquals(true, test.hasItem("apples"));
        assertEquals(3, test.getQuantity("apples"));
        assertEquals(2, test.getPrice("apples"));

        assertEquals(true, test.hasItem("oranges"));
        assertEquals(5, test.getQuantity("oranges"));
        assertEquals(4, test.getPrice("oranges"));
    }

    /**
     * Test setQuantity method.
     */
    @Test
    public void testSetQuant() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 2, 1);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 2, 4);

        test.setQuantity("apples", 4);

        assertEquals(copy, test);
    }

    /**
     * Test getQuantity method.
     */
    @Test
    public void testGetQuant() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 2, 4);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 2, 4);

        assertEquals(4, test.getQuantity("apples"));
        assertEquals(copy, test);
    }

    /**
     * Test setPrice method.
     */
    @Test
    public void testSetPrice() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 8, 4);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 2, 4);

        test.setPrice("apples", 2);

        assertEquals(copy, test);
    }

    /**
     * Test getPrice method.
     */
    @Test
    public void testGetPrice() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 2, 4);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 2, 4);

        assertEquals(2, test.getPrice("apples"));
        assertEquals(copy, test);
    }

    /**
     * Test removeAll method.
     */
    @Test
    public void testRemoveAll() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 10);
        test.addNewItem("oranges", 2, 7);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 1, 10);

        int q = test.removeAll("oranges");

        assertEquals(7, q);
        assertEquals(copy, test);
    }

    /**
     * Test removeAll method on inventory with one item.
     */
    @Test
    public void testRemoveAllLeftEmpty() {
        Inventory test = new Inventory1();
        test.addNewItem("oranges", 2, 7);

        Inventory copy = new Inventory1();

        int q = test.removeAll("oranges");

        assertEquals(7, q);
        assertEquals(copy, test);
    }

    /**
     * Test hasItem method with result = false.
     */
    @Test
    public void testHasItemFalse() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 10);
        test.addNewItem("oranges", 2, 7);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 1, 10);
        copy.addNewItem("oranges", 2, 7);

        assertEquals(false, test.hasItem("bananas"));
        assertEquals(copy, test);
    }

    /**
     * Test hasItem method with result = true.
     */
    @Test
    public void testHasItem() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 10);
        test.addNewItem("oranges", 2, 7);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 1, 10);
        copy.addNewItem("oranges", 2, 7);

        assertEquals(true, test.hasItem("oranges"));
        assertEquals(copy, test);
    }

    /**
     * Test items method.
     */
    @Test
    public void testItems() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 10);
        test.addNewItem("oranges", 2, 7);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 1, 10);
        copy.addNewItem("oranges", 2, 7);

        Set<String> exp = new Set1L<>();
        exp.add("oranges");
        exp.add("apples");

        Set<String> items = new Set1L<>();
        String[] list = test.items();
        for (String s : list) {
            items.add(s);
        }

        assertEquals(exp, items);
        assertEquals(copy, test);
    }

    /**
     * Test size method.
     */
    @Test
    public void testSize() {
        Inventory test = new Inventory1();
        test.addNewItem("apples", 1, 10);
        test.addNewItem("oranges", 2, 7);

        Inventory copy = new Inventory1();
        copy.addNewItem("apples", 1, 10);
        copy.addNewItem("oranges", 2, 7);

        assertEquals(2, test.size());
        assertEquals(copy, test);
    }

    /**
     * Test size method with empty inventory.
     */
    @Test
    public void testSizeEmpty() {
        Inventory test = new Inventory1();
        Inventory copy = new Inventory1();

        assertEquals(0, test.size());
        assertEquals(copy, test);
    }

}
