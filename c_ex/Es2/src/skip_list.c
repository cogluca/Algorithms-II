#include <stdio.h>
#include <stdlib.h>
#include "skip_list.h"
#include <time.h>

#define MAX_HEIGHT 8   // max number of pointer in a node


struct _SkipList {
    Node *head;
    unsigned int max_level; // current max pointer in a node
    int (*compare)(void *, void *);
};

struct _Node {
    Node **next;
    unsigned int size;  // number of pointer in the node
    void *item;
};

static unsigned long level_generator() {

    unsigned long int level = 1;

    while((rand() %2 )< 0.5 && level < MAX_HEIGHT-1){       // max value of level is MAX_HEIGHT -1
        level++;
    }

    return  level;
}

SkipList *skip_list_init(int (*compare)(void *, void *)) {
    if (compare == NULL) {
        fprintf(stderr, "ordered_array_create: precedes parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }

    srand(time(NULL));

    SkipList *list = (SkipList *) malloc(sizeof(SkipList));
    Node *node = (Node *) malloc(sizeof(Node));
    list->head = node;
    node->size = 1; // indica il numero di puntatori del nodo
    node->next = (Node **) malloc(sizeof(Node *) * MAX_HEIGHT);

    for (int i = 0; i < MAX_HEIGHT; i++) {
        node->next[i] = NULL;
    }

    list->max_level = 1;
    list->compare = compare;

    return list;
}

static Node *create_node(void *item, int level) {
    Node *node = (Node *) malloc(sizeof(Node));
    node->item = item;
    node->size = level;
    node->next = (Node **) malloc(sizeof(Node *) * level+1);

    return node;
}

void insert_skiplist(SkipList *list, void *item) {
    Node *newNode = create_node(item, level_generator());
    if (list->max_level < newNode->size)
        list->max_level = newNode->size;

    Node *list_head = list->head;


    for (int k = list->max_level; k >= 0; k--) {

            if (list_head->next[k] == NULL || list->compare(item, list_head->next[k]->item) == 2) {   
                if (k < newNode->size) {                       
                    newNode->next[k] = list_head->next[k];
                    list_head->next[k] = newNode;
                }
            } else {
                list_head = list_head->next[k];
                k++;
            }

    }
}

void *search_node_element(SkipList *list, void *item) {
    Node *list_head = list->head;

    //searching through express ways
    for (int i = list->max_level; i >= 0; i--) {

        while (list_head->next[i] != NULL && list->compare(item, list_head->next[i]->item) == 1) {
            list_head = list_head->next[i];
        }

    }

    list_head = list_head->next[0];
    if (list->head != NULL && list->compare(item, list_head->item) == 0) {
        return list_head->item;
    } else {
        return NULL;
    }
}

void free_skiplist(SkipList *list) {
    Node *currentNode = list->head->next[0];    
    while (currentNode != NULL) {
        Node *nextNode = currentNode->next[0];
        free(currentNode->next);
        free(currentNode);
        currentNode = nextNode;
    }

    free(list->head);
    free(list);
}