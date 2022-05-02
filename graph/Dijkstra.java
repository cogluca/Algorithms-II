import datastructure.Edge;
import datastructure.Graph;
import datastructure.Node;

import java.util.*;

public class Dijkstra<T extends Comparable<T>,L extends Comparable<L>, C extends Comparable<C>> {


    private Heap<Node<T,L>, L> heapSupport;
    private Graph<T, L> resultingGraph;



    public Graph<String,Float> dijkstraAlgorithm(Graph<String, Float> graphBeingAnalyzed, String entryCity, Class distanceType) throws Exception {

        //load the graph
        //starting point is Torino, find Torino
        //from Torino as an entrypoint start searching for arc values on the frontier
        //add such values to the heap
        //organize the heap
        //extract the shortest
        //rerun the process
        //missing the frontier relaxation
        //how do the queue updates work ? When do I remove an element from the queue, for the insertion sure as hell I insert the frontier from the graph that I'm at
        //I need a removal from heap when a similar lest costly path has been taken from another node, to avoid cyclical paths

        Graph<String, Float> toReturnGraph = new Graph<>(false, distanceType, Node.ComparisonType.DISTANCE);

        Heap<Node<String, Float>, L> unexploredNodes = new Heap<>();
        List<Node<String, Float>> exploredNodes = new ArrayList<>();
        Heap<Node<String,Float>,Float> frontierNodes = new Heap<>();


        Node<String, Float> entryPoint = graphBeingAnalyzed.getSpecificNode(entryCity);
        entryPoint.setDistance((Float.valueOf(0)));


        unexploredNodes.addElement(entryPoint);


        while (unexploredNodes.getSize() > 0) {

            Node<String, Float> toExploreNode = unexploredNodes.extractMin();
            HashMap<Node<String, Float>, Edge<String, Float>> connectionsOfNodeExplored = toExploreNode.getEdgeReference();

            //calculate distances
            for (Map.Entry<Node<String, Float>, Edge<String, Float>> singleFrontierReference : connectionsOfNodeExplored.entrySet()) {
                Node<String,Float> frontierNode = singleFrontierReference.getKey();
                Edge<String,Float> connectingEdge = singleFrontierReference.getValue();

                if(!exploredNodes.contains(frontierNode)) {
                    if (frontierNode.getFloatDistance() > toExploreNode.getFloatDistance() + connectingEdge.getLabel()) {
                        frontierNode.setDistance(toExploreNode.getFloatDistance() + connectingEdge.getLabel());
                        unexploredNodes.addElement(frontierNode);
                    }
                }

            }
            exploredNodes.add(toExploreNode);


            toReturnGraph.addNode(toExploreNode.getValue());

        }


        return toReturnGraph;
    }




}
