package com.intuit.sample.impl;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.intuit.sample.api.BookDto;
import com.intuit.sample.api.BookStatus;
import com.intuit.sample.api.search.BookSpecifications;

public class BookshelfImplTest {
    private BookDto randomBook() {
        BookDto book = new BookDto();
        book.setAuthor(UUID.randomUUID().toString());
        book.setIsbn(UUID.randomUUID().toString());
        book.setTitle(UUID.randomUUID().toString());
        return book;
    }

    @Test
    public void testAdd() {
        BookshelfImpl shelf = new BookshelfImpl();
        shelf.setLimit(0);

        try {
            shelf.add(new BookDto());
            Assert.fail("Should have thrown size exception.");
        } catch (Exception ex) {
            // Success
        }

        shelf.setLimit(10);

        for (int i = 0; i < 10; i++) {
            shelf.add(randomBook());
            Assert.assertEquals(i + 1, shelf.search().size());
        }
    }

    @Test
    public void testCheckOutAndCheckIn() {
        BookshelfImpl shelf = new BookshelfImpl();
        shelf.setLimit(10);

        for (int i = 0; i < 10; i++) {
            shelf.add(randomBook());
        }

        List<BookDto> books = shelf.search();
        BookDto book = books.get(5);

        String userName = UUID.randomUUID().toString();
        BookDto checkedOut = shelf.checkOut(book, userName);
        Assert.assertEquals(book.getId(), checkedOut.getId());
        Assert.assertEquals(userName, checkedOut.getBorrower());
        Assert.assertNotNull(checkedOut.getDueDate());
        Assert.assertEquals(BookStatus.UNAVAILABLE, checkedOut.getStatus());

        try {
            shelf.checkOut(book, userName);
            Assert.fail("Should have thrown unavailable exception");
        } catch (Exception ex) {
            // Success
        }

        shelf.checkIn(checkedOut);

        try {
            shelf.checkIn(checkedOut);
            Assert.fail("Should have thrown is not checked out exception.");
        } catch (Exception ex) {
            // Success
        }
    }

    @Test
    public void testSearch() {
        BookshelfImpl shelf = new BookshelfImpl();
        shelf.setLimit(10);

        for (int i = 0; i < 10; i++) {
            shelf.add(randomBook());
        }

        List<BookDto> books = shelf.search();
        BookDto book = books.get(5);

        // Search by author
        List<BookDto> searchResults = shelf.search(BookSpecifications.authorEquals(book.getAuthor()));
        Assert.assertEquals(1, searchResults.size());
        Assert.assertEquals(book, searchResults.get(0));

        // Search by isbn
        searchResults = shelf.search(BookSpecifications.isbnEquals(book.getIsbn()));
        Assert.assertEquals(1, searchResults.size());
        Assert.assertEquals(book, searchResults.get(0));

        // Search by title
        searchResults = shelf.search(BookSpecifications.titleEquals(book.getTitle()));
        Assert.assertEquals(1, searchResults.size());
        Assert.assertEquals(book, searchResults.get(0));

        // Search by author + isbn + title
        searchResults = shelf.search(BookSpecifications.authorEquals(book.getAuthor()),
                BookSpecifications.isbnEquals(book.getIsbn()), BookSpecifications.titleEquals(book.getTitle()));
        Assert.assertEquals(1, searchResults.size());
        Assert.assertEquals(book, searchResults.get(0));

        // Search by overdue
        searchResults = shelf.search(BookSpecifications.overdue());
        Assert.assertEquals(0, searchResults.size());

        shelf.setBorrowingPeriod(-10);
        shelf.checkOut(book, UUID.randomUUID().toString());
        searchResults = shelf.search(BookSpecifications.overdue());
        Assert.assertEquals(1, searchResults.size());
        Assert.assertEquals(book.getId(), searchResults.get(0).getId());

    }
}
