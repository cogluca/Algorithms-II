import datastructure.Edge;
import datastructure.Graph;
import datastructure.Node;

import java.util.HashMap;

public class Dijkstra<T extends Comparable<T>,L extends Comparable<L>> {


    private Heap<Edge<T,L>> heapSupport;
    private Graph<String, Float> resultingGraph;



    public Graph<String,Float> dijkstraAlgorithm(Graph<String,Float> graphBeingAnalyzed) {

        //load the graph
        //starting point is Torino, find Torino
        //from Torino as an entrypoint start searching for arc values on the frontier
        //add such values to the heap
        //organize the heap
        //extract the shortest
        //rerun the process
        //how do the queue updates work ? When do I remove an element from the queue, for the insertion sure as hell I insert the frontier from the graph that I'm at
        //I need a removal from heap when a similar lest costly path has been taken from another node, to avoid cyclicality

        Graph<String,Float> toReturnGraph = null;
        HashMap<Node<String,Float>,Edge<String, Float>> edgesOfSingleNode;
        
        
        Node<String, Float> entryPoint = graphBeingAnalyzed.getSpecificNode("Torino");
        edgesOfSingleNode = entryPoint.getEdgeReference();

        
        heapSupport = new Heap<>();
        
        
        return toReturnGraph;





    }





    public static void runDijkstra() {




    }


}
