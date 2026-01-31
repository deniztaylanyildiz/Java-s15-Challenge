package com.workingtechs15Proj.app;

import com.workingtechs15Proj.repository.LibraryRepository;
import com.workingtechs15Proj.service.*;
import com.workingtechs15Proj.ui.LibraryCLI;
import com.workingtechs15Proj.utils.InputReader;

public class Main {
    public static void main(String[] args) {
        LibraryRepository repo = new LibraryRepository();
        InputReader input = new InputReader();
        IPaymentService payment = new InvoiceManager();
        LibraryManager manager = new LibraryManager(repo, payment);

        InventoryService inv = new InventoryService(repo, input);
        MemberService mem = new MemberService(repo, input);
        TransactionService trans = new TransactionService(manager, repo, input);

        LibraryCLI cli = new LibraryCLI(inv, mem, trans);
        cli.loadInitialData();
        cli.start();
    }
}