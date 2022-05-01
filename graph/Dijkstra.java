import datastructure.Graph;
import datastructure.Node;

import java.util.Collection;

public class Dijkstra<T extends Comparable<T>,L extends Comparable<L>, C extends Comparable<C>> {


    private Heap<Node<T,L>, L> heapSupport;
    private Graph<T, L> resultingGraph;



    public Graph<T,L> dijkstraAlgorithm(Graph<T, L> graphBeingAnalyzed, T entryCity, Class distanceType) throws Exception {

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

        Graph<T,L> toReturnGraph = new Graph<>(true, distanceType );
        Collection<Node<T,L>> unexploredNodes;
        
        
        Node<T, L> entryPoint = graphBeingAnalyzed.getSpecificNode(entryCity);
        unexploredNodes = graphBeingAnalyzed.getNodes();
        unexploredNodes.remove(entryPoint);

        heapSupport = new Heap<>();

        for(Node<T,L> currentEdge: unexploredNodes) {
            heapSupport.addElement(currentEdge);
        }

        Node<T,L> minPath = heapSupport.extractMin();

        /*
        toReturnGraph.addNode(minPath.getSecondNode().getValue());
        toReturnGraph.addEdge(entryPoint.getValue(), minPath.getSecondNode().getValue(),minPath.getLabel());


         */


        

        
        
        return toReturnGraph;





    }





    public static void runDijkstra() {




    }


}
