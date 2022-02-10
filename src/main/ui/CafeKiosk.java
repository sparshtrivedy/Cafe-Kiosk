package ui;

import model.CoffeeOrder;
import model.Customer;
import model.OrderList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// this class sets up the user interaction with the application and is
// responsible for how the application runs as a whole
public class CafeKiosk {
    private static final String JSON_STORE = "./data/orderlist.json";
    private static final double SALESTAX = 0.15;
    protected Scanner input;
    private Customer customer;
    private CoffeeOrder coffeeOrder;
    private OrderList orderList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the cafe kiosk application
    public CafeKiosk() throws FileNotFoundException {
        runBarista();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBarista() {
        System.out.println("\nHello and welcome to our caffe!");

        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
                System.out.println("Goodbye!");
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            coffeeOrder = new CoffeeOrder(null, null,null,null);
            chooseDrinkSize();
            chooseDrinkName();
            chooseDrinkTemp();
            chooseDrinkBase();
            orderList.addOrder(coffeeOrder);
        } else if (command.equals("b")) {
            System.out.println("\nWhich drink would you like to remove?");
            String name = input.next();
            orderList.removeOrder(name);
        } else if (command.equals("c")) {
            System.out.println("\nHere is what you have so far...");
            System.out.println(orderList.returnCart());
        } else if (command.equals("d")) {
            generateBill(orderList);
        } else if (command.equals("s")) {
            saveOrderList();
        } else if (command.equals("l")) {
            loadOrderList();
        } else {
            System.out.println("\nSelection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        customer = new Customer(null, 0);
        orderList = new OrderList();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a drink to my cart");
        System.out.println("\tb -> Remove a drink from my cart");
        System.out.println("\tc -> Check items in my cart");
        System.out.println("\td -> Place order and generate my bill");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: prompts user to choose a drink
    private void chooseDrinkName() {
        System.out.println("\nWhich drink would you like?");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Latte");
        System.out.println("\tb -> Mocha");
        System.out.println("\tc -> Americano");
        System.out.println("\td -> Cappuccino");
        String command = input.next();
        coffeeOrder.setDrinkName(command);
    }

    // EFFECTS: prompts user to choose a drink size
    private void chooseDrinkSize() {
        System.out.println("\nWhich size drink would you like?");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Small");
        System.out.println("\tb -> Medium");
        System.out.println("\tc -> Large");
        String command = input.next();
        coffeeOrder.setDrinkSize(command);
    }

    // EFFECTS: prompts user to choose a drink temperature
    private void chooseDrinkTemp() {
        System.out.println("\nWould you like your drink to be hot or iced?");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Hot");
        System.out.println("\tb -> Iced");
        String command = input.next();
        coffeeOrder.setDrinkTemp(command);
    }

    // EFFECTS: prompts user to choose a drink base
    private void chooseDrinkBase() {
        System.out.println("What would you like with with your coffee?");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> 2% Cow's milk");
        System.out.println("\tb -> Oat milk");
        System.out.println("\tc -> Almond milk");
        System.out.println("\td -> Soy milk");
        String command = input.next();
        coffeeOrder.setDrinkBase(command);
    }

    // EFFECTS: generates a bill for the complete order
    private void generateBill(OrderList orderList) {
        double st = 0;
        double total = 0;
        System.out.println("\nCan I have your name please?");
        customer.setCustName(input.next());
        System.out.println("\nPlease enter your 4-digit customer number:");
        customer.setCustID(input.nextInt());
        if (donateToCharity()) {
            total++;
        }
        System.out.println("\nYou ordered the following items...");
        total += orderList.returnTotal();
        st = total * SALESTAX;
        total += st;
        System.out.println("SALES TAX: $" + st);
        System.out.println("TOTAL: $" + total);
        System.out.println("\nThank you, " + customer.getCustName() + " - " + customer.getCustID()
                + " your order will be ready shortly!");
    }

    // EFFECTS: - prompts user to choose if they want to donate to a charity
    //          - prints an automated response accordingly.
    //          - returns true if they donate, else false
    private boolean donateToCharity() {
        System.out.println("\nWould you like to donate $1 to our charity?");
        System.out.println("\n[a] Yes \n[b] No");
        String ch = input.next();
        if (ch.equals("a")) {
            System.out.println("\nThanks for your donation!");
            System.out.println("\nGenerating your bill...");
            return true;
        } else if (ch.equals("b")) {
            System.out.println("\nGenerating your bill...");
            return false;
        } else {
            System.out.println("\nInvalid entry...");
        }
        return false;
    }

    // EFFECTS: saves the orderlist to file
    private void saveOrderList() {
        try {
            jsonWriter.open();
            jsonWriter.write(orderList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads orderlist from file
    private void loadOrderList() {
        try {
            orderList = jsonReader.read();
            System.out.println("Loaded from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}