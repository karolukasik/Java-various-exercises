package com.karolina.sortingalgorithms;

import java.util.List;

public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    @Override
    void sort(List<T> list) {

        if (list.size() > 1) {
            T currentMin;
            T comparedValue;

            
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j > 0; j--) {
                    currentMin = list.get(j - 1);
                    comparedValue = list.get(j);

                    if (comparedValue.compareTo(currentMin) < 0) {
                        list.set(j, currentMin);
                        list.set(j - 1, comparedValue);
                    } else {
                        break;
                    }
                }
            }
        } else {
            return;
        }

    }
}
