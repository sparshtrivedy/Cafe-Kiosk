package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// this class adds each order to the list of orders, removes orders and returns a
// summary of the orders in the list
public class OrderList implements Writable {
    // fields
    private ArrayList<CoffeeOrder> orderList;

    // constructor
    public OrderList() {
        orderList = new ArrayList<>();
    }

    // getter
    // EFFECTS: returns the order list
    public List<CoffeeOrder> getOrderList() {
        return orderList;
    }

    // EFFECTS: returns the size of the order list
    public int size() {
        return orderList.size();
    }

    // EFFECTS: returns true if the given coffee order is contained within this set
    public boolean contains(CoffeeOrder coffeeOrder) {
        return orderList.contains(coffeeOrder);
    }

    // MODIFIES: this
    // EFFECTS: adds the coffee order to the list of orders
    public void addOrder(CoffeeOrder coffeeOrder) {
        orderList.add(coffeeOrder);
        EventLog.getInstance().logEvent(new Event("Order added"));
    }

    // REQUIRES: orderList is not empty
    // MODIFIES: this
    // EFFECTS: - finds the coffee order with the given drink name
    //              - removes the order from the list of orders
    //              - returns true on successful removal
    //          - else returns false
    public boolean removeOrder(String nm) {
        for (CoffeeOrder coffeeOrder : orderList) {
            if (coffeeOrder.getDrinkName().equals(nm)) {
                orderList.remove(coffeeOrder);
                EventLog.getInstance().logEvent(new Event("Order removed"));
                return true;
            }
        }
        return false;
    }

    // REQUIRES: orderList is not empty
    // EFFECTS: returns the order cart, a list with size, temperature, name and base of every drink
    public String returnCart() {
        int index = 0;
        String cart = "";
        for (CoffeeOrder coffeeOrder : orderList) {
            index++;
            cart += ("\nItem " + index + ".) " + coffeeOrder.getDrinkSize() + " " + coffeeOrder.getDrinkTemp()
                    + " " + coffeeOrder.getDrinkName() + " in " + coffeeOrder.getDrinkBase() + "\n");
            cart += "<html><br><html>";
        }
        return cart;
    }

    // REQUIRES: orderList is not empty
    // EFFECTS: returns total cost of drinks (excluding tax)
    public double returnTotal() {
        double total = 0;
        for (CoffeeOrder coffeeOrder : orderList) {
            total += coffeeOrder.getTotalBill();
        }
        return total;
    }

    // REQUIRES: orderList is not empty
    // EFFECTS: returns the order summary
    public String returnList() {
        int index = 0;
        String sum = "";
        for (CoffeeOrder coffeeOrder : orderList) {
            index++;
            sum += ("\n" + index + ".) " + coffeeOrder.getDrinkName() + "/" + coffeeOrder.getDrinkSize()
                    + "/" + coffeeOrder.getDrinkTemp() + "/" + coffeeOrder.getDrinkBase() + "\n");
            sum += "<html><br><html>";
            sum += ("\n-------------------$" + coffeeOrder.getTotalBill() + "\n");
            sum += "<html><br><html>";
        }
        return sum;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("orders", orderToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray orderToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CoffeeOrder o : orderList) {
            jsonArray.put(o.toJson());
        }

        return jsonArray;
    }
}