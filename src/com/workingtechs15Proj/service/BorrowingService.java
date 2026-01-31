package com.workingtechs15Proj.service;


import com.workingtechs15Proj.Model.abs.MemberRecord;
import com.workingtechs15Proj.Model.abs.Publication;
import com.workingtechs15Proj.repository.LibraryRepository;

public class BorrowingService {
    private LibraryRepository repo;

    public BorrowingService(LibraryRepository repo) {
        this.repo = repo;
    }

    public void processBorrow(String mId, String bId) {
        MemberRecord member = repo.findMemberById(mId);
        Publication book = repo.findBookById(bId);


        if (member.getBorrowedBooks().size() >= member.getMaxBookLimit()) {
            System.out.println("Limit aşımı!");
            return;
        }


        book.setBorrowed(true);
        member.getBorrowedBooks().add(book);
    }
}