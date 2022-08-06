package com.karolina.sortingalgorithms;

import java.util.ArrayList;
import java.util.Random;

public class SortingAlgorithms {
    public static void main(String[] args) {
        SelectionSort<Integer> selectionSorter = new SelectionSort<>();
        ArrayList<Integer> list = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            list.add(rnd.nextInt(20));
        }

        System.out.println(list);

        selectionSorter.sort(list);
        System.out.println(list);

    }
}
