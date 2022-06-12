#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include "sort_library.h"


typedef struct{
    char id_field;
    char* string_field;
    int integer_field;
    double float_field;
}Record;

int dictionary_size = 0;        // numero attuale di elementi nell'array


static int precedesRecordIdField(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedesRecordIdField: the first parameter is a null pointer\n");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedesRecordIdField: the second parameter is a null pointer\n");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record*)r1_p;
    Record *rec2_p = (Record*)r2_p;
    if(rec1_p->id_field < rec2_p->id_field){
        return(2);
    }else if(rec1_p->id_field > rec2_p->id_field){
        return(1);
    }
    return(0);
}

static int precedesRecordFloatField(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedesRecordFloatField: the first parameter is a null pointer");
        exit(EXIT_FAILURE);    
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedesRecordFloatField: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record*)r1_p;
    Record *rec2_p = (Record*) r2_p;
    if(rec1_p->float_field < rec2_p->float_field){
        return(2);
    }else if(rec1_p->float_field > rec2_p->float_field){
        return(1);
    }
    return(0);
}

static int precedesRecordIntegerField(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedesRecordIntegerField: the first parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedesRecordIntegerField: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record*)r1_p;
    Record *rec2_p = (Record*)r2_p;
    if(rec1_p->integer_field < rec2_p->integer_field){
        return(2);
    }else if(rec1_p->integer_field > rec2_p->integer_field){
        return(1);
    }
    return(0);
}

char *redstr(char* string){
    char* tmp = strdup(string);
    char* ptr = tmp;

    while(*ptr){
        *ptr = tolower(*ptr);
        ptr++;
    }

    return tmp;
}

static int precedesRecordStringField(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedesRecordStringField: the first parameter is a null pointer\n");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedesRecordStringField: the second parameter is a null pointer\n");
    }
    Record *rec1_p = (Record*)r1_p;
    Record *rec2_p = (Record*)r2_p;

    char *strlow1 = redstr(rec1_p->string_field);
    char *strlow2 = redstr(rec2_p->string_field);

    if(strcmp(strlow1, strlow2) < 0){
        return(2);
    }else if(strcmp(strlow1, strlow2) > 0){
        return(1);
    }
    return(0);
}

static void **loadArray(const char* file_name, int k){
    int ar_size = 2;
    void** array = (void **)malloc(sizeof(void*) * ar_size);  // alloco memoria per un array di puntatori a struct record
    int j = 0;
    
    if(array == NULL){
        fprintf(stderr,"main: unable to allocate memory for the read record\n");
        exit(EXIT_FAILURE);
    }

    char *read_line_p;
    char buffer[1024];      // devo creare un buffer di caratteri, ogni riga di testo la carico in un buffer
    int buf_size = 1024;    // specifico dimensione del buffer
    FILE *fp;               // puntatore al file da leggere
    printf("\nLoading data from file...\n");
    fp = fopen(file_name,"r");    // apro il file in lettura ("r")
    if(fp == NULL){               // controllo se c'è stato qualche errore
        fprintf(stderr,"main: unable to open the file\n");
        exit(EXIT_FAILURE);
    }

    while(fgets(buffer,buf_size,fp) != NULL && j < k){  // fgets: legge da un file di testo una riga alla volta fino alla fine del file, una vola raggiunto restituisce null
        
        if(ar_size <= dictionary_size){ // check sulla grandezza dell'array dinamico
            
            /*printf("\nelementi nell'array fino a questo punto\n");
            for(char i = 0; i < dictionary_size; i++){
                Record* r_p = (Record *)array[i];
                
                printf("\nrecord[%d]: { \
                        id = %d\
                        string = %s\
                        int = %d\
                        float = %f\n",
                        i, r_p->id_field, r_p->string_field, r_p->integer_field, r_p->float_field);
            }*/
            
            ar_size *= 2;
            array = (void **)realloc(array, sizeof(void*) * ar_size);  // mi aumenta lo spazio per contenere ar_size elementi di grandezza sizeof(record)

        }

        read_line_p = malloc((strlen(buffer)+1)*sizeof(char));
        if(read_line_p == NULL){
            fprintf(stderr,"main: unable to allocate memory for the read line\n");
            exit(EXIT_FAILURE);
        }   
        strcpy(read_line_p,buffer);
        // strtok restituisce puntatori agli elementi trovati e separati dalla ','
        char *id_field_in_read_line_p = strtok(read_line_p, ",");
        char *string_field_in_read_line_p = strtok(NULL,",");    // string tokenizer è una funzione che individua nella stringa gli elementi separati dall'elemento separatore indicato, in questo caso la ','
        char *integer_field_in_read_line_p = strtok(NULL,",");   // dalla seconda volta si passa null come puntatore perché sennò ricomincia da capo      
        char *float_field_in_read_line_p = strtok(NULL, ",");            
        
        Record *r = (Record *) malloc(sizeof(Record));
        r->id_field = atoi(id_field_in_read_line_p);
        r->string_field = malloc((strlen(string_field_in_read_line_p)+1)*sizeof(char));
        if(r->string_field == NULL){
            fprintf(stderr,"main: unable to allocate memory for the record\n");
            exit(EXIT_FAILURE);
        }
        strcpy(r->string_field, string_field_in_read_line_p);
        r->integer_field = atoi(integer_field_in_read_line_p);
        r->float_field = atof(float_field_in_read_line_p);

        void *record_pointer = r;
        array[dictionary_size] = record_pointer;

        dictionary_size += 1;
        
        free(read_line_p);  // pulisco il buffer che legge la stringa per riempirlo con la prossima riga
        j++;
    }

    fclose(fp); // chiudo la lettura del file
    printf("\nData loaded\n");

    return(array);
}

