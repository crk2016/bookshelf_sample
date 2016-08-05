package com.intuit.sample.api;

import java.util.List;

import com.intuit.sample.api.search.SearchSpecification;

public interface Bookshelf {
    public void add(BookDto book);

    public void checkIn(BookDto book);

    public BookDto checkOut(BookDto book, String userName);

    public List<BookDto> search(SearchSpecification... criteria);

}
