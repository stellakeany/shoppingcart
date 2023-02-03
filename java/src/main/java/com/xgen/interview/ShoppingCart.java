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

    /**
     * Created overloaded method so that the user can toggle the receipt format. Took this approach as till hardware
     * may create ShoppingCart object without boolean value. This allows for True to be the default value for the
     * format type if no boolean value inputted
     * @param pricer price database for items in shop
     * @param defaultFormat toggle receipt format. True = default view, False = alternative view
     */
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

    /**
     * Utilised StringBuilder instead of string format for better efficiency
     * Fetches format for each line from ReceiptFormat class
     * Did not want to change the fact that the result is printed to output incase that had something to do with till hardware
     */
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
