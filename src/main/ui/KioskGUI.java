package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// This class sets up a graphical user interface
public class KioskGUI extends JFrame {
    // fields
    private static final String JSON_STORE = "./data/orderlist.json";
    private static final double SALESTAX = 0.15;

    private Customer customer;
    private Scanner input;
    private OrderList orderList;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private CoffeeOrder coffeeOrder;

    private JButton addOrderButton;
    private JButton cartButton;
    private JButton removeButton;
    private JButton generateBillButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton quitButton;

    private JButton submitRemoveButton;
    private JButton submitDrinkSize;
    private JButton submitDrinkName;
    private JButton submitDrinkTemp;
    private JButton submitDrinkBase;
    private JButton submitNameButton;
    private JButton submitIDButton;
    private JButton closeBillButton;
    private JButton closeCartButton;

    private JTextField removeThis;
    private JTextField sizeDrinkThis;
    private JTextField nameDrinkThis;
    private JTextField tempDrinkThis;
    private JTextField baseDrinkThis;
    private JTextField nameCustomerThis;
    private JTextField idCustomerThis;

    private MyFrame window;
    private MyFrame removeWindow;
    private MyFrame billWindow;
    private MyFrame customerWindow;
    private MyFrame cartWindow;
    private JPanel panel;
    private JLabel label;

    private JFrame welcomeWindow;
    private JPanel welcomePanel;
    private JLabel welcomeLabel;
    private JLabel newLabel;
    private JButton welcomeButton;

    // EFFECTS: sets up the welcome panel
    public KioskGUI() {
        welcomeLabel = new JLabel();
        newLabel = new JLabel();
        newLabel.setBounds(100,10,500,100);
        welcomeLabel.setBounds(5,167,490,500);
        newLabel.setText("WELCOME TO THE CAFE");
        welcomeLabel.setIcon(new ImageIcon("./data/cafe.jpeg"));
        newLabel.setFont(new Font("dialog", Font.BOLD, 24));

        welcomeButton = new JButton("Place Order");
        welcomeButton.setBounds(170,100,150,40);

        welcomePanel = new JPanel();
        welcomePanel.add(newLabel);
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(welcomeButton);
        welcomePanel.setBounds(0,0,500,672);
        welcomePanel.setBackground(new Color(60,120,60));
        welcomePanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        welcomeWindow = new JFrame();
        welcomeWindow.setSize(500,700);
        welcomeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeWindow.setLayout(null);
        welcomeWindow.setVisible(true);
        welcomeWindow.add(welcomePanel);

        addWelcomeListener();
    }

    // EFFECTS: calls method for setting an action for the welcomeButton
    private void addWelcomeListener() {
        welcomeButton.addActionListener(new KioskGUI.WelcomeToolHandler());
    }

    // this class sets action for the welcomeButton
    private class WelcomeToolHandler implements ActionListener {

