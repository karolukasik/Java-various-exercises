package com.karolina.sortingalgorithms;

import java.util.List;

public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    @Override
    public void sort(List<T> list) {

        if (list.size() <= 1) {
            return;
        }

        boolean wasSwapped;
        T firstValue;
        T secondValue;

        do {
            wasSwapped = false;
            for (int i = 0; i < list.size() - 1; i++) {
                firstValue = list.get(i);
                secondValue = list.get(i + 1);
                if (firstValue.compareTo(secondValue) > 0) {
                    list.set(i, secondValue);
                    list.set(i + 1, firstValue);
                    wasSwapped = true;
                }
            }
        } while (wasSwapped == true);

    }

}
