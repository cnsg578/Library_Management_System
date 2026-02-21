package com.library;

import com.library.factory.BookFactory;
import com.library.model.Book;
import com.library.model.Patron;
import com.library.service.*;

public class Main {

    public static void main(String[] args) {

        // Initialize services
        LibraryService libraryService = new LibraryService();
        PatronService patronService = new PatronService();
        LendingService lendingService = new LendingService();

        // Add branch
        libraryService.addBranch("MainBranch");

        // Create book
        Book book1 = BookFactory.createBook("Clean Code", "Robert Martin", "123", 2008);

        // Add book to branch
        libraryService.addBook("MainBranch", book1);

        // Create patron
        Patron patron = new Patron("1", "Chinmay", "chinmay@email.com");

        // Add patron
        patronService.addPatron(patron);

        // Checkout book
        lendingService.checkout(book1, patron);

        // Return book
        lendingService.returnBook(book1.getId());

        System.out.println("Completed Successfully!");
    }
}