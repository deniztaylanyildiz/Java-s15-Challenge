package com.workingtechs15Proj.service;
import com.workingtechs15Proj.Model.abs.MemberRecord;
import com.workingtechs15Proj.Model.abs.Publication;

public interface IPaymentService {
    void processInvoice(MemberRecord m, Publication p);
    void processRefund(MemberRecord m, Publication p);
}