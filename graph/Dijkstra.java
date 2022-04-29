import datastructure.Edge;
import datastructure.Graph;
import datastructure.Node;

import java.util.Collection;
import java.util.HashMap;

public class Dijkstra<T extends Comparable<T>,L extends Comparable<L>> {


    private Heap<Edge<T,L>> heapSupport;
    private Graph<T, L> resultingGraph;



    public Graph<T,L> dijkstraAlgorithm(Graph<T,L> graphBeingAnalyzed, T entryCity) {

        //load the graph
        //starting point is Torino, find Torino
        //from Torino as an entrypoint start searching for arc values on the frontier
        //add such values to the heap
        //organize the heap
        //extract the shortest
        //rerun the process
        //how do the queue updates work ? When do I remove an element from the queue, for the insertion sure as hell I insert the frontier from the graph that I'm at
        //I need a removal from heap when a similar lest costly path has been taken from another node, to avoid cyclical paths

        Graph<T,L> toReturnGraph = new Graph<>(true);
        Collection<Edge<T,L>> edgesOfSingleNode;
        
        
        Node<T, L> entryPoint = graphBeingAnalyzed.getSpecificNode(entryCity);
        edgesOfSingleNode = entryPoint.getEdgeReference().values();

        heapSupport = new Heap<>();

        for(Edge<T,L> currentEdge: edgesOfSingleNode) {
            heapSupport.addElement(currentEdge);
        }

        Edge<T,L> minPath = heapSupport.extractMin();

        

        
        
        return toReturnGraph;





    }





    public static void runDijkstra() {




    }


}
