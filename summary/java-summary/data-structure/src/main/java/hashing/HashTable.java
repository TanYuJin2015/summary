package hashing;

public interface HashTable<T> {
    /**
     * 获取散列表中的元素总个数
     * @return 散列表中的元素总个数
     */
    int size();
    /**
     * 获取当前散列表的长度
     * @return 当前散列表的长度
     */
    int capacity();
    /**
     * 清空hash表
     */
    void makeEmpty();
    /**
     * 判断散列表是否为空（没有元素）
     */
    boolean isEmpty();
    /**
     * 判断存在性
     * @param value
     * @return
     */
    boolean contains(T value);
    /**
     * 插入例程
     * @param value 插入值
     */
    void insert(T value);
    /**
     * 删除例程
     * @param value
     */
    void remove(T value);
}
