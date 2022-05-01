

import datastructure.Edge;
import datastructure.Node;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


//forse il riordinamento va fatto in base al valore "distanza" che il nodo assume e non il peso dell'arco, per avere il rilassamento della frontiera

public class Heap<T extends Comparable<T>, L extends Comparable<L>> {

    //L è la stringa nome del Nodo, C è il valore della distanza


    //ho bisogno di una Heap che sia di tipo Edge

    //l'ultima funzione impone il vincolo che il vettore sia una hashmap

    private ArrayList<T> vector;
    private int size;

    private HashMap<T, Integer> keyMap;

    public Heap() {


        this.vector = new ArrayList<>(1);
        int lenght = vector.size();
        keyMap = new HashMap<>();

        this.size = 0;

    }

    public ArrayList<T> getVector() {
        return vector;
    }

    public HashMap<T, Integer> getKeyMap() {
        return keyMap;
    }

    public int getSize() {
        return size;
    }


    public T returnParentOfElement(int index) {
        return vector.get(index / 2);
    }

    public T returnLeftChildOfElement(int index) {
        return vector.get((index * 2) + 1);
    }

    public T returnRightChildOfElement(int index) {
        return vector.get((index * 2) + 2);
    }

    public int getParentIndex(int index) {
        return (index) / 2;
    }

    public int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    public int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    public boolean hasRightChild(int index) {
        return (index * 2) + 2 < size - 1;
    }

    public boolean hasLeftChild(int index) {
        return (index * 2) + 1 < size - 1;
    }


    private void swapElements(int currentIndex, int targetIndex) {
        T swap = vector.get(targetIndex);
        vector.set(targetIndex, vector.get(currentIndex));
        vector.set(currentIndex, swap);
        keyMap.put(vector.get(currentIndex), currentIndex);
        keyMap.put(vector.get(targetIndex), targetIndex);
    }

    public void heapifyDown(int index) {

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

    public void heapifyUp(int index) {
        if (index > 0) {
            if (vector.get(index).compareTo(vector.get(getParentIndex(index))) < 0) {
                swapElements(index, getParentIndex(index));
                heapifyUp(getParentIndex(index));
            }
        }
    }

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
        heapifyDown(indexOfDecreasedElement);


    }


//TODO When adding element remember to increase heap size, capacity is the concrete data type's size, size is the size of the heap
//TODO Add null expection to compares, they don't take into account nullness

}