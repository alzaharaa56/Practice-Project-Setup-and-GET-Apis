package com.example.trainee_app;

public class InventoryBook {
    private int id;
    private String title;
    private double price;
    private int stockCount;

    public InventoryBook(int id, String title, double price, int stockCount) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stockCount = stockCount;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getStockCount() {
        return stockCount;
    }
}
