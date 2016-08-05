package com.intuit.sample.impl.search;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.intuit.sample.impl.Book;

public class FieldEqualsFilterTest {
    @Test
    public void test() {
        Book book = new Book();
        book.setTitle(UUID.randomUUID().toString());

        FieldEqualsFilter<Book, String> filter = new FieldEqualsFilter();
        filter.setExpectedValue(book.getTitle());
        filter.setFunction(Book::getTitle);

        Assert.assertTrue(filter.evaluate(book));

        filter.setExpectedValue(UUID.randomUUID().toString());
        Assert.assertFalse(filter.evaluate(book));
    }
}
