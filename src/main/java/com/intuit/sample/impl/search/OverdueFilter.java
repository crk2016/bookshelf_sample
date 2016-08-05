package com.intuit.sample.impl.search;

import java.time.LocalDate;

import com.intuit.sample.impl.Book;

public class OverdueFilter implements SearchFilter<Book> {
    private LocalDate now;

    @Override
    public boolean evaluate(Book book) {
        return book.isOverdue(now);
    }

    public LocalDate getNow() {
        return now;
    }

    public void setNow(LocalDate now) {
        this.now = now;
    }

}
