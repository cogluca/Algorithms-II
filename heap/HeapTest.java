import datastructure.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class HeapTest {


    private Heap<Integer, Integer> toTestHeap;
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

        Integer testMin = toTestHeap.extractMin();
        assertEquals((Integer) 1, testMin);

    }

    @Test
    public void testExtractMinReordering() {

        toTestHeap.addElement(5);
        toTestHeap.addElement(1);
        toTestHeap.addElement(8);
        toTestHeap.addElement(4);



        Integer testMin = toTestHeap.extractMin();
        //Testing reordering
        Integer secondMin = toTestHeap.extractMin();
        assertEquals((Integer) 4, secondMin);


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
    public void testDiminishingValue() {

        Heap<Node<Integer, Integer>,Integer> particularHeapToTest = new Heap<>();
        Node<Integer, Integer> nodeOne = new Node<>(5,Integer.class, Node.ComparisonType.VALUE);
        Node<Integer, Integer> nodeTwo = new Node<>(7,Integer.class, Node.ComparisonType.VALUE);
        Node<Integer, Integer> nodeThree = new Node<>(2,Integer.class, Node.ComparisonType.VALUE);
        Node<Integer, Integer> nodeFour = new Node<>(3,Integer.class, Node.ComparisonType.VALUE);
        Node<Integer, Integer> nodeFive = new Node<>(4,Integer.class, Node.ComparisonType.VALUE);




        particularHeapToTest.addElement(nodeOne);
        particularHeapToTest.addElement(nodeTwo);
        particularHeapToTest.addElement(nodeThree);
        particularHeapToTest.addElement(nodeFour);particularHeapToTest.addElement(nodeFive);


        Integer toRetrieve = particularHeapToTest.getKeyMap().get(nodeTwo);

        particularHeapToTest.diminishElementValue(particularHeapToTest.getVector().get(toRetrieve), (Integer)3,null);

        Integer toRetrieve2 = particularHeapToTest.getKeyMap().get(nodeTwo);

        Node<Integer, Integer> testedNode = particularHeapToTest.getVector().get(toRetrieve2);







    }


}
