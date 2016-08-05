package com.intuit.sample.impl;

import java.util.function.Function;

import com.intuit.sample.api.BookDto;

public class BookDtoValidator {
    private final BookDto bookDto;

    public BookDtoValidator(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    private void validateAuthor() {
        validateNotEmpty(BookDto::getAuthor, "Invalid author.");
    }

    private void validateBorrower() {
        validateNotEmpty(BookDto::getBorrower, "Invalid borrower.");
    }

    public void validateForAddition() {
        validateNotNull();
        validateAuthor();
        validateIsbn();
        validateTitle();
    }

    public void validateForCheckIn() {
        validateNotNull();
        validateId();
        validateBorrower();
    }

    public void validateForCheckOut() {
        validateNotNull();
        validateId();
    }

    private void validateId() {
        validateNotEmpty(BookDto::getId, "Invalid id.");
    }

    private void validateIsbn() {
        validateNotEmpty(BookDto::getIsbn, "Invalid ISBN.");
    }

    private void validateNotEmpty(Function<BookDto, String> function, String message) {
        if (bookDto != null) {
            String s = function.apply(bookDto);
            if (s == null || s.isEmpty()) {
                throw new RuntimeException(message);
            }
        }
    }

    private void validateNotNull() {
        if (bookDto == null) {
            throw new RuntimeException("Book is null.");
        }
    }

    private void validateTitle() {
        validateNotEmpty(BookDto::getTitle, "Invalid title.");
    }
}
