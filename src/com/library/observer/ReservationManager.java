package com.library.observer;

import java.util.*;

public class ReservationManager implements Subject {

    // Each book has its own observer list
    private Map<String, List<Observer>> bookObservers = new HashMap<>();

    @Override
    public void registerObserver(String bookId, Observer observer) {
        bookObservers.putIfAbsent(bookId, new ArrayList<>());
        bookObservers.get(bookId).add(observer);
    }

    @Override
    public void removeObserver(String bookId, Observer observer) {
        if (bookObservers.containsKey(bookId)) {
            bookObservers.get(bookId).remove(observer);
        }
    }

    @Override
    public void notifyObservers(String bookId, String message) {
        if (!bookObservers.containsKey(bookId)) return;

        for (Observer observer : bookObservers.get(bookId)) {
            observer.update(bookId, message);
        }
    }
}