import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class HeapTest {


    private Heap<Integer> toTestHeap;
    private Integer element1, element2, element3, element4;


    @Before
    public void initialize() {

        toTestHeap = new Heap<>(Integer.class);
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





    }


}
