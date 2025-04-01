package hashing;

import org.junit.Assert;
import org.junit.Test;

public class QuadraticProbingHashTableTest {
    @Test
    public void testQuadraticProbingHashTable() {
        QuadraticProbingHashTable<Integer> hashTable = new QuadraticProbingHashTable<Integer>();

        hashTable.insert(23);
        Assert.assertEquals(true, hashTable.contains(23));

        hashTable.remove(23);
        Assert.assertEquals(false, hashTable.contains(23));
    }
}
