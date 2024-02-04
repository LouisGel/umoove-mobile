package com.example.umoove;

public class CouponModel {
    int price;
    String name;
    String description;
    String entreprise;
    int remaining;


    public CouponModel(int price, String name, String description, String entreprise, int remaining) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.entreprise = entreprise;
        this.remaining = remaining;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public int getRemaining() {
        return remaining;
    }
}
