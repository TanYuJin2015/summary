package heap;

import heap.exception.UnderflowException;

public class BinomialQueue<T extends Comparable<? super T>> {
    // 默认初始化分配两个节点空间
    private static final int DEFAULT_TREES = 1;

    // 节点数组
    private Node<T>[] theTrees;
    private int currentSize;

    /**
     * 构建一个空的二项队列
     */
    public BinomialQueue() {
        theTrees = new Node[DEFAULT_TREES];
        makeEmpty();
    }
    /**
     * 构建一个二项队列并插入item
     * @param item 节点元素
     */
    public BinomialQueue(T item) {
        currentSize = 1;
        theTrees = new Node[1];
        theTrees[ 0 ] = new Node<>( item, null, null );
    }

    private static class Node<T> {
        T element;
        Node<T> leftChild;
        Node<T> nextSibling;

        Node(T theElement) {
            this(theElement, null, null);
        }
        Node(T theElement, Node<T> lt, Node<T> nt) {
            element = theElement;
            leftChild = lt;
            nextSibling = nt;
        }

        boolean hasLeftChild() {
            return this.leftChild != null;
        }

        boolean hasNextSibling() {
            return this.nextSibling != null;
        }
    }

    /**
     * 遍历二项队列
     */
    public void print() {
        for(int i = 0; i < theTrees.length; i++) {
            if(theTrees[i] == null) {
                continue;
            } else {
                // 输出当前节点元素
                print(theTrees[i]);
                System.out.println();
            }
        }
    }
    /**
     * 获取二项队列中的元素总个数
     * @return 返回二项队列中的元素总个数
     */
    public int size() {
        return currentSize;
    }
    /**
     * 判断二项队列是否为空
     * @return 空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }
    /**
     * 清空二项队列
     */
    public void makeEmpty() {
        currentSize = 0;
        for(int i = 0; i < theTrees.length; i++) {
            theTrees[i] = null;
        }
    }
    /**
     * 插入新元素
     * @param x
     */
    public void insert(T x) {
        merge(new BinomialQueue<T>(x));
    }
    /**
     * 合并二项树
     */
    public void merge(BinomialQueue<T> rhs) {
        if(rhs == this) {   // 避免混叠问题
            return;
        }

        currentSize += rhs.currentSize;

        if(currentSize > capacity()) {
            int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
            expandTheTrees(maxLength + 1);
        }

        Node<T> carry = null;
        for(int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
            Node<T> t1 = theTrees[i];
            Node<T> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch( whichCase ) {
                case 0: /* No trees */
                case 1: /* Only this */
                    break;
                case 2: /* Only rhs */
                    theTrees[ i ] = t2;
                    rhs.theTrees[ i ] = null;
                    break;
                case 4: /* Only carry */
                    theTrees[ i ] = carry;
                    carry = null;
                    break;
                case 3: /* this and rhs */
                    carry = combineTrees( t1, t2 );
                    theTrees[ i ] = rhs.theTrees[ i ] = null;
                    break;
                case 5: /* this and carry */
                    carry = combineTrees( t1, carry );
                    theTrees[ i ] = null;
                    break;
                case 6: /* rhs and carry */
                    carry = combineTrees( t2, carry );
                    rhs.theTrees[ i ] = null;
                    break;
                case 7: /* All three */
                    theTrees[ i ] = carry;
                    carry = combineTrees( t1, t2 );
                    rhs.theTrees[ i ] = null;
                    break;
            }
        }
    }
    /**
     * 获取最小元并返回
     * @return 返回最小元
     */
    public T findMin() {
        if(isEmpty()) {
            throw new UnderflowException();
        }

        return theTrees[ findMinIndex() ].element;
    }
    /**
     * 删除最小元并返回
     * @return 返回最小元
     */
    public T deleteMin() {
        if(isEmpty()) {
            throw new UnderflowException();
        }

        int minIndex = findMinIndex();
        T minItem = theTrees[minIndex].element;

        Node<T> deletedTree = theTrees[minIndex].leftChild;

        // 创建一个二项队列 H '' , 其空间规模为 minIndex + 1
        BinomialQueue<T> deletedQueue = new BinomialQueue<T>();
        deletedQueue.expandTheTrees(minIndex + 1);

        // 2^minIndex - 1
        deletedQueue.currentSize = (1 << minIndex) - 1;
        for(int j = minIndex - 1; j >= 0; j--) {
            // 逐个依次断开nextSibling
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }

        // 构建 H '
        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge(deletedQueue);

        return minItem;
    }

    /**
     * 遍历二项队列
     */
    private void print(Node<T> t) {
        if(t.hasNextSibling()) {
            print(t.nextSibling);
        }
        System.out.print(t.element + " ");
        if(t.hasLeftChild()) {
            print(t.leftChild);
        }
    }
    /**
     * 改变数组规模
     * @param newNumTrees 新的数组大小
     */
    private void expandTheTrees(int newNumTrees) {
        Node<T>[] old = theTrees;           // 原二项队列数组
        int oldNumTrees = theTrees.length;  // 原二项队列数组的长度

        theTrees = new Node[ newNumTrees ];

        for( int i = 0; i < Math.min( oldNumTrees, newNumTrees ); i++ ) {
            theTrees[i] = old[i];
        }
        for( int i = oldNumTrees; i < newNumTrees; i++ ) {
            theTrees[i] = null;
        }
    }
    /**
     * 合并两个相同高度的二项树的逻辑代码, 改变两个根节点的链的指向
     * @param t1
     * @param t2
     */
    private Node<T> combineTrees(Node<T> t1, Node<T> t2) {
        if( t1.element.compareTo(t2.element) > 0 ) {
            return combineTrees(t2, t1);
        }
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }
    /**
     * 获取二项队列数组的容量
     * @return 返回二项队列数组的容量
     */
    private int capacity() {
        return (1 << theTrees.length) - 1;
    }
    /**
     * 寻找最小元所在的二项树在数组中的位置
     * @return 返回最小元所在的树在数组中的索引
     */
    private int findMinIndex() {
        int i;
        int minIndex;

        // 跳过空的位置
        for( i = 0; theTrees[ i ] == null; i++ )
            ;

        for( minIndex = i; i < theTrees.length; i++ ) {
            if (theTrees[i] != null &&
                    theTrees[i].element.compareTo(theTrees[minIndex].element) < 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
