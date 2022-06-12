#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "sort_library.h"

static void swap(void **arr, int i, int j) {
    void *tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

static int binarySearch(void **arr, void *elem, int low, int high, func func) {

    while (low <= high) {

        int mid = low + (high - low) / 2;
        void *elem_to_move = arr[mid];
        int result = func(elem, elem_to_move);

        if (result == 0) {
            return mid;
        } else if (result > 0) {
            low = mid + 1;
        } else if (result < 0) {
            high = mid - 1;
        }
    }
    return low;
}

void insertSort(void **array_to_order, int size, func func) {
    int i, pos, j;

    for (i = 1; i < size; ++i) {
        j = i - 1;
        void *elem = array_to_order[i];

        pos = binarySearch(array_to_order, elem, 0, j, func);


        while (j >= pos) {
            swap(array_to_order, j, j + 1);
            j--;
        }
        printf("sto swappando elemento in: %d\n", i);
    }
}

void quickSort(void **array_to_order, int first, int last, func func) {
    if (first < last) {
        int pivot = partition(array_to_order, first, last, func);  // ordino array

        quickSort(array_to_order, first, pivot-1, func);    // ordino parte destra
        quickSort(array_to_order, pivot + 1, last, func);  // ordino parte sinistra
    }
}

static int partition(void **array, int low, int high, func func) {

    int mid = low + (high - low) / 2;
    int median = (low + mid + high) / 3;

    swap(array, high, median);

    /*int random_pivot = (rand() % (high - low + 1)) + low;
    swap(array, high, random_pivot);*/

    void *piv_p = array[high];
    int i = low - 1;
    int j = low;
    while (j < high) {
        if (func(array[j], piv_p) <= 0) {
            i++;
            swap(array, i, j);
        }
        j++;
    }

    swap(array, i+1, high);
    return i+1;
}