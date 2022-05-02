package datastructure;

import org.junit.Before;
    import org.junit.Test;

    import static org.junit.Assert.*;

    public class GraphTest {

        private Graph<Integer, Double> toTestGraph;
        private int element1, element2, element3;
        private double label1, label2;


        @Before
        public void initialize() {

            element1 = 5;
            element2 = 7;
            element3 = 8;
            label1 = 4.2342;
            label2 = 5.3423;

        }

        @Test
        public void testEmpyGraph() {

            toTestGraph = new Graph<>(false, Double.class,Node.ComparisonType.VALUE);
            assertEquals(toTestGraph.graphNodeSize(),0);
            assertEquals(toTestGraph.graphEdgeSize(), 0);
            assertEquals(toTestGraph.getNodes().size(), 0);
            assertEquals(toTestGraph.getEdges().size(),0);

        }

        @Test
        public void testFilledGraph() throws Exception {
            toTestGraph = new Graph<>(true, Double.class,Node.ComparisonType.VALUE);

            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);
            toTestGraph.addNode(element3);
            toTestGraph.addEdge(element1, element2, label1);
            toTestGraph.addEdge(element2,element3,label2);

            assertEquals(toTestGraph.graphNodeSize(),3);
            assertEquals(toTestGraph.graphEdgeSize(),2);
            assertTrue(toTestGraph.containsNode(element1));
            assertTrue(toTestGraph.containsNode(element2));

        }

        @Test
        public void testIsDirected() {
            toTestGraph = new Graph<>(true, Double.class, Node.ComparisonType.VALUE);
            Graph<Integer,Double> anotherGraph = new Graph<>(false, Integer.class,Node.ComparisonType.VALUE);
            assertTrue(toTestGraph.isDirected());
            assertFalse(anotherGraph.isDirected());

        }

        @Test
        public void containsNode() throws Exception {
            toTestGraph = new Graph<>(true, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);


            assertTrue(toTestGraph.containsNode(element1));
            assertTrue(toTestGraph.containsNode(element2));

        }

        @Test
        public void containsEdge() throws Exception {

            toTestGraph = new Graph<>(true, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);
            toTestGraph.addNode(element3);
            toTestGraph.addEdge(element1, element2, label1);
            toTestGraph.addEdge(element2,element3,label2);

            assertTrue(toTestGraph.containsEdge(element1,element2));
            assertTrue(toTestGraph.containsEdge(element2,element3));

        }


        @Test
        public void deleteNode() throws Exception {

            toTestGraph = new Graph<>(true, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);

            toTestGraph.deleteNode(element1);
            toTestGraph.deleteNode(element2);

            assertEquals(toTestGraph.getNodeMapping().get(element1),null);
            assertEquals(toTestGraph.getNodeMapping().get(element2), null);


        }

        @Test
        public void deleteArc() throws Exception {

            toTestGraph = new Graph<>(true, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);
            toTestGraph.addNode(element3);
            toTestGraph.addEdge(element1, element2, label1);
            toTestGraph.addEdge(element2,element3,label2);

            toTestGraph.deleteEdge(element1, element2);
            toTestGraph.deleteEdge(element2, element3);

            assertEquals(toTestGraph.getNodeMapping().get(element1).getEdgeReference().get(element2), null);
            assertEquals(toTestGraph.getNodeMapping().get(element2).getEdgeReference().get(element3), null);

        }

        @Test
        public void nodeSize() throws Exception {

            toTestGraph = new Graph<>(true, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);
            toTestGraph.addNode(element3);

            assertEquals(toTestGraph.graphNodeSize(),3);
        }

        @Test
        public void edgeSize() throws Exception {

            toTestGraph = new Graph<>(false, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);
            toTestGraph.addNode(element3);
            toTestGraph.addEdge(element1, element2, label1);
            toTestGraph.addEdge(element2,element3,label2);

            assertEquals(toTestGraph.graphEdgeSize(),2);

        }

        @Test
        public void getNodes() throws Exception {

            toTestGraph = new Graph<>(true, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);
            toTestGraph.addNode(element3);

            assertEquals(toTestGraph.getNodes().size(),3);


        }

        @Test
        public void getEdges() throws Exception {

            toTestGraph = new Graph<>(true, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);
            toTestGraph.addNode(element3);
            toTestGraph.addEdge(element1, element2, label1);
            toTestGraph.addEdge(element2,element3,label2);

            assertEquals(toTestGraph.getEdges().size(),2);

        }

        @Test
        public void adjacentNodes() throws Exception {

            toTestGraph = new Graph<>(false, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);
            toTestGraph.addNode(element3);
            toTestGraph.addEdge(element1, element2, label1);
            toTestGraph.addEdge(element2,element3,label2);

            assertEquals(toTestGraph.getAdjacentNodes(element2).size(), 2);
        }

        @Test
        public void getLabelOfEdge() throws Exception {

            toTestGraph = new Graph<>(false, Double.class,Node.ComparisonType.VALUE);
            toTestGraph.addNode(element1);
            toTestGraph.addNode(element2);
            toTestGraph.addNode(element3);
            toTestGraph.addEdge(element1, element2, label1);
            toTestGraph.addEdge(element2,element3,label2);

            assertEquals((Object) toTestGraph.getLabelOfEdge(element1,element2),(Object) label1);

        }


    }
