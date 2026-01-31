package com.workingtechs15Proj.service;
import com.workingtechs15Proj.Model.abs.MemberRecord;
import com.workingtechs15Proj.Model.abs.Publication;

public class InvoiceManager implements IPaymentService {
    @Override
    public void processInvoice(MemberRecord m, Publication p) {
        System.out.println("\n[FATURA] Üye: " + m.getName() + " | Kitap: " + p.getTitle() + " | Tutar: " + p.getPrice() + " TL");
    }

    @Override
    public void processRefund(MemberRecord m, Publication p) {
        System.out.println("\n[İADE FİŞİ] Üye: " + m.getName() + " | İade: " + p.getTitle() + " | Tutar: " + p.getPrice() + " TL");
    }
}