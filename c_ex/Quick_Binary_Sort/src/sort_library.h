typedef int (*func)(void*, void*);


/**
 * this function swap the element in the array 
 * 
 * @param arr: array that contains the element which need to be swapped
 * @param i: position of the first element that need to be swapped
 * @param j: position of the second element that need to be swapped
 * @param size: of the element swapped
 */
static void swap (void **arr, int i, int j);

/**
 * This function sort an array, it takes the middle element as pivot and start the reorder the element according to the function.
 * 
 * @param array_to_order: array to sort
 * @param first: position of the first element
 * @param last: position of the last element
 * @param func: pointer to a function used to sort the element in the array
 */
void quick_sort(void **array_to_order, int first, int last, int(*order) (void*, void*));


/**
 * This function support quick_sort function, it choose a pivot used to divide the array 
 * 
 * @param array: array that need to be divided
 * @param low: position of the first element
 * @param high: position of the last element
 * @return int it return the position where the array is divided
 */
static int partition(void **array, int low, int high, int(*order)(void*, void*));
