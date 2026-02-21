package com.library.service;

import com.library.model.Patron;
import com.library.util.LibraryLogger;

import java.util.HashMap;
import java.util.Map;

public class PatronService {

    private Map<String, Patron> patrons = new HashMap<>();

    public void addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
        LibraryLogger.logger.info("Patron added: " + patron.getId());
    }

    public Patron getPatron(String patronId) {
        return patrons.get(patronId);
    }

    public void updatePatron(String patronId, String newName, String newEmail) {
        Patron patron = patrons.get(patronId);
        if (patron != null) {
            patron.setName(newName);
            patron.setEmail(newEmail);
            LibraryLogger.logger.info("Patron updated: " + patronId);
        }
    }
}