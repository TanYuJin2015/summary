package tree;

import java.util.Collection;
import java.util.Comparator;

/**
 * 二叉查找/排序树（BinarySearchTree）
 * @author 谈裕锦
 * 时间复杂度：
 * 索引：O(logN)
 * 插入：O(logN)
 * 查找：O(logN)
 * 删除：O(logN)
 * 存在问题：交替插入和删除O(N^2)次，右沉的树将明显地不平衡
 * 解决方法；找到一种使树不管插入删除多少次都能保持平衡的算法
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Tree<T> {
	private BinaryNode<T> root;			// 根节点
	private Comparator<? super T> cmp;	// 自定义外部比较器
	
	public BinarySearchTree() {
		this(null);
	}
	public BinarySearchTree(Comparator<? super T> cmp) {
		this.root = null;
		this.cmp = cmp;
	}

	private static class BinaryNode<T> {	// 节点内部类
		T element;
		BinaryNode<T> left;
		BinaryNode<T> right;		
		
		BinaryNode(T element) {
			this(element, null, null);
		}
		BinaryNode(T element, BinaryNode<T> lt, BinaryNode<T> rt) {
			this.element = element;
			this.left = lt;
			this.right = rt;
		}
	}
	
	// 清空树
	public void makeEmpty() {
		this.root = null;
	}
	// 判断树是否为空
	public boolean isEmpty() {
		return this.root == null;
	}
	// 是否含有项x的节点
	public boolean contains(T x) {
		return contains(x, this.root);
	}
	// 查找项最小的节点
	public T findMin() {
		if(isEmpty()) {
			throw new RuntimeException();
		}
		return findMin(this.root).element;
	}
	// 查找项最大的节点
	public T findMax() {		
		if(isEmpty()) {
			throw new RuntimeException();
		}
		return findMax(this.root).element;
	}
	// 增加（插入）节点
	public void insert(T x) {
		this.root = insert(x, this.root);
	}
	// 删除节点
	public void remove(T x) {
		this.root = remove(x, this.root);
	}
	// 将集合转为树
	public void insertCollection(Collection<T> collection) {
		for(T x : collection) {
			this.insert(x);
		}
	}
	// 打印树
	public void printTree() {
		if(isEmpty()) {
			System.out.println("Empty tree");
		} else {
			printTree(root);
		}
	}
	// 先序遍历
	public void DLR() {

	}
	// 中序遍历
	public void LDR() {

	}
	// 后序遍历
	public void LRD() {

	}

	/**
	 * 是否含有项x的节点
	 * @param x 欲比较的项
	 * @param element 节点的项
	 * @return
	 */
	private int myCompare(T x, T element) {
		if(this.cmp == null) {			
			return ((Comparable<? super T>)x).compareTo(element);
		} else {			
			return cmp.compare(x, element);
		}
	}
	/**
	 * 打印树
	 * @param t 节点
	 */
	private int height(BinaryNode<T> t) {
		if(t == null) {
			return -1;
		} else {
			return 1 + Math.max(height(t.left), height(t.right));
		}
	}
	private void printTree(BinaryNode<T> t) {
		if(t != null) {
			printTree(t.left);
			System.out.print(t.element + "\t");
			printTree(t.right);
		}
	}
	/**
	 * 是否含有项x的节点
	 * @param x 节点的项
	 * @param t 节点
	 * @return boolean 若存在含有项x的节点，则返回true, 反之返回false
	 */
	private boolean contains(T x, BinaryNode<T> t) {
		if(t == null) {
			return false;
		}
		
		int compareResult = myCompare(x, t.element);
		
		if(compareResult < 0) {
			return contains(x, t.left);
		} else if(compareResult > 0) {
			return contains(x, t.right);
		} else {
			return true;
		}		
	}
	/**
	 * 查找值最小的节点
	 * @param t 节点
	 * @return BinaryNode 项最小的节点
	 */
	private BinaryNode<T> findMin(BinaryNode<T> t) {
		if(t == null) {
			return t;
		} else if(t.left == null) {
			return t;
		} else {
			return findMin(t.left);
		}
	}
	/**
	 * 查找值最大的节点
	 * @param t 节点
	 * @return BinaryNode 项最大的节点
	 */
	private BinaryNode<T> findMax(BinaryNode<T> t) {
		if(t != null) {
			while(t.right != null) {
				t = t.right;
			}
		}
		return t;
	}
	/**
	 * 增加（插入）节点
	 * @param x 节点的项
	 * @param t 节点
	 * @return BinaryNode 项最大的节点
	 */
	private BinaryNode<T> insert(T x, BinaryNode<T> t) {
		if(t == null) {
			return new BinaryNode<T>(x);
		} 
		
		int compareResult = myCompare(x, t.element);

		if(compareResult < 0) {
			t.left = insert(x, t.left);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
		} else {
			// do nothing
		}
		return t;
	}
	/**
	 * 删除节点
	 * @param x 节点的项
	 * @param t 节点
	 * @return BinaryNode 项最大的节点
	 */
	private BinaryNode<T> remove(T x, BinaryNode<T> t) {
		if(t == null) {
			return t;
		}
		
		int compareResult = myCompare(x, t.element);

		if(compareResult < 0) {
			t.left = remove(x, t.left);
		} else if(compareResult > 0) {
			t.right= remove(x, t.right);
		} else if(t.left != null && t.right != null) {
			// 欲删除的节点具有两个儿子
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else {
			// 具有一个儿子 或为树叶
			t = (t.left != null) ? t.left : t.right;
		}
		return t;
	}
}






