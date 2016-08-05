package com.intuit.sample.api.search;

public class BookSpecifications {
    public static SearchSpecification authorEquals(String value) {
        FieldEqualsSpecification spec = new FieldEqualsSpecification();
        spec.setField(BookSearchField.AUTHOR);
        spec.setValue(value);
        return spec;
    }

    public static SearchSpecification isbnEquals(String value) {
        FieldEqualsSpecification spec = new FieldEqualsSpecification();
        spec.setField(BookSearchField.ISBN);
        spec.setValue(value);
        return spec;
    }

    public static SearchSpecification overdue() {
        return new OverdueSpecification();
    }

    public static SearchSpecification titleEquals(String value) {
        FieldEqualsSpecification spec = new FieldEqualsSpecification();
        spec.setField(BookSearchField.TITLE);
        spec.setValue(value);
        return spec;
    }

}
