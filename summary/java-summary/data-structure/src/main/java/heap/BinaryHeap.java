package heap;

import heap.exception.UnderflowException;

/**
 * insert(x) --- 插入 --- 平均时间 O(1) --- 最坏情形时间 O(logN)
 * findMin() --- 获取最小元 --- O(1)
 * deleteMin() --- 删除最小元 --- 平均时间 O(logN) --- 最坏情形时间 O(logN)
 * buildHeap() --- 构建堆 --- 平均时间 O(N) --- 最坏情形时间 O(NlogN)
 * changeKey(p, delta) --- 改变关键字的值
 * delete(p) --- 删除
 */
public class BinaryHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 10; // 二叉堆的初始化大小
    private int currentSize;    // 二叉堆的总元素个数
    private T[] array;          // 二叉堆数组

    /**
     * 构建二叉堆
     */
    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }
    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (T[]) new Comparable[capacity + 1];
    }
    public BinaryHeap(T[] items) {
        currentSize = items.length;

        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for( T item : items ) {
            array[i++] = item;
        }
        buildHeap( );
    }

    /**
     * 按顺序遍历完全二叉树
     */
    public void print() {
        for(int i = 1; i <= currentSize; i++) {
            System.out.print(array[i] + " ");
        }
    }
    /**
     * 获取散列表中的元素总个数
     * @return 散列表中的元素总个数
     */
    public int size() {
        return currentSize;
    }
    /**
     * 获取当前散列表的长度
     * @return 当前散列表的长度
     */
    public int capacity( ) {
        return array.length;
    }
    /**
     * 判断优先队列是否为空
     * @return 空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }
    /**
     * 使优先队列在逻辑上变为空
     */
    public void makeEmpty() {
        currentSize = 0;
    }
    /**
     * 将一个元素x插入到堆中
     * 平均时间：O(1), 最坏情形时间：O(logN)
     * @param x 欲插入的元素
     */
    public void insert(T x) {
        if(currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }

        // 将元素插入到最后一个位置
        int hole = ++currentSize;
        array[hole] = x;

        // 上滤
        percolateUp(hole);
    }
    /**
     * 寻找最小元
     * @return 返回最小元, 若队列为空时抛出UnderflowException()
     */
    public T findMin() {
        if(isEmpty()) {
            throw new UnderflowException();
        }
        return array[1];
    }
    /**
     * 从优先队列中删除最小元
     * @return 返回最小元, 若队列为空时抛出UnderflowException()
     */
    public T deleteMin() {
        if(isEmpty()) {
            throw new UnderflowException();
        }
        array[1] = array[currentSize--];    // 将最后一个元素放入根节点的空穴
        percolateDown(1);

        return findMin();   // 返回最小元（根节点元素）
    }
    /**
     * 改变关键字的值
     * @param p 位置坐标
     * @param value 不等于null, 则给位置p的关键字赋予对应值;等于null, 则直接返回
     */
    public void changeKey(int p, T value) {
        if(p < 1 && p > currentSize) {
            throw new UnderflowException();
        }
        if(value == null) {
            return;
        }
        if(array[p].compareTo(value) > 0) {
            array[p] = value;
            percolateUp(p);
        } else {
            array[p] = value;
            percolateDown(p);
        }
    }
    /**
     * 删除指定元素
     * @param p 位置坐标
     */
    public void delete(int p) {
        changeKey(p, findMin());
        deleteMin();
    }

    /**
     * 上滤
     * 在 insert() 和 decreaseKey(p, delta) 操作中调用
     */
    private void percolateUp(int hole) {
        T tmp = array[hole];

        for(array[0] = tmp; tmp.compareTo(array[hole/2]) < 0; hole /= 2) {
            // 如果元素 < 其父节点, 则父节点下移, 元素上滤
            array[hole] = array[hole/2];
        }
        array[hole] = tmp;
    }
    /**
     * 下滤
     * 在 deleteMin() 和 increaseKey(p, delta) 操作中调用
     */
    private void percolateDown(int hole) {
        int child;
        // 记录需要下滤的元素为tmp
        T tmp = array[hole];

        for(; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;

            // 比较左右儿子
            if(child != currentSize && array[child + 1].compareTo(array[child]) < 0) {
                // 如果右儿子 < 左儿子, 则选择右儿子
                child++;
            }

            // 比较下滤元素和其最小的儿子节点, 若下滤元素 > 其最小的儿子节点, 则将该儿子节点上移
            if(array[child].compareTo((tmp)) < 0) {
                array[hole] = array[child];
            } else {
               break;
            }
        }

        array[hole] = tmp;
    }
    /**
     * 构建堆
     * 平均时间：O(N), 最坏情形时间：O(NlogN)
     */
    private void buildHeap() {
        for(int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }
    /**
     * 扩容
     * @param newSize 新的队列大小
     */
    private void enlargeArray(int newSize) {
        T[] old = array;
        array = (T[]) new Comparable[newSize];

        for( int i = 0; i < old.length; i++ ) {
            array[i] = old[i];
        }
    }
}
