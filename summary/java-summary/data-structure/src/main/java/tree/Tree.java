package tree;

import java.util.Collection;

public interface Tree<T> {
    /**
     * 清空树
     */
    void makeEmpty();
    /**
     * 判断树是否为空
     */
    boolean isEmpty();
    /**
     * 是否含有项x的节点
     * @param x
     */
    boolean contains(T x);
    /**
     * 查找项最小的节点
     */
    T findMin();
    /**
     * 查找项最大的节点
     */
    T findMax();
    /**
     * 增加（插入）节点
     * @param x
     */
    void insert(T x);
    /**
     * 删除节点
     * @param x
     */
    void remove(T x);
    /**
     * 将集合转为树
     * @param collection
     */
    void insertCollection(Collection<T> collection);
    /**
     * 打印树
     */
    void printTree();
    /**
     * 先序遍历
     */
    void DLR();
    /**
     * 中序遍历
     */
    void LDR();
    /**
     * 后序遍历
     */
    void LRD();
}
