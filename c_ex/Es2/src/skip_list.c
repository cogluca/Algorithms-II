#include <stdio.h>
#include <stdlib.h>
#include "skip_list.h"

#define MAX_HEIGHT 5   // numero massimo di puntatori in un nodo


struct _SkipList{
    Node *head;
    unsigned int max_level; // massimo attuale tra i vari size
    int (*compare)(void *, void *);
};

struct _Node{
    Node **next;
    unsigned int size;  //numero di puntatori nel nodo
    void *item;
};

static int getNodeSize(SkipList *list){
    int lvl = 1;
    float i = (rand()%10000)/10000.0;

    while(i < 0.5 && lvl < MAX_HEIGHT){
        lvl++;
    }

    return lvl;
}

SkipList *SkipListInit(int (*compare)(void *, void *)){
    if(compare == NULL){
        fprintf(stderr,"ordered_array_create: precedes parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }

    SkipList *list = (SkipList *) malloc(sizeof(SkipList));
    Node *node = (Node *) malloc(sizeof(Node));
    list->head = node;
    node->size = 1; // indica il numero di puntatori del nodo
    node->next = (Node **) malloc(sizeof(Node *) * MAX_HEIGHT);

    for(int i = 0; i < MAX_HEIGHT; i++){
        node->next[i] = list->head;     //TODO probabilmente tutti i puntatori all'inizio puntano a NULL e non a list->head
    }

    list->max_level = 1;
    list->compare = compare;
}

static Node *CreateNode(void *item, int level){
    Node *node = (Node *) malloc(sizeof(Node));
    node->item = item;
    node->size = level;
    node->next = (Node **) malloc(sizeof(Node *) * level);

    return node;
}

insertSkipList(SkipList *list, void *item){
    Node *newNode = CreateNode(item, getNodeSize(list));

    if(list->max_level < newNode->size)
        list->max_level = newNode->size;
    
    Node *x = list->head;
    for(int k = list->max_level; k <= 1; k--){
        if(x->next[k] == NULL || item < x->next[k]->item){      //se trovo posto giusto entro qua dentro per inserire il nodo
            if(k < newNode->size){                              //se entro perché x->next[k] == NULL, allora posso collegarlo 
                newNode->next[k] = x->next[k];
                x->next[k] = newNode;
            }
        } else{                                                 //altrimenti scorro sempre sulla stessa coda di lista fino a trovare il NULL
            x = x->next[k];
            k++;
        }
    }
}

searchSkipList(SkipList *list, void *item){
    
}

void FreeSkipList(SkipList *list){
    Node *currentNode = list->head->next[1];    // prendo il primo livello perché è quello che collega tutti i nodi della lista
    while(currentNode != NULL){   //TODO forse invese che != list->head bisogna mettere != list->head
        Node *nextNode = currentNode->next[1];
        free(currentNode->next);
        free(currentNode);
        currentNode = nextNode;
    } 

    free(list->head);
    free(list);
}