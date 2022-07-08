        #include <stdio.h>
        #include <stdlib.h>
        #include "string.h"
        #include "skip_list.h"
        #include "../../C_resource//unity.h"
        #define UNITY_INCLUDE_DOUBLE

        int comp_string(void *a, void *b){
            if(a == NULL){
                fprintf(stderr, "comp_string: the first parameter is a null pointer");
                exit(EXIT_FAILURE);
            }
            if(b == NULL){
                fprintf(stderr, "comp_string: the second parameter is a null pointer");
            }
            char *char_a = (char *) a;
            char *char_b = (char *) b;
            if((char_a) == NULL)
                return 2;
            if((char_b) == NULL)
                return 1;
            if(strcmp(char_a, char_b) < 0){
                return 2;
            }else if(strcmp(char_a, char_b) > 0){
                return 1;
            }
            return 0;
        }

        SkipList *skip_list_string;

        void setUp(void) {
            printf("Sto inizializzando skip_list_string\n");
            skip_list_string = skip_list_init(comp_string);
        }

        void tearDown(void) {
            free_skiplist(skip_list_string);
        }

        //tests for strings without differences
        void test_insertion() {
            char* a_string = "Fauna";

            insert_skiplist(skip_list_string, a_string);


            TEST_ASSERT_EQUAL_STRING(a_string, search_node_element(skip_list_string, a_string));

        }

        void test_add_get_one() {

            char strings[13][50] = {  "Piante", "Pina", "Selvaggio",
                                        "Albero", "Siamese", "a",
                                        "f", "pippo", "formica",
                                        "gioco", "libellula", "Barella"
                            };

            for(int i = 0; i < 13; i++){
                insert_skiplist(skip_list_string, strings[i]);
            }

            TEST_ASSERT_EQUAL_STRING(strings[10], search_node_element(skip_list_string, "libellula"));
        }

        void test_add_get_all() {

            char strings[13][50] = {  "Piante", "Pina", "Selvaggio",
                                        "Albero", "Siamese", "a",
                                        "f", "pippo", "formica",
                                        "gioco", "libellula", "Barella"
                            };

            for(int i = 0; i < 13; i++){
                insert_skiplist(skip_list_string, strings[i]);
            }

            for(int i = 0; i < 13; i++){
                TEST_ASSERT_EQUAL_STRING(strings[i], search_node_element(skip_list_string, strings[i]));
            }
        }

        //executes tests
        int main() {
            UNITY_BEGIN();

            RUN_TEST(test_insertion);

            RUN_TEST(test_add_get_one);

            RUN_TEST(test_add_get_all);

            return UNITY_END();
        }
