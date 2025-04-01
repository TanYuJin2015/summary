package heap;

import heap.exception.UnderflowException;
import org.junit.Assert;
import org.junit.Test;

public class SkewHeapTest {
    @Test(expected = UnderflowException.class)
    public void testSkewHeap() {
        Integer[] items1 = new Integer[]{3, 10, 8, 21, 14, 17, 23, 26};
        Integer[] items2 = new Integer[]{6, 12, 7, 18, 24, 37, 18, 33};

        SkewHeap<Integer> skewHeap1 = new SkewHeap(items1);
        SkewHeap<Integer> skewHeap2 = new SkewHeap(items2);

        Assert.assertEquals(3, (int)skewHeap1.deleteMin());
        Assert.assertEquals(6, (int)skewHeap2.deleteMin());

        Assert.assertEquals(8, (int)skewHeap1.findMin());
        Assert.assertEquals(7, (int)skewHeap2.findMin());

        skewHeap1.merge(skewHeap2);
        Assert.assertEquals(7, (int)skewHeap1.findMin());

        // There should be an UnderflowException..
        skewHeap2.deleteMin();
    }
}
