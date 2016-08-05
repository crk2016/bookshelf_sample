package com.intuit.sample.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.intuit.sample.api.BookDto;
import com.intuit.sample.api.BookStatus;
import com.intuit.sample.api.Bookshelf;
import com.intuit.sample.api.search.SearchSpecification;
import com.intuit.sample.impl.search.SearchFilter;
import com.intuit.sample.impl.search.SearchFilterFactory;

public class BookshelfImpl implements Bookshelf {
    private final Map<String, Book> books           = new ConcurrentHashMap();
    private int                     borrowingPeriod = 14;
    private int                     limit;

    @Override
    public void add(BookDto bookDto) {
        validateBooksSize();

        getBookDtoValidator(bookDto).validateForAddition();

        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setStatus(BookStatus.AVAILABLE);

        synchronized (books) {
            validateBooksSize();
            books.put(book.getId(), book);
        }
    }

    @Override
    public void checkIn(BookDto bookDto) {
        getBookDtoValidator(bookDto).validateForCheckIn();

        Book book = getBook(bookDto.getId());

        if (book == null) {
            throw new RuntimeException("No such book: " + bookDto.getId());
        }

        if (!BookStatus.UNAVAILABLE.equals(book.getStatus())) {
            throw new RuntimeException("Book is not checked out.");
        }

        synchronized (book) {
            book.setBorrower(null);
            book.setDueDate(null);
            book.setStatus(BookStatus.AVAILABLE);
        }
    }

    @Override
    public BookDto checkOut(BookDto bookDto, String userName) {
        getBookDtoValidator(bookDto).validateForCheckOut();

        if (userName == null || userName.isEmpty()) {
            throw new RuntimeException("Invalid user name: " + userName);
        }

        Book book = getBook(bookDto.getId());

        if (book == null) {
            throw new RuntimeException("Invalid book: " + bookDto);
        }

        if (!BookStatus.AVAILABLE.equals(book.getStatus())) {
            throw new RuntimeException("Book is unavailable.");
        }

        synchronized (book) {
            if (!BookStatus.AVAILABLE.equals(bookDto.getStatus())) {
                throw new RuntimeException("Book is unavailable.");
            }

            book.setStatus(BookStatus.UNAVAILABLE);
            book.setBorrower(userName);
            book.setDueDate(getDueDate());
            return book.toDto();
        }
    }

    private Book getBook(String id) {
        return books.get(id);
    }

    private BookDtoValidator getBookDtoValidator(BookDto bookDto) {
        return new BookDtoValidator(bookDto);
    }

    private LocalDate getDueDate() {
        return LocalDate.now().plusDays(borrowingPeriod);
    }

    private List<SearchFilter> getSearchFilters(SearchSpecification... specifications) {
        SearchFilterFactory factory = new SearchFilterFactory();

        List<SearchFilter> filters = new ArrayList();

        for (SearchSpecification specification : specifications) {
            filters.add(factory.getFilter(specification));
        }

        return filters;
    }

    @Override
    public List<BookDto> search(SearchSpecification... criteria) {
        List<BookDto> result = new ArrayList();
        List<SearchFilter> filters = getSearchFilters(criteria);

        for (Book book : books.values()) {
            boolean accept = true;

            for (SearchFilter filter : filters) {
                if (!filter.evaluate(book)) {
                    accept = false;
                    break;
                }
            }

            if (accept) {
                result.add(book.toDto());
            }
        }
        return result;
    }

    public void setBorrowingPeriod(int borrowingPeriod) {
        this.borrowingPeriod = borrowingPeriod;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    private void validateBooksSize() {
        if (books.size() >= limit) {
            throw new RuntimeException("The bookshelf has reached its limit.");
        }
    }

}
