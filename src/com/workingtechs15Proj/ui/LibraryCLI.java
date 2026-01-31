package com.workingtechs15Proj.ui;

import com.workingtechs15Proj.service.*;
import com.workingtechs15Proj.utils.InputReader;
import com.workingtechs15Proj.Model.abs.Publication;
import com.workingtechs15Proj.Model.abs.MemberRecord;
import com.workingtechs15Proj.Model.StudyBook;
import com.workingtechs15Proj.Model.Magazine;
import com.workingtechs15Proj.Model.Student;
import com.workingtechs15Proj.Model.Faculty;

public class LibraryCLI {
    private final InventoryService inventory;
    private final MemberService memberService;
    private final TransactionService transaction;
    private final InputReader input = new InputReader();

    // Constructor: Tüm servisleri Main'den alır
    public LibraryCLI(InventoryService inv, MemberService mem, TransactionService trans) {
        this.inventory = inv;
        this.memberService = mem;
        this.transaction = trans;
    }

    public void start() {
        System.out.println("========================================");
        System.out.println("   KÜTÜPHANE SİSTEMİNE HOŞ GELDİNİZ     ");
        System.out.println("========================================");

        String choice = "";
        // choice "0" olmadığı sürece bu döngü programı açık tutar.
        while (!choice.equals("0")) {
            showMenu();
            choice = input.readString("Seçiminiz: ");
            handleChoice(choice);
        }
    }

    private void showMenu() {
        System.out.println("\n----------- İŞLEM MENÜSÜ -----------");
        System.out.println("1. Kitap/Dergi Ekle    | 2. Envanteri Listele");
        System.out.println("3. Kitap Sil           | 4. Üye Sil");
        System.out.println("5. Kategoriye Göre Ara | 6. Kitap Ara (İsim veya Yazar) ");
        System.out.println("7. Yeni Üye Kaydı      | 8. Üyeleri Listele");
        System.out.println("9. KIRAYA VER          |10. İADE AL (Refund) ");
        System.out.println("                0. GÜVENLİ ÇIKIŞ");
        System.out.println("------------------------------------");
    }

    private void handleChoice(String choice) {
        switch (choice) {
            case "1" -> inventory.addNewPublication();
            case "2" -> inventory.listAll();
            case "3" -> inventory.removePublication();
            case "4" -> memberService.removeMember();
            case "5" -> inventory.searchByCategory();
            case "6" -> inventory.searchPublication();
            case "7" -> memberService.registerMember();
            case "8" -> memberService.listMembers();
            case "9" -> transaction.executeBorrow();
            case "10" -> transaction.executeReturn();
            case "0" -> System.out.println("\nSistemden başarıyla çıkış yapıldı. İyi günler!");
            default -> System.out.println("\n[!] Hata: Geçersiz bir seçim yaptınız, tekrar deneyin.");
        }
    }


    public void loadInitialData() {

        this.inventory.getRepo().saveBook(new StudyBook("1001", "Java Programming", "Deitel", 450.0, "11. Edition"));
        this.inventory.getRepo().saveBook(new StudyBook("1002", "Clean Code", "Robert Martin", 380.0, "1. Edition"));

        this.inventory.getRepo().saveBook(new Magazine("2001", "Scientific American", "Nature Group", 75.0, 542));

        // Eğer değişken adın sadece 'memberService' ise
        this.memberService.getRepo().saveMember(new Student("1", "Ahmet Yılmaz"));
        this.memberService.getRepo().saveMember(new Faculty("3", "Doç. Dr. Mehmet Öz"));

        System.out.println("[Sistem] Başlangıç verileri başarıyla yüklendi.");
    }
}