package datastructure;

public class Edge<T, L extends Comparable<L>> implements Comparable<Edge<T, L>> {

    private L label;
    private Node<T, L> firstNode;
    private Node<T, L> secondNode;

    /**
     * Constructor to define an edge on a given graph, engineered to be parametric and express different types of edges in different type of graphs
     *
     * @param label      defines the label of a given arch, might assume any type of value
     * @param firstNode  defines the node from which the edge is incident
     * @param secondNode defines the node to which the edge is incident
     */

    public Edge(L label, Node<T, L> firstNode, Node<T, L> secondNode) {
        this.label = label;
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }

    public L getLabel() {
        return label;
    }

    public Node<T, L> getFirstNode() {
        return firstNode;
    }

    public Node<T, L> getSecondNode() {
        return secondNode;
    }

    @Override
    public int compareTo(Edge<T,L> o) {
        return this.getLabel().compareTo(o.getLabel());
    }
}
