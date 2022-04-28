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
} Record;

typedef struct{
    func fun;
    char const* path;
    int algo;
    int caseSensitive;
} Option;

int ar_elem = 0;

/**
 * @brief function that func records using the id field
 * 
 * @param r1_p pointer to struct record 1
 * @param r2_p pointer to struct record 2
 * @return int that show which one are the successor between them
 */
static int precedes_record_id_field(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedes_record_id_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedes_record_id_field: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record*)r1_p;
    Record *rec2_p = (Record*)r2_p;
    if(rec1_p->id_field < rec2_p->id_field){
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

/**
 * @brief function that func records using the length of the string
 * 
 * @param r1_p pointer to struct record 1
 * @param r2_p pointer to struct record 2
 * @return int that show which one are the successor between them
 */
static int precedes_record_string_field_lower(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedes_record_string_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedes_record_string_field: the second parameter is a null pointer");
    }
    Record *rec1_p = (Record*)r1_p;
    Record *rec2_p = (Record*)r2_p;

    char *strlow1 = redstr(rec1_p->string_field);
    char *strlow2 = redstr(rec2_p->string_field);

    if(strcmp(strlow1, strlow2) < 0){
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
    Record *rec1_p = (Record*)r1_p;
    Record *rec2_p = (Record*)r2_p;

    if(strcmp(rec1_p->string_field, rec2_p->string_field) < 0){
        return(1);
    }
    return(0);
}

/**
 * @brief function that func records using the integer field
 * 
 * @param r1_p pointer to struct record 1
 * @param r2_p pointer to struct record 2
 * @return int that show which one are the successor between them
 */
static int precedes_record_integer_field(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedes_record_integer_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedes_record_integer_field: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record*)r1_p;
    Record *rec2_p = (Record*)r2_p;
    if(rec1_p->integer_field < rec2_p->integer_field){
        return(1);
    }
    return(0);
}

/**
 * @brief function that func records using the integer field
 * 
 * @param r1_p pointer to struct record 1
 * @param r2_p pointer to struct record 2
 * @return int that show which one are the successor between them
 */
static int precedes_record_float_field(void* r1_p, void* r2_p){
    if(r1_p == NULL){
        fprintf(stderr, "precedes_record_float_field: the first parameter is a null pointer");
        exit(EXIT_FAILURE);    
    }
    if(r2_p == NULL){
        fprintf(stderr, "precedes_record_float_field: the second parameter is a null pointer");
        exit(EXIT_FAILURE);
    }
    Record *rec1_p = (Record*)r1_p;
    Record *rec2_p = (Record*) r2_p;
    if(rec1_p->float_field < rec2_p->float_field){
        return(1);
    }
    return(0);
}

Option parse_options(int argc, char const*argv[]){
    if(argc < 4 || argc > 5){
        printf("Usage: missing element for the execution of the program");
        return(EXIT_FAILURE);
    }else{
        Option programOption;
        programOption.path = argv[2];

        programOption.caseSensitive = (argc == 5 && !strcmp(argv[4], "n"))? 0 : 1;

        if(!strcmp(argv[1], "-1")){
            programOption.fun = (func)precedes_record_id_field;
        }else if(!strcmp(argv[1], "-2")){
            if(programOption.caseSensitive){
                programOption.fun = (func)precedes_record_string_field;
            }else{
                programOption.fun = (func)precedes_record_string_field_lower;
            }
        }else if(!strcmp(argv[1], "-3")){
            programOption.fun = (func)precedes_record_integer_field;
        }else if(!strcmp(argv[1], "-4")){
            programOption.fun = (func)precedes_record_float_field;
        }else{
             printf("Usage: Parameters Error");
             return(EXIT_FAILURE);
        }
        programOption.algo = !strcmp(argv[3], "-1") ? -1 : -2;

        return programOption;
    }
}

static void free_memory(void* array){
    free(array);
}

static void **load_array(const char* file_name){
    int ar_size = 2;
    void** array = (void **)malloc(sizeof(void*) * ar_size);  // alloco memoria per un array di puntatori a struct record
    
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

    while(fgets(buffer,buf_size,fp) != NULL){  // fgets: legge da un file di testo una riga alla volta fino alla fine del file, una vola raggiunto restituisce null
        
        if(ar_size <= ar_elem){ // check sulla grandezza dell'array dinamico
            
            printf("\nelementi nell'array fino a questo punto\n");
            for(char i = 0; i < ar_elem; i++){
                Record* r_p = (Record *)array[i];
                
                printf("\nrecord[%d]: { \
                        id = %d\
                        string = %s\
                        int = %d\
                        float = %f\n",
                        i, r_p->id_field, r_p->string_field, r_p->integer_field, r_p->float_field);
            }
            
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
        array[ar_elem] = record_pointer;

        ar_elem += 1;
        
        free(read_line_p);  // pulisco il buffer che legge la stringa per riempirlo con la prossima riga
    }

    fclose(fp); // chiudo la lettura del file
    printf("\nData loaded\n");

    return(array);
}

static void test_with_quicksort_function(Option opt){
    void** array = load_array(opt.path);
    
    clock_t start = clock();
    //opt.algo == -1 ? quick_sort(array, 0, ar_elem-1, opt.fun) : binary_insertion_sort(array, 0, ar_elem-1, opt.fun);
    quick_sort(array, 0, ar_elem-1, opt.fun);
    clock_t end = clock();
    
    fprintf(stdout,"Program succesfully ended\n array sorted in: %f\n", (float)(end - start)/CLOCKS_PER_SEC);
}

/**
 * 4 elementi da passare: path, parametro di sort, algo di sort
 * 
 * @param argc 
 * @param argv 
 * @return int 
 */

int main(int argc, char const *argv[]){
    test_with_quicksort_function(parse_options(argc, argv));

    return(EXIT_SUCCESS);
}

