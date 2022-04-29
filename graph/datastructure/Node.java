package datastructure;

import java.util.HashMap;

    public class Node<T,L extends Comparable<L>> {

        private T value;
        private HashMap<Node<T,L>, Edge<T,L>> edgeReference;
        private Integer distance;

        public Node(T value) {
            this.value = value;
            this.edgeReference = new HashMap<>();
            distance = Integer.MAX_VALUE;
        }

        public T getValue() {
            return value;
        }

        public HashMap<Node<T, L>, Edge<T, L>> getEdgeReference() {
            return edgeReference;
        }






    }