        // EFFECTS: this method sets action for the welcomeButton
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == welcomeButton) {
                placeOrderWindow();
                welcomeWindow.dispose();
            }
        }
    }

    // EFFECTS: -this window is displayed the Place Order button is clicked
    //          -this window has several buttons for performing different functions
    //          such as placing order, removing order, displaying cart, generating
    //          bill, saving and loading.

    private void placeOrderWindow() {
        initializeFields();
        
        label = new JLabel();
        label.setBounds(0,305,500,500);
        label.setIcon(new ImageIcon("./data/beans.jpeg"));

        setButtons();

        panel = new JPanel();
        panel.add(addOrderButton);
        panel.add(removeButton);
        panel.add(cartButton);
        panel.add(generateBillButton);
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(quitButton);
        panel.add(label);
        panel.setBounds(0,0,500,672);
        panel.setBackground(new Color(60,120,60));

        window = new MyFrame();
        window.add(panel);

        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool, currentDrawing to null, and instantiates drawings and tools with ArrayList
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
        customer = new Customer(null, 0);
        orderList = new OrderList();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input.useDelimiter("\n");
    }

    // EFFECTS: initializes buttons for the placeOrderWindow
    private void setButtons() {
        addOrderButton = new JButton("<html><h3>Add Order</h3></html>");
        addOrderButton.setPreferredSize(new Dimension(245, 40));
        removeButton = new JButton("<html><h3>Remove Order</h3></html>");
        removeButton.setPreferredSize(new Dimension(245, 40));
        cartButton = new JButton("<html><h3>Check Cart</h3></html>");
        cartButton.setPreferredSize(new Dimension(245, 40));
        generateBillButton = new JButton("<html><h3>Generate Bill</h3></html>");
        generateBillButton.setPreferredSize(new Dimension(245, 40));
        loadButton = new JButton("<html><h3>Load Order</h3></html>");
        loadButton.setPreferredSize(new Dimension(245, 40));
        saveButton = new JButton("<html><h3>Save Order</h3></html>");
        saveButton.setPreferredSize(new Dimension(245, 40));
        quitButton = new JButton("<html><h3>Quit</h3></html>");
        quitButton.setPreferredSize(new Dimension(495, 40));
    }

    // EFFECTS: calls respective classes for setting actions for buttons in placeOrderWindow
    private void addListener() {
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
            }
        });
        addOrderButton.addActionListener(new KioskGUI.AddToolHandler());
        cartButton.addActionListener(new KioskGUI.AddToolHandler());
        generateBillButton.addActionListener(new KioskGUI.AddToolHandler());
        loadButton.addActionListener(new KioskGUI.AddToolHandler());
        saveButton.addActionListener(new KioskGUI.AddToolHandler());
        removeButton.addActionListener(new KioskGUI.AddToolHandler());
        quitButton.addActionListener(new KioskGUI.AddToolHandler());
    }

    // sets up action for the addOrderButton
    private class AddToolHandler implements ActionListener {

        // EFFECTS: sets action for the addOrderButton
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addOrderButton) {
                processAddOrder();
            } else if (e.getSource() == removeButton) {
                processRemoveOrder();
            } else if (e.getSource() == cartButton) {
                processCart();
            } else if (e.getSource() == generateBillButton) {
                processGenerateBill();
            } else if (e.getSource() == loadButton) {
                processLoad();
            } else if (e.getSource() == saveButton) {
                processSave();
            } else if (e.getSource() == quitButton) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        }
    }

    //EFFECTS: sets function for addOrderButton
    private void processAddOrder() {
        window = new MyFrame();
        panel = new JPanel();
        panel.setBackground(new Color(60,120,60));
        panel.setBounds(0,0,500,700);
        coffeeOrder = new CoffeeOrder(null, null,null,null);
        chooseDrinkSize();
        chooseDrinkName();
        chooseDrinkTemp();
        chooseDrinkBase();
        window.add(panel);
        orderList.addOrder(coffeeOrder);
    }

    // EFFECTS: prompts user to choose a drink size
    private void chooseDrinkSize() {
        panel.add(new JLabel("<html><h3>Which size drink would you like?<br>Select from:<br>(a) Small   (b) Medium"
                + "   (c) Large</h3></html>"));
        sizeDrinkThis = new JTextField();
        submitDrinkSize = new JButton("Submit");
        submitDrinkSize.setPreferredSize(new Dimension(500,40));
        submitDrinkSize.setBounds(0,100,100,100);
        sizeDrinkThis.setPreferredSize(new Dimension(500,30));
        panel.add(sizeDrinkThis);
        panel.add(submitDrinkSize);
        window.add(panel);
        sizeListener();
    }

    // EFFECTS: sets action listener for submitDrinkSize
    private void sizeListener() {
        submitDrinkSize.addActionListener(new KioskGUI.SizeToolHandler());
    }

    // sets action for submitDrinkSize
    private class SizeToolHandler implements ActionListener {

        // EFFECTS: sets action for submitDrinkSize to set drinkSize
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitDrinkSize) {
                coffeeOrder.setDrinkSize(sizeDrinkThis.getText());
            }
        }
    }

    // EFFECTS: prompts user to choose a drink
    private void chooseDrinkName() {
        panel.add(new JLabel("<html><h3>Which drink would you like?<br>Select from:<br>(a) Latte    (b) Mocha"
                + "   (c) Americano    (d) Cappuccino</h3></html>"));
        nameDrinkThis = new JTextField();
        submitDrinkName = new JButton("Submit");
        submitDrinkName.setPreferredSize(new Dimension(500,40));
        nameDrinkThis.setPreferredSize(new Dimension(500,30));
        panel.add(nameDrinkThis);
        panel.add(submitDrinkName);
        window.add(panel);
        nameListener();
    }

    // EFFECTS: sets action listener for submitDrinkName
    private void nameListener() {
        submitDrinkName.addActionListener(new KioskGUI.NameToolHandler());
    }

    // sets action for submitDrinkName
    private class NameToolHandler implements ActionListener {

        // EFFECTS: sets action for submitDrinkName to set drinkName
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitDrinkName) {
                coffeeOrder.setDrinkName(nameDrinkThis.getText());
            }
        }
    }

    // EFFECTS: prompts user to choose a drink temperature
    private void chooseDrinkTemp() {
        panel.add(new JLabel("<html><h3>Would you like your drink hot or cold?<br>Select from:<br>(a) Hot   "
                + "(b) Cold</h3></html>"));
        tempDrinkThis = new JTextField();
        submitDrinkTemp = new JButton("Submit");
        submitDrinkTemp.setPreferredSize(new Dimension(500,40));
        tempDrinkThis.setPreferredSize(new Dimension(500,30));
        panel.add(tempDrinkThis);
        panel.add(submitDrinkTemp);
        window.add(panel);
        tempListener();
    }

    // EFFECTS: sets action listener for submitDrinkTemp
    private void tempListener() {
        submitDrinkTemp.addActionListener(new KioskGUI.TempToolHandler());
    }

    // sets action for submitDrinkTemp
    private class TempToolHandler implements ActionListener {

        // EFFECTS: sets action for submitDrinkTemp to set drinkTemp
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitDrinkTemp) {
                coffeeOrder.setDrinkTemp(tempDrinkThis.getText());
            }
        }
    }

    // EFFECTS: prompts user to choose a drink base
    private void chooseDrinkBase() {
        panel.add(new JLabel("<html><h3>What would you like with with your coffee?<br>Select from:<br>"
                + "(a) 2% Cow's milk    (b) Oat milk<br>(c) Almond milk    (d) Soy milk</h3></html>"));
        baseDrinkThis = new JTextField();
        submitDrinkBase = new JButton("Submit");
        submitDrinkBase.setPreferredSize(new Dimension(500,40));
        baseDrinkThis.setPreferredSize(new Dimension(500,30));
        panel.add(baseDrinkThis);
        panel.add(submitDrinkBase);
        window.add(panel);
        baseListener();
    }

    // EFFECTS: sets action listener for submitDrinkBase
    private void baseListener() {
        submitDrinkBase.addActionListener(new KioskGUI.BaseToolHandler());
    }

    // sets action for submitDrinkBase
    private class BaseToolHandler implements ActionListener {

        // EFFECTS: sets action for submitDrinkBase to set drinkBase
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitDrinkBase) {
                coffeeOrder.setDrinkBase(baseDrinkThis.getText());
                window.dispose();
            }
        }
    }

    // EFFECTS: performs function for generateBillButton
    private void processGenerateBill() {
        generateBillWindow(orderList);
    }

    // EFFECTS: generates a window to set customer info
    private void generateBillWindow(OrderList orderList) {
        customerWindow = new MyFrame();
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60,120,60));
        panel.add(new JLabel("<html><h3>Can I have your name please?</h3></html>"));
        nameCustomerThis = new JTextField();
        submitNameButton = new JButton("<html><h3>Submit</h3></html>");
        submitNameButton.setPreferredSize(new Dimension(500,40));
        nameCustomerThis.setPreferredSize(new Dimension(500,30));
        panel.add(nameCustomerThis);
        panel.add(submitNameButton);
        custNameListener();
        panel.add(new JLabel("<html><h3>Please enter your 4-digit customer number:</h3></html>"));
        idCustomerThis = new JTextField();
        submitIDButton = new JButton("<html><h3>Submit</h3></html>");
        submitIDButton.setPreferredSize(new Dimension(500,40));
        idCustomerThis.setPreferredSize(new Dimension(500,30));
        panel.add(idCustomerThis);
        panel.add(submitIDButton);
        customerWindow.add(panel);
        custIDListener();
    }

    // EFFECTS: sets action listener for submitNameButton
    private void custNameListener() {
        submitNameButton.addActionListener(new KioskGUI.CustNameHandler());
    }

    // sets functionality for submitNameButton
    private class CustNameHandler implements ActionListener {

        // EFFECTS: sets functionality for submitNameButton to set customerName
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitNameButton) {
                customer.setCustName(nameCustomerThis.getText());
            }
        }
    }

    // EFFECTS: sets action listener for submitIDButton
    private void custIDListener() {
        submitIDButton.addActionListener(new KioskGUI.CustIDHandler());
    }

    // sets functionality for submitIDButton
    private class CustIDHandler implements ActionListener {

        // EFFECTS: sets functionality for submitIDButton to set customerID
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitIDButton) {
                customer.setCustID(Integer.parseInt(idCustomerThis.getText()));
                customerWindow.dispose();
                generateBill();
            }
        }
    }

    // EFFECTS: generates bill on the billWindow
    private void generateBill() {
        billWindow = new MyFrame();
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60,120,60));
        double st = 0;
        double total = 0;
        panel.add(new JLabel("<html><h3>You ordered the following items...<br></html></h3>"));
        panel.add(new JLabel("<html><h3>" + orderList.returnList() + "<br></h3></html>"));

        total += orderList.returnTotal();
        st = total * SALESTAX;
        total += st;
        panel.add(new JLabel("<html><h3>SALES TAX: $" + st + "<br>TOTAL: $" + total + "<br>Thank you, "
                + customer.getCustName() + " - " + customer.getCustID()
                + " <br>your order will be ready shortly!</html></h3>"));
        closeBillButton = new JButton("<html><h3>Close</h3></html>");
        closeBillButton.setPreferredSize(new Dimension(500,40));
        panel.add(closeBillButton);
        closeBill();
        billWindow.add(panel);
    }

    // EFFECTS: sets action listener for closeBillButton
    private void closeBill() {
        closeBillButton.addActionListener(new KioskGUI.CloseBillHandler());
    }

    // sets functionality for closeBillButton
    private class CloseBillHandler implements ActionListener {

        // EFFECTS: sets functionality for closeBillButton to close billWindow
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == closeBillButton) {
                billWindow.dispose();
            }
        }
    }

    // EFFECTS: sets functionality for cartButton
    private void processCart() {
        cartWindow = new MyFrame();
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60,120,60));
        panel.add(new JLabel("<html><h3>Here is what you have so far...<br></h3></html>"));
        panel.add(new JLabel("<html><h3>" + orderList.returnCart() + "<br></h3></html>"));
        closeCartButton = new JButton("<html><h3>Close</h3></html>");
        panel.add(closeCartButton);
        closeCartButton.setPreferredSize(new Dimension(500,40));
        closeCart();
        cartWindow.add(panel);
    }

    // EFFECTS: sets action listener for closeCartButton
    private void closeCart() {
        closeCartButton.addActionListener(new KioskGUI.CloseCartHandler());
    }

    // sets functionality for closeCartButton
    private class CloseCartHandler implements ActionListener {

        // EFFECTS: sets functionality for closeCartButton to close cartWindow
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == closeCartButton) {
                cartWindow.dispose();
            }
        }
    }

    // EFFECTS: sets functionality for removeOrderButton to remove an order
    private void processRemoveOrder() {
        removeWindow = new MyFrame();
        JPanel panel = new JPanel();
        panel.setBackground(new Color(60,120,60));
        panel.add(new JLabel("<html><h3>Which drink would you like to remove?</h3></html>"));
        removeThis = new JTextField();
        submitRemoveButton = new JButton("<html><h3>submit</h3></html>");
        submitRemoveButton.setPreferredSize(new Dimension(500,40));
        removeThis.setPreferredSize(new Dimension(500,30));
        panel.add(removeThis);
        panel.add(submitRemoveButton);
        removeWindow.add(panel);
        removeListener();
    }

    // EFFECTS: sets action listener for submitRemoveButton
    private void removeListener() {
        submitRemoveButton.addActionListener(new KioskGUI.RemoveToolHandler());
    }

    // sets function for submitRemoveButton
    private class RemoveToolHandler implements ActionListener {

        // EFFECTS: sets function for submitRemoveButton to remove the removeThis order
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitRemoveButton) {
                orderList.removeOrder(removeThis.getText());
                removeWindow.dispose();
            }
        }
    }

    // EFFECTS: sets functionality for saveButton to save order
    private void processSave() {
        saveOrderList();
    }

    // EFFECTS: saves the orderlist to file
    private void saveOrderList() {
        try {
            jsonWriter.open();
            jsonWriter.write(orderList);
            jsonWriter.close();
            JFrame window = new JFrame();
            window.setResizable(false);
            window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            window.setVisible(true);
            window.setSize(300, 200);
            JPanel panel = new JPanel();
            panel.setBackground(new Color(60,120,60));
            window.add(panel);
            panel.add(new Label("<html><h3>Saved to " + JSON_STORE + " <br></h3></html>"));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: sets functionality for loadButton to load order
    private void processLoad() {
        loadOrderList();
    }

    // MODIFIES: this
    // EFFECTS: loads orderlist from file
    private void loadOrderList() {
        try {
            orderList = jsonReader.read();
            JFrame window = new JFrame();
            window.setResizable(false);
            window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            window.setVisible(true);
            window.setSize(300, 200);
            JPanel panel = new JPanel();
            panel.setBackground(new Color(60,120,60));
            window.add(panel);
            panel.add(new Label("<html><h3>Loaded from " + JSON_STORE + " <br></h3></html>"));
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints the logged events onto the console
    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

    // EFFECTS: calls KioskGUI
    public static void main(String[] args) {
        new KioskGUI();
    }
}