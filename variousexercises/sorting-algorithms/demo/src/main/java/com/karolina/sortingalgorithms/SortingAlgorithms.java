package com.karolina.sortingalgorithms;

import java.util.ArrayList;
import java.util.Random;

public class SortingAlgorithms {
    public static void main(String[] args) {
        InsertionSort<Integer> insertionSorter = new InsertionSort<>();
        ArrayList<Integer> list = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < 20; i++) {
        list.add(rnd.nextInt(20));
        }


        System.out.println(list);

        insertionSorter.sort(list);
        System.out.println(list);

    }
}
