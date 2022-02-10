package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// this class tests the Customer class
public class CustomerTest {
    private Customer testCustomer;

    @BeforeEach
    void setTestCustomer() {
        testCustomer = new Customer("Regina",1234);
    }

    // tests the constructor
    @Test
    void testConstructor() {
        assertEquals(1234, testCustomer.getCustID());
        assertEquals("Regina", testCustomer.getCustName());
    }

    // tests if customer name is correctly assigned
    @Test
    void testSetCustName() {
        testCustomer.setCustName("Felangi");
        assertEquals("Felangi",testCustomer.getCustName());
    }

    // - tests for when customer ID meets specification
    // - tests if customer ID is correctly assigned
    @Test
    void testCorrectSetCustID() {
        testCustomer.setCustID(5678);
        assertEquals(5678, testCustomer.getCustID());
    }

    // tests for when customer ID is below the specification
    @Test
    void testSmallSetCustID() {
        testCustomer.setCustID(567);
        assertEquals(1234, testCustomer.getCustID());
    }

    // tests for when customer ID is over the specification
    @Test
    void testBigSetCustID() {
        testCustomer.setCustID(56789);
        assertEquals(1234, testCustomer.getCustID());
    }
}
