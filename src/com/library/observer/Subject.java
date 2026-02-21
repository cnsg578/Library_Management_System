package com.library.observer;

public interface Subject {
    void registerObserver(String bookId, Observer observer);
    void removeObserver(String bookId, Observer observer);
    void notifyObservers(String bookId, String message);
}