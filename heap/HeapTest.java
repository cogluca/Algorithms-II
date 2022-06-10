import datastructure.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeapTest {


    private Heap<Integer> toTestHeap;
    private Integer element1, element2, element3, element4;


    @Before
    public void initialize() {

        toTestHeap = new Heap<>();
        element1 = 5;
        element2 = 3;
        element3 = 8;
        element4 = 1;

    }

    @Test
    public void testHeapInsertion() {

        toTestHeap.addElement(5);
        toTestHeap.addElement(1);
        toTestHeap.addElement(8);
        toTestHeap.addElement(4);
        toTestHeap.addElement(9);
        toTestHeap.addElement(12);
        toTestHeap.addElement(3);
        try {
            Integer testMin = toTestHeap.extractMin();
            assertEquals((Integer) 1, testMin);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testExtractMinReordering() {

        toTestHeap.addElement(5);
        toTestHeap.addElement(1);
        toTestHeap.addElement(8);
        toTestHeap.addElement(4);


        try {

            Integer testMin = toTestHeap.extractMin();

            //Testing reordering
            Integer secondMin = toTestHeap.extractMin();
            assertEquals((Integer) 4, secondMin);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*
        Heap<Node<Integer, Integer>,Integer> particularHeapToTest = new Heap<>();
        Node<Integer, Integer> nodeOne = new Node<>(5,Integer.class);
        Node<Integer, Integer> nodeTwo = new Node<>(7,Integer.class);
        Node<Integer, Integer> nodeThree = new Node<>(2,Integer.class);
        Node<Integer, Integer> nodeFour = new Node<>(3,Integer.class);
        Node<Integer, Integer> nodeFive = new Node<>(4,Integer.class);




        particularHeapToTest.addElement(nodeOne);
        particularHeapToTest.addElement(nodeTwo);
        particularHeapToTest.addElement(nodeThree);
        particularHeapToTest.addElement(nodeFour);particularHeapToTest.addElement(nodeFive);




         */


    }

    @Test
    public void testDiminishingValue() throws Exception {

        Heap<Node<Integer, Integer>> particularHeapToTest = new Heap<>();
        Node<Integer, Integer> nodeOne = new Node<>(5, Integer.class, Node.ComparisonType.VALUE);
        Node<Integer, Integer> nodeTwo = new Node<>(7, Integer.class, Node.ComparisonType.VALUE);
        Node<Integer, Integer> nodeThree = new Node<>(2, Integer.class, Node.ComparisonType.VALUE);
        Node<Integer, Integer> nodeFour = new Node<>(6, Integer.class, Node.ComparisonType.VALUE);
        Node<Integer, Integer> nodeFive = new Node<>(4, Integer.class, Node.ComparisonType.VALUE);


        particularHeapToTest.addElement(nodeOne);
        particularHeapToTest.addElement(nodeTwo);
        particularHeapToTest.addElement(nodeThree);
        particularHeapToTest.addElement(nodeFour);
        particularHeapToTest.addElement(nodeFive);


        Integer toRetrieve = particularHeapToTest.getKeyMap().get(nodeTwo);
        Node<Integer,Integer> node = particularHeapToTest.getVector().get(toRetrieve);
        node.setValue(3);

        particularHeapToTest.diminishElement(particularHeapToTest.getVector().get(toRetrieve), node);

        Integer toRetrieve2 = particularHeapToTest.getKeyMap().get(nodeTwo);

        Node<Integer, Integer> testedNode = particularHeapToTest.getVector().get(toRetrieve2);




        assertEquals((Integer) 1, particularHeapToTest.getKeyMap().get(node) );


    }


    @Test
    public void maxRotation() throws Exception {
        Heap<Integer> particularHeap = new Heap<>();
        Heap<Integer> resultHeap = new Heap<>();
        System.out.println("MAX ROTATION STARTS HERE!!!!");
        int i = 900;
        for(i = 25; i >= 0 ; i--){
            particularHeap.addElement(i);
        }

        for(Integer extracted: particularHeap.getVector()){
            System.out.println(extracted);
        }




        /*
        for(int j = 0; j <= 900; j++){
            resultHeap.addElement(j);
        }


         */

        System.out.println("Extraction starts here");
        while (particularHeap.getSize() != 0){
            Integer extracted = particularHeap.extractMin();
            System.out.println(extracted);
        }



        //assertEquals(resultHeap, toTestHeap);

    }


}
