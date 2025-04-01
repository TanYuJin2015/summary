package tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BinarySearchTreeTest {
	@BeforeClass
	public static void setupBeforeClass() {}
	
	@Before
	public void setup() {}
	
	@Test
	public void testBST() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		final int MIN_UNIT = 1;
		// 二叉查找树不会自动平衡，故插入有序序列时相当于一个链表，MAX_UNIT很大，运行时间也会很大，若MAX_UNIT太大，会导致栈溢出错误（函数递归引起）, 此时需要AVL
		final int MAX_UNIT = 100;
		final int MID_UNIT = (MIN_UNIT + MAX_UNIT) / 2;
		
		// 往列表中插入项为1到20的节点
		for(int i=MIN_UNIT; i <= MAX_UNIT; i++) {
			list.add(i);
		}
		
		// 测试 isEmpty()
		Assert.assertTrue(bst.isEmpty());
		
		// 测试 changeToTree()
		bst.insertCollection(list);
		// 测试 printTree()
		bst.printTree();
		// 测试 contains()
		Assert.assertTrue(bst.contains(15));
		// 测试 isEmpty()
		Assert.assertFalse(bst.isEmpty());
		// 测试  findMin() 和 findMax()
		Assert.assertEquals(MIN_UNIT, (long)bst.findMin());
		Assert.assertEquals(MAX_UNIT, (long)bst.findMax());
		// 测试 remove()
		bst.remove(MID_UNIT);
		Assert.assertFalse(bst.contains(MID_UNIT));
		
		// 测试 makeEmpty()
		bst.makeEmpty();
		Assert.assertTrue(bst.isEmpty());				
	}
	
	@After
	public void tearDown() {}

	@AfterClass
	public static void tearDownAfterClass() {}
}
