package hashing.cuckoo;

import hashing.cuckoo.family.HashFamily;

public class CuckooHashTableClassic<T> {
    private static final double MAX_LOAD = 0.49;        // 表的最大负载（装填因子）
    private static final int ALLOWED_REHASHES = 100;    // 指定插入冲突时我们最多能执行多少次再散列
    private static final int DEFAULT_TABLE_SIZE = 101;  // 散列表初始化长度h

    private final HashFamily<? super T> hashFunctions;  // 散列函数集
    private final int numHashFunctions;                 // 散列函数的个数
    private T[] array;                                  // The array of elements
    private int currentSize;

    private int subTableSize;
    private int[] subTableStarts;

    private int rehashes = 0;       // 跟踪已经为这次插入尝试了多少次再散列

    /**
     * 构建散列表
     * @param hf 散列函数集
     */
    public CuckooHashTableClassic(HashFamily<? super T> hf) {
        this(hf, DEFAULT_TABLE_SIZE);
    }
    /**
     * 构建散列表
     * @param hf 散列函数集
     * @param size 散列表初始化长度
     */
    public CuckooHashTableClassic(HashFamily<? super T> hf, int size) {
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
        subTableSize = nextPrime( size / numHashFunctions );
        subTableStarts = new int[ numHashFunctions ];
        for( int i = 0; i < numHashFunctions; i++ ) {
            subTableStarts[i] = i * subTableSize;
        }
        allocateArray( subTableSize * numHashFunctions );
        doClear( );
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
     * 清空hash表
     */
    public void makeEmpty() {
        doClear();
    }
    /**
     * 判断是否包含传入元素
     * @param x
     * @return 包含返回true, 否则返回 false
     */
    public boolean contains(T x) {
        return findPos(x) != -1;
    }
    /**
     * 增加（插入）元素
     * @param x 欲增加（插入）的元素
     * @return 插入成功返回true, 否则（元素已存在于表中）返回 false
     */
    public boolean insert(T x) {
        final int COUNT_LIMIT = 100;

        if(contains(x)) {
            return false;
        }

        if(currentSize >= array.length * MAX_LOAD) {
            expand();
        }

        for( int i = 0, which = 0; i < COUNT_LIMIT; i++, which++ ) {
            if( which == numHashFunctions ) {
                which = 0;
            }

            int pos = myhash( x, which );

            if( array[ pos ] == null ) {
                array[ pos ] = x;
                currentSize++;
                return true;
            }

            T tmp = array[ pos ];
            array[ pos ] = x;
            x = tmp;
        }

        if( rehashes++ >= ALLOWED_REHASHES ) {
            expand( );  // 使表更大
            rehashes = 0;
        } else {
            rehash();   // 同样的表长, 但使用新的hash函数
        }
        return insert( x );
    }
    /**
     * 删除元素
     * @param x 欲删除的元素
     * @return 删除成功返回true, 否则（元素不存在于表中）返回 false
     */
    public boolean remove(T x) {
        int pos = findPos(x);

        if(pos != -1) {
            array[pos] = null;
            currentSize--;
        }

        return pos != -1;
    }

    /**
     * 散列函数
     * @param x 要进行hash计算的元素
     * @param which 指定执行散列函数集合中的哪一个特定的散列函数
     */
    protected int myhash( T x, int which ) {
        int hashVal = hashFunctions.hash( x, which );

        hashVal %= subTableSize;
        if( hashVal < 0 ) {
            hashVal += subTableSize;
        }

        return hashVal + subTableStarts[ which ];
    }

    /**
     * 找到元素并返回下标
     * @param x 要寻找的元素
     * @return 搜到的索引位置, 如果找不到返回 -1
     */
    private int findPos(T x) {
        for(int i = 0; i < numHashFunctions; i++) {
            int pos = myhash(x, i);
            if(array[pos] != null && array[pos].equals(x)) {
                return pos;
            }
        }
        return -1;
    }
    /**
     * 创建一个更大的数组, 但是保持用同样的散列函数
     */
    private void expand() {
        rehash( numHashFunctions * nextPrime( (int) ( array.length / MAX_LOAD  / numHashFunctions ) ) );
    }
    /**
     * 保持数组规模不变, 但创建一个新的数组, 用新选的散列函数去填充
     */
    private void rehash() {
        hashFunctions.generateNewFunctions( );
        rehash( array.length );
    }
    /**
     * @param newLength 新的散列表长度
     */
    private void rehash( int newLength )
    {
        T[ ] oldArray = array;

        if( newLength != array.length ) {
            subTableSize = newLength / numHashFunctions;
            for( int i = 0; i < numHashFunctions; i++ ) {
                subTableStarts[i] = i * subTableSize;
            }
        }

        allocateArray( newLength );

        currentSize = 0;

        // Copy table over
        for(T str: oldArray) {
            if (str != null) {
                insert(str);
            }
        }
    }

    /**
     * 分配数组
     * @param arraySize 长度
     */
    private void allocateArray(int arraySize) {
        array = (T[]) new Object[arraySize];
    }
    /**
     * 清空散列表
     */
    private void doClear() {
        currentSize = 0;
        for(int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }
    /**
     * 从n为开始寻找下一个素数
     * @param n
     */
    private static int nextPrime( int n ) {
        if (n % 2 == 0) {
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
