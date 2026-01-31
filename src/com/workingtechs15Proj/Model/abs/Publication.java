package com.workingtechs15Proj.Model.abs;

public abstract class Publication {
    private String id, title, author;
    private double price;
    private boolean isBorrowed = false;

    public Publication(String id, String title, String author, double price) {
        this.id = id; this.title = title; this.author = author; this.price = price;
    }

    // Getters & Setters (Encapsulation)
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public void setTitle(String title) { this.title = title; }
    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean borrowed) { isBorrowed = borrowed; }

    public abstract String getCategory();
    public abstract void display(); // Polymorphism
}