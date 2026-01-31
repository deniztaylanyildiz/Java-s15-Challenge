package com.workingtechs15Proj.service;

import com.workingtechs15Proj.Model.abs.MemberRecord;
import com.workingtechs15Proj.Model.abs.Publication;
import com.workingtechs15Proj.repository.LibraryRepository;

public class LibraryManager {
    private LibraryRepository repo;
    private IPaymentService payment;

    public LibraryManager(LibraryRepository repo, IPaymentService payment) {
        this.repo = repo;
        this.payment = payment;
    }

    public void borrowBook(String memberId, String bookId) {
        MemberRecord member = repo.findMemberById(memberId);
        Publication book = repo.findBookById(bookId);

        // Kontroller
        if (book.isBorrowed()) {
            System.out.println("Hata: Kitap şu an kütüphanede değil (Ödünç verilmiş).");
            return;
        }

        if (member.getBorrowedBooks().size() >= 5) { // 5 Kitap Limiti
            System.out.println("Hata: " + member.getName() + " isimli üye 5 kitap limitine ulaştı!");
            return;
        }

        // Ödünç verme işlemi
        book.setBorrowed(true);
        member.getBorrowedBooks().add(book);

        // Fatura Kesme
        payment.processInvoice(member, book);
        System.out.println(">>> İşlem başarılı. Kitap ödünç verildi.");
    }

    public void returnBook(String memberId, String bookId) {
        MemberRecord member = repo.findMemberById(memberId);
        Publication book = repo.findBookById(bookId);

        if (member.getBorrowedBooks().contains(book)) {
            member.getBorrowedBooks().remove(book);
            book.setBorrowed(false);

            // Ücret İadesi
            payment.processRefund(member, book);
            System.out.println(">>> Kitap başarıyla iade edildi.");
        } else {
            System.out.println("Hata: Bu kitap bu üyede kayıtlı görünmüyor.");
        }
    }
}