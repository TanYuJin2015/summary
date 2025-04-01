package heap;

import heap.exception.UnderflowException;
import org.junit.Assert;
import org.junit.Test;

public class LeftistHeapTest {
    @Test(expected = UnderflowException.class)
    public void testLeftistHeap() {
        Integer[] items1 = new Integer[]{3, 10, 8, 21, 14, 17, 23, 26};
        Integer[] items2 = new Integer[]{6, 12, 7, 18, 24, 37, 18, 33};

        LeftistHeap<Integer> leftistHeap1 = new LeftistHeap(items1);
        LeftistHeap<Integer> leftistHeap2 = new LeftistHeap(items2);

        Assert.assertEquals(3, (int)leftistHeap1.deleteMin());
        Assert.assertEquals(6, (int)leftistHeap2.deleteMin());

        Assert.assertEquals(8, (int)leftistHeap1.findMin());
        Assert.assertEquals(7, (int)leftistHeap2.findMin());

        leftistHeap1.merge(leftistHeap2);
        Assert.assertEquals(7, (int)leftistHeap1.findMin());

        // There should be an UnderflowException..
        leftistHeap2.deleteMin();
    }
}
