package hashing;

import org.junit.Assert;
import org.junit.Test;

public class SeparateChainingHashTableTest {
    @Test
    public void testSeparateChainingHashTable() {
        SeparateChainingHashTable<Integer> hashTable = new SeparateChainingHashTable<Integer>();

        hashTable.insert(23);
        Assert.assertEquals(true, hashTable.contains(23));

        hashTable.remove(23);
        Assert.assertEquals(false, hashTable.contains(23));
    }
}
