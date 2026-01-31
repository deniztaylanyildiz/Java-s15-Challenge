package com.workingtechs15Proj.service;

import com.workingtechs15Proj.Model.*;
import com.workingtechs15Proj.Model.abs.Publication;
import com.workingtechs15Proj.repository.LibraryRepository;
import com.workingtechs15Proj.utils.InputReader;
import com.workingtechs15Proj.utils.ValidationUtil;

public class InventoryService {
    private final LibraryRepository repo;
    private final InputReader input;

    public InventoryService(LibraryRepository repo, InputReader input) {
        this.repo = repo;
        this.input = input;
    }

    public void addNewPublication() {
        String id;
        while (true) {
            id = input.readString("Kitap ID (1000 ve üzeri): ");
            if (id.matches("\\d+") && Integer.parseInt(id) >= 1000) {
                if (repo.findBookById(id) == null) break;
                else System.out.println("[!] Bu ID zaten kullanımda!");
            } else {
                System.out.println("[!] Hata: Kitap ID 1000'den büyük bir rakam olmalıdır!");
            }
        }

        String title = input.readString("Başlık: ");
        String author = input.readString("Yazar: ");
        double price = input.readDouble("Fiyat: ");

        System.out.println("1. Ders Kitabı | 2. Dergi");
        int type = (int) input.readDouble("Seçim: ");

        if (type == 1) {
            String edition = input.readString("Baskı: ");
            repo.saveBook(new StudyBook(id, title, author, price, edition));
        } else {
            int issue = (int) input.readDouble("Sayı: ");
            repo.saveBook(new Magazine(id, title, author, price, issue));
        }
        System.out.println("[✔] Kayıt başarıyla eklendi.");
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
    public void removePublication() {
        String id = input.readString("Silinecek Kitap ID: ");

        // 1. Kitabı bul
        Publication pub = repo.findBookById(id);

        if (pub == null) {
            System.out.println("[!] Hata: Bu ID ile kayıtlı bir kitap bulunamadı.");
            return;
        }

        // 2. Güvenlik Kontrolü: Kitap şu an birinde mi?
        if (pub.isBorrowed()) {
            System.out.println("[!] Hata: Kitap şu an bir üyede ödünçte olduğu için silinemez!");
            return;
        }

        // 3. Silme Onayı
        String confirm = input.readString(pub.getTitle() + " silinecek. Emin misiniz? (E/H): ");
        if (confirm.equalsIgnoreCase("E")) {
            repo.deleteBook(id); // Repository'den sil
            System.out.println("[✔] Kitap başarıyla envanterden kaldırıldı.");
        } else {
            System.out.println("İşlem iptal edildi.");
        }
    }
    public void searchPublication() {
        System.out.println("\n--- KİTAP ARAMA ---");
        String searchTerm = input.readString("Aramak istediğiniz kitap adı veya yazar: ").toLowerCase();

        boolean found = false;
        System.out.println("\n--- Arama Sonuçları ---");

        for (Publication pub : repo.getAllBooks()) {
            if (pub.getTitle().toLowerCase().contains(searchTerm) ||
                    pub.getAuthor().toLowerCase().contains(searchTerm)) {
                pub.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("[!] Aradığınız kriterlere uygun kitap bulunamadı.");
        }

    }
    public LibraryRepository getRepo() {
        return this.repo;
    }
}