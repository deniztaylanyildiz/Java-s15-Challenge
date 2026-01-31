package com.workingtechs15Proj.Model;

import com.workingtechs15Proj.Model.abs.Publication;

public class Magazine extends Publication {


    private int issueNumber;

    public Magazine(String id, String title, String author, double price, int issueNumber) {
        super(id, title, author, price);
        this.issueNumber = issueNumber;
    }

    @Override
    public String getCategory() {
        return "Dergi";
    }

    @Override
    public void display() {

        System.out.println("[Dergi] ID: " + getId() + " | " + getTitle() + " - Sayı: " + issueNumber);
    }
}