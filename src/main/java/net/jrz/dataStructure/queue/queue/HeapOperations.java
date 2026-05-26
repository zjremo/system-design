package net.jrz.dataStructure.queue.queue;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class HeapOperations {
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = 0;
        while ((left = index * 2 + 1) < heapSize) {
            int larger = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            larger = arr[index] > arr[larger] ? index : larger;

            if (larger == index)
                break;
            swap(arr, index, larger);
            index = larger;
        }
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

/*
        for (int i = (arr.length >>> 1) - 1; i >= 0; --i) {
            heapify(arr, i, arr.length);
        }
*/
        for (int i = 1; i < arr.length; ++i) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    public static void swap(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }

        arr[l] ^= arr[r];
        arr[r] ^= arr[l];
        arr[l] ^= arr[r];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strNums = sc.nextLine().replace(" ", "").split(",");

        int[] nums = Arrays.stream(strNums).mapToInt(Integer::parseInt).toArray();
        heapSort(nums);

        IntStream.of(nums).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}

