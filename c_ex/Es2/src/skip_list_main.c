//
// Created by Gianluca Cognigni on 26/05/22.
//


#include <ctype.h>
#include "string.h"
#include "stdio.h"
#include "stdlib.h"
#include "skip_list.h"

#define BUFFER_SIZE 128
#define ARRAY_SIZE 670000

/**
 * Detects words from file and encloses them into an array
 * @param file_name file from which to retrieve words
 * @param word_array array to fill
 * @return returns size of filled array
 */

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


/**
 * Detects words from the file and loads them onto two arrays
 * @param file_name name of file being read
 * @param word_array array onto which words are being loaded
 * @return size of array being returned
 */


int detect_words_from_file(const char* file_name, char** word_array) {
    char buffer[6700000];
    int size = 0;
    int buffer_length;

    if (file_name == NULL)
        printf("File name is null");

    FILE * actual_file = fopen(file_name, "r");

    if (word_array == NULL)
        printf("Array is null");

    if (actual_file == NULL)
        printf("Unable to open file");

    while (1) {

        fscanf(actual_file, "%s", buffer);

        printf("%s\n",buffer);

        buffer_length = strlen(buffer);

        if(feof(actual_file))
            break;

        if (buffer[buffer_length - 1] == ',' || buffer[buffer_length - 1] == '.' || buffer[buffer_length - 1] == ':')
            buffer[--buffer_length] = '\0';

        if(word_array != NULL) {
            word_array[size] = (char *) malloc((buffer_length + 1) * sizeof(char));
            strcpy(word_array[size++], buffer);
        }
    }
    return size;

}


SkipList* loadSkipList(char** word_array){

    SkipList* prepared_dictionary;
    prepared_dictionary = SkipListInit(comp_string);

    while(word_array != NULL) {

        insertSkipList(prepared_dictionary, word_array);
        word_array = word_array +1;
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
void print_words_absent_from_dictionary(char** to_analyze_word, SkipList* prepared_dictionary , int dictionary_size, int correct_me_size) {

    char* returned_word;

    for(int i = 0; i < correct_me_size; i++){

        returned_word = searchNodeElement(prepared_dictionary, to_analyze_word);

        if(returned_word == NULL) {
            printf("%s", to_analyze_word[i]);
        }
        to_analyze_word = to_analyze_word +1;
    }

    FreeSkipList(prepared_dictionary);
}


/**
 * Contains the logic for taking a file to correct and a dictionary in input, translating such elements onto data structures (namely arrays), apply the edit distance algorithms and finally print
 * the outcomes
 */
int main() {

    char** correctme;
    int correctme_size;
    char correctme_filename[BUFFER_SIZE];

    char** dictionary;
    int dictionary_size;
    char dictionary_filename[BUFFER_SIZE];

    SkipList* loaded_dictionary;

    printf("Insert name of file in need of correction: \n");
    scanf("%s", correctme_filename);

    printf("Insert dictionary: \n");
    scanf("%s", dictionary_filename);

    correctme = (char**) malloc(sizeof(char*) * ARRAY_SIZE);
    dictionary = (char**) malloc(sizeof(char*) * ARRAY_SIZE);

    correctme_size = detect_words_from_file(correctme_filename, correctme);
    printf("correct me loaded");

    dictionary_size = detect_words_from_file(dictionary_filename, dictionary);
    printf("dictionary loaded");

    printf("data loaded onto arrays");

    loaded_dictionary = loadSkipList(dictionary);

    printf("skiplist loaded");

    print_words_absent_from_dictionary(correctme, loaded_dictionary, dictionary_size, correctme_size);

    printf("print done");

    free(correctme);
    free(dictionary);

    return 0;
}
