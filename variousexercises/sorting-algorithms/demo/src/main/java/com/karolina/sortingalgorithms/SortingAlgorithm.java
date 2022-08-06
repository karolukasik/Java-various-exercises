package com.karolina.sortingalgorithms;

import java.util.List;

public abstract class SortingAlgorithm<T extends Comparable<T>> {

    private List<T> sortedList;

    abstract void sort(List<T> list);

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (T element : sortedList) {
            toReturn.append(" - " + element.toString());
        }

        return toReturn.toString();
    }

}
