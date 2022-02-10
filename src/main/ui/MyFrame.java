package ui;

import javax.swing.*;

// This class initializes a JFrame
public class MyFrame extends JFrame {

    MyFrame() {
        this.setTitle("Cafe Kiosk");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 700);
    }
}
