package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// this class tests the methods in the CoffeeOrder class
public class CoffeeOrderTest {
    private CoffeeOrder testCoffeeOrder;

    @BeforeEach
    void setCoffeeOrderTest() {
        testCoffeeOrder = new CoffeeOrder("latte", "medium", "iced", "oat milk");
    }

    // tests the constructor
    @Test
    void testCoffeeOrderConstructor() {
        assertEquals(0,testCoffeeOrder.getTotalBill());
        assertEquals("latte",testCoffeeOrder.getDrinkName());
        assertEquals("medium",testCoffeeOrder.getDrinkSize());
        assertEquals("iced",testCoffeeOrder.getDrinkTemp());
        assertEquals("oat milk",testCoffeeOrder.getDrinkBase());
    }

    // tests if the drink name is correctly assigned
    @Test
    void testSetDrinkName() {
        testCoffeeOrder.setDrinkName("b");
        assertEquals("Mocha",testCoffeeOrder.getDrinkName());
        assertEquals(4.5,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setDrinkName("c");
        assertEquals("Americano",testCoffeeOrder.getDrinkName());
        assertEquals(4.5+3.0,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setDrinkName("d");
        assertEquals("Cappuccino",testCoffeeOrder.getDrinkName());
        assertEquals(4.5+3.0+4.0,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setDrinkName("a");
        assertEquals("Latte",testCoffeeOrder.getDrinkName());
        assertEquals(4.5+3.0+4.0+3.5,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setDrinkName("f");
        assertEquals("Latte",testCoffeeOrder.getDrinkName());
        assertEquals(4.5+3.0+4.0+3.5,testCoffeeOrder.getTotalBill());
    }

    // tests if the drink size is correctly assigned
    @Test
    void testSetDrinkSize() {
        testCoffeeOrder.setTotalBill(100);
        testCoffeeOrder.setDrinkSize("a");
        assertEquals("small",testCoffeeOrder.getDrinkSize());
        assertEquals(100,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setTotalBill(100);
        testCoffeeOrder.setDrinkSize("b");
        assertEquals("medium",testCoffeeOrder.getDrinkSize());
        assertEquals(110,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setTotalBill(100);
        testCoffeeOrder.setDrinkSize("c");
        assertEquals("large",testCoffeeOrder.getDrinkSize());
        assertEquals(120,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setDrinkSize("d");
        assertEquals("large",testCoffeeOrder.getDrinkSize());
        assertEquals(120,testCoffeeOrder.getTotalBill());
    }

    // tests if the drink temperature is correctly assigned
    @Test
    void testSetDrinkTemp() {
        testCoffeeOrder.setDrinkTemp("a");
        assertEquals("hot",testCoffeeOrder.getDrinkTemp());
        testCoffeeOrder.setDrinkTemp("b");
        assertEquals("iced",testCoffeeOrder.getDrinkTemp());
        testCoffeeOrder.setDrinkTemp("c");
        assertEquals("iced",testCoffeeOrder.getDrinkTemp());
    }

    // tests if the drink base is correctly assigned
    @Test
    void testSetDrinkBase() {
        testCoffeeOrder.setDrinkBase("a");
        assertEquals("2% cow's milk",testCoffeeOrder.getDrinkBase());
        assertEquals(0.15,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setDrinkBase("b");
        assertEquals("oat milk",testCoffeeOrder.getDrinkBase());
        assertEquals(0.15+0.50,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setDrinkBase("c");
        assertEquals("almond milk",testCoffeeOrder.getDrinkBase());
        assertEquals(0.15+0.50+0.45,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setDrinkBase("d");
        assertEquals("soy milk",testCoffeeOrder.getDrinkBase());
        assertEquals(0.15+0.50+0.45+0.75,testCoffeeOrder.getTotalBill());
        testCoffeeOrder.setDrinkBase("e");
        assertEquals("soy milk",testCoffeeOrder.getDrinkBase());
        assertEquals(0.15+0.50+0.45+0.75,testCoffeeOrder.getTotalBill());
    }
}
