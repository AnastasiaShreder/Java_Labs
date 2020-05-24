package com.jdbc;

public class Item {
    private int id;
    private int prodid;
    private String title;
    private double cost;

    public Item(int id, int prodid, String title, double cost) {
        this.id = id;
        this.prodid = prodid;
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getProdid() {
        return prodid;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("id: ")
                .append(id)
                .append(",prodID: ")
                .append(prodid)
                .append(",title: ")
                .append(title)
                .append(",cost: ")
                .append(cost);
        return string.toString();
    }
}