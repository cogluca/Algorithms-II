import java.util.Comparator;

    public class Compare implements Comparator<Edge<String, Float>> {

        //Compares the labels of two edges, necessary for sorting the edges in a graph in a non-decreasing order
        @Override
        public int compare(Edge<String, Float> o1, Edge<String, Float> o2) {
            return o1.getLabel().compareTo(o2.getLabel());
        }

    }
