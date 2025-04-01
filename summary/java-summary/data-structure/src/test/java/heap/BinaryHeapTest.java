package heap;

import org.junit.Assert;
import org.junit.Test;

public class BinaryHeapTest {
    @Test
    public void testBinaryHeap() {
        Integer[] items = new Integer[]{150, 80, 40, 30, 10, 70, 110, 100, 20, 90, 60, 50, 120, 140, 130};
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(items);
        System.out.print("初始化：");
        binaryHeap.print();

        binaryHeap.insert(5);
        Assert.assertEquals(5, (int)binaryHeap.findMin());
        System.out.println();
        System.out.print("插入后：");
        binaryHeap.print();

        binaryHeap.deleteMin();
        Assert.assertEquals(10, (int)binaryHeap.findMin());
        System.out.println();
        System.out.print("删除最小元后：");
        binaryHeap.print();

        binaryHeap.delete(2);
        binaryHeap.delete(1);
        System.out.println();
        System.out.print("依次删除第 2个位置, 第1个位置节点的值后：");
        binaryHeap.print();
        Assert.assertEquals(30, (int)binaryHeap.findMin());

        binaryHeap.changeKey(4, -999);
        System.out.println();
        System.out.print("改变第4个位置节点的值：");
        binaryHeap.print();
        Assert.assertEquals(-999, (int)binaryHeap.findMin());
    }
}
