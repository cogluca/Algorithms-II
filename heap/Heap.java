

import java.util.*;


/**
 * Heap ADT class, concrete implementation consists on array list and an auxiliary map of keys to retrieve elements in constan time, size tracks the actual size of the heap
 * @param <T> type of element that the heap has to order
 */

public class Heap<T extends Comparable<? super T>> {


    private ArrayList<T> vector;
    private int size;

    private HashMap<T, Integer> keyMap;


    Map.Entry<Integer, Integer> compareTo = new AbstractMap.SimpleEntry<Integer, Integer>(5, 5);

    /*
        Constructor
         */
    public Heap() {


        this.vector = new ArrayList<>(1);

        keyMap = new HashMap<>();

        this.size = 0;

    }

    /**
     * Returns concrete data type of heap
     *
     * @return
     */
    public ArrayList<T> getVector() {
        return vector;
    }

    /**
     * returns reference hashmap with indexing
     *
     * @return
     */
    public HashMap<T, Integer> getKeyMap() {
        return keyMap;
    }

    public int getSize() {
        return size;
    }


    /**
     * Returns parent of element at index
     *
     * @param index index of element in consideration
     * @return returns parent of element in consideration
     */
    public T returnParentOfElement(int index) {
        return vector.get(index / 2);
    }

    /**
     * returns left child of a given element
     *
     * @param index index of element in considerations
     * @return returns left child
     */

    public T returnLeftChildOfElement(int index) throws Exception {
        if (hasLeftChild(index)) {
            return vector.get((index * 2) + 1);
        } else {
            throw new Exception("element has no left child");
        }
    }

    /**
     * returns right child of a given element
     *
     * @param index index of element in considerations
     * @return returns right child
     */
    public T returnRightChildOfElement(int index) throws Exception {
        if (hasRightChild(index)) {
            return vector.get((index * 2) + 2);
        } else {
            throw new Exception("element has no right child");
        }
    }

    /**
     * Returns parent of element at index
     *
     * @param index index of element in consideration
     * @return returns parent of element in consideration
     */
    public int getParentIndex(int index) {
        return (index) / 2;
    }

    /**
     * returns left child of a given element
     *
     * @param index index of element in considerations
     * @return returns left child
     */
    public int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    /**
     * returns right child of a given element
     *
     * @param index index of element in considerations
     * @return returns right child
     */
    public int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    /**
     * Checks if an element has a right child
     *
     * @param index index of element in considerations
     * @return true or false based on existence of right child
     */
    public boolean hasRightChild(int index) {
        return (index * 2) + 2 <= size - 1;
    }

    /**
     * Checks if an element has a left child
     *
     * @param index index of element in considerations
     * @return true or false based on existence of left child
     */
    public boolean hasLeftChild(int index) {
        return (index * 2) + 1 <= size - 1;
    }


    /**
     * Simple method to swap two elements and sign on the key map their updated positions and values
     *
     * @param currentIndex index of current element to be swapped
     * @param targetIndex  index of desired position that the element at currentIndex has to reach
     */
    private void swapElements(int currentIndex, int targetIndex) {
        T swap = vector.get(targetIndex);
        vector.set(targetIndex, vector.get(currentIndex));
        vector.set(currentIndex, swap);
        keyMap.put(vector.get(currentIndex), currentIndex);
        keyMap.put(vector.get(targetIndex), targetIndex);
    }



    /**
     * Once the minimum gets extracted the procedure puts on top the last element from left to right of the last level at the root and then reorganizes it with this method by dragging down
     *
     * @param index index of the element to be dragged down
     */
    private void heapifyDown(int index) throws Exception {

        if (hasLeftChild(index)) {
            int smallestIndex = getLeftChildIndex(index);
            T leftChild = returnLeftChildOfElement(index);

            boolean rightChildBool = hasRightChild(index);
            if (hasRightChild(index)) {
                if (returnLeftChildOfElement(index).compareTo(returnRightChildOfElement(index)) > 0) {
                    smallestIndex = getRightChildIndex(index);
                }
            }

            if (vector.get(index).compareTo(vector.get(smallestIndex)) > 0) {
                swapElements(index, smallestIndex);
                heapifyDown(smallestIndex);
            }
        }

    }

    /**
     * Procedure to organize the heap when an element gets added, it basically drags the element upwards until no parent is smaller than him (we're in a Min Heap)
     *
     * @param index index of element to be dragged up
     */
    private void heapifyUp(int index) {


        T elementToMove = vector.get(index);
        while (index > 0) {

            int currentParentIndex = (index - 1) / 2;
            T currentParent = vector.get(currentParentIndex);
            if (elementToMove.compareTo(currentParent) >= 0)
                break;
            vector.set(index, currentParent);
            keyMap.put(currentParent, index);
            index = currentParentIndex;

        }
        vector.set(index, elementToMove);
        keyMap.put(elementToMove, index);

    }



    /**
     * Adds an element to the Heap and reorganizes it in logn time
     *
     * @param element
     */
    public void addElement(T element) {


        if (size >= 1) {
            vector.add(element);
            size++;
            //culprit ?
            keyMap.put(vector.get(size - 1), size - 1);
            int indexOfAdded = keyMap.get(element);
            heapifyUp(indexOfAdded);

        } else {
            vector.add(element);
            keyMap.put(vector.get(0), 0);
            size++;
        }


    }

    /**
     * Diminishes either value of an element and reorganizes based on logn time
     * @param element element selected for change
     * @param changeElement element to be changed with
     * @throws Exception
     */


    public void diminishElement(T element, T changeElement) throws Exception {

        int indexOfRemoval = keyMap.get(element);
        keyMap.remove(element);
        vector.set(indexOfRemoval, changeElement);
        keyMap.put(changeElement, indexOfRemoval);

        if (returnParentOfElement(indexOfRemoval).compareTo(changeElement) > 0) {
            heapifyUp(indexOfRemoval);
        } else if (hasLeftChild(indexOfRemoval) || hasRightChild(indexOfRemoval)) {
            if (returnLeftChildOfElement(indexOfRemoval).compareTo(changeElement) < 0)
                heapifyDown(indexOfRemoval);
        }
    }

    /**
     * extracts the minimum element from the heap, base on first position and reorganizes the heap in logn time
     *
     * @return returns the minimum of the entire queues
     */
    public T extractMin() throws Exception {

        T toReturnMin = null;


        if (size > 0) {
            toReturnMin = vector.get(0);

            vector.set(0, vector.get(size - 1));

            T elemSet = vector.get(size - 1);

            keyMap.remove(vector.get(size - 1));
            keyMap.put(vector.get(0), 0);


            size--;

            heapifyDown(0);
            return toReturnMin;
        }
        else
            throw new Exception("Heap is empty");


    }




}