package hashing.cuckoo;

import hashing.cuckoo.family.HashFamily;

import java.util.Random;

public class CuckooHashTable<T> {
    private static final double MAX_LOAD = 0.4;         // 表的最大负载（装填因子）
    private static final int ALLOWED_REHASHES = 1;      // 指定插入冲突时我们最多能执行多少次再散列
    private static final int DEFAULT_TABLE_SIZE = 101;  // 散列表初始化长度h

    private final HashFamily<? super T> hashFunctions;  // 散列函数集
    private final int numHashFunctions;                 // 散列函数的个数
    private T[] array;
    private int currentSize;

    private int rehashes = 0;       // 跟踪已经为这次插入尝试了多少次再散列
    private Random r = new Random();

    /**
     * 构建散列表
     * @param hf 散列函数集
     */
    public CuckooHashTable(HashFamily<? super T> hf) {
        this(hf, DEFAULT_TABLE_SIZE);
    }
    /**
     * 构建散列表
     * @param hf 散列函数集
     * @param size 散列表初始化长度
     */
    public CuckooHashTable(HashFamily<? super T> hf, int size) {
        allocateArray(nextPrime(size));                 // 分配散列表大小
        doClear();
        hashFunctions = hf;                             // 实例化散列函数集接口
        numHashFunctions = hf.getNumberOfFunctions();   // 散列函数个数
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
        if(contains(x)) {
            return false;
        }

        if(currentSize >= array.length * MAX_LOAD) {
            expand();
        }

        return insertHelper1(x);
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
    private int myhash(T x, int which) {
        int hashVal = hashFunctions.hash(x, which);

        hashVal %= array.length;
        if(hashVal < 0) {
            hashVal += array.length;
        }

        return hashVal;
    }
    /**
     * 找到元素并返回下标, 运用平方探测法
     * @param x 要寻找的元素
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
     * 插入算法逻辑
     * @param x 欲插入的元素
     * @return 插入成功返回true, 否则（元素已存在于表中）返回 false
     */
    private boolean insertHelper1(T x) {
        final int COUNT_LIMIT = 100;

        while(true) {
            int lastPos = -1;
            int pos;

            for(int count = 0; count < COUNT_LIMIT; count++) {
                for(int i = 0; i < numHashFunctions; i++) {
                    pos = myhash(x, i);

                    if(array[pos] == null) {
                        array[pos] = x;
                        currentSize++;
                        return true;
                    }
                }

                // 所有散列函数均无空位时, 选择一个随机的位置
                int i = 0;
                do {
                    pos = myhash(x, r.nextInt(numHashFunctions));
                } while(pos == lastPos && i++ < 5); // 当前散列的位置与上一次相同超过五次时, 终止循环

                T tmp = array[lastPos = pos];
                array[pos] = x;
                x = tmp;
            }


            if(++rehashes > ALLOWED_REHASHES) {
                expand();   // 使表更大
                rehashes = 0;
            } else {
                rehash();   // 同样的表长, 但使用新的hash函数
            }
        }
    }
    private boolean insertHelper2(T x) {
        final int COUNT_LIMIT = 100;

        while( true ) {
            for( int count = 0; count < COUNT_LIMIT; count++ ) {
                int pos = myhash( x, count % numHashFunctions );

                T tmp = array[ pos ];
                array[ pos ] = x;

                if( tmp == null ) {
                    return true;
                } else {
                    x = tmp;
                }
            }

            if( ++rehashes > ALLOWED_REHASHES ) {
                expand( );
                rehashes = 0;
            } else {
                rehash( );
            }
        }
    }
    /**
     * 创建一个更大的数组, 但是保持用同样的散列函数
     */
    private void expand() {
        rehash((int)(array.length / MAX_LOAD));
    }
    /**
     * 保持数组规模不变, 但创建一个新的数组, 用新选的散列函数去填充
     */
    private void rehash() {
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }
    /**
     * @param newLength 新的散列表长度
     */
    private void rehash(int newLength) {
        T[] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;

        for(T str: oldArray) {
            if(str != null) {
                insert(str);
            }
        }
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
     * 分配数组
     * @param arraySize 长度
     */
    private void allocateArray(int arraySize) {
        array = (T[]) new Object[arraySize];
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
