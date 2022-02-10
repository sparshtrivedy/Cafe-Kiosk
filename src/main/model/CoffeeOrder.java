package model;

import org.json.JSONObject;
import persistence.Writable;

// this class sets up a coffee order by specifying the name, size, temperature
// and base of a drink
public class CoffeeOrder implements Writable {
    // constant fields
    private static final double LATTEPRICE = 3.5;
    private static final double AMERICANOPRICE = 3.0;
    private static final double MOCHAPRICE = 4.5;
    private static final double CAPPUCCINOPRICE = 4.0;
    private static final double COWMILK = 0.15;
    private static final double SOYMILK = 0.75;
    private static final double OATMILK = 0.50;
    private static final double ALMONDMILK = 0.45;

    // fields
    private String drinkName;
    private String drinkSize;
    private String drinkTemp;
    private String drinkBase;
    private double totalBill;

    // constructor
    public CoffeeOrder(String drinkName, String drinkSize, String drinkTemp, String drinkBase) {
        this.drinkName = drinkName;
        this.drinkSize = drinkSize;
        this.drinkTemp = drinkTemp;
        this.drinkBase = drinkBase;
        totalBill = 0;
    }

    // getters
    public String getDrinkName() {
        return drinkName;
    }

    public String getDrinkSize() {
        return drinkSize;
    }

    public String getDrinkTemp() {
        return drinkTemp;
    }

    public String getDrinkBase() {
        return drinkBase;
    }

    public double getTotalBill() {
        return totalBill;
    }

    //EFFECTS: sets total bill amount to the value of parameter
    public void setTotalBill(double amt) {
        totalBill = amt;
    }

    // MODIFIES: this
    // EFFECTS: - sets the drink size based on the choice
    //          - else returns automated response for invalid choice
    //          - adds cost of drink to total bill
    public void setDrinkSize(String option) {
        if (option.equals("a")) {
            drinkSize = "small";
            totalBill += totalBill * 0.0;
        } else if (option.equals("b")) {
            drinkSize = "medium";
            totalBill += totalBill * 0.10;
        } else if (option.equals("c")) {
            drinkSize = "large";
            totalBill += totalBill * 0.20;
        } else {
            System.out.println("You have entered an invalid option...\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: - sets the drink name based on the choice
    //          - else returns automated response for invalid choice
    //          - adds cost of drink to total bill
    public void setDrinkName(String option) {
        if (option.equals("a")) {
            drinkName = "Latte";
            totalBill += LATTEPRICE;
        } else if (option.equals("b")) {
            drinkName = "Mocha";
            totalBill += MOCHAPRICE;
        } else if (option.equals("c")) {
            drinkName = "Americano";
            totalBill += AMERICANOPRICE;
        } else if (option.equals("d")) {
            drinkName = "Cappuccino";
            totalBill += CAPPUCCINOPRICE;
        } else {
            System.out.println("You have entered an invalid option...\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: - sets the drink temperature based on the choice
    //          - else returns automated response for invalid choice
    //          - adds cost of drink to total bill
    public void setDrinkTemp(String option) {
        if (option.equals("a")) {
            drinkTemp = "hot";
        } else if (option.equals("b")) {
            drinkTemp = "iced";
        } else {
            System.out.println("You have entered an invalid option...\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: - sets the drink base based on the choice
    //          - else returns automated response for invalid choice
    //          - adds cost of drink to total bill
    public void setDrinkBase(String option) {
        if (option.equals("a")) {
            drinkBase = "2% cow's milk";
            totalBill += COWMILK;
        } else if (option.equals("b")) {
            drinkBase = "oat milk";
            totalBill += OATMILK;
        } else if (option.equals("c")) {
            drinkBase = "almond milk";
            totalBill += ALMONDMILK;
        } else if (option.equals("d")) {
            drinkBase = "soy milk";
            totalBill += SOYMILK;
        } else {
            System.out.println("You have entered an invalid option...\n");
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", drinkName);
        json.put("size", drinkSize);
        json.put("temp", drinkTemp);
        json.put("base", drinkBase);
        return json;
    }
}