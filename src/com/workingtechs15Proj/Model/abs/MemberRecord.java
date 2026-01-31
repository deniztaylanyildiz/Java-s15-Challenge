package com.workingtechs15Proj.Model.abs;
import java.util.HashSet;
import java.util.Set;

public abstract class MemberRecord {
    private String id;
    private String name;
    private Set<Publication> borrowedBooks = new HashSet<>();

    public MemberRecord(String id, String name) {
        this.id = id; this.name = name;
    }

    public abstract String getMemberType();
    public abstract int getMaxBookLimit();

    public String getId() { return id; }
    public String getName() { return name; }
    public Set<Publication> getBorrowedBooks() { return borrowedBooks; }
}