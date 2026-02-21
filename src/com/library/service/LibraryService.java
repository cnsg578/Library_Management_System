package com.library.service;

import com.library.model.Book;
import com.library.model.Branch;
import com.library.util.LibraryLogger;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {

    private Map<String, Branch> branches = new HashMap<>();

    // ================= BRANCH MANAGEMENT =================

    public void addBranch(String branchName) {
        branches.put(branchName, new Branch(branchName));
        LibraryLogger.logger.info("Branch added: " + branchName);
    }

    public Branch getBranch(String branchName) {
        return branches.get(branchName);
    }

    // ================= BOOK MANAGEMENT =================

    public void addBook(String branchName, Book book) {
        Branch branch = branches.get(branchName);
        if (branch == null) throw new IllegalArgumentException("Branch not found");

        branch.addBook(book);
        LibraryLogger.logger.info("Book added to branch: " + branchName);
    }

    public void removeBook(String branchName, String bookId) {
        Branch branch = branches.get(branchName);
        if (branch != null) {
            branch.getBooks().remove(bookId);
            LibraryLogger.logger.info("Book removed: " + bookId);
        }
    }

    public void transferBook(String fromBranch, String toBranch, String bookId) {
        Branch source = branches.get(fromBranch);
        Branch destination = branches.get(toBranch);

        if (source == null || destination == null)
            throw new IllegalArgumentException("Invalid branch");

        Book book = source.getBooks().remove(bookId);
        if (book != null) {
            destination.addBook(book);
            LibraryLogger.logger.info("Book transferred from " + fromBranch + " to " + toBranch);
        }
    }

    // ================= SEARCH =================

    public List<Book> searchByTitle(String title) {
        return getAllBooks().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        return getAllBooks().stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public Book searchByIsbn(String isbn) {
        return getAllBooks().stream()
                .filter(b -> b.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null);
    }

    public List<Book> getAllBooks() {
        return branches.values().stream()
                .flatMap(branch -> branch.getBooks().values().stream())
                .collect(Collectors.toList());
    }
}