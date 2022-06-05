#include <stdio.h>
#include <stdlib.h>
#include "string.h"
#include "skip_list.h"
#include "../../C_resource//unity.h"
#define UNITY_INCLUDE_DOUBLE


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
    if((char_a) == NULL)
        return 2;
    if((char_b) == NULL)
        return 1;
    if(strcmp(char_a, char_b) < 0){
        return 2;
    }else if(strcmp(char_a, char_b) > 0){
        return 1;
    }
    return 0;
}

SkipList *skip_list;

void setUp(void) {
    skip_list = skip_list_init(comp_string);
}

void tearDown(void) {
    free_skiplist(skip_list);
}

//tests for strings without differences
void test_string_insertion() {
    char* a_string = "Franco";

    insert_skiplist(skip_list, a_string);


    TEST_ASSERT_EQUAL_STRING(a_string, search_node_element(skip_list, a_string));

}

void test_int_insertion() {

    int an_int = 2;

    insert_skiplist(skip_list, &an_int);

    int* searched_int = search_node_element(skip_list, &an_int);

    TEST_ASSERT_EQUAL_INT(an_int, *searched_int );

}



void test_multiple_string_insertion() {

    char strings[13][50] = {  "Piante", "Pina", "Selvaggio", 
                                "Albero", "Siamese", "a", 
                                "f", "pippo", "formica", 
                                "gioco", "libellula", "Barella"
                    };

    for(int i = 0; i < 13; i++){
        insert_skiplist(skip_list, strings[i]);
    }

    for(int i = 0; i < 13; i++){
        TEST_ASSERT_EQUAL_STRING(strings[i], search_node_element(skip_list, strings[i]));
    }
}

//executes tests
int main() {
    UNITY_BEGIN();

    RUN_TEST(test_string_insertion);

    RUN_TEST(test_multiple_string_insertion);

    RUN_TEST(test_int_insertion);


    return UNITY_END();
}
