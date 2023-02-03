package com.xgen.interview;

public class ReceiptFormat {

    public ReceiptFormat(){}

    public String formatLine(String name, float price, int quantity, boolean defaultFormat){
        String priceString = String.format("€%.2f", price/100);

        if (defaultFormat){
            return name + " - " + quantity + " - " + priceString + "\n";
        }
        else return price + " - " + quantity + " - " + priceString + "\n";
    }

    public String formatTotal(float total){
        String totalString = String.format("€%.2f", total);
        return "Total: " + totalString;
    }
}