int main(){

    char buf[100];
    printf("inserire numero di elementi da scannerizzare\n");
    fgets(buf, 100, stdin);

    int k = atoi(buf);

    void** array = loadArray("/home/rjuck/Desktop/laboratorio-algoritmi-2021-2022/c_ex/records.csv", k); //loadArray("/Users/frankacarkan/Desktop/Algo/ex1_new/records.csv", k);
    Record *r;
    clock_t start, end;

    /*void** array = (void **) malloc(sizeof(void*) * k);
    for(int i = 0; i < k; i++){
        array[i] = array[i];
    }*/

//    printf("\nelementi ricevuti\n");
//             for(char i = 0; i < k; i++){
//                 r = array[i];
//                 printf("\nrecord[%d]: { \
//                         id = %d\
//                         string = %s\
//                         int = %d\
//                         float = %f\n",
//                         i, r->id_field, r->string_field, r->integer_field, r->float_field);
//             }

    start= clock();
    // quickSort(array, 0, k-1, *precedesRecordIdField);
    // quickSort(array, 0, k-1, *precedesRecordStringField);
    // quickSort(array, 0, k-1, *precedesRecordIntegerField);
    quickSort(array, 0, k-1, *precedesRecordFloatField);
    // insertSort(array, dictionary_size, precedesRecordIdField);
    end = clock();
    
    printf("\nelementi ordinati per id in %f\n", (float)(end - start)/CLOCKS_PER_SEC);

    /*start = clock();
    quickSort(array, 0, k-1, *precedesRecordStringField);
    insertSort(array, dictionary_size, precedesRecordStringField);
    end = clock();

    printf("\nelementi ordinati per string in %f\n", (float)(end - start)/CLOCKS_PER_SEC);

    start = clock();
    quickSort(array, 0, k-1, *precedesRecordIntegerField);
    insertSort(array, dictionary_size, precedesRecordIntegerField);
    end = clock();

    printf("\nelementi ordinati per int in %f\n", (float)(end - start)/CLOCKS_PER_SEC);

    start = clock();
    quickSort(array, 0, k-1, *precedesRecordFloatField);
    insertSort(array, dictionary_size, precedesRecordFloatField);
    end = clock();

    printf("\nelementi ordinati per float in %f\n", (float)(end - start)/CLOCKS_PER_SEC);*/
    //         for(char i = 0; i < k; i++){
    //             r = array[i];
    //             printf("\nrecord[%d]: { \
    //                     id = %d\
    //                     string = %s\
    //                     int = %d\
    //                     float = %f\n",
    //                     i, r->id_field, r->string_field, r->integer_field, r->float_field);
    //         }

    

    free(array);

    return 0;
}