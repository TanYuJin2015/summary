package hashing;

public class QuadraticProbingHashTable<T> implements HashTable<T> {

    private static final int DEFAULT_TABLE_SIZE = 11;
    private HashEntry<T>[] array;
    private int currentSize;

    /**
     * 构建散列表
     */
    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }
    /**
     * 构建散列表
     * @param size 散列表初始化长度
     */
    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
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
     * 判断散列表是否为空（没有元素）
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }
    /**
     * 清空hash表
     */
    public void makeEmpty() {
        currentSize = 0;
        for(int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }
    /**
     * 判断是否包含传入元素
     * @param x
     * @return 包含返回true, 否则返回 false
     */
    public boolean contains(T x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }
    /**
     * 增加（插入）元素
     * @param x 欲增加（插入）的元素
     */
    public void insert(T x) {
        int currentPos = findPos(x);
        if(isActive(currentPos)) {
            return;
        }
        array[currentPos] = new HashEntry<T>(x, true);

        if(currentSize > array.length / 2) {
            rehash();
        }
    }
    /**
     * 删除元素
     * @param x 欲删除的元素
     */
    public void remove(T x) {
        int currentPos = findPos(x);
        if(isActive(currentPos)) {
            array[currentPos].isActive = false;
        }
    }

    // 内部类
    private static class HashEntry<T> {
        public T element;
        public boolean isActive;
        public HashEntry(T e) {
            this(e, true);
        }
        public HashEntry(T e, boolean i) {
            element = e;
            isActive = i;
        }
    }

    /**
     * 分配数组
     * @param arraySize 长度
     */
    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }
    /**
     * 如果currentPos存在且它active, 返回true
     * @param currentPos findPos()返回的结果
     * @return true 如果currentPos存在且它active
     */
    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }
    /**
     * 找到元素并返回下标, 运用平方探测法
     * @param x 要寻找的元素
     */
    private int findPos(T x) {
        int offset = 1;
        int currentPos = myhash(x);

        // 探测到空位 或者 探测到相同的值 就停止循环
        while(array[currentPos] != null && !array[currentPos].element.equals(x)) {
            // 平方探测法
            currentPos += offset;
            offset += 2;
            if(currentPos >= array.length) {
                currentPos -= array.length;
            }
        }
        return currentPos;
    }
    /**
     * 扩大散列表的大小
     */
    private void rehash() {
        HashEntry<T>[] oldArray = array;

        // 创建两倍长度的散列表
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        // 复制散列表
        for(int i = 0; i < oldArray.length; i++) {
            if(oldArray[i] != null && oldArray[i].isActive) {
                insert(oldArray[i].element);
            }
        }
    }
    /**
     * hash函数
     */
    private int myhash(T x) {
        int hashVal = x.hashCode();

        hashVal %= array.length;
        if(hashVal < 0) {
            hashVal += array.length;
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
