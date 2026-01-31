package com.workingtechs15Proj.repository;

import com.workingtechs15Proj.Model.abs.Publication;
import com.workingtechs15Proj.Model.abs.MemberRecord;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryRepository {
    private Map<String, Publication> books = new HashMap<>();
    private Map<String, MemberRecord> members = new HashMap<>();

    public void saveBook(Publication b) { books.put(b.getId(), b); }
    public void saveMember(MemberRecord m) { members.put(m.getId(), m); }

    public Publication findBookById(String id) { return books.get(id); }
    public MemberRecord findMemberById(String id) { return members.get(id); }

    // Kitap Silme
    public boolean removeBook(String id) {
        return books.remove(id) != null;
    }

    // Yazara göre filtreleme
    public List<Publication> findByAuthor(String author) {
        return books.values().stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    // Kategoriye göre filtreleme
    public List<Publication> findByCategory(String category) {
        return books.values().stream()
                .filter(b -> b.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Collection<Publication> getAllBooks() { return books.values(); }
    public Collection<MemberRecord> getAllMembers() { return members.values(); }
}