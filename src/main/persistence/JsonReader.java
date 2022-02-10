// code inspired by JsonReader class in JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob
// /master/src/main/persistence/JsonReader.java

package persistence;

import model.CoffeeOrder;
import model.OrderList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads orderlist from JSON data stored in file
public class JsonReader {
    // field
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads orderlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public OrderList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrderList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses orderlist from JSON object and returns it
    private OrderList parseOrderList(JSONObject jsonObject) {
        OrderList ol = new OrderList();
        addOrders(ol, jsonObject);
        return ol;
    }

    // MODIFIES: ol
    // EFFECTS: parses order from JSON object and adds them to orderlist
    private void addOrders(OrderList ol, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("orders");
        for (Object json : jsonArray) {
            JSONObject nextCoffeeOrder = (JSONObject) json;
            addOrder(ol, nextCoffeeOrder);
        }
    }

    // MODIFIES: ol
    // EFFECTS: parses order from JSON object and adds it to orderlist
    private void addOrder(OrderList ol, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String size = jsonObject.getString("size");
        String temp = jsonObject.getString("temp");
        String base = jsonObject.getString("base");
        CoffeeOrder order = new CoffeeOrder(name, size, temp, base);
        ol.addOrder(order);
    }
}