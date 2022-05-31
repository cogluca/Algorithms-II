//
// Created by Gianluca Cognigni on 26/05/22.
//


#include <ctype.h>
#include "string.h"
#include "stdio.h"
#include "stdlib.h"
#include "skip_list.h"
#include "time.h"

#define BUFFER_SIZE 500
#define ARRAY_SIZE 661562

/**
 * Detects words from file and encloses them into an array
 * @param file_name file from which to retrieve words
 * @param word_array array to fill
 * @return returns size of filled array
 */

int ar_elem = 0;
int word_counter = 0;

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


int cmp(const void *p1, const void *p2) {
    return strcmp(p1, p2);
}


/**
 * Detects words from the file and loads them onto two arrays
 * @param file_name name of file being read
 * @param word_array array onto which words are being loaded
 * @return size of array being returned
 */

static char **load_file_to_correct(const char *file_name) {
    int ar_size = 2;
    char **array = (char **) malloc(
            sizeof(char *) * ar_size);  // alloco memoria per un array di puntatori a struct record

    if (array == NULL) {
        fprintf(stderr, "main: unable to allocate memory for the read record\n");
        exit(EXIT_FAILURE);
    }

    char buffer[1024];      // devo creare un buffer di caratteri, ogni riga di testo la carico in un buffer
    int buf_size = 1024;    // specifico dimensione del buffer
    FILE *fp;               // puntatore al file da leggere
    printf("\nLoading data from file...\n");
    fp = fopen(file_name, "r");    // apro il file in lettura ("r")
    if (fp == NULL) {               // controllo se c'è stato qualche errore
        fprintf(stderr, "main: unable to open the file\n");
        exit(EXIT_FAILURE);
    }


    fgets(buffer, buf_size, fp);
    char* sep = ":,.  \n";

    char* one_word = strtok(buffer, sep);

    while (one_word != NULL){  // fgets: legge da un file di testo una riga alla volta fino alla fine del file, una vola raggiunto restituisce null

        if (ar_size <= word_counter ) { // check sulla grandezza dell'array dinamico
            ar_size *= 2;
            array = (char **) realloc(array, sizeof(char *) * ar_size);  // mi aumenta lo spazio per contenere ar_size elementi di grandezza sizeof(record)
        }

        char *read_word = (char*) malloc((strlen(one_word) + 1) * sizeof(char));
        if (read_word == NULL) {
            fprintf(stderr, "main: unable to allocate memory for the read line\n");
            exit(EXIT_FAILURE);
        }

        strcpy(read_word, one_word);

        //printf("Read word is %s\n",read_word); QUESTA PRINT FUNZIONA

        // strtok restituisce puntatori agli elementi trovati e separati dalla ','


        array[word_counter] = read_word;

        //printf("%s\n", array[word_counter]); FUNZIONA



        word_counter += 1;

        one_word = strtok(NULL, sep);// pulisco il buffer che legge la stringa per riempirlo con la prossima riga



        //printf("%s\n", array[word_counter]); NORMALE CHE NON FUNZIONI è LA PAROLA DAVANTI VUOTA IN OGNI CICLO GRAZIE A WORD COUNTER ++

    }

/*
    for(int i = 0; i < word_counter; i++){
        printf("%s\n", *array);
        array = array+1;
    }
*/
/*
    if (ar_size > ar_elem) { // check sulla grandezza dell'array dinamico
        array = (char **) realloc(array, sizeof(char **) *
                                         ar_elem);  // mi aumenta lo spazio per contenere ar_size elementi di grandezza sizeof(record)
    }
*/
    fclose(fp); // chiudo la lettura del file
    printf("\nData loaded\n");

    return (array);
}

static char **load_dictionary(const char *file_name) {
    int ar_size = 2;
    char **array = (char **) malloc(
            sizeof(char *) * ar_size);  // alloco memoria per un array di puntatori a struct record

    if (array == NULL) {
        fprintf(stderr, "main: unable to allocate memory for the read record\n");
        exit(EXIT_FAILURE);
    }

    char *read_line_p;
    char buffer[1024];      // devo creare un buffer di caratteri, ogni riga di testo la carico in un buffer
    int buf_size = 1024;    // specifico dimensione del buffer
    FILE *fp;               // puntatore al file da leggere
    printf("\nLoading data from file...\n");
    fp = fopen(file_name, "r");    // apro il file in lettura ("r")
    if (fp == NULL) {               // controllo se c'è stato qualche errore
        fprintf(stderr, "main: unable to open the file\n");
        exit(EXIT_FAILURE);
    }

    while (fgets(buffer, buf_size, fp) !=
           NULL) {  // fgets: legge da un file di testo una riga alla volta fino alla fine del file, una vola raggiunto restituisce null

        if (ar_size <= ar_elem) { // check sulla grandezza dell'array dinamico
            ar_size *= 2;
            array = (char **) realloc(array, sizeof(char *) *
                                             ar_size);  // mi aumenta lo spazio per contenere ar_size elementi di grandezza sizeof(record)
        }

        read_line_p = malloc((strlen(buffer) + 1) * sizeof(char));
        if (read_line_p == NULL) {
            fprintf(stderr, "main: unable to allocate memory for the read line\n");
            exit(EXIT_FAILURE);
        }

        strcpy(read_line_p, buffer);

        char *elem = (char *) malloc((strlen(read_line_p) + 1) * sizeof(char));
        strcpy(elem, read_line_p);
        // strtok restituisce puntatori agli elementi trovati e separati dalla ','


        array[ar_elem] = elem;


        ar_elem += 1;

        free(read_line_p);  // pulisco il buffer che legge la stringa per riempirlo con la prossima riga
    }


    if (ar_size > ar_elem) { // check sulla grandezza dell'array dinamico
        array = (char **) realloc(array, sizeof(char **) *
                                         ar_elem);  // mi aumenta lo spazio per contenere ar_size elementi di grandezza sizeof(record)
    }


    fclose(fp); // chiudo la lettura del file
    printf("\nData loaded\n");

    return (array);
}


