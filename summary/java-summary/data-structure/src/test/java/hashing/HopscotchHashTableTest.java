package hashing;

import org.junit.Assert;
import org.junit.Test;

public class HopscotchHashTableTest {
    @Test
    public void testHopscotchHashTable() {
        HopscotchHashTable<Integer> hashTable = new HopscotchHashTable<Integer>();

        hashTable.insert(50);
        Assert.assertTrue(hashTable.contains(50));

        hashTable.remove(50);
        Assert.assertFalse(hashTable.contains(50));

        hashTable.insert(3);
        hashTable.insert(15);
        hashTable.insert(56);
        hashTable.insert(99);
        hashTable.insert(45);
        hashTable.insert(65);
        hashTable.insert(11);
        hashTable.insert(222);

        hashTable.print();
    }
}
