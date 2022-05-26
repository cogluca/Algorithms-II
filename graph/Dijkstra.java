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
     * @param graphBeingAnalyzed graph taken into consideration
     * @param entryCity starting node from which to carry calculations
     * @param distanceType type of quantification of distances
     * @return returns the resulting shortest path tree
     * @throws Exception
     */

    public Graph<T, L> dijkstraAlgorithm(Graph<T, L> graphBeingAnalyzed, T entryCity, Class distanceType) throws Exception {

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


        Node<T, L> frontierChange = null ;
        Node<T, L> updateEdgeNode = null;
        Edge<T,L> updateEdge = null;
        Node<T, L> toExploreNode = null;

        unexploredNodes.addElement(entryPoint);

        System.out.println("total nodes at beginning of analysis are " + graphBeingAnalyzed.getNodes().size());

        //main part, iteration over Priority Queue elements
        while (unexploredNodes.getSize() > 0) {

            for(Node<T,L> node: unexploredNodes.getVector()){

                if(node.getEdgeReference().containsKey(frontierChange)){
                    node.getEdgeReference().remove(frontierChange);
                    node.getEdgeReference().put(updateEdgeNode, updateEdge);
                }

            }

            toExploreNode = unexploredNodes.extractMin();



            HashMap<Node<T, L>, Edge<T, L>> connectionsOfNodeExplored = toExploreNode.getEdgeReference();
            ArrayList<HashMap<Node<String, Float>, Edge<String, Float>>> listOfEdges;
            //calculate distances PROBLEM IS HERE in this internal loop up until now it all works decently, by that I mean there are high numbers in each variable.
            for (Map.Entry<Node<T, L>, Edge<T, L>> singleFrontierReference : connectionsOfNodeExplored.entrySet()) {
                Node<T, L> frontierNode = singleFrontierReference.getKey();
                Edge<T, L> connectingEdge = singleFrontierReference.getValue();

                //adjacent nodes get explored only as nodes partaining in another nodes edge reference, outside of that they're still having distance infinity, so if at a certain point I pick from
                //heap a node that was supposed to be already explored and might be a sure path it has instead cost infinity, and this is one problem

                //maybe the fact that minimum extraction is destructive creates also problems

                if (frontierNode.getFloatDistance() > toExploreNode.getFloatDistance() + (Float)connectingEdge.getLabel()) {

                    frontierChange = frontierNode;
                    frontierNode.setDistance(toExploreNode.getFloatDistance() + (Float) connectingEdge.getLabel());
                    //graphBeingAnalyzed.updateDistance(frontierNode); //i update all the references to that frontier node with the newfound value
                    unexploredNodes.substituteElement(frontierChange, frontierNode);

                    updateEdgeNode = frontierNode;
                    updateEdge = connectingEdge;
                }

            }
            exploredNodes.add(toExploreNode);

            try {
                toReturnGraph.addSpecificNode(toExploreNode);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        /*
        for(Node<String,Float> node: exploredNodes){
            System.out.println(node.getFloatDistance());
        }

         */

        int i = 0;
        for(Node<T,L> node: exploredNodes){
            if(node.getFloatDistance().equals(Float.valueOf(4000000)) )
                i++;
        }
        System.out.println("Not truly explored nodes are "+i);



        //extract the target node distance
        for(Node<T,L> node: exploredNodes){
            if(node.getValue().equals("catania"))
                System.out.println("cost to catania is "+ node.getFloatDistance());
        }


        System.out.println("explored nodes are " + exploredNodes.size());

        return toReturnGraph;
    }


/*
    public Float getQuantifiableFloatLabel(Edge<T,L> edgeToObserve){

        L label = edgeToObserve.getLabel();
        if(label.getClass() == Float.class)
            return (Float) label;
        return Float.valueOf(-1);
    }

    public Double getQuantifiableDoubleLabel(Edge<T,L> edgeToObserve){

        L label = edgeToObserve.getLabel();
        if(label.getClass() == Float.class)
            return (Double) label;
        return Double.valueOf(-1);
    }

    public Integer getQuantifiableIntegerLabel(Edge<T,L> edgeToObserve){

        L label = edgeToObserve.getLabel();
        if(label.getClass() == Float.class)
            return (Integer) label;
        return -1;
    }

    public Object getQuantifiableLabel(Edge<T,L> edgeToObserve){

        Integer tentativeLabel = getQuantifiableIntegerLabel(edgeToObserve);
        Double tentativeDoubleLabel = getQuantifiableDoubleLabel(edgeToObserve);
        Float tentativeFloatLabel = getQuantifiableFloatLabel(edgeToObserve);

        //what a shit, frivolous OOP
        //got a problem, cannot return a diversified type of Numeric, can return a generic Object at best, either I remove this stuff and do a class control on Dijkstra logic and cast to type
        //the most expansive numeric is Float, if it turns into int it truncates by itself, same goes for double

        if(tentativeLabel.)



    }


 */



}
