package com.karolina.sortingalgorithms;

import java.util.List;

public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    @Override
    void sort(List<T> list) {

        if (list.size() > 1 && !list.isEmpty()) {
            T currentMin;
            T comparedValue;
            int indexOfMin;

            for (int i = 0; i < list.size() - 1; i++) {
                currentMin = list.get(i);
                indexOfMin = i;
                for (int j = i + 1; j < list.size(); j++) {
                    comparedValue = list.get(j);

                    if (comparedValue.compareTo(currentMin) < 0) {
                        currentMin = comparedValue;
                        indexOfMin = j;
                    } else {
                        continue;
                    }
                }
                list.set(indexOfMin, list.get(i));
                list.set(i, currentMin);
            }
        } else {
            return;
        }

    }
}
