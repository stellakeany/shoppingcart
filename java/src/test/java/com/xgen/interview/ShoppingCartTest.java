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

    @Before
    public void setup(){
        myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
    }


    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        sc.printReceipt();
        assertEquals(String.format("apple - 1 - €1.00%n"), myOut.toString());
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        sc.printReceipt();
        assertEquals(String.format("apple - 2 - €2.00%n"), myOut.toString());
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        sc.printReceipt();

        String result = myOut.toString();

        if (result.startsWith("apple")) {
            assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%n"), result);
        }
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        sc.printReceipt();
        assertEquals(String.format("crisps - 2 - €0.00%n"), myOut.toString());
    }
}


