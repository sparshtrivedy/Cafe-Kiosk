package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// this class tests the OrderList class
public class OrderListTest {
    private OrderList testOrderList;
    private CoffeeOrder o1;
    private CoffeeOrder o2;
    private CoffeeOrder o3;
    private CoffeeOrder o4;

    @BeforeEach
    void setTestOrderList() {
        testOrderList = new OrderList();
        o1 = new CoffeeOrder("Latte","small","iced","oat milk");
        o2 = new CoffeeOrder("Mocha","medium","hot","almond milk");
        o3 = new CoffeeOrder("Americano","large","hot","2% cow's milk");
        o4 = new CoffeeOrder("Cappuccino","small","iced","soy milk");
    }

    // tests if orders are correctly added to the order list
    @Test
    void testAddOrder() {
        testOrderList.addOrder(o1);
        assertEquals(1, testOrderList.size());
        assertTrue(testOrderList.contains(o1));
        testOrderList.addOrder(o2);
        assertEquals(2, testOrderList.size());
        assertTrue(testOrderList.contains(o2));
        testOrderList.addOrder(o3);
        assertEquals(3, testOrderList.size());
        assertTrue(testOrderList.contains(o3));
        testOrderList.addOrder(o4);
        assertEquals(4, testOrderList.size());
        assertTrue(testOrderList.contains(o4));
    }

    // tests for when order to be removed exists in the list
    @Test
    void testRemoveExistingOrder() {
        testOrderList.addOrder(o1);
        testOrderList.addOrder(o2);
        testOrderList.addOrder(o3);
        assertTrue(testOrderList.removeOrder("Latte"));
        assertEquals(2, testOrderList.size());
        assertFalse(testOrderList.contains(o1));
        assertTrue(testOrderList.contains(o2));
        assertTrue(testOrderList.contains(o3));
        assertTrue(testOrderList.removeOrder("Mocha"));
        assertTrue(testOrderList.removeOrder("Americano"));
        assertEquals(0, testOrderList.size());
        assertFalse(testOrderList.contains(o4));
        assertFalse(testOrderList.contains(o3));
        assertFalse(testOrderList.contains(o2));
    }

    // tests for when order to be removed does not exist in the list
    @Test
    void testRemoveNonExistingOrder() {
        testOrderList.addOrder(o1);
        testOrderList.addOrder(o2);
        testOrderList.addOrder(o3);
        assertFalse(testOrderList.removeOrder("Chai"));
        assertTrue(testOrderList.removeOrder("Latte"));
        assertFalse(testOrderList.removeOrder("Latte"));
    }

    // tests if total bill amount is correctly assigned
    @Test
    void testListSummary() {
        assertEquals(0, testOrderList.returnTotal());
        testOrderList.addOrder(o1);
        testOrderList.addOrder(o2);
        assertEquals(o1.getTotalBill()+ o2.getTotalBill(), testOrderList.returnTotal());
    }

    // tests if shopping cart contents are correctly returned
    @Test
    void testReturnCart() {
        testOrderList.addOrder(o1);
        assertEquals("\nItem 1.) small iced Latte in oat milk\n<html><br><html>",testOrderList.returnCart());
        testOrderList.addOrder(o2);
        assertEquals("\nItem 1.) small iced Latte in oat milk\n<html><br><html>\n"
                        + "Item 2.) medium hot Mocha in almond milk\n<html><br><html>",
                testOrderList.returnCart());
        testOrderList.addOrder(o3);
        testOrderList.addOrder(o4);
        assertEquals("\nItem 1.) small iced Latte in oat milk\n<html><br><html>\n"
                        + "Item 2.) medium hot Mocha in almond milk\n<html><br><html>\n"
                        + "Item 3.) large hot Americano in 2% cow's milk\n<html><br><html>\n"
                        + "Item 4.) small iced Cappuccino in soy milk\n<html><br><html>", testOrderList.returnCart());
    }

    // tests returnList with single and multiple inputs to list
    @Test
    void testReturnList() {
        testOrderList.addOrder(o1);
        assertEquals("\n1.) Latte/small/iced/oat milk"
                + "\n<html><br><html>\n-------------------$0.0\n<html><br><html>", testOrderList.returnList());
        testOrderList.addOrder(o2);
        assertEquals("\n1.) Latte/small/iced/oat milk"
                + "\n<html><br><html>\n-------------------$0.0\n<html><br><html>"
                + "\n2.) Mocha/medium/hot/almond milk"
                + "\n<html><br><html>\n-------------------$0.0\n<html><br><html>", testOrderList.returnList());
        testOrderList.addOrder(o3);
        testOrderList.addOrder(o4);
        assertEquals("\n1.) Latte/small/iced/oat milk"
                + "\n<html><br><html>\n-------------------$0.0\n<html><br><html>"
                + "\n2.) Mocha/medium/hot/almond milk"
                + "\n<html><br><html>\n-------------------$0.0\n<html><br><html>"
                + "\n3.) Americano/large/hot/2% cow's milk"
                + "\n<html><br><html>\n-------------------$0.0\n<html><br><html>"
                + "\n4.) Cappuccino/small/iced/soy milk"
                + "\n<html><br><html>\n-------------------$0.0\n<html><br><html>", testOrderList.returnList());
    }
}

