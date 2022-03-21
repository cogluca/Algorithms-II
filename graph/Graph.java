    package DjikstraOnGraph.datastructure;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.HashMap;

    public class Graph <T,L> {

        private HashMap<T,Node<T,L>> nodeMapping;
        private boolean isDirected;


        public Graph(boolean directed) {

            this.nodeMapping = new HashMap<>();
            this.isDirected = directed;

        }

        public HashMap<T, Node<T, L>> getNodeMapping() {
            return nodeMapping;
        }

        public void addNode(T aNode) throws Exception {
            if(aNode == null)
                throw new Exception("Node doesn't exist");
            this.nodeMapping.putIfAbsent(aNode, new Node<>(aNode));
        }

        /**
         * Adds an edge to a graph given the the values we wish to connect and a certain label for the edge
         * @param origin Supposedly
         * @param destination
         * @param label
         * @throws Exception
         */

        public void addEdge(T origin, T destination, L label) throws Exception {
            if(!containsNode(origin) || !containsNode(destination))
                throw new Exception("Either origin or destination do not exist");

            Node<T,L> nodeSeekingConnection = nodeMapping.get(origin);
            Node<T,L> nodeToConnect = nodeMapping.get(destination);

            nodeMapping.get(origin).getEdgeReference().put(nodeToConnect  , new Edge<>(label,nodeSeekingConnection ,nodeToConnect ));
            if(!isDirected())
                nodeMapping.get(destination).getEdgeReference().put(nodeSeekingConnection, new Edge<>(label, nodeToConnect, nodeSeekingConnection));

        }

        /**
         * Retrieves an attribute specifying if the current graph is either directed or undirected, namely do the edges have specific directions
         * or are they generic connections?
         * @return
         */
        public boolean isDirected() {
            return isDirected;
        }

        /**
         * Searches for a node by its contained value
         * @param aNode the node value
         * @return returns true if node exists
         */

        public boolean containsNode(T aNode){

           return this.nodeMapping.containsKey(aNode);

        }

        /**
         * Searched for a connection between two given values representing node values
         * @param firstNode first node to which the theoretical edge is adjacent
         * @param secondNode first node to which the theoretical edge is adjacent
         * @return true in case there is an edge connecting the given nodes, false otherwise
         * @throws Exception if the given nodes are null
         */

        public boolean containsEdge(T firstNode, T secondNode) throws Exception {

            if(!containsNode(firstNode)|| !containsNode(secondNode))
                throw new Exception("Either first or second node do not exist");

            Node<T,L> firstNodeObj = nodeMapping.get(firstNode);
            Node<T,L> secondNodeObj = nodeMapping.get(secondNode);

            return this.nodeMapping.get(firstNode).getEdgeReference().containsKey(secondNodeObj);
        }

        /**
         * Deletes a certain node and all the edges connected to it
         * @param aNode value of a certain node to delete
         */

        public void deleteNode(T aNode) {
            Node<T,L> nodeToDelete = nodeMapping.get(aNode);
            this.nodeMapping.remove(aNode);

            for(Node<T,L> node: nodeMapping.values()) {
                if(node.getEdgeReference().containsKey(nodeToDelete))
                    node.getEdgeReference().remove(nodeToDelete);
            }
        }


        /**
         * Deletes a certain edge given two nodevalues that supposedly connect that edge
         * @param originNodeValue first node-retrieving value
         * @param destinationNodeValue second node-retrieving value
         */

        public void deleteEdge(T originNodeValue, T destinationNodeValue) {

            nodeMapping.get(originNodeValue).getEdgeReference().remove(destinationNodeValue);
            if(!isDirected())
                nodeMapping.get(destinationNodeValue).getEdgeReference().remove(originNodeValue);

        }


        /**
         * Retrieves the number of nodes in a graph
         * @return returns an int representing the number of nodes in a graph
         */

        public int graphNodeSize() {
            return nodeMapping.size();
        }

        /**
         * Retrieves the number of edges in a graph
         * @return returns an int representing the number of edges in a graph
         */

        public int graphEdgeSize() {

            int archSizeCumulator = 0;

            for(Node<T,L> iteratedNode: nodeMapping.values()) {
                archSizeCumulator += iteratedNode.getEdgeReference().size();
            }
            if(!isDirected())
                return archSizeCumulator/2;

            return archSizeCumulator;

        }

        /**
         * Retrieves all the nodes composing a graph
         * @return retrieves a Collection of class Node representing the graph-composing nodes
         */
        public Collection<Node<T,L>> getNodes() {

            Collection<Node<T,L>> retrievedNodes;

            retrievedNodes =  nodeMapping.values();

            return retrievedNodes;

        }

        /**
         * Retrieves all the edges composing a graph
         * @return Returns a Collection of real type ArrayList, representing the number of edges in the related graph
         */
        public Collection<Edge<T,L>> getEdges() {

            Collection<Edge<T,L>> retrievedEdges = new ArrayList<>();
            for(Node<T,L> iteratedNode: nodeMapping.values()) {
                retrievedEdges.addAll(iteratedNode.getEdgeReference().values());
            }
            return retrievedEdges;
        }

        /**
         * Retrieves nodes adjacent to a given node
         * @param node represents the value of a node from which we want the adjacent nodes
         * @return returns the adjacent nodes to a given str
         */
        public Collection<Node<T,L>> getAdjacentNodes(T node) {

            Collection<Node<T,L>> retrievedNodes = new ArrayList<>();
            retrievedNodes.addAll(nodeMapping.get(node).getEdgeReference().keySet());

            return retrievedNodes;

        }

        /**
         * Returns the label of a certain edge containing two nodes given in input
         * @param nodeValueOne first value of a node given in input
         * @param nodeValueTwo second value of a node given in input
         * @return returns the label of an edge
         */
        public L getLabelOfEdge(T nodeValueOne, T nodeValueTwo) {

            Node<T,L> secondNode = nodeMapping.get(nodeValueTwo);
            return nodeMapping.get(nodeValueOne).getEdgeReference().get(secondNode).getLabel();
        }


    }
