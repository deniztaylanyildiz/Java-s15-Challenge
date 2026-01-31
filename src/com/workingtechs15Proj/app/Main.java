package com.workingtechs15Proj.app;

import com.workingtechs15Proj.repository.LibraryRepository;
import com.workingtechs15Proj.service.*;
import com.workingtechs15Proj.ui.LibraryCLI;

public class Main {
    public static void main(String[] args) {

        LibraryRepository repository = new LibraryRepository();
        IPaymentService paymentService = new InvoiceManager(); // Fatura servisi
        LibraryManager manager = new LibraryManager(repository, paymentService);

        // UI
        LibraryCLI cli = new LibraryCLI(manager, repository);


        cli.loadInitialData();
        cli.start();
    }
}