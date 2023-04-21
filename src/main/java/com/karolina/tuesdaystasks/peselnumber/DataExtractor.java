package com.karolina.tuesdaystasks.peselnumber;

public interface DataExtractor<T> {

    T extract(String encodedData);
}
