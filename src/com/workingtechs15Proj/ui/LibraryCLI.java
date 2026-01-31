package com.workingtechs15Proj.ui;

import com.workingtechs15Proj.Model.*;
import com.workingtechs15Proj.Model.abs.Publication;
import com.workingtechs15Proj.repository.LibraryRepository;
import com.workingtechs15Proj.service.LibraryManager;
import com.workingtechs15Proj.utils.InputReader; // Yeni yardımcı sınıfımız
import com.workingtechs15Proj.utils.ValidationUtil;
import java.util.List;

public class LibraryCLI {
    private final LibraryManager manager;
    private final LibraryRepository repo;
    private final InputReader input;

    public LibraryCLI(LibraryManager manager, LibraryRepository repo) {
        this.manager = manager;
        this.repo = repo;
        this.input = new InputReader(); // Scanner işlemlerini bu sınıf yönetecek
    }

    public void start() {
        String choice;
        do {
            showMenu();
            choice = input.readString("");
            handleChoice(choice);
        } while (!choice.equals("0"));
    }

    private void showMenu() {
        System.out.println("\n--- KÜTÜPHANE YÖNETİM SİSTEMİ ---");
        System.out.println("1. Kitap/Dergi Ekle  | 2. Tümünü Listele");
        System.out.println("3. Bilgi Güncelle    | 4. Kayıt Sil");
        System.out.println("5. Yazara Göre Ara   | 6. Kategoriye Göre Ara");
        System.out.println("7. Yeni Üye Kaydı    | 8. Üyeleri Listele");
        System.out.println("9. KİRALA  (Fatura)  | 10. İADE AL (Refund)");
        System.out.println("0. Çıkış");
        System.out.print("Seçiminiz: ");
    }

    private void handleChoice(String choice) {
        switch (choice) {
            case "1" -> addBook();
            case "2" -> repo.getAllBooks().forEach(Publication::display);
            case "3" -> updateBook();
            case "4" -> deleteBook();
            case "5" -> searchByAuthor();
            case "6" -> searchByCategory();
            case "7" -> addMember();
            case "8" -> listMembers();
            case "9" -> borrowFlow();
            case "10" -> returnFlow();
            case "0" -> System.out.println("Sistemden çıkılıyor...");
            default -> System.out.println("Geçersiz seçim!");
        }
    }

    // Envanter

    private void addBook() {
        String id = input.readString("ID: ");
        String title = input.readString("Başlık: ");
        String author = input.readString("Yazar: ");
        double price = input.readDouble("Fiyat: ");
        int type = (int) input.readDouble("Tip (1:Kitap, 2:Dergi): ");

        if (type == 1) {
            String ed = input.readString("Baskı (Edition): ");
            repo.saveBook(new StudyBook(id, title, author, price, ed));
        } else {
            int issue = (int) input.readDouble("Sayı (Issue No): ");
            repo.saveBook(new Magazine(id, title, author, price, issue));
        }
        System.out.println("Kayıt başarıyla eklendi.");
    }

    private void updateBook() {
        String id = input.readString("Güncellenecek ID: ");
        Publication b = repo.findBookById(id);
        if (b != null) {
            b.setTitle(input.readString("Yeni Başlık: "));
            b.setPrice(input.readDouble("Yeni Fiyat: "));
            System.out.println("Güncellendi.");
        } else System.out.println("Bulunamadı.");
    }

    private void deleteBook() {
        String id = input.readString("Silinecek ID: ");
        if (repo.removeBook(id)) System.out.println("Silindi.");
        else System.out.println("Bulunamadı.");
    }

    // Arama işlemleri

    private void searchByAuthor() {
        String author = input.readString("Yazar Adı: ");
        List<Publication> results = repo.findByAuthor(author);
        if (results.isEmpty()) System.out.println("Sonuç yok.");
        else results.forEach(Publication::display);
    }

    private void searchByCategory() {
        System.out.println("1. Ders Kitabı | 2. Dergi");
        String choice = input.readString("Seçim: ");
        String target = choice.equals("1") ? "Ders Kitabı" : "Dergi";
        repo.findByCategory(target).forEach(Publication::display);
    }

    // --- ÜYE işlemleri

    private void addMember() {
        String id = input.readString("Üye ID: ");
        String name = input.readString("Üye İsim: ");
        String type = input.readString("Tip (1:Öğrenci, 2:Hoca): ");
        if (type.equals("1")) repo.saveMember(new Student(id, name));
        else repo.saveMember(new Faculty(id, name));
        System.out.println("Üye kaydedildi.");
    }

    private void listMembers() {
        repo.getAllMembers().forEach(m ->
                System.out.println("ID: " + m.getId() + " | " + m.getName() + " [" + m.getMemberType() + "]"));
    }

    private void borrowFlow() {
        String mId = input.readString("Üye ID: ");
        String bId = input.readString("Kitap ID: ");
        if (ValidationUtil.isMemberExists(repo, mId) && ValidationUtil.isBookExists(repo, bId)) {
            manager.borrowBook(mId, bId);
        }
    }

    private void returnFlow() {
        String mId = input.readString("Üye ID: ");
        String bId = input.readString("Kitap ID: ");
        if (ValidationUtil.isMemberExists(repo, mId) && ValidationUtil.isBookExists(repo, bId)) {
            manager.returnBook(mId, bId);
        }
    }
    //test-data
    public void loadInitialData() {

        repo.saveBook(new StudyBook("101", "Java Programming", "Deitel", 150.0, "10th Edition"));
        repo.saveBook(new Magazine("201", "Scientific American", "Nature Group", 45.0, 112));
        repo.saveMember(new Student("1", "Ahmet Yılmaz"));
        repo.saveMember(new Faculty("2", "Dr. Ayşe Demir"));

        System.out.println(">>> Sistem hazır: Başlangıç verileri başarıyla yüklendi.");
    }
}