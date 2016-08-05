package com.intuit.sample.impl.search;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.intuit.sample.api.search.BookSearchField;
import com.intuit.sample.api.search.FieldEqualsSpecification;
import com.intuit.sample.api.search.OverdueSpecification;
import com.intuit.sample.api.search.SearchSpecification;
import com.intuit.sample.impl.Book;

public class SearchFilterFactory {
    private static final Map<BookSearchField, Function<Book, Object>> FUNCTIONS;

    static {
        FUNCTIONS = new HashMap();
        FUNCTIONS.put(BookSearchField.AUTHOR, Book::getAuthor);
        FUNCTIONS.put(BookSearchField.ISBN, Book::getIsbn);
        FUNCTIONS.put(BookSearchField.TITLE, Book::getTitle);
    }

    public SearchFilter getFilter(SearchSpecification specification) {
        if (specification == null) {
            throw new RuntimeException("Null specification");
        }

        if (specification instanceof FieldEqualsSpecification) {
            FieldEqualsSpecification es = (FieldEqualsSpecification) specification;

            Function<Book, Object> function = FUNCTIONS.get(es.getField());

            if (function == null) {
                throw new RuntimeException("Invalid search field.");
            }

            FieldEqualsFilter filter = new FieldEqualsFilter();
            filter.setFunction(function);
            filter.setExpectedValue(es.getValue());
            return filter;
        } else if (specification instanceof OverdueSpecification) {
            OverdueFilter filter = new OverdueFilter();
            filter.setNow(LocalDate.now());
            return filter;
        }

        throw new RuntimeException("Invalid specification type: " + specification.getClass().getName());
    }
}
