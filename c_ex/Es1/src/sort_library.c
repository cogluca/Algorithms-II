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

static int binarySearch(void **arr, void *elem, int  low, int high, func func){

    while(low <= high){

        int mid = low + (high-low)/2;
        void *check_elem = arr[mid];
        int result = func(elem, check_elem);

        if(result == 0){
            return mid + 1;
        }else if(result == 1){
            low = mid + 1;
        }else if(result == 2){
            high = mid - 1;
        }
    }
        return low;
}

void insertSort(void **array_to_order, int n, func func){
    int i, pos, j;

    for(i = 1; i < n; i++){
        j = i - 1;
        void *elem = array_to_order[i];

        pos = binarySearch(array_to_order, elem, 0, j, func);
        
        

        while(j >= pos){
            swap(array_to_order, j, j+1);
            j--;
        }
    }
}

void quickSort(void **array_to_order, int first, int last, func func){
    if(first < last){ 
        int pivot = partition(array_to_order, first, last, func);  // ordino array
        
        quickSort(array_to_order, first, pivot, func);    // ordino parte destra
        quickSort(array_to_order, pivot +1, last, func);  // ordino parte sinistra
    }
}

static int partition(void **array, int low, int high, func func){
    void *piv_p = array[high];
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