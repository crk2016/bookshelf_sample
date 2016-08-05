package com.intuit.sample.impl;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.intuit.sample.api.BookDto;

public class BookDtoValidatorTest {
    @Test
    public void testValidateForAddition() {
        // Test null check

        try {
            new BookDtoValidator(null).validateForAddition();
            Assert.fail("Should have failed because of null book dto");
        } catch (Exception ex) {
            // Success
        }

        BookDto book = new BookDto();
        BookDtoValidator v = new BookDtoValidator(book);

        // Test author validation

        try {
            v.validateForAddition();
            Assert.fail("Should have failed because of null author");
        } catch (Exception ex) {
            // Success
        }

        book.setAuthor("");
        try {
            v.validateForAddition();
            Assert.fail("Should have failed because of empty author");
        } catch (Exception ex) {
            // Success
        }

        book.setAuthor(UUID.randomUUID().toString());

        // Test isbn validation

        try {
            v.validateForAddition();
            Assert.fail("Should have failed because of null isbn");
        } catch (Exception ex) {
            // Success
        }

        book.setIsbn("");
        try {
            v.validateForAddition();
            Assert.fail("Should have failed because of empty isbn");
        } catch (Exception ex) {
            // Success
        }

        book.setIsbn(UUID.randomUUID().toString());

        // Test title validation

        try {
            v.validateForAddition();
            Assert.fail("Should have failed because of null title");
        } catch (Exception ex) {
            // Success
        }

        book.setTitle("");
        try {
            v.validateForAddition();
            Assert.fail("Should have failed because of empty title");
        } catch (Exception ex) {
            // Success
        }

        book.setTitle(UUID.randomUUID().toString());
        v.validateForAddition();
    }

    @Test
    public void testValidateForCheckIn() {
        // Test null check

        try {
            new BookDtoValidator(null).validateForCheckIn();
            Assert.fail("Should have failed because of null book dto");
        } catch (Exception ex) {
            // Success
        }

        BookDto book = new BookDto();
        BookDtoValidator v = new BookDtoValidator(book);

        // Test id validation

        try {
            v.validateForCheckIn();
            Assert.fail("Should have failed because of null id");
        } catch (Exception ex) {
            // Success
        }

        book.setId("");
        try {
            v.validateForCheckIn();
            Assert.fail("Should have failed because of empty id");
        } catch (Exception ex) {
            // Success
        }

        book.setId(UUID.randomUUID().toString());

        // Test borrower validation

        try {
            v.validateForCheckIn();
            Assert.fail("Should have failed because of null borrower");
        } catch (Exception ex) {
            // Success
        }

        book.setBorrower("");

        try {
            v.validateForCheckIn();
            Assert.fail("Should have failed because of empty borrower");
        } catch (Exception ex) {
            // Success
        }

        book.setBorrower(UUID.randomUUID().toString());
        v.validateForCheckIn();
    }

    @Test
    public void testValidateForCheckOut() {
        // Test null check

        try {
            new BookDtoValidator(null).validateForCheckOut();
            Assert.fail("Should have failed because of null book dto");
        } catch (Exception ex) {
            // Success
        }

        BookDto book = new BookDto();
        BookDtoValidator v = new BookDtoValidator(book);

        // Test id validation

        try {
            v.validateForCheckOut();
            Assert.fail("Should have failed because of null id");
        } catch (Exception ex) {
            // Success
        }

        book.setId("");
        try {
            v.validateForCheckOut();
            Assert.fail("Should have failed because of empty id");
        } catch (Exception ex) {
            // Success
        }

        book.setId(UUID.randomUUID().toString());
        v.validateForCheckOut();
    }

}
