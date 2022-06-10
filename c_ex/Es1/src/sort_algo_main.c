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
         * @brief function that sort records using the id field
         *
         * @param r1_p pointer to struct record 1
         * @param r2_p pointer to struct record 2
         * @return int that show which one are the successor between them
         */
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
                return(-1);
            }else if(rec1_p->id_field > rec2_p->id_field){
                return(1);
            }
            return(0);
        }

        /**
         * @brief function that sort records using the length of the string
         *
         * @param r1_p pointer to struct record 1
         * @param r2_p pointer to struct record 2
         * @return int that show which one are the successor between them
         */

        static int precedesRecordStringField(void* r1_p, void* r2_p){
            if(r1_p == NULL){
                fprintf(stderr, "precedesRecordStringField: the first parameter is a null pointer");
                exit(EXIT_FAILURE);
            }
            if(r2_p == NULL){
                fprintf(stderr, "precedesRecordStringField: the second parameter is a null pointer");
            }
            Record *rec1_p = (Record*)r1_p;
            Record *rec2_p = (Record*)r2_p;
            return strcmp(rec1_p->string_field, rec2_p->string_field);
        }

        /**
         * @brief function that sort records using the integer field
         *
         * @param r1_p pointer to struct record 1
         * @param r2_p pointer to struct record 2
         * @return int that show which one are the successor between them
         */
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
                return(-1);
            }else if(rec1_p->integer_field > rec2_p->integer_field){
                return(1);
            }
            return(0);
        }

        /**
         * @brief function that sort records using the float field
         *
         * @param r1_p pointer to struct record 1
         * @param r2_p pointer to struct record 2
         * @return int that show which one are the successor between them
         */
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
                return(-1);
            }else if(rec1_p->float_field > rec2_p->float_field){
                return(1);
            }
            return(0);
        }

        //./a.out <-1/-2/-3/-4> <path_file> <-1/-2>
        //./a.out <select how to order the records> <path to file that contains record> <sort algo chosen>

        Option parseOptions(int argc, char const*argv[]){
            if(argc != 4){
                printf("Usage: check the element for the execution of the program");
                exit(EXIT_FAILURE);
            }else{
                Option programOption;
                programOption.path = argv[2];

                if(!strcmp(argv[1], "-1")){
                    programOption.fun = (func)precedesRecordIdField;
                }else if(!strcmp(argv[1], "-2")){
                    programOption.fun = (func)precedesRecordStringField;
                }else if(!strcmp(argv[1], "-3")){
                    programOption.fun = (func)precedesRecordIntegerField;
                }else if(!strcmp(argv[1], "-4")){
                    programOption.fun = (func)precedesRecordFloatField;
                }else{
                     printf("Usage: Parameters Error");
                }
                programOption.algo = !strcmp(argv[3], "-1") ? -1 : -2;

                return programOption;
            }
        }

        static void freeMemory(void** array){
            for(int i = 0; i < ar_elem; i++){
                Record *rec_p = (Record *) array[i];
                free(rec_p->string_field);
            }
            free(array);
        }

        static void **loadArray(const char* file_name){
            int ar_size = 2;
            void** array = (void **)malloc(sizeof(void*) * ar_size);

            if(array == NULL){
                fprintf(stderr,"main: unable to allocate memory for the read record\n");
                exit(EXIT_FAILURE);
            }

            char *read_line_p;
            char buffer[1024];      
            int buf_size = 1024;
            FILE *fp;               
            printf("\nLoading data from file...\n");
            fp = fopen(file_name,"r");    
            if(fp == NULL){              
                fprintf(stderr,"main: unable to open the file\n");
                exit(EXIT_FAILURE);
            }

            while(fgets(buffer,buf_size,fp) != NULL){ 

                if(ar_size <= ar_elem){ 
                    ar_size *= 2;
                    array = (void **)realloc(array, sizeof(void*) * ar_size); 
                }

                read_line_p = malloc((strlen(buffer)+1)*sizeof(char));
                if(read_line_p == NULL){
                    fprintf(stderr,"main: unable to allocate memory for the read line\n");
                    exit(EXIT_FAILURE);
                }
                strcpy(read_line_p,buffer);
               
                char *id_field_in_read_line_p = strtok(read_line_p, ",");
                char *string_field_in_read_line_p = strtok(NULL,",");  
                char *integer_field_in_read_line_p = strtok(NULL,","); 
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

                free(read_line_p);  
            }

            if(ar_size > ar_elem){ 
                    array = (void **)realloc(array, sizeof(void*) * ar_elem);
                }

            fclose(fp); 
            printf("\nData loaded\n");

            return(array);
        }

        static void testFunction(Option opt){
            clock_t start, end;
            void** array = loadArray(opt.path);

            Record *r;

            start = clock();
            opt.algo == -1 ? quickSort(array, 0, ar_elem-1, opt.fun) : insertSort(array, ar_elem, opt.fun);
            end = clock();

            freeMemory(array);

            fprintf(stdout,"Program succesfully ended\narray sorted in: %f\n", (float)(end - start)/CLOCKS_PER_SEC);
        }

        /**
         * 3 elementi da passare: parametro di sort, path, algo di sort
         *
         * @param argc
         * @param argv
         * @return int
         */

        int main(int argc, char const *argv[]){
            testFunction(parseOptions(argc, argv));

            return(EXIT_SUCCESS);
        }

