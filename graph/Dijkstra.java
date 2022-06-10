import datastructure.Edge;
import datastructure.Graph;
import datastructure.Node;

import java.util.*;

public class Dijkstra<T extends Comparable<T>, L extends Comparable<L>, C extends Comparable<C>> {

    /**
     * Dijkstra algorithm that calculates minimum distance from an entry node to all other sources, achieves this through a greedy approach: it takes the local optimum choice in hope of reaching an overarching
     * optimal goal, stepwise the approach is considers a node from a priority queue containing all nodes of a certain graph, extracts the one having minimum distance, explores the adjacent nodes assigning them a value
     * composed of connecting edge plus the value of the minimum extracted and assigns this value to the frontier node, such approach is called frontier relaxation. Next element extracted comes from the frontier and is
     * the minimum value one.
     *
     * @param graphBeingAnalyzed graph taken into consideration
     * @param entryCity          starting node from which to carry calculations
     * @param distanceType       type of quantification of distances
     * @return returns the resulting shortest path tree
     * @throws Exception
     */

    public List<Node<T,L>> dijkstraAlgorithm(Graph<T, L> graphBeingAnalyzed, T entryCity, Class<L> distanceType) throws Exception {

        Graph<T, L> toReturnGraph = new Graph<>(false, distanceType, Node.ComparisonType.DISTANCE);

        Heap<Node<T, L>> unexploredNodes = new Heap<>();
        List<Node<T, L>> exploredNodes = new ArrayList<>();

        Node<T, L> entryPoint = graphBeingAnalyzed.getSpecificNode(entryCity);
        entryPoint.setDistance((Float.valueOf(0)));

        //Filling up the queue
        for (Node<T, L> node : graphBeingAnalyzed.getNodes()) {
            if (!node.equals(entryPoint)) {
                unexploredNodes.addElement(node);
            }
        }


        Node<T, L> frontierChange = null;
        Node<T, L> updateEdgeNode = null;
        Edge<T, L> updateEdge = null;
        Node<T, L> toExploreNode = null;
        Node<T, L> currentlyExploredNode = null;

        unexploredNodes.addElement(entryPoint);

        System.out.println("total nodes at beginning of analysis are " + graphBeingAnalyzed.getNodes().size());

        //main part, iteration over Priority Queue elements
        while (unexploredNodes.getSize() > 0) {

            for (Node<T, L> node : unexploredNodes.getVector()) {

                if (node.getEdgeReference().containsKey(frontierChange)) {
                    node.getEdgeReference().remove(frontierChange);
                    node.getEdgeReference().put(updateEdgeNode, updateEdge);
                }

            }

            toExploreNode = unexploredNodes.extractMin();


            HashMap<Node<T, L>, Edge<T, L>> connectionsOfNodeExplored = toExploreNode.getEdgeReference();
            ArrayList<HashMap<Node<String, Float>, Edge<String, Float>>> listOfEdges;

            for (Map.Entry<Node<T, L>, Edge<T, L>> singleFrontierReference : connectionsOfNodeExplored.entrySet()) {
                Node<T, L> frontierNode = singleFrontierReference.getKey();
                Edge<T, L> connectingEdge = singleFrontierReference.getValue();

                if (frontierNode.getFloatDistance() > toExploreNode.getFloatDistance() + (Float) connectingEdge.getLabel()) {

                    frontierChange = frontierNode;
                    frontierNode.setDistance(toExploreNode.getFloatDistance() + (Float) connectingEdge.getLabel());
                    unexploredNodes.diminishElement(frontierChange, frontierNode);

                    updateEdgeNode = frontierNode;
                    updateEdge = connectingEdge;

                    currentlyExploredNode = constructExploredNode(distanceType, toExploreNode , frontierNode, connectingEdge);
                }

            }

            if (currentlyExploredNode != null) {
                exploredNodes.add(currentlyExploredNode);
            }

        }


        return exploredNodes;
    }

    /**
     * Recontstructs a node to be fed onto the new returned tree, this avoids having a search and consequent pruning event inside of the already heavy Dijkstra algorithm
     * @param distanceType type of distance for the node 3 main quantifiable options Integer, Float, Double
     * @param toRebuildNode Node to be rebuilt
     * @param frontierNode Node reached to be put in the edge reference
     * @param connectingEdge edge connecting the two nodes
     * @return the constructed node
     * @throws Exception
     */
    public Node<T, L> constructExploredNode(Class<L> distanceType, Node<T, L> toRebuildNode, Node<T, L> frontierNode, Edge<T, L> connectingEdge) throws Exception {

        Class<Integer> oneType = Integer.class;
        Class<Double> anotherType = Double.class;
        Class<Float> thirdType = Float.class;


        Node<T, L> toReturnNode = new Node<T, L>(toRebuildNode.getValue(), distanceType, Node.ComparisonType.DISTANCE);
        if(distanceType.equals(thirdType))
            toReturnNode.setDistance(toRebuildNode.getFloatDistance());
        else if (distanceType.equals(oneType))
            toReturnNode.setDistance(toRebuildNode.getIntegerDistance());
        else if(distanceType.equals(anotherType))
            toReturnNode.setDistance(toRebuildNode.getDoubleDistance());

        toReturnNode.getEdgeReference().put(frontierNode, connectingEdge);

        return toReturnNode;
    }


}
