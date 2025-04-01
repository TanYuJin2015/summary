package tree;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AvlTreeTest {
	@BeforeClass
	public static void setupBeforeClass() {}
	
	@Before
	public void setup() {}
	
	@Test
	public void testAVL() {
		AvlTree<Integer> avl = new AvlTree<Integer>();
		List<Integer> list = new LinkedList<Integer>();
		final int MIN_UNIT = 1;
		// 二叉平衡树会自适应平衡，即使插入的是有序列表，MAX_UNIT很大时，其效率还是会很乐观的, 但MAX_UNIT太大，会引起内存溢出错误（内存空间存储量不够引起）, 此时需要B+树
		final int MAX_UNIT = 200;
		final int MID_UNIT = (MIN_UNIT + MAX_UNIT) / 2;
			
		// 往列表中插入项为1到20的节点
		for(int i=MIN_UNIT; i <= MAX_UNIT; i++) {
			list.add(i);
		}
		
		// 测试 isEmpty()
		Assert.assertTrue(avl.isEmpty());
		
		// 测试 changeToTree()
		avl.insertCollection(list);
		// 测试 printTree()
		avl.printTree();
		// 测试 contains()
		Assert.assertTrue(avl.contains(15));
		// 测试 isEmpty()
		Assert.assertFalse(avl.isEmpty());
		// 测试  findMin() 和 findMax()
		Assert.assertEquals(MIN_UNIT, (long)avl.findMin());
		Assert.assertEquals(MAX_UNIT, (long)avl.findMax());
		// 测试 remove()
		avl.remove(MID_UNIT);
		Assert.assertFalse(avl.contains(MID_UNIT));
		
		// 测试 makeEmpty()
		avl.makeEmpty();
		Assert.assertTrue(avl.isEmpty());				
	}
	
	@After
	public void tearDown() {}

	@AfterClass
	public static void tearDownAfterClass() {}
}
