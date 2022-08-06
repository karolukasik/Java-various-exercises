package com.karolina.sortingalgorithms;

import java.util.List;

public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    @Override
    void sort(List<T> list) {

        if (list.size() > 1 && !list.isEmpty()) {
            int startingPos;
            T comparedValue;
            T originalValue;

            for (int i = 0; i < list.size() - 1; i++) {
                startingPos = i + 1;

                for (int j = startingPos - 1; j >= 0; j--) {
                    comparedValue = list.get(j);
                    originalValue = list.get(j + 1);

                    if (originalValue.compareTo(comparedValue) < 0) {
                        list.set(j + 1, comparedValue);
                        list.set(j, originalValue);
                    } else {
                        break;
                    }
                }
            }

        }

    }

}
