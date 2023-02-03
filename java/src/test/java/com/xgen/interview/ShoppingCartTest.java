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
    @Before
    public void setup(){
        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        sc = new ShoppingCart(new Pricer(), defaultFormat);
    }

    @Test
    public void canAddAnItem() {
        sc.addItem("apple", 1);

        sc.printReceipt();
        if (defaultFormat){
            assertEquals(String.format("apple - 1 - €1.00%n"), myOut.toString());
        }else assertEquals(String.format("€1.00 - 1 - apple%n"), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {
        sc.addItem("apple", 2);

        sc.printReceipt();
        if (defaultFormat){
            assertEquals(String.format("apple - 2 - €2.00%n"), myOut.toString());
        }else assertEquals(String.format("€2.00 - 2 - apple%n"), myOut.toString());
    }

    @Test
    public void canAddDifferentItems() {
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        sc.printReceipt();

        if (defaultFormat){
            assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%n"), myOut.toString());
        }else assertEquals(String.format("€2.00 - 2 - apple%n€2.00 - 1 - banana%n"), myOut.toString());

    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        sc.addItem("crisps", 2);

        sc.printReceipt();

        if (defaultFormat){
            assertEquals(String.format("crisps - 2 - €0.00%n"), myOut.toString());
        }else assertEquals(String.format("€0.00 - 2 - crisps%n"), myOut.toString());
    }
}


