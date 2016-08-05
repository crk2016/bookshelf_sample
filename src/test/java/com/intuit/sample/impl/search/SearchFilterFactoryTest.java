package com.intuit.sample.impl.search;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.intuit.sample.api.search.BookSearchField;
import com.intuit.sample.api.search.FieldEqualsSpecification;
import com.intuit.sample.api.search.SearchSpecification;
import com.intuit.sample.impl.Book;

public class SearchFilterFactoryTest {
    @Test
    public void test() {
        SearchFilterFactory factory = new SearchFilterFactory();

        try {
            factory.getFilter(null);
            Assert.fail("Should have thrown exception for null specification.");
        } catch (Exception ex) {
            // Success
        }

        try {
            factory.getFilter(new SearchSpecification() {
            });
            Assert.fail("Should have thrown exception for invalid specification.");
        } catch (Exception ex) {
            // Success
        }

        FieldEqualsSpecification specification = new FieldEqualsSpecification();

        try {
            factory.getFilter(new SearchSpecification() {
            });
            Assert.fail("Should have thrown exception for invalid search field.");
        } catch (Exception ex) {
            // Success
        }

        Book book = new Book();
        book.setTitle(UUID.randomUUID().toString());
        book.setIsbn(UUID.randomUUID().toString());
        book.setAuthor(UUID.randomUUID().toString());

        specification.setField(BookSearchField.TITLE);
        specification.setValue(UUID.randomUUID().toString());

        FieldEqualsFilter filter = (FieldEqualsFilter) factory.getFilter(specification);
        Assert.assertEquals(specification.getValue(), filter.getExpectedValue());
        Assert.assertEquals(book.getTitle(), filter.getFunction().apply(book));

        specification.setField(BookSearchField.AUTHOR);
        filter = (FieldEqualsFilter) factory.getFilter(specification);
        Assert.assertEquals(book.getAuthor(), filter.getFunction().apply(book));

        specification.setField(BookSearchField.ISBN);
        filter = (FieldEqualsFilter) factory.getFilter(specification);
        Assert.assertEquals(book.getIsbn(), filter.getFunction().apply(book));
    }
}
