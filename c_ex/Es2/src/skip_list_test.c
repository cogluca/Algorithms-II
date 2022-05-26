#include <stdio.h>
#include <stdlib.h>
#include "string.h"
#include "skip_list.h"
#include "/home/rjuck/Desktop/laboratorio-algoritmi-2021-2022/c_ex/C_resource/unity.h"


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
    if(strcmp(char_a, char_b) < 0){
        return 2;
    }else if(strcmp(char_a, char_b) > 0){
        return 1;
    }
    return 0;
}

SkipList *skip_list;

void setUp(void) {
    skip_list = SkipListInit(comp_string);
}

void tearDown(void) {
    FreeSkipList(skip_list);
}

//tests for strings without differences
void test_string_insertion() {
    char* a_string = "Franco";

    insertSkipList(skip_list, a_string);


    TEST_ASSERT_EQUAL_STRING(a_string, searchNodeElement(skip_list, a_string));

}


//executes tests
int main() {
    UNITY_BEGIN();

    RUN_TEST(test_string_insertion);


    return UNITY_END();
}
