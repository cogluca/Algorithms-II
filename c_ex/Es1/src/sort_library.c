#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "sort_library.h"

/**
 *  
 * 
 */

static void swap (void **arr, int i, int j){
    void *tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

static int binary_search(void **arr, void *elem, int  low, int high, func func){
    while(low <= high){

        int mid = low - (high-low)/2;
        void *rec_p = arr[mid];
        int p = func(elem, rec_p);

        if(func(elem, rec_p) == 0){
            return mid + 1;
        }else if(func(elem, rec_p) == 1){
            low = mid + 1;
        }else if(func(elem, rec_p) == 2){
            high = mid - 1;
        }

        return low;
    }
}

void insert_sort(void **array_to_order, int n, func func){
    int i, pos, j;
    void *elem;

    for(i = 1; i < n; i++){
        j = i - 1;
        elem = array_to_order[i];

        pos = binary_search(array_to_order, elem, 0, j, func);

        while(j >= pos){
            swap(array_to_order, j, j+1);
            j--;
        }
    }
}

void quick_sort(void **array_to_order, int first, int last, func func){
    if(first < last){ 
        int pivot = partition(array_to_order, first, last, func);  // ordino array
        
        quick_sort(array_to_order, first, pivot, func);    // ordino parte destra
        quick_sort(array_to_order, pivot +1, last, func);  // ordino parte sinistra
    }
}

static int partition(void **array, int low, int high, func func){
    void *piv_p = array[high]; // uso l'ultimo elemento come pivot TODO controllare se è ben definito il puntatore
    int i = low;
    int j = high-1;

    while (i <= j){
        if(func(piv_p, array[i]) < 2){
            i++;
        }else{
            if(func(piv_p, array[j]) == 2){
                j--;
            }else{
                swap(array, i, j);
                i++;
                j--;
            }
        }
    }
    if(func(piv_p, array[i]) == 2)
        swap(array, high, i);
        
    return j;
}