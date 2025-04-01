package hashing;

/**
 * 跳房子散列
 * @param <T>
 */
public class HopscotchHashTable<T> implements HashTable<T> {
    private HashItem<T>[] array;                          //散列表主体
    private static final int DEFAULT_TBALE_SIZE = 101;  //默认散列表大小
    private int currentSize;                            //元素个数
    private static final double MAX_LOAD = 0.5;         //装填因子
    private static final int MAX_DIST = 4;              //最大跳跃距离

    /**
     * 无参构造器
     */
    public HopscotchHashTable() {
        this(DEFAULT_TBALE_SIZE);
    }
    /**
     * 带参构造器
     * @param size 散列表大小
     */
    public HopscotchHashTable(int size) {
        array = new HashItem[size];
        // 初始化，值全为空，距离标志全为0
        for(int i = 0; i < array.length; i++) {
            array[i] = new HashItem<T>(null);
        }
        currentSize = 0;
    }

    // 内部类
    private static class HashItem <T> {
        private T value;    //值
        private int dist;   //距离标志
        public HashItem(T value) {
            this.value = value;
        }
        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }
        public int getDist() {
            return dist;
        }
        public void setDist(int dist) {
            this.dist = dist;
        }
    }

    /**
     * 打印测试方法
     */
    public void print() {
        T value = null;
        for(HashItem<T> item : array) {
            value = item.getValue();
            if(value != null) {
                System.out.print(value + " ");
            }
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
     * 判断散列表是否为空（没有元素）
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }
    /**
     * 清空hash表
     */
    public void makeEmpty() {
        for(int i = 0; i < array.length; i++) {
            array[i] = new HashItem<T>(null);
        }
        currentSize = 0;
    }
    /**
     * 判断存在性
     * @param value
     * @return
     */
    public boolean contains(T value) {
        return findPos(value) != -1;
    }
    /**
     * 插入例程
     * @param value 插入值
     */
    public void insert(T value) {
        // 如果元素数目达到装载极限
        if(currentSize >= (int)(array.length * MAX_LOAD)) {
            rehash(); // 再散列，即扩容
        }
        // 不断循环直至插入成功
        insertHelper(value);
    }
    /**
     * 删除例程
     * @param value
     */
    public void remove(T value) {
        int pos = findPos(value);
        int hash = myhash(value);
        if(pos != -1) {
            array[pos].setValue(null);;
            array[hash].setDist(array[hash].getDist() - (1 << (MAX_DIST - 1 - pos + hash)));
            currentSize--;
        }
    }

    /**
     * 寻找值所在散列位置，不存在返回-1;
     * @param value
     * @return
     */
    private int findPos(T value) {
        int hash = myhash(value);
        for(int i = 0; i < MAX_DIST; i++) {
            int dist = array[hash].getDist();
            if((dist >> i) % 2 == 1) {
                if(array[hash + MAX_DIST - 1 - i].getValue().equals(value)) {
                    return hash + MAX_DIST - 1 - i;
                }
            }
        }
        return -1;
    }
    /**
     * 插入例程的脏活累活
     * @param value
     */
    private void insertHelper(T value) {
        while(true) {
            // 获取散列位置
            int pos = myhash(value);
            // 保存最初散列值
            int temp = pos;
            // 循环以得到空位
            while(array[pos].getValue() != null) {
                pos++;
            }
            // 如果空位在距离内，直接插入并修改距离标志
            if(pos <= temp + MAX_DIST - 1) {
                array[pos].setValue(value);
                array[temp].setDist(array[temp].getDist() + (1 << (MAX_DIST - 1 - pos + temp)));    // 修改距离标志
                currentSize++;
                return;
            }
            // 如果不在距离内，调整位置直至符合距离要求
            while(true) {
                boolean isNotDist = false;  // 设置标志判断是否调整位置成功，便于二次循环的跳转
                // 散列位置从最远处开始
                for(int i = MAX_DIST - 1; i > 0; i--) {
                    // 距离标志从最高位开始
                    for(int j = MAX_DIST - 1; j > MAX_DIST - 1 - i; j--) {
                        // 如果距离标志位为1，则可以调整位置
                        if((array[pos - i].getDist() >> j) % 2 == 1) {
                            HashItem<T> item = array[pos - i + MAX_DIST - 1 - j]; // 获得需要被调整的散列位置
                            array[pos].setValue(item.getValue());
                            item.setDist(item.getDist() - (1 << j) + 1);    // 修改被调整值的距离标志
                            pos = pos - i + MAX_DIST - 1 - j;
                            // 如果在距离内，直接插入并修改距离标志
                            if(pos <= temp + 3) {
                                array[pos].setValue(value);
                                array[temp].setDist(array[temp].getDist() + (1 << (MAX_DIST - 1 - pos + temp)));// 修改距离标志
                                currentSize++;
                                return;
                            } else {  // 如果不在距离标志内
                                isNotDist = true;
                                break;
                            }
                        }
                    }
                    if(isNotDist) {
                        break;
                    }
                }
                // 如果无法调整位置
                if(!isNotDist) {
                    break;
                }
            }
            // 再散列，重新开始插入
            rehash();
        }
    }
    /**
     * 再散列
     */
    private void rehash() {
        HashItem<T>[] oldArr = array;
        array = new HashItem[(int)(array.length / MAX_LOAD)];
        for(int i = 0; i < array.length; i++) {
            array[i] = new HashItem<T>(null);
        }
        currentSize = 0;
        for(HashItem<T> item : oldArr) {
            if(item.getValue() != null) {
                insert(item.getValue());
            }
        }
    }
    /**
     * 散列函数
     * @param value
     * @return
     */
    private int myhash(T value) {
        int hash = value.hashCode();
        hash %= array.length;
        if(hash < 0) {
            hash += array.length;
        }
        return hash;
    }
}  