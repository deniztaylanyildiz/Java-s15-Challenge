package com.workingtechs15Proj.service;

import com.workingtechs15Proj.Model.*;
import com.workingtechs15Proj.Model.abs.MemberRecord;
import com.workingtechs15Proj.repository.LibraryRepository;
import com.workingtechs15Proj.utils.InputReader;

public class MemberService {
    private final LibraryRepository repo;
    private final InputReader input;

    public MemberService(LibraryRepository repo, InputReader input) {
        this.repo = repo;
        this.input = input;
    }

    public void registerMember() {
        String id;

        // 1. AŞAMA: ID DOĞRULAMA VE ARALIK KONTROLÜ
        while (true) {
            id = input.readString("Üye ID (1-999 arası): ");

            // Sadece rakam mı?
            if (!id.matches("\\d+")) {
                System.out.println("[!] Hata: ID sadece rakamlardan oluşmalıdır!");
                continue;
            }

            int idInt = Integer.parseInt(id);

            // Belirlediğimiz 1-999 aralığında mı?
            if (idInt < 1 || idInt > 999) {
                System.out.println("[!] Hata: Üye ID'si 1 ile 999 arasında olmalıdır!");
                continue;
            }

            // Bu ID zaten sistemde var mı? (Mükerrer kontrolü)
            if (repo.findMemberById(id) != null) {
                System.out.println("[!] Hata: " + id + " ID'li bir üye zaten mevcut!");
                continue;
            }

            break; // Tüm kontrollerden geçtiyse döngüden çık
        }

        // 2. AŞAMA: VERİ ALMA
        String name = input.readString("Üye İsim: ");

        System.out.println("\n--- Üye Tipi Seçiniz ---");
        System.out.println("1. Öğrenci (Limit: 5)");
        System.out.println("2. Akademisyen (Limit: 10)");

        int typeChoice = (int) input.readDouble("Seçim (1/2): ");

        // 3. AŞAMA: NESNE OLUŞTURMA VE KAYDETME
        if (typeChoice == 1) {
            repo.saveMember(new Student(id, name));
            System.out.println("[✔] Öğrenci kaydı başarıyla tamamlandı.");
        } else if (typeChoice == 2) {
            repo.saveMember(new Faculty(id, name));
            System.out.println("[✔] Akademisyen kaydı başarıyla tamamlandı.");
        } else {
            System.out.println("[!] Geçersiz seçim! Kayıt işlemi iptal edildi.");
        }
    }

    public void listMembers() {
        System.out.println("\n--- KAYITLI ÜYELER ---");
        if (repo.getAllMembers().isEmpty()) {
            System.out.println("Sistemde kayıtlı üye bulunmamaktadır.");
        } else {
            repo.getAllMembers().forEach(m ->
                    System.out.println("ID: " + m.getId() + " | İsim: " + m.getName() + " [" + m.getMemberType() + "]"));
        }
    }

    // Bu metodu MemberService sınıfının içine ekle
    public void removeMember() {
        System.out.println("\n--- ÜYE KAYDI SİLME ---");
        String id = input.readString("Silinecek Üye ID: ");

        // 1. Üye var mı?
        MemberRecord member = repo.findMemberById(id);
        if (member == null) {
            System.out.println("[!] Hata: Bu ID ile bir üye bulunamadı.");
            return;
        }

        if (!member.getBorrowedBooks().isEmpty()) {
            System.out.println("[!] Hata: Bu üyenin üzerinde iade edilmemiş " +
                    member.getBorrowedBooks().size() + " adet kitap var!");
            System.out.println("[!] Üye silinmeden önce tüm kitaplar iade edilmelidir.");
            return;
        }

        // 3. Onay
        System.out.println("Silinecek Üye: " + member.getName() + " (" + member.getMemberType() + ")");
        String confirm = input.readString("Bu üyeyi silmek istediğinize emin misiniz? (E/H): ");

        if (confirm.equalsIgnoreCase("E")) {
            repo.deleteMember(id);
            System.out.println("[✔] Üye kaydı başarıyla silindi.");
        } else {
            System.out.println("[!] İşlem iptal edildi.");
        }
    }
    public LibraryRepository getRepo() {
        return this.repo;
    }
}