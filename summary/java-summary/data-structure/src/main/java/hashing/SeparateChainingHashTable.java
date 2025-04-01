package hashing;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable<T> implements HashTable<T> {
    private static final int DEFAULT_TABLE_SIZE = 10;   // 散列表的大小, 该值最好为素数
    private List<T>[] theLists;     // 链表数组, 数组下标为散列表的关键字 Key
    private int currentSize;        // 节点总个数

    /**
     * 构建散列表
     */
    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }
    /**
     * 构建散列表
     * @param size 散列表初始化长度
     */
    public SeparateChainingHashTable(int size) {
        // 初始化链表数组, 若传入的size不是素数, 自动从size开始寻找下一个素数作为表长
        theLists = new LinkedList[nextPrime(size)];
        for(int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
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
        return theLists.length;
    }
    /**
     * 判断散列表是否为空（没有元素）
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }
    /**
     * 清空hash表
     */
    public void makeEmpty() {
        for(int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }
    /**
     * 判断是否包含传入元素
     * @param x
     * @return 包含返回true, 否则返回 false
     */
    public boolean contains(T x) {
        List<T> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }
    /**
     * 增加（插入）元素
     * @param x 欲增加（插入）的元素
     */
    public void insert(T x) {
        List<T> whichList = theLists[myhash(x)];
        if(!whichList.contains(x)) {
            whichList.add(x);

            // Rehash
            if(++currentSize > theLists.length) {
                rehash();
            }
        }
    }
    /**
     * 删除元素
     * @param x 欲删除的元素
     */
    public void remove(T x) {
        List<T> whichList = theLists[myhash(x)];
        if(whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    /**
     * 扩大散列表的大小
     */
    private void rehash() {
        List<T>[] oldLists = theLists;

        theLists = new List[nextPrime(2 * theLists.length)];    // 创建两倍长度的散列表
        for(int j = 0; j < theLists.length; j++) {
            theLists[j] = new LinkedList<>();
        }

        // 复制散列表
        currentSize = 0;
        for(int i = 0;i < oldLists.length; i++) {
            for(T item: oldLists[i]) {
                insert(item);
            }
        }
    }
    /**
     * hash函数
     * @param x 要进行hash计算的元素
     */
    private int myhash(T x) {
        int hashVal = x.hashCode();

        hashVal %= theLists.length;
        if(hashVal < 0) {
            hashVal += theLists.length;
        }

        return hashVal;
    }
    /**
     * 从n为开始寻找下一个素数
     * @param n
     */
    private static int nextPrime( int n ) {
        if( n % 2 == 0 ) {
            n++;
        }
        for( ; !isPrime( n ); n += 2 ); // 从奇数中找到最近的一个素数

        return n;
    }
    /**
     * 判断n是否为素数
     * @param n
     */
    private static boolean isPrime( int n ) {
        if( n == 2 || n == 3 ) {
            return true;
        }
        if( n == 1 || n % 2 == 0 ) {
            return false;
        }
        for( int i = 3; i * i <= n; i += 2 ) {  // 到sqrt(n)止
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
