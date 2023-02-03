package com.xgen.interview;

public class ReceiptFormat {

    public ReceiptFormat(){}

    /**
     * Created separate class so that the receipt format can be edited without having to update every test
     * Also makes ShoppingCart look cleaner
     * @param name name of item
     * @param price price of individual item
     * @param quantity number of items scanned of that type
     * @param defaultFormat True = default receipt format, False = alternative receipt format
     * @return String containing name, price and quantity in desired format
     */
    public String formatLine(String name, float price, int quantity, boolean defaultFormat){
        String priceString = String.format("€%.2f", price/100);

        if (defaultFormat){
            return name + " - " + quantity + " - " + priceString + "\n";
        }
        else return priceString + " - " + quantity + " - " + name + "\n";
    }

    /**
     * Formats the final total
     * @param total final total
     * @return String displaying final total
     */
    public String formatTotal(float total){
        String totalString = String.format("€%.2f", total);
        return "Total: " + totalString;
    }
}
