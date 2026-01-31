package com.workingtechs15Proj.Model;

import com.workingtechs15Proj.Model.abs.Publication;

public class StudyBook extends Publication {
    private String edition;

    public StudyBook(String id, String title, String author, double price, String edition) {
        super(id, title, author, price);
        this.edition = edition;
    }

    @Override
    public String getCategory() {
        return "Ders Kitabı";
    }

    @Override
    public void display() {
        super.display(); // Artık hata vermez çünkü üst sınıfta içi dolu bir display var
        System.out.println(" | Baskı: " + edition + " | Kategori: " + getCategory());
    }
}