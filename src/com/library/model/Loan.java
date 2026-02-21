package com.library.model;

import java.time.LocalDate;

public class Loan {
    private Book book;
    private Patron patron;
    private LocalDate checkoutDate;
    private LocalDate returnDate;

    public Loan(Book book, Patron patron) {
        this.book = book;
        this.patron = patron;
        this.checkoutDate = LocalDate.now();
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        book.setAvailable(true);
    }
}