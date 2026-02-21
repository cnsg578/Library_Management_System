package com.library.model;

import java.util.HashMap;
import java.util.Map;

public class Branch {
    private String name;
    private Map<String, Book> books = new HashMap<>();

    public Branch(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public Map<String, Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
}