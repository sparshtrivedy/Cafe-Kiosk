package model;

// this class specifies the name and ID of the customer
public class Customer {
    // fields
    private String custName;
    private int custID;

    // constructor
    public Customer(String custName, int custID) {
        this.custName = custName;
        this.custID = custID;
    }

    // getters
    public String getCustName() {
        return custName;
    }

    public int getCustID() {
        return custID;
    }

    // MODIFIES: this
    // EFFECTS: sets customer name to the value of the parameter
    public void setCustName(String name) {
        custName = name;
    }

    // MODIFIES: this
    // EFFECTS: - sets customer ID to the value of the parameter if length of ID = 4
    //          - else prints automated response for invalid entry
    public void setCustID(int num) {
        int length = String.valueOf(num).length();
        if (length == 4) {
            custID = num;
        } else {
            System.out.println("Invalid number of digits ...");
        }
    }
}
