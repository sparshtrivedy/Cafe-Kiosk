// code inspired by JsonWriterTest class in JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob
// /master/src/test/persistence/JsonWriterTest.java

package persistence;

import model.CoffeeOrder;
import model.OrderList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// tests the writer for the orderlist
class JsonWriterTest extends JsonTest {

    // tests the writer for an invalid file
    @Test
    void testWriterInvalidFile() {
        try {
            OrderList orderList = new OrderList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // tests the writer for an empty order list
    @Test
    void testWriterEmptyOrderList() {
        try {
            OrderList orderList = new OrderList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrderList.json");
            writer.open();
            writer.write(orderList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrderList.json");
            orderList = reader.read();
            assertEquals(0, orderList.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // tests the writer for a regular orderlist with orders
    @Test
    void testWriterGeneralOrderList() {
        try {
            OrderList orderList = new OrderList();
            orderList.addOrder(new CoffeeOrder("Latte", "small", "hot",
                    "oat milk"));
            orderList.addOrder(new CoffeeOrder("Americano", "large", "iced",
                    "soy milk"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(orderList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralOrderList.json");
            orderList = reader.read();
            List<CoffeeOrder> orders = orderList.getOrderList();
            assertEquals(2, orders.size());
            checkCoffeeOrder("Latte", "small", "hot", "oat milk",
                    orders.get(0));
            checkCoffeeOrder("Americano", "large", "iced", "soy milk",
                    orders.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
