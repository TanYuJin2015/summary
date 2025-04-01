package hashing.cuckoo;

import hashing.cuckoo.family.StringHashFamily;
import org.junit.Assert;
import org.junit.Test;

public class CuckooHashTableTest {
    @Test
    public void testCuckooHashTable() {
        CuckooHashTable<String> cuckooHashTable = new CuckooHashTable(new StringHashFamily(2));
        cuckooHashTable.insert("TanYuJin");
        Assert.assertTrue(cuckooHashTable.contains("TanYuJin"));

        cuckooHashTable.remove("TanYuJin");
        Assert.assertFalse(cuckooHashTable.contains("TanYuJin"));
    }
}
