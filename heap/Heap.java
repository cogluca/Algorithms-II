

import datastructure.Edge;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Heap<T extends Comparable<T>> {

    //ho bisogno di una Heap che sia di tipo Edge

    private ArrayList<T> vector;
    private int size;

    public Heap() {


        this.vector = new ArrayList<>(1);
        int lenght = vector.size();

        this.size = 0;

    }

    public ArrayList<T> getVector() {
        return vector;
    }

    public int getSize() {
        return size;
    }


    public T returnParentOfElement(int index) {
        return vector.get(index / 2);
    }

    public T returnLeftChildOfElement(int index) {
        return vector.get((index * 2)+1);
    }

    public T returnRightChildOfElement(int index) {
        return vector.get((index * 2)+2);
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


        if (size > 1) {
            vector.add(element);
            heapifyUp(size - 1);

        } else {
            vector.add(element);
        }
        size++;

    }

    public T extractMin() {

        T toReturnMin = vector.get(0);

        if (size > 0) {
            vector.set(0, vector.get(size-1));
        }//can throw exceptions with primitives like int


        size--;
        heapifyDown(0);

        return toReturnMin;
    }


//TODO When adding element remember to increase heap size, capacity is the concrete data type's size, size is the size of the heap
//TODO Add null expection to compares, they don't take into account nullness

}