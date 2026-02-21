package com.library.service;

import com.library.model.Book;
import com.library.model.Patron;
import com.library.observer.Observer;
import com.library.observer.ReservationManager;
import com.library.util.LibraryLogger;

import java.util.*;

public class ReservationService {

    private Map<String, Queue<Patron>> reservationQueue = new HashMap<>();
    private ReservationManager reservationManager = new ReservationManager();

    public void reserveBook(Book book, Patron patron) {

        reservationQueue.putIfAbsent(book.getId(), new LinkedList<>());
        reservationQueue.get(book.getId()).add(patron);

        Observer observer = new PatronObserver(patron);
        reservationManager.registerObserver(book.getId(), observer);

        LibraryLogger.logger.info("Book reserved by Patron: " + patron.getId());
    }

    public void bookReturned(Book book) {

        String bookId = book.getId();

        Queue<Patron> queue = reservationQueue.get(bookId);
        if (queue == null || queue.isEmpty()) return;

        Patron nextPatron = queue.poll();

        reservationManager.notifyObservers(
                bookId,
                "Book '" + book.getTitle() + "' is now available."
        );

        LibraryLogger.logger.info("Notification sent to Patron: " + nextPatron.getId());
    }

    // ================= Observer Implementation =================

    static class PatronObserver implements Observer {

        private Patron patron;

        public PatronObserver(Patron patron) {
            this.patron = patron;
        }

        @Override
        public void update(String bookId, String message) {
            System.out.println("Notification for " + patron.getName() + ": " + message);
        }
    }
}