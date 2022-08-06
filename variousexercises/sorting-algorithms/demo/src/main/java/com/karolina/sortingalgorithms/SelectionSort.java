package com.karolina.sortingalgorithms;

import java.util.List;

public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    @Override
    void sort(List<T> list) {

        if (list.size() > 1) {
            T min;

            for (int i = 0; i < list.size()-1; i++) {

                for (int j = i + 1; j > 0; j--) {
                    min = list.get(j - 1);
                    T comparedValue = list.get(j);
                    
                    if (comparedValue.compareTo(min) < 0) {
                        list.set(j, min);
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
