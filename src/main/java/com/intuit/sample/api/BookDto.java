package com.intuit.sample.api;

import java.time.LocalDate;
import java.util.Objects;

public class BookDto {
    private String     author;
    private String     borrower;
    private LocalDate  dueDate;
    private String     id;
    private String     isbn;
    private BookStatus status;
    private String     title;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!BookDto.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        BookDto other = (BookDto) obj;

        return Objects.equals(author, other.author) && Objects.equals(borrower, other.borrower)
                && Objects.equals(dueDate, other.dueDate) && Objects.equals(id, other.id) && Objects.equals(isbn, other.isbn)
                && Objects.equals(status, other.status) && Objects.equals(title, other.title);
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(author, borrower, dueDate, id, isbn, status, title);
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
}
