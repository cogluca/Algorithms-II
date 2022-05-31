typedef struct _SkipList SkipList;
typedef struct _Node Node;

/**
 * @brief Get the Node Size object
 * 
 * @param list 
 * @return int 
 */
static int getNodeSize();

/**
 * @brief 
 * 
 * @param list 
 * @return SkipList* 
 */
SkipList *SkipListInit(int (*compare)(void *, void *));

/**
 * @brief 
 * 
 */
static Node *CreateNode(void *item, int level);

/**
 * @brief Construct a new insert Skip List object
 * 
 * @param list 
 * @param item 
 */
void insertSkipList(SkipList *list, void *item);

/**
 * @brief Construct a new search Skip List object
 * 
 * @param list 
 * @param item 
 */
void *searchNodeElement(SkipList *list, void *item);

/**
 * @brief 
 * 
 * @param list 
 */
void FreeSkipList(SkipList *list);