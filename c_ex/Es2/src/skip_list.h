        typedef struct _SkipList SkipList;
        typedef struct _Node Node;

        /**
         * @brief randomly choose the number of pointer will be in this specific node
         *
         * @return unsigned long: number of node choosen
         */
        static unsigned long level_generator();

        /**
         * @brief initialize the SkipList
         *
         * @param compate: function that is used in the skiplist to place and search element
         * @return SkipList*: SkipList created with all the memory that occurs allocated
         */
        SkipList *skip_list_init(int (*compare)(void *, void *));

        /**
         * @brief Create a node object
         *
         * @param item: element of the node
         * @param level: number of pointer to node present in this node
         * @return Node*: pointer to node created
         */
        static Node *create_node(void *item, int level);

        /**
         * @brief create a new node to be inserted in the skiplist
         *
         * @param list: a skiplist already initialized where to put a new element node
         * @param item: element given to the new insert node
         */
        void insert_skiplist(SkipList *list, void *item);

        /**
         * @brief  search if the element is present in the skiplist, if it isn't present it will return a NULL
         *
         * @param list: a skiplist already initialized where to put a new element node
         * @param item: element that have to be found in the list
         * @return void*: pointer to the node with the item searched, or NULL if the item isn't in the list
         */
        void *search_node_element(SkipList *list, void *item);

        /**
         * @brief release memory occupied by the skiplist already initialized
         *
         * @param list: a skiplist already initialized where to put a new element node
         */
        void free_skiplist(SkipList *list);