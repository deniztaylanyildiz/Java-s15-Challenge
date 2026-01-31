package com.workingtechs15Proj.Model;

import com.workingtechs15Proj.Model.abs.Publication;

public class Magazine extends Publication {


    private int issueNumber;

    public Magazine(String id, String title, String author, double price, int issueNumber) {
        super(id, title, author, price);
        this.issueNumber = issueNumber;
    }
    public int getIssue() {
        return issueNumber;
    }
    @Override
    public String getCategory() {
        return "Dergi";
    }

    @Override
    public void display() {
        String status = isBorrowed() ? "[ÖDÜNÇ VERİLDİ]" : "[RAFTA / MÜSAİT]";
        System.out.println(String.format("%-5s | %-20s | %-15s | Sayı: %-5d | %s",
                getId(), getTitle(), getAuthor(), getIssue(), status));
    }
}