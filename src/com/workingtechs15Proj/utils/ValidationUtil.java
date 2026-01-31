package com.workingtechs15Proj.utils;

import com.workingtechs15Proj.repository.LibraryRepository;

public class ValidationUtil {
    public static boolean isValidId(String id) {
        return id != null && id.matches("\\d+"); // Sadece rakam
    }

    public static boolean isBookExists(LibraryRepository repo, String id) {
        if (repo.findBookById(id) == null) {
            System.out.println("Hata: Kitap bulunamadı!");
            return false;
        }
        return true;
    }

    public static boolean isMemberExists(LibraryRepository repo, String id) {
        if (repo.findMemberById(id) == null) {
            System.out.println("Hata: Üye bulunamadı!");
            return false;
        }
        return true;
    }

    public static boolean isPositive(double val) { return val > 0; }
}