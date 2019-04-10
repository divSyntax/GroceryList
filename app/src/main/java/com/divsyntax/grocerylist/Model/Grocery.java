package com.divsyntax.grocerylist.Model;

public class Grocery
{
    private String name;
    private String quantity;
    private String dateAdded;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grocery(String name, String quantity, String dateAdded, int id) {
        this.name = name;
        this.quantity = quantity;
        this.dateAdded = dateAdded;
        this.id = id;
    }

    public Grocery() {
    }
}
