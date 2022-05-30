#include <stdio.h>
#include <stdlib.h>
#include "skip_list.h"

#define MAX_HEIGHT 10   // numero massimo di puntatori in un nodo


struct _SkipList {
    Node *head;
    unsigned int max_level; // massimo attuale tra i vari size
    int (*compare)(void *, void *);
};

struct _Node {
    Node **next;
    unsigned int size;  //numero di puntatori nel nodo
    void *item;
};

static double my_random() {

    return (double)rand() / (double)RAND_MAX;

}

static int getNodeSize(SkipList *list) {
    int lvl = 1;

    while (my_random() < 0.5 && lvl < MAX_HEIGHT) {
        lvl++;
    }

    return lvl;
}

SkipList *SkipListInit(int (*compare)(void *, void *)) {
    if (compare == NULL) {
        fprintf(stderr, "ordered_array_create: precedes parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }

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

static Node *CreateNode(void *item, int level) {
    Node *node = (Node *) malloc(sizeof(Node));
    node->item = item;
    node->size = level;
    node->next = (Node **) malloc(sizeof(Node *) * level);

    return node;
}

void insertSkipList(SkipList *list, void *item) {
    Node *newNode = CreateNode(item, getNodeSize(list));
    if (list->max_level < newNode->size)
        list->max_level = newNode->size;

    Node *list_head = list->head;

    for (int k = list->max_level; k >= 0; k--) {

        if(k <= list_head->size) {
            if (list_head->next[k] == NULL || list->compare(item, list_head->next[k]->item) ==
                                              2) {      //se trovo posto giusto entro qua dentro per inserire il nodo

                if (k <=
                    newNode->size) {                         //se entro perché head->next[k] == NULL, allora posso collegarlo
                    newNode->next[k] = list_head->next[k];
                    list_head->next[k] = newNode;
                }
            } else {
                //altrimenti scorro sempre sulla stessa coda di lista fino a trovare il NULL
                list_head = list_head->next[k];
                k++;

            }
        }
    }
}

void *searchNodeElement(SkipList *list, void *item) {
    Node *list_head = list->head;

    for (int i = list->max_level; i >= 0; i--) {

        Node* element = list_head->next[i];

        while (list_head->next[i] != NULL && list->compare(item, list_head->next[i]->item) == 1 ) {
            list_head = list_head->next[i];
        }

    }

    list_head = list_head->next[1];
    if (list->compare(item, list_head->item) == 0) {
        return list_head->item;
    } else {
        return NULL;
    }
}

void FreeSkipList(SkipList *list) {
    Node *currentNode = list->head->next[1];    // prendo il primo livello perché è quello che collega tutti i nodi della lista
    while (currentNode != NULL) {
        Node *nextNode = currentNode->next[1];
        free(currentNode->next);
        free(currentNode);
        currentNode = nextNode;
    }

    free(list->head);
    free(list);
}