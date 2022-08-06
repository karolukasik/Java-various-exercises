package com.karolina.sortingalgorithms;

import java.util.ArrayList;
import java.util.Random;

public class SortingAlgorithms {
    public static void main(String[] args) {
        BubbleSort<Integer> bubbleSorter = new BubbleSort<>();
        ArrayList<Integer> list = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            list.add(rnd.nextInt(20));
        }

        System.out.println(list);

        bubbleSorter.sort(list);
        System.out.println(list);

    }
}
