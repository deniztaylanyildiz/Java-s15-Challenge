package com.workingtechs15Proj.Model.abs;
import com.workingtechs15Proj.Model.abs.Publication;

import java.util.HashSet;
import java.util.Set;

public abstract class MemberRecord {
    private String id, name;
    private Set<Publication> borrowedBooks = new HashSet<>();

    public MemberRecord(String id, String name) { this.id = id; this.name = name; }

    public String getId() { return id; }
    public String getName() { return name; }
    public Set<Publication> getBorrowedBooks() { return borrowedBooks; }

    // HATA ÇÖZÜMÜ: Bu metodu ekliyoruz
    public abstract int getMaxBookLimit();
    public abstract String getMemberType();
}