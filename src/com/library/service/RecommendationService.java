package com.library.service;

import com.library.model.Book;
import com.library.model.Patron;
import com.library.strategy.RecommendationStrategy;

import java.util.List;

public class RecommendationService {

    private RecommendationStrategy strategy;

    public RecommendationService(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Book> recommend(Patron patron, List<Book> allBooks) {
        return strategy.recommend(patron, allBooks);
    }

    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }
}