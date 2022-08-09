package com.karolina.sortingalgorithms;

import java.util.ArrayList;
import java.util.Random;

public class SortingAlgorithmsProject {
    public static void main(String[] args) {
        QuickSort<Integer> quickSorter = new QuickSort<>();
        SelectionSort<Integer> selectionSorter = new SelectionSort<>();

        ArrayList<Integer> listForQuickSort = new ArrayList<>();
        ArrayList<Integer> listForSelectionSort = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < 100000; i++) {
            int value = rnd.nextInt(10000);
            listForQuickSort.add(value);
            listForSelectionSort.add(value);
        }

        quickSorter.printSortingTime(quickSorter, listForQuickSort);
        selectionSorter.printSortingTime(selectionSorter, listForSelectionSort);

    }

}
