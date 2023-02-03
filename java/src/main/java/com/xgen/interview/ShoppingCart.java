package com.xgen.interview;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    LinkedHashMap<String, Integer> contents = new LinkedHashMap<>();
    Pricer pricer;
    boolean defaultFormat = true;

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
    }

    public ShoppingCart(Pricer pricer, Boolean defaultFormat) {
        this.pricer = pricer;
        this.defaultFormat = defaultFormat;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {

        Object[] keys = contents.keySet().toArray();

        for (Object key : keys) {
            float price = pricer.getPrice((String) key) * contents.get(key);
            float priceFloat = price / 100;
            String priceString = String.format("€%.2f", priceFloat);

            if (defaultFormat) {
                System.out.println(key + " - " + contents.get(key) + " - " + priceString);
            } else System.out.println(priceString+ key + " - " + contents.get(key) + " - ");
        }
    }
}
