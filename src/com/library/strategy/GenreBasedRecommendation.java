package com.library.strategy;

import com.library.model.Book;
import com.library.model.Patron;
import java.util.ArrayList;
import java.util.List;

public class GenreBasedRecommendation implements RecommendationStrategy {

    @Override
    public List<Book> recommend(Patron patron, List<Book> allBooks) {
        List<Book> recommendations = new ArrayList<>();
        if (patron.getBorrowingHistory().isEmpty()) return recommendations;

        String lastAuthor = patron.getBorrowingHistory()
                .get(patron.getBorrowingHistory().size()-1)
                .getAuthor();

        for (Book book : allBooks) {
            if (book.getAuthor().equals(lastAuthor) && book.isAvailable()) {
                recommendations.add(book);
            }
        }
        return recommendations;
    }
}