package com.karolina.sortingalgorithms;

import java.util.List;

public abstract class SortingAlgorithm<T extends Comparable<T>> {

    public abstract void sort(List<T> list);

    private long getSortingTime(List<T> list) {
        long sortStartTime = System.nanoTime();
        this.sort(list);
        long sortElapsedTime = System.nanoTime() - sortStartTime;

        return sortElapsedTime;
    }

    public void printSortingTime(List<T> list) {
        System.out.println(this.getClass().getSimpleName() + " algorithm took " + getSortingTime(list) / 1000000
                + " milliseconds");
    }
}
