package com.intuit.sample.impl.search;

public interface SearchFilter<T> {
    public boolean evaluate(T obj);
}
