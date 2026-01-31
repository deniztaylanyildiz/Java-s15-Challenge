package com.workingtechs15Proj.Model;

import com.workingtechs15Proj.Model.abs.Publication;

public class StudyBook extends Publication {
    private String edition;

    public StudyBook(String id, String title, String author, double price, String edition) {
        super(id, title, author, price);
        this.edition = edition;
    }
    public String getEdition() {
        return edition;
    }
    @Override
    public String getCategory() {
        return "Ders Kitabı";
    }

    @Override
    public void display() {
        String status = isBorrowed() ? "[ÖDÜNÇ VERİLDİ]" : "[RAFTA / MÜSAİT]";
        System.out.println(String.format("%-5s | %-20s | %-15s | %-10s | %s",
                getId(), getTitle(), getAuthor(), getEdition(), status));
    }
}