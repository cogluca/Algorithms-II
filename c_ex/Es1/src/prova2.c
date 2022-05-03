#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct{
    char id_field;
    char* string_field;
    int integer_field;
    double float_field;
}Record;

int main(){
    int size = 5;
    void** array = (void**)malloc(size * sizeof(void*));
    char s[5][100] = {{"pippo"},{"pluto"},{"paperino"},{"topolino"},{"minnie"}};
    int i[5] = {1, 2, 3, 4, 5};
    double f[5] = {3.6, 4.8, 9.5, 7.3, 5.5};
    Record* r1;

    for(int j = 0; j < 5; j++){
        r1 = (Record*)malloc(sizeof(Record));
        r1->id_field = j;
        r1->string_field = s[j];
        r1->integer_field = i[j];
        r1->float_field = f[j];

        void* v = r1;

        array[j] = v;
    }
    Record *r;

    for(int j = 0; j < 5; j++){
        r = array[j];
        printf("elementi nell'array:\n\
                        record[%d] = {  id = %d\n\
                                        string = %s\n\
                                        int = %d\n\
                                        float = %f}\n",
                                        j, r->id_field, r->string_field, r->integer_field, r->float_field);
    }
    return 0;
}