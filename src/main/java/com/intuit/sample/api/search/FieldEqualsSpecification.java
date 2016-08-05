package com.intuit.sample.api.search;

public class FieldEqualsSpecification implements SearchSpecification {
    private BookSearchField field;
    private String          value;

    public BookSearchField getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public void setField(BookSearchField field) {
        this.field = field;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
