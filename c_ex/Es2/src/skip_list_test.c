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

void test_int_insertion() {

    int an_int = 2;

    insertSkipList(skip_list, &an_int);

    int* searched_int = searchNodeElement(skip_list, &an_int);

    TEST_ASSERT_EQUAL_INT(an_int, *searched_int );

}



void test_multiple_string_insertion() {

   char* a_string = "Franco";
   char* another_string = "va";
   char* third_string = "da Fratimo";
   char* fourth_string = "e";
   char* trova = "trova";
   char* un_osso = "un osso";
   char* fuori_posto = "fuori posto";
   char* davvero = "davvero";
   char* pero_boh = "pero boh";
   char* che_storia = "che storia";
   char* e_crasha = "stop";
   char* why_non_crasha= "why non crasha";

    insertSkipList(skip_list, a_string);
    insertSkipList(skip_list, another_string);
    insertSkipList(skip_list, third_string);
    insertSkipList(skip_list, fourth_string);
    insertSkipList(skip_list, trova);
    insertSkipList(skip_list, un_osso);
    insertSkipList(skip_list, fuori_posto);
    insertSkipList(skip_list, davvero);
    //insertSkipList(skip_list, pero_boh);
    //insertSkipList(skip_list, che_storia);


    TEST_ASSERT_EQUAL_STRING(a_string, searchNodeElement(skip_list, a_string));
    TEST_ASSERT_EQUAL_STRING(another_string, searchNodeElement(skip_list, another_string));
    TEST_ASSERT_EQUAL_STRING(third_string, searchNodeElement(skip_list, third_string));

}






//executes tests
int main() {
    UNITY_BEGIN();

    RUN_TEST(test_string_insertion);

    RUN_TEST(test_multiple_string_insertion);

    RUN_TEST(test_int_insertion);


    return UNITY_END();
}
