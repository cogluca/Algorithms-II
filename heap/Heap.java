import java.lang.reflect.Array;
import java.util.Arrays;

public class Heap<T extends Comparable<T>> {

    private T[] vector;
    private int size;

    public Heap(Class<T> heapElemType) {

        this.vector = (T[]) Array.newInstance(heapElemType, 1);
        this.size = 0;

    }

    public T[] getVector() {
        return vector;
    }

    public int getSize() {
        return size;
    }

    public void checkForExtensionOnArray() {
        if (size == vector.length || size == 0) {

            int newCapacity = vector.length * 2;

            this.vector = Arrays.copyOf(vector, newCapacity);

        }
    }

    public T returnParentOfElement(int index) {
        return vector[index / 2];
    }

    public T returnLeftChildOfElement(int index) {
        return vector[(index * 2) + 1];
    }

    public T returnRightChildOfElement(int index) {
        return vector[(index * 2) + 2];
    }

    public int getParentIndex(int index) {
        return index / 2;
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
        T swap = vector[targetIndex];
        vector[targetIndex] = vector[currentIndex];
        vector[currentIndex] = swap;
    }

    public void heapifyDown(int index) {

        if (hasLeftChild(index)) {
            int smallestIndex = getLeftChildIndex(index);
            if (returnLeftChildOfElement(index).compareTo(returnRightChildOfElement(index)) < 0)
                smallestIndex = getRightChildIndex(index);

            if (vector[index].compareTo(vector[smallestIndex]) > 0) {
                swapElements(index, smallestIndex);
                heapifyDown(smallestIndex);
            }
        }
    }

    public void heapifyUp(int index) {
        if (index > 0) {
            if (vector[index].compareTo(vector[getParentIndex(index)]) < 0) {
                swapElements(index, getParentIndex(index));
                heapifyUp(getParentIndex(index));
            }
        }
    }

    public void addElement(T element) {
        if (size > 0) {
            checkForExtensionOnArray();
            vector[size - 1] = element;
            heapifyUp(size - 1);

        } else {
            checkForExtensionOnArray();
            vector[size] = element;
        }
        size++;
    }

    public T extractMin() {

        T toReturnMin = vector[0];
        if (size > 0) {
            vector[0] = vector[size - 1];
        }//can throw exceptions with primitives like int
        size--;

        heapifyDown(0);

        return toReturnMin;
    }


//TODO When adding element remember to increase heap size, capacity is the concrete data type's size, size is the size of the heap
//TODO Add null expection to compares, they don't take into account nullness

}