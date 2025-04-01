package heap;

import org.junit.Assert;
import org.junit.Test;

public class BinomialQueueTest {
    @Test
    public void testBinomialQueue() {
        BinomialQueue<Integer> binomialQueue = new BinomialQueue<Integer>(1);
        binomialQueue.insert(2);
        binomialQueue.insert(3);
        binomialQueue.insert(4);
        binomialQueue.insert(5);
        binomialQueue.insert(6);
        binomialQueue.insert(7);
        binomialQueue.insert(8);

        binomialQueue.print();

        Assert.assertEquals(1, (int)binomialQueue.findMin());


    }
}
