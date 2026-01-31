package com.workingtechs15Proj.service;

import com.workingtechs15Proj.repository.LibraryRepository;
import com.workingtechs15Proj.utils.InputReader;
import com.workingtechs15Proj.utils.ValidationUtil;

public class TransactionService {
    private final LibraryManager manager;
    private final LibraryRepository repo;
    private final InputReader input;

    public TransactionService(LibraryManager manager, LibraryRepository repo, InputReader input) {
        this.manager = manager;
        this.repo = repo;
        this.input = input;
    }

    public void executeBorrow() {
        String mId = input.readString("Üye ID: ");
        String bId = input.readString("Kitap ID: ");
        if (ValidationUtil.isMemberExists(repo, mId) && ValidationUtil.isBookExists(repo, bId)) {
            manager.borrowBook(mId, bId);
        }
    }

    public void executeReturn() {
        String mId = input.readString("Üye ID: ");
        String bId = input.readString("Kitap ID: ");
        if (ValidationUtil.isMemberExists(repo, mId) && ValidationUtil.isBookExists(repo, bId)) {
            manager.returnBook(mId, bId);
        }
    }
}