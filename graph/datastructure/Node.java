package datastructure;

import java.util.HashMap;

/**
 * Node class that defines a node in a graph
 *
 * @param <T> Types of the underlying node value
 * @param <L> Types of the edge's label, edge's label is the same value that then gets associated to the distance variable
 */

public class Node<T extends Comparable<T>, L extends Comparable<L>> implements Comparable<Node<T, L>> {

    private T value;
    private HashMap<Node<T, L>, Edge<T, L>> edgeReference;

    Class distanceTypeOfNode;
    Integer integerDistance;
    Double doubleDistance;
    Float floatDistance;

    public Node(T value, Class<L> distanceType) {
        this.value = value;
        this.edgeReference = new HashMap<>();
        if (distanceType == Integer.class) {
            integerDistance = Integer.MAX_VALUE;
            distanceTypeOfNode = Integer.class;
        }
        if (distanceType == Float.class) {
            floatDistance = Float.MAX_VALUE;
            distanceTypeOfNode = Float.class;
        }
        if (distanceType == Double.class) {
            doubleDistance = Double.MAX_VALUE;
            distanceTypeOfNode = Float.class;
        }
    }

    public T getValue() {
        return value;
    }

    public HashMap<Node<T, L>, Edge<T, L>> getEdgeReference() {
        return edgeReference;
    }

    public Integer getIntegerDistance() {
        return integerDistance;
    }

    public Double getDoubleDistance() {
        return doubleDistance;
    }

    public Float getFloatDistance() {
        return floatDistance;
    }


    @Override
    public int compareTo(Node<T, L> o) {
        if (distanceTypeOfNode == Integer.class) {
            return integerDistance.compareTo(o.integerDistance);
        }
        if (distanceTypeOfNode == Float.class) {
            return floatDistance.compareTo(o.floatDistance);
        } else {
            return doubleDistance.compareTo(o.doubleDistance);
        }
    }

    public Node<T, L> substituteValue(Object valueToSubstitute) {

        if (distanceTypeOfNode == Integer.class) {
            integerDistance = (Integer) valueToSubstitute;
        }
        if (distanceTypeOfNode == Float.class) {
            floatDistance = (Float) valueToSubstitute;
        }
        if (distanceTypeOfNode == Double.class) {
            doubleDistance = (Double) valueToSubstitute;
        }

        return this;


    }

}
