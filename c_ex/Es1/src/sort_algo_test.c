#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sort_library.h"
#include "unity.h"

#define STRING_SIZE 64
#define ARR_CAPACITY 7

int comp_int(void *a, void *b){
    int *int_a = (int *) a;
    int *int_b = (int *) b;
    if(*int_a < *int_b){
        return 2;
    }else if(*int_a > *int_b){
        return 1;
    }
    return 0;
}

int comp_float(void *a, void *b){
    float *float_a = (float *) a;
    float *float_b = (float *) b;

    if(*float_a < *float_b){
        return 2;
    }else if(*float_a > *float_b){
        return 1;
    }
    return 0;
}

int comp_string(void *a, void *b){
    char *char_a = (char *) a;
    char *char_b = (char *) b;
    if(strcmp(*char_a, *char_b) > 0){
        return 2;
    }else if(strcmp(*char_a, *char_b) < 0){
        return 1;
    }
    return 0;
}

//static int arr[ARR_CAPACITY];

void setUp(void) {
    /*for(char i = 0; i < ARR_CAPACITY; i++){
        arr[i] = i;
    }*/
}

void tearDown(void) {
}

/*static void free_memory(void** array){
    for(int i = 0; i < ARR_CAPACITY; i++){
        Record *rec_p = (Record *) array[i];
        free(rec_p->string_field);
    }
    free(array);    
}*/

void test_sorted_int_array() { 
  int arr[] = {1,2,3,4,5,6,7};
  int* array[] = {&arr[0],&arr[1],&arr[2],&arr[3],&arr[4],&arr[5],&arr[6]};
  int* correct_array[] = {&arr[0],&arr[1],&arr[2],&arr[3],&arr[4],&arr[5],&arr[6]};
  
  insert_sort((void**)array,7,comp_int);
  TEST_ASSERT_EQUAL_PTR_ARRAY((void**)correct_array, (void**)array,7);
}

void test_unsorted_int_array() {
  int arr[] = {1,2,3,4,5,6,7};
  int* array[] = {&arr[0],&arr[4],&arr[1],&arr[6],&arr[2],&arr[5],&arr[3]};
  int* correct_array[] = {&arr[0],&arr[1],&arr[2],&arr[3],&arr[4],&arr[5],&arr[6]};

  insertion_sort((void**)array,7,comp_int);
  TEST_ASSERT_EQUAL_PTR_ARRAY((void**)correct_array,(void**) array,7);
}

void test_sorted_float_array() { 
  float arr[] = {1.5, 2.3, 2.8, 3.15, 5.89, 5.95, 6.22};
  float* array[] = {&arr[0],&arr[1],&arr[2],&arr[3],&arr[4],&arr[5],&arr[6]};
  float* correct_array[] ={&arr[0],&arr[1],&arr[2],&arr[3],&arr[4],&arr[5],&arr[6]};

  insertion_sort((void**)array,7,comp_float);
  TEST_ASSERT_EQUAL_PTR_ARRAY((void**)correct_array, (void**)array,7);
}

void test_unsorted_float_array() {
  float arr[] = {1.5, 2.3, 2.8, 3.15, 5.89, 5.95, 6.22};
  float* array[] = {&arr[0],&arr[4],&arr[1],&arr[6],&arr[2],&arr[5],&arr[3]};
  float* correct_array[] = {&arr[0],&arr[1],&arr[2],&arr[3],&arr[4],&arr[5],&arr[6]};

  insertion_sort((void**)array,7,comp_float);
  TEST_ASSERT_EQUAL_PTR_ARRAY((void**)correct_array, (void**)array,7);
}

void test_sorted_string_array() { 
  char *arr[] = {"acqua","ciao","elefante","formica","nave","orologio","zattera"};
  char** array[] = {&arr[0],&arr[1],&arr[2],&arr[3],&arr[4],&arr[5],&arr[6]};
  char** correct_array[] = {&arr[0],&arr[1],&arr[2],&arr[3],&arr[4],&arr[5],&arr[6]};

  insertion_sort((void**)array,7,comp_string);
  TEST_ASSERT_EQUAL_PTR_ARRAY((void**)correct_array, (void**)array, 7);
}

void test_unsorted_string_array() {
  char *arr[] = {"acqua","ciao","elefante","formica","nave","orologio","zattera"};
  char** array[] = {&arr[0],&arr[4],&arr[1],&arr[6],&arr[2],&arr[5],&arr[3]};
  char** correct_array[] = {&arr[0],&arr[1],&arr[2],&arr[3],&arr[4],&arr[5],&arr[6]};
  insertion_sort((void**)array,7,comp_string);
  TEST_ASSERT_EQUAL_PTR_ARRAY((void**)correct_array, (void**)array, 7);
}


int main()
{
  UNITY_BEGIN();

  RUN_TEST(test_sorted_int_array);
  RUN_TEST(test_unsorted_int_array);
  RUN_TEST(test_sorted_float_array);
  RUN_TEST(test_unsorted_float_array);
  RUN_TEST(test_sorted_string_array);
  RUN_TEST(test_unsorted_string_array);

  return UNITY_END();
}