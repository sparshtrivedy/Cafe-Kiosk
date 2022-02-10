// code inspired by JsonTest class in JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob
// /master/src/test/persistence/JsonTest.java

package persistence;

import model.CoffeeOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCoffeeOrder(String drinkName, String drinkSize, String drinkTemp, String drinkBase,
                                    CoffeeOrder coffeeOrder) {
        assertEquals(drinkName, coffeeOrder.getDrinkName());
        assertEquals(drinkSize, coffeeOrder.getDrinkSize());
        assertEquals(drinkTemp, coffeeOrder.getDrinkTemp());
        assertEquals(drinkBase, coffeeOrder.getDrinkBase());
    }
}
