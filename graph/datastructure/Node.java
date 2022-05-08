package datastructure;

import java.util.*;


/**
 * Node class that defines a node in a graph
 *
 * @param <T> Types of the underlying node value
 * @param <L> Types of the edge's label, edge's label is the same value that then gets associated to the distance variable
 */

public class Node<T extends Comparable<T>, L extends Comparable<L>> implements Comparable<Node<T, L>> {

    private T value;

    private HashMap<Node<T, L>, Edge<T, L>> edgeReference;
    private List<Node<T,L>> shortestPath;


    public Class getDistanceTypeOfNode() {
        return distanceTypeOfNode;
    }

    Class distanceTypeOfNode;

    Integer integerDistance;
    Double doubleDistance;
    Float floatDistance;



    public enum ComparisonType{
        DISTANCE,
        VALUE;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public ComparisonType getComparisonType() {
        return comparisonType;
    }

    ComparisonType comparisonType;

    public Node(T value, Class<L> distanceType, ComparisonType definedComparisonType) {
        this.value = value;
        this.edgeReference = new HashMap<>();
        if (distanceType == Integer.class) {
            integerDistance = Integer.MAX_VALUE -1;
            distanceTypeOfNode = Integer.class;
        }
        if (distanceType == Float.class) {
            floatDistance = Float.valueOf(4000000);
            distanceTypeOfNode = Float.class;
        }
        if (distanceType == Double.class) {
            doubleDistance = Double.MAX_VALUE;
            distanceTypeOfNode = Float.class;
        }

        comparisonType = definedComparisonType;
        shortestPath = new ArrayList<>();
    }

    public void setDistance (Object distance) throws Exception {
        if(distance.getClass() == Integer.class) {
            integerDistance = (Integer) distance;
        }
        if(distance.getClass() == Double.class) {
            doubleDistance = (Double) distance;
        }
        if(distance.getClass() == Float.class) {
            floatDistance = (Float) distance;
        }
        else{
            throw new Exception("Sorry distance type isn't included");
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

        if(comparisonType == ComparisonType.DISTANCE) {

            if (distanceTypeOfNode == Integer.class) {
                return integerDistance.compareTo(o.integerDistance);
            }
            if (distanceTypeOfNode == Float.class) {
                return floatDistance.compareTo(o.floatDistance);
            } else {
                return doubleDistance.compareTo(o.doubleDistance);
            }
        }
        else {
            return value.compareTo(o.value);
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

    public void pruneConnections(Edge<T,L> toPreserveEdge) {

        for(Map.Entry<Node<T,L>,Edge<T,L>> possiblyToPruneConnection: edgeReference.entrySet()) {

            if(!possiblyToPruneConnection.equals(toPreserveEdge)){
                edgeReference.remove(possiblyToPruneConnection.getKey());
            }

        }
    }

}
