package com.workingtechs15Proj.service;

import com.workingtechs15Proj.Model.*;
import com.workingtechs15Proj.Model.abs.Publication;
import com.workingtechs15Proj.repository.LibraryRepository;
import com.workingtechs15Proj.utils.InputReader;

public class InventoryService {
    private final LibraryRepository repo;
    private final InputReader input;

    public InventoryService(LibraryRepository repo, InputReader input) {
        this.repo = repo;
        this.input = input;
    }

    public void addNewPublication() {
        String id = input.readString("ID: ");
        String title = input.readString("Başlık: ");
        String author = input.readString("Yazar: ");
        double price = input.readDouble("Fiyat: ");
        int type = (int) input.readDouble("Tip (1:Kitap, 2:Dergi): ");

        if (type == 1) {
            repo.saveBook(new StudyBook(id, title, author, price, "1. Baskı"));
        } else {
            repo.saveBook(new Magazine(id, title, author, price, 1));
        }
        System.out.println("Sisteme eklendi.");
    }

    public void listAll() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(String.format("%-5s | %-20s | %-15s | %-10s | %s",
                "ID", "BAŞLIK", "YAZAR", "DETAY", "DURUM"));
        System.out.println("-".repeat(80));

        if (repo.getAllBooks().isEmpty()) {
            System.out.println("Envanter boş.");
        } else {
            repo.getAllBooks().forEach(Publication::display);
        }
        System.out.println("=".repeat(80));
    }

    public void searchByCategory() {
        System.out.println("1. Ders Kitabı | 2. Dergi");
        String choice = input.readString("Seçim: ");
        String target = choice.equals("1") ? "Ders Kitabı" : "Dergi";
        repo.findByCategory(target).forEach(Publication::display);
    }
}