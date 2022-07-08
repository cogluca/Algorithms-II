        #include <ctype.h>
        #include "string.h"
        #include "stdio.h"
        #include "stdlib.h"
        #include "skip_list.h"
        #include "time.h"

        #define BUFFER_SIZE 500
        #define ARRAY_SIZE 661562

        int dictionary_size = 0;
        int correct_me_size = 0;

        char* my_tolower(char* to_convert){
            for(int i = 0; to_convert[i]; i++){
                to_convert[i] = tolower(to_convert[i]);
            }
            return to_convert;
        }

        /**
         * String comparator
         * @param a
         * @param b
         * @return
         */

        int comp_string(void *a, void *b) {
            char *char_a = (char *) a;
            char *char_b = (char *) b;

            if (strcmp(char_a, char_b) < 0) {
                return 2;
            } else if (strcmp(char_a, char_b) > 0) {
                return 1;
            }
            return 0;
        }


        /**
         * Detects words from the file and loads them onto a file
         * @param file_name name of file being read
         * @param word_array array onto which words are being loaded
         * @return size of array being returned
         */
        static char **load_file_to_correct(const char *file_name) {
            int ar_size = 2;
            char **array = (char **) malloc(sizeof(char *) * ar_size);

            if (array == NULL) {
                fprintf(stderr, "main: unable to allocate memory for the read record\n");
                exit(EXIT_FAILURE);
            }

            char buffer[1024];
            int buf_size = 1024;
            FILE *fp;
            printf("\nLoading data from file...\n");
            fp = fopen(file_name, "r");
            if (fp == NULL) {
                fprintf(stderr, "main: unable to open the file\n");
                exit(EXIT_FAILURE);
            }

            fgets(buffer, buf_size, fp);
            char* sep = ":,.  ";

            char* one_word = strtok(buffer, sep);

            while (one_word != NULL){

                if (ar_size <= correct_me_size ) {
                    ar_size *= 2;
                    array = (char **) realloc(array, sizeof(char *) * ar_size);
                }

                char *read_word = (char*) malloc((strlen(one_word) + 1) * sizeof(char));
                if (read_word == NULL) {
                    fprintf(stderr, "main: unable to allocate memory for the read line\n");
                    exit(EXIT_FAILURE);
                }

                strcpy(read_word, one_word);

                read_word = my_tolower(read_word);

                array[correct_me_size] = read_word;

                correct_me_size += 1;

                one_word = strtok(NULL, sep);

            }
            if (ar_size > correct_me_size) {
                array = (char **) realloc(array, sizeof(char *) * correct_me_size);
            }

            fclose(fp);
            printf("\nFile to correct loaded\n");

            return (array);
        }

        /**
         * Takes in input a filename and returns an array loaded with the file content
         * @param file_name file to be accessed
         * @return array with loaded data
         */

        static char **load_dictionary(const char *file_name) {
            int ar_size = 2;
            char **array = (char **) malloc(sizeof(char *) * ar_size);

            if (array == NULL) {
                fprintf(stderr, "main: unable to allocate memory for the read record\n");
                exit(EXIT_FAILURE);
            }

            char *read_line_p;
            char buffer[1024];
            int buf_size = 1024;
            FILE *fp;
            printf("\nLoading data from file...\n");
            fp = fopen(file_name, "r");
            if (fp == NULL) {
                fprintf(stderr, "main: unable to open the file\n");
                exit(EXIT_FAILURE);
            }

            while (fgets(buffer, buf_size, fp) != NULL) {

                if (ar_size <= dictionary_size) {
                    ar_size *= 2;
                    array = (char **) realloc(array, sizeof(char *) * ar_size);
                }

                read_line_p = malloc((strlen(buffer) + 1) * sizeof(char));
                if (read_line_p == NULL) {
                    fprintf(stderr, "main: unable to allocate memory for the read line\n");
                    exit(EXIT_FAILURE);
                }

                strcpy(read_line_p, buffer);

                char *elem = (char *) malloc((strlen(read_line_p) + 1) * sizeof(char));
                strcpy(elem, read_line_p);

                elem = strtok(elem, "\n");


                array[dictionary_size] = elem;


                dictionary_size += 1;

                free(read_line_p);
            }


            if (ar_size > dictionary_size) {
                array = (char **) realloc(array, sizeof(char *) * dictionary_size);
            }


            fclose(fp);
            printf("\nDictionary loaded\n\n");

            return (array);
        }


        SkipList *load_skip_list(char **word_array, int array_size) {

            SkipList *prepared_dictionary;
            prepared_dictionary = skip_list_init(comp_string);

            while (array_size > 0) {
                array_size--;
                insert_skiplist(prepared_dictionary, word_array[array_size]);
            }

            return prepared_dictionary;

        }


        /**
         * Reads from the file words that should get corrected, from there it searches in the dictionary organized in a skiplist structure in log n time if there are any matches
         * if it doesn't find matches it prints the word, the semantic of such operation defines the word as wrong
         * @param to_analyze_word current to_analyze_word to take onto examination
         * @param prepared_dictionary dictionary loaded onto a skiplist structure
         * @param correct_me_size size of words being analyzed
         */
        void print_uncorrect_words(char **to_analyze_word, SkipList *prepared_dictionary, int dictionary_size,
                                                int correct_me_size) {

            char *returned_word;

            for (int i = 0; i < correct_me_size; i++) {

                returned_word = search_node_element(prepared_dictionary, *to_analyze_word);

                if(returned_word == NULL){
                    printf("%s\n", *to_analyze_word);
                }

                to_analyze_word = to_analyze_word+1;
            }

            free_skiplist(prepared_dictionary);
        }

        /**
         * Contains the logic for taking a file to correct and a dictionary in input, translating such elements onto data structures (namely arrays), apply the search for missing words and finally print
         * the outcomes
         */
        int main() {

            clock_t start, end;

            char **correctme;
            char correct_me_filename[BUFFER_SIZE];

            char **dictionary;
            char dictionary_filename[BUFFER_SIZE];

            SkipList *skip_list;

            srand(time(NULL));

            printf("Insert name of file in need of correction: \n");
            scanf("%s", correct_me_filename);

            printf("Insert dictionary: \n");
            scanf("%s", dictionary_filename);

            correctme = (char **) malloc(sizeof(char *) * ARRAY_SIZE);
            dictionary = (char **) malloc(sizeof(char *) * ARRAY_SIZE);


            char** file_to_correct = load_file_to_correct(correct_me_filename);
            char** dictionary_file = load_dictionary(dictionary_filename);

            skip_list = load_skip_list(dictionary_file, dictionary_size);

            start = clock();
            print_uncorrect_words(file_to_correct, skip_list,dictionary_size, correct_me_size);
            end = clock();

            fprintf(stdout,"Program succesfully ended\nword searched in: %f\n", (float)(end - start)/CLOCKS_PER_SEC);

            free(correctme);
            free(dictionary);


            return 0;
        }
