package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class Patron {

    private String id;
    private String name;
    private String email;
    private List<Book> borrowingHistory = new ArrayList<>();

    public Patron(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // ================= Getters =================

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBorrowingHistory() {
        return borrowingHistory;
    }

    // ================= Setters =================

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ================= Business Logic =================

    public void addToHistory(Book book) {
        borrowingHistory.add(book);
    }
}