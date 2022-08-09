package com.karolina.sortingalgorithms;

import java.util.List;

public abstract class SortingAlgorithm<T extends Comparable<T>> {

    public List<T> list;

    abstract void sort(List<T> list);

    public long checkSortingTime(SortingAlgorithm<Integer> sorter, List<Integer> list) {
        long sortStartTime = System.nanoTime();
        sorter.sort(list);
        long sortElapsedTime = System.nanoTime() - sortStartTime;

        return sortElapsedTime;
    }

    public void printSortingTime(SortingAlgorithm<Integer> sorter, List<Integer> list) {
        System.out.println("Quick sorting took " + checkSortingTime(sorter, list) / 1000000 + " milliseconds");
    }
}