SkipList *loadSkipList(char **word_array, int array_size) {

    printf("sono entrato\n");

    SkipList *prepared_dictionary;
    prepared_dictionary = SkipListInit(comp_string);

    while (array_size > 0) {
        array_size--;
        insertSkipList(prepared_dictionary, word_array[array_size]);
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
void print_words_absent_from_dictionary(char **to_analyze_word, SkipList *prepared_dictionary, int dictionary_size,
                                        int correct_me_size) {

    char *returned_word;

    int yo = 0;
    /*
    while(yo < word_counter){

        printf("%s\n", *to_analyze_word);
        to_analyze_word = to_analyze_word+1;
        yo++;

    }
*/
    //REASON BRUDAH, REASON

    int not_so_meant_to_be_let_me_know_when_you_feel_like_talking = 0;

    for (int i = 0; i < word_counter; i++) {

        returned_word = searchNodeElement(prepared_dictionary, *to_analyze_word);

        if (returned_word == NULL) {
            printf("%s\n", *to_analyze_word);
        }
        to_analyze_word = to_analyze_word+1;

        //no seriamente sono solo abbastanza bruciato da vede i vostri pattern
    }

    FreeSkipList(prepared_dictionary);
}

/**
 * Contains the logic for taking a file to correct and a dictionary in input, translating such elements onto data structures (namely arrays), apply the edit distance algorithms and finally print
 * the outcomes
 */
int main() {


    char **correctme;
    int correctme_size;
    char correctme_filename[BUFFER_SIZE];

    char **dictionary;
    int dictionary_size;
    char dictionary_filename[BUFFER_SIZE];

    SkipList *skip_list;

    srand(time(NULL));

    printf("Insert name of file in need of correction: \n");
    //scanf("%s", correctme_filename);

    printf("Insert dictionary: \n");
    //scanf("%s", dictionary_filename);

    //skip_list = SkipListInit(comp_string);

    sprintf(correctme_filename, "/Users/frankacarkan/Downloads/es2_dataset/correctme.txt");
    sprintf(dictionary_filename, "/Users/frankacarkan/Downloads/es2_dataset/dictionary.txt");

    correctme = (char **) malloc(sizeof(char *) * ARRAY_SIZE);
    dictionary = (char **) malloc(sizeof(char *) * ARRAY_SIZE);


    char** file_to_correct = load_file_to_correct(correctme_filename);
    char** dictionary_file = load_dictionary(dictionary_filename);


    int array_pos_48 = 0;


    /*while(array_pos_48 <= word_counter){

        printf("%s\n",*file_to_correct);
        file_to_correct = file_to_correct+1;
        array_pos_48++;

    }
     */


/*
     //DECOMMENT THIS TO CHECK THE ARRAY BEING FILLED CORRECTLY
    int counter = 0;
    while(counter < 661565){

        printf("%s\n", *dictionary_file);
        dictionary_file = dictionary_file + 1;
        counter++;
    }
    printf("Manually counted dictionary: %d\n", counter);
*/

    //NECESSARIO PER AVERE LE OPERAZIONI RICHIESTE NELLA SKIPLIST IN LOG N, ma va in segmentation fault, assieme al MAX HEIGHT DELLE SKIPLIST SETTATO A 10-20 secondo un ragazzo
    //heapsort(dictionary_file, 661561, sizeof *dictionary_file,cmp);
    printf("prima del load nella skiplist\n");

    skip_list = loadSkipList(dictionary_file, ar_elem);


    printf("post caricamento skiplist\n");


    print_words_absent_from_dictionary(file_to_correct, skip_list,dictionary_size, 49);

    free(correctme);
    free(dictionary);

    return 0;
}
