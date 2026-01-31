package com.workingtechs15Proj.service;

import com.workingtechs15Proj.Model.*;
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
        String id = input.readString("Üye ID: ");
        String name = input.readString("Üye İsim: ");
        System.out.println("1. Öğrenci | 2. Akademisyen (Hoca)");
        String choice = input.readString("Seçim: ");

        if (choice.equals("1")) {
            repo.saveMember(new Student(id, name));
        } else {
            repo.saveMember(new Faculty(id, name));
        }
        System.out.println("Üye başarıyla kaydedildi.");
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
}