package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    private ByteArrayOutputStream myOut;
    private ShoppingCart sc;
    private final boolean defaultFormat = true;
    private final Pricer pricer = new Pricer();
    private final ReceiptFormat receipt = new ReceiptFormat();
    @Before
    public void setup(){
        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        sc = new ShoppingCart(new Pricer(), defaultFormat);
    }

    @Test
    public void receiptFormatCorrect(){
        String actual = receipt.formatLine("apple", pricer.getPrice("apple"), 1, defaultFormat) +
                receipt.formatTotal(1);
        String expected;
        if (defaultFormat){
            expected = "apple - 1 - €1.00\nTotal: €1.00";
        } else expected = "€1.00 - 1 - apple\nTotal: €1.00";

        assertEquals(expected, actual);
    }

    @Test
    public void canAddAnItem() {
        sc.addItem("apple", 1);

        String expectedOutput = receipt.formatLine("apple", pricer.getPrice("apple"), 1, defaultFormat) +
                receipt.formatTotal(1);

        sc.printReceipt();
        assertEquals(expectedOutput, myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {
        sc.addItem("apple", 2);

        String expectedOutput = receipt.formatLine("apple", pricer.getPrice("apple"), 2, defaultFormat) +
                receipt.formatTotal(2);

        sc.printReceipt();
        assertEquals(String.format(expectedOutput), myOut.toString());

    }

    @Test
    public void canAddDifferentItems() {
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        String expectedOutput = receipt.formatLine("apple", pricer.getPrice("apple"), 2, defaultFormat)
                + receipt.formatLine("banana", pricer.getPrice("banana"), 1, defaultFormat)
                + receipt.formatTotal(4);

        sc.printReceipt();
        assertEquals(expectedOutput, myOut.toString());

    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        sc.addItem("crisps", 2);

        String expectedOutput = receipt.formatLine("crisps", pricer.getPrice("crisps"), 2, defaultFormat) +
                receipt.formatTotal(0);
        sc.printReceipt();
        assertEquals(expectedOutput, myOut.toString());
    }
}


