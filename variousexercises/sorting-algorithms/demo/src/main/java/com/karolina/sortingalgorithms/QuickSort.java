package com.karolina.sortingalgorithms;

import java.util.List;
import java.util.Random;

public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    @Override
    void sort(List<T> list) {
        sort(list, 0, list.size() - 1);
    }

    void sort(List<T> list, int bottomIndex, int upperIndex) {
        if (bottomIndex >= upperIndex) {
            return;
        }
        // random selection of pivot makes algorithm more efficient
        int pivotIndex = new Random().nextInt(upperIndex - bottomIndex) + bottomIndex;
        T pivot = list.get(pivotIndex);
        swap(list, pivotIndex, upperIndex);

        int leftPointer = partition(list, bottomIndex, upperIndex, pivot);

        sort(list, bottomIndex, leftPointer - 1);
        sort(list, leftPointer + 1, upperIndex);
    }

    private int partition(List<T> list, int bottomIndex, int upperIndex, T pivot) {
        int leftPointer = bottomIndex;
        int rightPointer = upperIndex;

        while (leftPointer < rightPointer) {
            while (list.get(leftPointer).compareTo(pivot) <= 0 && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (list.get(rightPointer).compareTo(pivot) >= 0 && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(list, leftPointer, rightPointer);
        }
        swap(list, leftPointer, upperIndex);
        return leftPointer;
    }

    private void swap(List<T> list, int firstIndex, int secondIndex) {
        T temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
    }

}
