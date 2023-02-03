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

        float totalPrice = 0;
        StringBuilder output = new StringBuilder();
        ReceiptFormat receipt = new ReceiptFormat();
        Object[] itemNames = contents.keySet().toArray();

        for (Object itemName : itemNames) {
            float price = pricer.getPrice((String) itemName) * contents.get(itemName);
            output.append(receipt.formatLine((String) itemName, pricer.getPrice((String) itemName), contents.get(itemName), defaultFormat));
            totalPrice+=(price / 100);
        }
        output.append(receipt.formatTotal(totalPrice));
        System.out.print(output);
    }
}
