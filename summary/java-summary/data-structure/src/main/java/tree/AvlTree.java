package tree;

import java.util.Collection;
import java.util.Comparator;

/**
 * 平衡二叉树（AvlTree）
 * @author 谈裕锦
 * 时间复杂度：
 * 索引：O(logN)
 * 插入：O(logN)
 * 查找：O(logN)
 * 删除：O(logN)
 */
public class AvlTree<T extends Comparable<? super T>> implements Tree<T> {
	private AvlNode<T> root;	// 根节点
	private Comparator<? super T> cmp;		// 自定义外部比较器
	private static final int ALLOWED_IMBALANCE = 1;	// 最大允许左右子树的高度差
	
	public AvlTree() {
		this(null);
	}
	public AvlTree(Comparator<? super T> cmp) {
		this.root = null;
		this.cmp = cmp;
	}

	private static class AvlNode<T> {	// 节点内部类
		T element;
		AvlNode<T> left;
		AvlNode<T> right;
		int height;
		
		AvlNode(T element) {
			this(element, null, null);
		}
		AvlNode(T element, AvlNode<T> lt, AvlNode<T> rt) {
			this.element = element;
			this.left = lt;
			this.right = rt;
			this.height = 0;
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
	 * 封装比较器的两种情形。封装成一个比较函数
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
	private void printTree(AvlNode<T> t) {
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
	private boolean contains(T x, AvlNode<T> t) {
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
	private AvlNode<T> findMin(AvlNode<T> t) {
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
	private AvlNode<T> findMax(AvlNode<T> t) {
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
	private AvlNode<T> insert(T x, AvlNode<T> t) {
		if(t == null) {
			return new AvlNode<T>(x);
		} 
		
		int compareResult = myCompare(x, t.element);

		if(compareResult < 0) {
			t.left = insert(x, t.left);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
		} else {
			// do nothing
		}
		return balance(t);
	}
	/**
	 * 删除节点
	 * @param x 节点的项
	 * @param t 节点
	 * @return BinaryNode 项最大的节点
	 */
	private AvlNode<T> remove(T x, AvlNode<T> t) {
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
		return balance(t);
	}
	/**
	 * 确保树平衡
	 * @param t 节点
	 */
	private AvlNode<T> balance(AvlNode<T> t) {
		if(t == null) {
			return t;
		}
		// 对左儿子进行插入
		if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
			if(height(t.left.left) >= height(t.left.right)) {
				// 对左儿子的左子树进行一次插入
				t = rotateWithLeftChild(t);
			} else {
				// 对左儿子的右子树进行一次插入
				t = doubleWithLeftChild(t);
			}
		// 对右儿子进行插入
		} else if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
			if(height(t.right.right) >= height(t.right.left)) {
				// 对右儿子的右子树进行一次插入
				t = rotateWithRightChild(t);
			} else {
				// 对右儿子的左子树进行一次插入
				t = doubleWithRightChild(t);
			}
		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}
	/**
	 * 返回节点高度, 如果节点为null, 则返回-1
	 * @param t 节点
	 */
	private int height(AvlNode<T> t) {
		return t == null ? -1 : t.height;
	}
	/**
	 * 对左儿子的左子树进行一次插入（单旋转）
	 * @param k2 节点
	 */
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
		AvlNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
		
	}
	/**
	 * 对左儿子的右子树进行一次插入（双旋转）
	 * @param k3 节点
	 */
	private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}
	/**
	 * 对右儿子的右子树进行一次插入（单旋转）
	 * @param k1 节点
	 */
	private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
		AvlNode<T> k2 = k1.right;
		k1.right= k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}
	/**
	 * 对右儿子的左子树进行一次插入（双旋转）
	 * @param k3 节点
	 */
	private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}
}






