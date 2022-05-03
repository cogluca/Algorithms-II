#include <stdio.h>
#include <stdlib.h>
#include "../C_resource/unity.h"

/**
 * Test suite for library contains algorithm for sorting collections
 */

struct record{
    char id_field;
    char* string_field;
    int integer_field;
    double float_field;
};

static int precedes_record_id_field(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedes_record_id_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedes_record_id_field: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    struct record *rec1_p = (struct record*)r1_p;
    struct record *rec2_p = (struct record*)r2_p;
    if(rec1_p->id_field < rec2_p->id_field){
        return(1);
    }
    return(0);
}

static int precedes_record_string_field(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedes_record_string_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedes_record_string_field: the second parameter is a null pointer");
    }
    struct record *rec1_p = (struct record*)r1_p;
    struct record *rec2_p = (struct record*)r2_p;

    if(strcmp(rec1_p->string_field, rec2_p->string_field) < 0){
        return(1);
    }
    return(0);
}

static int precedes_record_integer_field(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedes_record_integer_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedes_record_integer_field: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    struct record *rec1_p = (struct record*)r1_p;
    struct record *rec2_p = (struct record*)r2_p;
    if(rec1_p->integer_field < rec2_p->integer_field){
        return(1);
    }
    return(0);
}

static int precedes_recors_float_field(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedes_record_float_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);    
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedes_record_float_field: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    struct record *rec1_p = (struct record*)r1_p;
    struct record *rec2_p = (struct record*) r2_p;
    if(rec1_p->float_field < rec2_p->float_field){
        return(1);
    }
    return(0);
}

static struct record r1, r2, r3;

void setUp(void){
    r1.id_field = 2;
    r1.string_field = "ciao";
    r1.integer_field = 9;
    r1.float_field = 78.651616;

    r2.id_field = 6;
    r2.string_field = "pippo";
    r2.integer_field = 4;
    r2.float_field = 1.916151;

    r1.id_field = 1;
    r1.string_field = "talpa";
    r1.integer_field = 5;
    r1.float_field = 50.7915;
}

static void test_sort_algo_