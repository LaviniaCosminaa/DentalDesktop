package com.example.demodental;

public class Instrument {
    protected int id;
    protected String name, details;
    protected int  amount, price;
    protected String stockDate;

    /**
     * constructor
     */
    public Instrument(int id, String name, String details, int amount, int price, String stockDate) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.amount = amount;
        this.price = price;
        this.stockDate = stockDate;
    }

    public Instrument() {}


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getStockDate() {
        return stockDate;
    }
    public void setStockDate(String stockDate) {
        this.stockDate = stockDate;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", stockDate='" + stockDate + '\'' +
                '}';
    }
}
