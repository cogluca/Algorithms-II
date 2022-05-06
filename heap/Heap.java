

import datastructure.Edge;
import datastructure.Node;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;


//forse il riordinamento va fatto in base al valore "distanza" che il nodo assume e non il peso dell'arco, per avere il rilassamento della frontiera

public class Heap<T extends Comparable<T>, L extends Comparable<L>> {

    //L è la stringa nome del Nodo, C è il valore della distanza


    //ho bisogno di una Heap che sia di tipo Edge

    //l'ultima funzione impone il vincolo che il vettore sia una hashmap

    private ArrayList<T> vector;
    private int size;

    private HashMap<T,Integer> keyMap;



    Map.Entry<Integer, Integer> compareTo = new AbstractMap.SimpleEntry<Integer,Integer>(5, 5);

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
     * @return
     */
    public ArrayList<T> getVector() {
        return vector;
    }

    /**
     * returns reference hashmap with indexing
     * @return
     */
    public HashMap<T,Integer>  getKeyMap() {
        return keyMap;
    }

    public int getSize() {
        return size;
    }


    /**
     * Returns parent of element at index
     * @param index index of element in consideration
     * @return returns parent of element in consideration
     */
    public T returnParentOfElement(int index) {
        return vector.get(index / 2);
    }
    /**
     * returns left child of a given element
     * @param index index of element in considerations
     * @return returns left child
     */

    public T returnLeftChildOfElement(int index) {
        return vector.get((index * 2) + 1);
    }

    /**
     * returns right child of a given element
     * @param index index of element in considerations
     * @return returns right child
     */
    public T returnRightChildOfElement(int index) {
        return vector.get((index * 2) + 2);
    }

    /**
     * Returns parent of element at index
     * @param index index of element in consideration
     * @return returns parent of element in consideration
     */
    public int getParentIndex(int index) {
        return (index) / 2;
    }

    /**
     * returns left child of a given element
     * @param index index of element in considerations
     * @return returns left child
     */
    public int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    /**
     * returns right child of a given element
     * @param index index of element in considerations
     * @return returns right child
     */
    public int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    /**
     * Checks if an element has a right child
     * @param index index of element in considerations
     * @return true or false based on existence of right child
     */
    public boolean hasRightChild(int index) {
        return (index * 2) + 2 < size - 1;
    }

    /**
     * Checks if an element has a left child
     * @param index index of element in considerations
     * @return true or false based on existence of left child
     */
    public boolean hasLeftChild(int index) {
        return (index * 2) + 1 < size - 1;
    }


    /**
     * Simple method to swap two elements and sign on the key map their updated positions and values
     * @param currentIndex index of current element to be swapped
     * @param targetIndex index of desired position that the element at currentIndex has to reach
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
     * @param index index of the element to be dragged down
     */
    private void heapifyDown(int index) {

        if (hasLeftChild(index)) {
            int smallestIndex = getLeftChildIndex(index);
            T leftChild = returnLeftChildOfElement(index);
            T rightChild = returnRightChildOfElement(index);
            if (returnLeftChildOfElement(index).compareTo(returnRightChildOfElement(index)) > 0)

                smallestIndex = getRightChildIndex(index);

            if (vector.get(index).compareTo(vector.get(smallestIndex)) > 0) {
                swapElements(index, smallestIndex);
                heapifyDown(smallestIndex);
            }
        }
        //cosa metto alle
    }

    /**
     * Procedure to organize the heap when an element gets added, it basically drags the element upwards until no parent is smaller than him (we're in a Min Heap)
     * @param index index of element to be dragged up
     */
    private void heapifyUp(int index) {
        if (index > 0) {
            if (vector.get(index).compareTo(vector.get(getParentIndex(index))) < 0) {
                swapElements(index, getParentIndex(index));
                heapifyUp(getParentIndex(index));
            }
        }
    }

    /**
     * Adds an element to the Heap and reorganizes it in logn time
     * @param element
     */
    public void addElement(T element) {


        if (size >= 1) {
            vector.add(element);
            size++;
            keyMap.put(vector.get(size - 1), size - 1);
            heapifyUp(size - 1);

        } else {
            vector.add(element);
            keyMap.put(vector.get(0), 0);
            size++;
        }


    }


    public void removeAndChangeElement(T element, T changeElement) {

        int indexOfRemoval = keyMap.get(element);
        vector.set(indexOfRemoval, changeElement);
        if(returnParentOfElement(indexOfRemoval).compareTo(changeElement)>0){
            heapifyUp(indexOfRemoval);
        }
        else if (returnParentOfElement(indexOfRemoval).compareTo(changeElement)<0)
            heapifyDown(indexOfRemoval);

    }

    /**
     * extracts the minimum element from the heap, base on first position and reorganizes the heap in logn time
     * @return returns the minimum of the entire queues
     */
    public T extractMin() {

        T toReturnMin = vector.get(0);

        if (size > 0) {
            vector.set(0, vector.get(size - 1));
            keyMap.put(vector.get(0), 0);

        }//can throw exceptions with primitives like int

        size--;
        heapifyDown(0);

        return toReturnMin;
    }

    /**
     * Diminishes either value of an element or the associated cost and reorganizes based on logn time
     * @param element chosen element to be substituted
     * @param complexElementValueToSubstitute used in case of simple types to substitute value
     * @param valueToSubstituteForSimpleElement used in case of complex types to substitute value
     */

    public void diminishElementValue(Object element, Object complexElementValueToSubstitute, T valueToSubstituteForSimpleElement) {

        //switch enorme con casistiche di Node, Edge, Integer, Float, Double e per i tipi compositi sottocasi in cui il valore da estrarre
        //serve al rilassamento della frontiera

        //theorically
        //squash the search time by using the heap properties

        //implementation difficulties

        int indexOfDecreasedElement = keyMap.get(element);
        keyMap.remove(element);

        if (element.getClass() == Node.class) {
            Node<?, ?> elementToDecrease = ((Node<?, ?>) element).substituteValue(complexElementValueToSubstitute);
            T elementRecasted = (T) elementToDecrease;
            vector.set(indexOfDecreasedElement, elementRecasted);

        } else {
            vector.set(indexOfDecreasedElement, valueToSubstituteForSimpleElement);
        }
        keyMap.put(vector.get(indexOfDecreasedElement), indexOfDecreasedElement);
        if(returnParentOfElement(indexOfDecreasedElement).compareTo(vector.get(indexOfDecreasedElement)) > 0 ){
            heapifyUp(indexOfDecreasedElement);
        }
        else if(returnParentOfElement(indexOfDecreasedElement).compareTo(vector.get(indexOfDecreasedElement)) < 0 ) {
            heapifyDown(indexOfDecreasedElement);
        }


    }

    public T getSpecificElement(T element) {


        int indexOfElement = keyMap.get(element);
        return vector.get(indexOfElement);

    }


//TODO When adding element remember to increase heap size, capacity is the concrete data type's size, size is the size of the heap
//TODO Add null expection to compares, they don't take into account nullness

}