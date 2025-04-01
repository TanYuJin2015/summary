package heap;

import heap.exception.UnderflowException;

/**
 * 也叫自适应堆, 是一种自适应的左偏树（左式堆）, 优势是其合并的速度远远大于二叉堆
 * 最坏情形运行时间 --- O(N)
 * 对任意M次连续操作, 总的最坏情形时间是O(MlogN), 故：
 * 摊还开销 --- O(logN)
 */
public class SkewHeap<T extends Comparable<? super T>> {
    private Node<T> root;   // 根节点

    private static class Node<T> {      // 节点内部类
        T element;      // 节点元素
        Node<T> left;   // 左节点
        Node<T> right;  // 右节点
        Node(T theElement) {            // 构造器
            this(theElement, null, null);
        }
        Node(T theElement, Node<T> lt, Node<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }
    }

    public SkewHeap() {  // 构造斜堆
        root = null;
    }
    public SkewHeap(T[] items) {  // 构造斜堆
        buildHeap(items);
    }

    /**
     * 判断堆是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }
    /**
     * 清空堆
     */
    public void makeEmpty() {
        root = null;
    }
    /**
     * 插入一个新元素
     * @param x 欲插入的元素
     */
    public void insert(T x) {
        root = merge(new Node<T>(x), root);
    }
    /**
     * 寻找最小元
     */
    public T findMin() {
        if(isEmpty()) {
            throw new UnderflowException();
        }
        return root.element;
    }
    /**
     * 从堆中删除最小元
     */
    public T deleteMin() {
        if(isEmpty()) {
            throw new UnderflowException();
        }

        T minItem = root.element;
        root = merge(root.left, root.right);

        return minItem;
    }
    /**
     * 合并两个堆
     */
    public void merge(SkewHeap<T> rhs) {
        if(this == rhs) {   // 避免混叠问题
            return;
        }

        root = merge(root, rhs.root);
        rhs.root = null;
    }

    /**
     * 清除一些特殊情形并保证H1有较小的根
     */
    private Node<T> merge(Node<T> h1, Node<T> h2) {
        if(h1 == null) {
            return h2;
        } else if(h2 == null) {
            return h1;
        } else if(h1.element.compareTo(h2.element) < 0) {
            return merge1(h1, h2);
        } else {
            return merge1(h2, h1);
        }
    }
    /**
     * 实际的合并操作
     */
    private Node<T> merge1(Node<T> h1, Node<T> h2) {
        // Single node
        if(h1.left == null) {
            // Other fields in h1 already accurate
            h1.left = h2;
        } else {
            h1.right = merge(h1.right, h2);
            if(h1.left != null && h1.right != null) {
                swapChildren(h1);
            }
        }
        return h1;
    }
    /**
     * 交换传入节点t的左右子树
     * @param t 欲交换左右子树的父节点
     */
    private void swapChildren(Node<T> t) {
        Node<T> tmp = t.left;
        t.left = t.right;
        t.right = tmp;
    }
    /**
     * 构建堆
     * 时间复杂度 O(NlogN)
     */
    private void buildHeap(T[] items) {
        for(T item: items) {
            insert(item);
        }
    }
}
