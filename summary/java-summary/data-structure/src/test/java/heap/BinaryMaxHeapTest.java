package heap;

import org.junit.Assert;
import org.junit.Test;

public class BinaryMaxHeapTest {
    @Test
    public void testBinaryMaxHeap() {
        Integer[] items = new Integer[]{150, 80, 40, 30, 10, 70, 110, 100, 20, 90, 60, 50, 120, 140, 130};
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>(items);
        System.out.print("初始化：");
        heap.print();

        heap.insert(500);
        Assert.assertEquals(500, (int)heap.findMax());
        System.out.println();
        System.out.print("插入后：");
        heap.print();

        heap.deleteMax();
        Assert.assertEquals(150, (int)heap.findMax());
        System.out.println();
        System.out.print("删除最大元后：");
        heap.print();

        heap.delete(2);
        heap.delete(1);
        System.out.println();
        System.out.print("依次删除第 2个位置, 第1个位置节点的值后：");
        heap.print();
        Assert.assertEquals(140, (int)heap.findMax());

        heap.changeKey(4, 999);
        System.out.println();
        System.out.print("改变第4个位置节点的值：");
        heap.print();
        Assert.assertEquals(999, (int)heap.findMax());
    }
}
