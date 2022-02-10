// code inspired by JsonReaderTest class in JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob
// /master/src/test/persistence/JsonReaderTest.java

package persistence;

import model.CoffeeOrder;
import model.OrderList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// tests the reader
public class JsonReaderTest extends JsonTest {

    // tests the reader for a non-existent file
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            OrderList orderList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // tests the reader for an empty orderlist
    @Test
    void testReaderEmptyOrderList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyOrderList.json");
        try {
            OrderList orderList = reader.read();
            assertEquals(0, orderList.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // tests the reader for a regular orderlist with orders
    @Test
    void testReaderGeneralOrderList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralOrderList.json");
        try {
            OrderList orderList = reader.read();
            List<CoffeeOrder> orders = orderList.getOrderList();
            assertEquals(2, orders.size());
            checkCoffeeOrder("Latte", "small", "hot", "oat milk",
                    orders.get(0));
            checkCoffeeOrder("Americano", "large", "iced", "soy milk",
                    orders.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
