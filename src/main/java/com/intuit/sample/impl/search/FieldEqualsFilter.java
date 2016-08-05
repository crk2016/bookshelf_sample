package com.intuit.sample.impl.search;

import java.util.Objects;
import java.util.function.Function;

public class FieldEqualsFilter<T, V> implements SearchFilter<T> {
    private V              expectedValue;
    private Function<T, V> function;

    @Override
    public boolean evaluate(T obj) {
        return Objects.equals(expectedValue, function.apply(obj));
    }

    public V getExpectedValue() {
        return expectedValue;
    }

    public Function<T, V> getFunction() {
        return function;
    }

    public void setExpectedValue(V expectedValue) {
        this.expectedValue = expectedValue;
    }

    public void setFunction(Function<T, V> function) {
        this.function = function;
    }

}
