package com.intuit.sample.impl;

import java.time.LocalDate;

import com.intuit.sample.api.BookDto;
import com.intuit.sample.api.BookStatus;

public class Book {
    private String     author;
    private String     borrower;
    private LocalDate  dueDate;
    private String     id;
    private String     isbn;
    private BookStatus status;
    private String     title;

    public String getAuthor() {
        return author;
    }

    public String getBorrower() {
        return borrower;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public boolean isOverdue(LocalDate now) {
        return dueDate != null && dueDate.isBefore(now);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookDto toDto() {
        BookDto dto = new BookDto();
        dto.setAuthor(author);
        dto.setBorrower(borrower);
        dto.setDueDate(dueDate);
        dto.setId(id);
        dto.setIsbn(isbn);
        dto.setStatus(status);
        dto.setTitle(title);
        return dto;
    }

}
