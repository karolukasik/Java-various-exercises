package com.karolina;

public interface DataExtractor<T> {

    T extract(String encodedData);
}
