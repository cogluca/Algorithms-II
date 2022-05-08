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

    public Graph<String, Float> dijkstraAlgorithm(Graph<String, Float> graphBeingAnalyzed, String entryCity, Class distanceType) throws Exception {

        //load the graph
        //starting point is Torino, find Torino
        //from Torino as an entrypoint start searching for arc values on the frontier
        //add such values to the heap
        //organize the heap
        //extract the shortest
        //rerun the process
        //frontier relaxation
        //how do the queue updates work ? When do I remove an element from the queue, for the insertion sure as hell I insert the frontier from the graph that I'm at
        //I need a removal from heap when a similar lest costly path has been taken from another node, to avoid cyclical paths

        Graph<String, Float> toReturnGraph = new Graph<>(false, distanceType, Node.ComparisonType.DISTANCE);

        Heap<Node<String, Float>> unexploredNodes = new Heap<>();
        List<Node<String, Float>> exploredNodes = new ArrayList<>();

        Node<String, Float> entryPoint = graphBeingAnalyzed.getSpecificNode(entryCity);
        entryPoint.setDistance((Float.valueOf(0)));

        //Filling up the queue
        for (Node<String, Float> node : graphBeingAnalyzed.getNodes()) {
            if (!node.equals(entryPoint)) {
                unexploredNodes.addElement(node);
            }
        }


        Node<String, Float> frontierChange = null ;
        Node<String, Float> updateEdgeNode = null;
        Edge<String,Float> updateEdge = null;
        Node<String, Float> toExploreNode = null;

        unexploredNodes.addElement(entryPoint);

        System.out.println("total nodes at beginning of analysis are " + graphBeingAnalyzed.getNodes().size());

        //main part, iteration over Priority Queue elements
        while (unexploredNodes.getSize() > 0) {

            for(Node<String,Float> node: unexploredNodes.getVector()){

                if(node.getEdgeReference().containsKey(frontierChange)){
                    node.getEdgeReference().remove(frontierChange);
                    node.getEdgeReference().put(updateEdgeNode, updateEdge);
                }

            }

            toExploreNode = unexploredNodes.extractMin();

            //supposedly all nodes extracted from the unexplored node should be a "frontier node" that previously has been relaxed

            HashMap<Node<String, Float>, Edge<String, Float>> connectionsOfNodeExplored = toExploreNode.getEdgeReference();
            ArrayList<HashMap<Node<String, Float>, Edge<String, Float>>> listOfEdges;
            //calculate distances PROBLEM IS HERE in this internal loop up until now it all works decently, by that I mean there are high numbers in each variable.
            for (Map.Entry<Node<String, Float>, Edge<String, Float>> singleFrontierReference : connectionsOfNodeExplored.entrySet()) {
                Node<String, Float> frontierNode = singleFrontierReference.getKey();
                Edge<String, Float> connectingEdge = singleFrontierReference.getValue();

                //adjacent nodes get explored only as nodes partaining in another nodes edge reference, outside of that they're still having distance infinity, so if at a certain point I pick from
                //heap a node that was supposed to be already explored and might be a sure path it has instead cost infinity, and this is one problem

                //maybe the fact that minimum extraction is destructive creates also problems

                if (frontierNode.getFloatDistance() > toExploreNode.getFloatDistance() + connectingEdge.getLabel()) {

                    frontierChange = frontierNode;
                    frontierNode.setDistance(toExploreNode.getFloatDistance() + connectingEdge.getLabel());
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
        for(Node<String,Float> node: exploredNodes){
            if(node.getFloatDistance().equals(Float.valueOf(4000000)) )
                i++;
        }
        System.out.println("Not truly explored nodes are "+i);



        //extract the target node distance
        for(Node<String,Float> node: exploredNodes){
            if(node.getValue().equals("catania"))
                System.out.println("cost to catania is "+ node.getFloatDistance());
        }


        System.out.println("explored nodes are " + exploredNodes.size());

        return toReturnGraph;
    }


}
