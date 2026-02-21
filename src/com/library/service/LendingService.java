package com.library.service;

import com.library.model.*;
import com.library.util.LibraryLogger;

import java.util.HashMap;
import java.util.Map;

public class LendingService {

    private Map<String, Loan> activeLoans = new HashMap<>();

    public void checkout(Book book, Patron patron) {

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available");
        }

        Loan loan = new Loan(book, patron);
        activeLoans.put(book.getId(), loan);

        book.setAvailable(false);
        patron.addToHistory(book);

        LibraryLogger.logger.info("Book checked out: " + book.getTitle());
    }

    public void returnBook(String bookId) {

        Loan loan = activeLoans.get(bookId);

        if (loan == null) {
            throw new IllegalArgumentException("No active loan found");
        }

        loan.returnBook();
        activeLoans.remove(bookId);

        LibraryLogger.logger.info("Book returned: " + bookId);
    }

    public boolean isBookBorrowed(String bookId) {
        return activeLoans.containsKey(bookId);
    }
}