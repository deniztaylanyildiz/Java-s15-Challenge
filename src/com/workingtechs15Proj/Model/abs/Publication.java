package com.workingtechs15Proj.Model.abs;

public abstract class Publication {
    private String id, title, author;
    private double price;
    private boolean isBorrowed = false;

    public Publication(String id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // --- Soyut Metotlar ---
    public abstract String getCategory();


    public void display() {
        System.out.print("[ID: " + id + "] " + title + " - Yazar: " + author);
    }

    // --- Getter / Setter

    public void setBorrowed(boolean borrowed) { // LibraryManager'ın aradığı metot buydu!
        this.isBorrowed = borrowed;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }

    public String getAuthor() { return author; } // Arama işlemi için gerekli
    public double getPrice() { return price; }   // Fatura işlemi için gerekli

    // Güncelleme işlemi için setterlar
    public void setTitle(String title) { this.title = title; }
    public void setPrice(double price) { this.price = price; }
}